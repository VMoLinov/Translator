package mvs.translator.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mvs.translator.databinding.AcMainRecyclerviewItemBinding
import mvs.translator.model.DataModel

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener
) : ListAdapter<mvs.translator.model.DataModel, MainAdapter.MainViewHolder>(MainCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        AcMainRecyclerviewItemBinding.inflate(parent.inflater(), parent, false).root
    ) {

        fun bind(data: mvs.translator.model.DataModel) {
            val binder = AcMainRecyclerviewItemBinding.bind(itemView)
            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text =
                data.meanings?.joinToString { it.translation?.translation.toString() }
            binder.root.setOnClickListener { openInNewWindow(data) }
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)

    private fun openInNewWindow(listItemData: mvs.translator.model.DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: mvs.translator.model.DataModel)
    }
}

object MainCallback : DiffUtil.ItemCallback<mvs.translator.model.DataModel>() {

    override fun areItemsTheSame(oldItem: mvs.translator.model.DataModel, newItem: mvs.translator.model.DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: mvs.translator.model.DataModel, newItem: mvs.translator.model.DataModel): Boolean {
        return oldItem.meanings == newItem.meanings && oldItem.text == newItem.text
    }
}
