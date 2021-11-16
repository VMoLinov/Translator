package mvs.translator.utils.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import mvs.translator.model.DataModel

object ListCallback : DiffUtil.ItemCallback<DataModel>() {

    override fun areItemsTheSame(
        oldItem: DataModel,
        newItem: DataModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DataModel,
        newItem: DataModel
    ): Boolean {
        return oldItem.meanings == newItem.meanings && oldItem.text == newItem.text
    }
}

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)
