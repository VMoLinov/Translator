package mvs.translator.view.history

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mvs.translator.databinding.ActivityHistoryRecyclerviewItemBinding
import mvs.translator.model.DataModel
import mvs.translator.utils.ui.ListCallback
import mvs.translator.utils.ui.inflater
import mvs.translator.view.base.OnListItemClickListener

class HistoryAdapter(
    private val onListItemClickListener: OnListItemClickListener
) : ListAdapter<DataModel, HistoryAdapter.HistoryViewHolder>(ListCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HistoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ActivityHistoryRecyclerviewItemBinding.inflate(parent.inflater(), parent, false).root
    ) {

        fun bind(data: DataModel) {
            val binder = ActivityHistoryRecyclerviewItemBinding.bind(itemView)
            binder.headerHistoryTextviewRecyclerItem.text = data.text
//            binder.descriptionTextviewRecyclerItem.text =
//                data.meanings?.joinToString { it.translation?.translation.toString() }
            binder.root.setOnClickListener { openInNewWindow(data) }
        }
    }

    fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }
}
