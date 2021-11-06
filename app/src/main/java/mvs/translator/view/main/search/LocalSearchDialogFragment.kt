package mvs.translator.view.main.search

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import mvs.translator.R

class LocalSearchDialogFragment : DialogFragment() {

    private var onSearchClickListener: OnSearchClickListener? = null
    private val onSearchButton = DialogInterface.OnClickListener { dialog, _ ->
        onSearchClickListener?.onClick((dialog as Dialog).findViewById<EditText>(R.id.search_field).text.toString())
        dialog.dismiss()
    }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(R.layout.local_search_dialog_fragment)
            .setPositiveButton(R.string.ok, onSearchButton)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onSearchClickListener = null
    }

    companion object {
        fun newInstance() = LocalSearchDialogFragment()
    }
}
