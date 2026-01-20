package online.saliev.searcher.ui.fragment.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import online.saliev.searcher.R
import online.saliev.searcher.databinding.FragmentLastOnBoardBinding

class LastOnBoardFragment : Fragment() {

    val binding: FragmentLastOnBoardBinding by lazy {
        FragmentLastOnBoardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = findNavController()
        binding.nextBtn.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
        binding.skip.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

}