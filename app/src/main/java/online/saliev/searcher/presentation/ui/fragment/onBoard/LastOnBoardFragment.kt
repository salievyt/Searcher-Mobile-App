package online.saliev.searcher.presentation.ui.fragment.onBoard

import androidx.navigation.fragment.findNavController
import online.saliev.searcher.R
import online.saliev.searcher.core.BaseFragment
import online.saliev.searcher.databinding.FragmentLastOnBoardBinding

class LastOnBoardFragment : BaseFragment<FragmentLastOnBoardBinding>() {

    override fun initialize() {
        val navController = findNavController()
        binding.nextBtn.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
        binding.skip.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }

    override fun getViewBinding() = FragmentLastOnBoardBinding.inflate(layoutInflater)

}