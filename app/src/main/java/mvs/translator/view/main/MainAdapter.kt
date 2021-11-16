package mvs.translator.view.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mvs.translator.databinding.AcMainRecyclerviewItemBinding
import mvs.translator.model.DataModel
import mvs.translator.utils.ui.ListCallback
import mvs.translator.utils.ui.inflater
import mvs.translator.view.base.OnListItemClickListener

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(ListCallback) {

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

    fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }
}
