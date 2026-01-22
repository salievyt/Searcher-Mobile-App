package online.saliev.searcher.presentation.ui.fragment.onBoard

import online.saliev.searcher.core.BaseFragment
import online.saliev.searcher.databinding.FragmentOnBoardBinding

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>() {

    override fun initialize() {
        val adapter = OnBoardAdapter()
        binding.viewPager.adapter = adapter
    }

    override fun getViewBinding() = FragmentOnBoardBinding.inflate(layoutInflater)

}