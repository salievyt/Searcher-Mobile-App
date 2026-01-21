package online.saliev.searcher.presentation.ui.fragment.onBoard

import androidx.navigation.fragment.findNavController
import online.saliev.searcher.R
import online.saliev.searcher.core.BaseFragment
import online.saliev.searcher.databinding.FragmentWhatsappOnBoardBinding


class WhatsappOnBoardFragment : BaseFragment<FragmentWhatsappOnBoardBinding>() {
    override fun initialize() {
        val navController = findNavController()
        binding.nextBtn.setOnClickListener {
            navController.navigate(R.id.lastOnBoardFragment)
        }
        binding.skip.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }
    override fun getViewBinding() = FragmentWhatsappOnBoardBinding.inflate(layoutInflater)

}