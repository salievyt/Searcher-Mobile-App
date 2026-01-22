package online.saliev.searcher.presentation.ui.fragment.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import online.saliev.searcher.core.BaseFragment
import online.saliev.searcher.data.api.model.Contact
import online.saliev.searcher.databinding.FragmentMainBinding
import online.saliev.searcher.presentation.ui.activity.main.MainActivity
import online.saliev.searcher.presentation.ui.fragment.main.ContactAdapter
import online.saliev.searcher.viewmodel.Links
import online.saliev.searcher.viewmodel.Roles
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.google.firebase.messaging.FirebaseMessaging
import online.saliev.searcher.App
import online.saliev.searcher.R

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val REQUEST_CODE = 1001


    private lateinit var adapter: ContactAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                loadContacts()
            } else {
                Toast.makeText(requireContext(), "Для отображения контактов необходимо разрешение", Toast.LENGTH_LONG).show()
            }
        }

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun initialize() {
        if (!App.prefs.isShow()){
            findNavController().navigate(R.id.OnBoardFragment)
            App.prefs.changeShow(true)
        }
        setupRecyclerView()
        checkContactsPermission()
        Log.d("GET TOKEN", FirebaseMessaging.getInstance().getToken().toString())
        val intent = Intent(Intent.ACTION_VIEW)
        val links = Links()

        binding.whatsappSearch.setOnClickListener {
            val inputText = binding.input.text.toString()
            if (inputText.isNotBlank()) {
                intent.setData(links.searchWhatsapp(inputText).toUri())
                startActivity(intent)
            } else {
                Toast.makeText(context, "Введите номер для поиска", Toast.LENGTH_SHORT).show()
            }
        }
        binding.telegramSearch.setOnClickListener {
            Toast.makeText(this.context, "Эта функция временно не доступна", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        // Теперь Android Studio будет знать, что такое ContactAdapter
        adapter = ContactAdapter(emptyList())
        binding.contactsList.layoutManager = LinearLayoutManager(requireContext())
        binding.contactsList.adapter = adapter
    }

    private fun checkContactsPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadContacts()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    @SuppressLint("Range")
    private fun loadContacts() {
        val contactList = mutableListOf<Contact>()
        Thread {
            val contentResolver = requireActivity().contentResolver
            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            )

            cursor?.use {
                while (it.moveToNext()) {
                    val name = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val number = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contactList.add(Contact(name = name, phoneNumber = number))
                }
            }
            requireActivity().runOnUiThread {
                adapter.updateContacts(contactList)
            }
        }.start()
    }
}
