package online.saliev.searcher.presentation.ui.fragment.onBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import online.saliev.searcher.R
import online.saliev.searcher.databinding.ItemOnBoardBinding

class OnBoardAdapter: RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardViewHolder {
        return OnBoardViewHolder(ItemOnBoardBinding.inflate(
            LayoutInflater.from(parent.context) , parent , false
        ))
    }

    override fun onBindViewHolder(
        holder: OnBoardViewHolder,
        position: Int
    ) {
        holder.onBind(position)

    }

    private val imagelist = arrayListOf(
        R.drawable.logos_telegram,
        R.drawable.logos_whatsapp_icon,
        R.drawable.success_icon
    )
    private val titlelist = arrayListOf(
        R.string.title_telegram_txt,
        R.string.title_whatsapp_txt,
        R.string.title_getStarted_txt
    )
    private val desclist = arrayListOf(
        R.string.desc_telegram,
        R.string.desc_whatsapp,
        R.string.desc_GetStarted
    )

    override fun getItemCount() = imagelist.size




    inner class OnBoardViewHolder(
        private val binding: ItemOnBoardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(position : Int) {
            binding.imageOnBoarting.setImageResource(imagelist[position])
            binding.title.setText(titlelist[position])
            binding.desc.setText(desclist[position])
        }


    }
}