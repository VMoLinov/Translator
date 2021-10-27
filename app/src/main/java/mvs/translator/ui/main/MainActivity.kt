package mvs.translator.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mvs.translator.R
import mvs.translator.databinding.AcMainBinding
import mvs.translator.model.data.AppState
import mvs.translator.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: AcMainBinding
    private var adapter: MainAdapter? = null
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        /** В ночной теме не отображается */
        binding.mainActivityRecyclerview.addItemDecoration(
            DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        )
        /** Что здесь происходит с адаптером? */
        adapter = MainAdapter { }
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_title_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter!!.submitList(data)
                }
                showViewWorking()
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
