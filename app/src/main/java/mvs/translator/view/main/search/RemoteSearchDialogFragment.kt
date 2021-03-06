package mvs.translator.view.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import mvs.translator.databinding.RemoteSearchDialogFragmentBinding
import mvs.translator.utils.ui.getEmptyString

class RemoteSearchDialogFragment : BottomSheetDialogFragment() {

    private lateinit var searchEditText: TextInputEditText
    private lateinit var clearTextImageView: ImageView
    private lateinit var searchButton: TextView
    private var _binding: RemoteSearchDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null
    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (searchEditText.text != null && searchEditText.text.toString().isNotEmpty()) {
                searchButton.isEnabled = true
                clearTextImageView.visibility = View.VISIBLE
            } else {
                searchButton.isEnabled = false
                clearTextImageView.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RemoteSearchDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = binding.searchEditText
        clearTextImageView = binding.clearTextImageview
        searchButton = binding.searchButtonTextview
        searchButton.setOnClickListener(onSearchButtonClickListener)
        searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onSearchClickListener = null
        _binding = null
    }

    private fun addOnClearClickListener() {
        clearTextImageView.setOnClickListener {
            searchEditText.setText(String.getEmptyString())
            searchButton.isEnabled = false
        }
    }

    companion object {
        fun newInstance(): RemoteSearchDialogFragment {
            return RemoteSearchDialogFragment()
        }
    }
}


