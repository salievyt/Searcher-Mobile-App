package online.saliev.searcher.ui.fragment.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import online.saliev.searcher.R
import online.saliev.searcher.databinding.FragmentMainBinding
import online.saliev.searcher.viewmodel.Links

class MainFragment : Fragment() {

    val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(Intent.ACTION_VIEW)
        val links = Links()
        binding.whatsappSearch.setOnClickListener {
            intent.setData(Uri.parse(links.searchWhatsapp(binding.input.text.toString())))
            startActivity(intent)

        }
    }

}