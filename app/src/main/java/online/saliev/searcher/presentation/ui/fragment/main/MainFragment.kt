package online.saliev.searcher.presentation.ui.fragment.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import online.saliev.searcher.core.BaseFragment
import online.saliev.searcher.data.api.model.Contact
import online.saliev.searcher.databinding.FragmentMainBinding
import online.saliev.searcher.presentation.ui.fragment.main.ContactAdapter
import online.saliev.searcher.viewmodel.Links

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var adapter: ContactAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Разрешение получено, загружаем контакты
                loadContacts()
            } else {
                // В разрешении отказано. Показываем сообщение пользователю.
                Toast.makeText(requireContext(), "Для отображения контактов необходимо разрешение", Toast.LENGTH_LONG).show()
            }
        }

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun initialize() {
        setupRecyclerView()
        checkContactsPermission()

        val intent = Intent(Intent.ACTION_VIEW)
        val links = Links()

        binding.whatsappSearch.setOnClickListener {
            val inputText = binding.input.text.toString()
            if (inputText.isNotBlank()) {
                intent.setData(Uri.parse(links.searchWhatsapp(inputText)))
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
