package online.saliev.searcher.presentation.ui.fragment.onBoard


import androidx.navigation.fragment.findNavController
import online.saliev.searcher.R
import online.saliev.searcher.core.BaseFragment
import online.saliev.searcher.databinding.FragmentTelegramOnBoardBinding


class TelegramOnBoardFragment : BaseFragment<FragmentTelegramOnBoardBinding>() {

    override fun initialize() {
        val navController = findNavController()
        binding.nextBtn.setOnClickListener {
            navController.navigate(R.id.whatsappOnBoardFragment)
        }
        binding.skip.setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
    }

    override fun getViewBinding() = FragmentTelegramOnBoardBinding.inflate(layoutInflater)

}