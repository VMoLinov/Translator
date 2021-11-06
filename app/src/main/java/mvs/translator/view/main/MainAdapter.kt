package mvs.translator.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mvs.translator.databinding.AcMainRecyclerviewItemBinding
import mvs.translator.data.DataModel

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(MainCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        AcMainRecyclerviewItemBinding.inflate(parent.inflater(), parent, false).root
    ) {

        fun bind(data: DataModel) {
            val binder = AcMainRecyclerviewItemBinding.bind(itemView)
            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text =
                data.meanings?.joinToString { it.translation?.translation.toString() }
            binder.root.setOnClickListener { openInNewWindow(data) }
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}

object MainCallback : DiffUtil.ItemCallback<DataModel>() {

    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem.meanings == newItem.meanings && oldItem.text == newItem.text
    }
}
