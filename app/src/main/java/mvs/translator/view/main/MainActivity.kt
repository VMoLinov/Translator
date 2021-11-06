package mvs.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mvs.translator.R
import mvs.translator.databinding.AcMainBinding
import mvs.translator.data.AppState
import mvs.translator.data.DataModel
import mvs.translator.view.base.BaseActivity
import mvs.translator.view.history.HistoryActivity
import mvs.translator.view.main.search.LocalSearchDialogFragment
import mvs.translator.view.main.search.OnSearchClickListener
import mvs.translator.view.main.search.RemoteSearchDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: AcMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    override val viewModel: MainViewModel by viewModel()
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = RemoteSearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onRemoteSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, REMOTE_SEARCH_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startDescriptionActivity(data)
            }
        }
    private val onRemoteSearchClickListener: OnSearchClickListener =
        object : OnSearchClickListener {
            override fun onClick(searchWord: String) {
                if (network.isOnline()) {
                    viewModel.getData(searchWord, true)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }
    private val onLocalSearchClickListener: OnSearchClickListener = object : OnSearchClickListener {
        override fun onClick(searchWord: String) {
            if (searchWord.isNotEmpty()) {
                viewModel.getSimpleWord(searchWord)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.addItemDecoration(
            DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        )
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.submitList(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.search_history -> {
                val localSearchFragment = LocalSearchDialogFragment.newInstance()
                localSearchFragment.setOnSearchClickListener(onLocalSearchClickListener)
                localSearchFragment.show(supportFragmentManager, LOCAL_SEARCH_FRAGMENT_DIALOG_TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val REMOTE_SEARCH_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
        private const val LOCAL_SEARCH_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092396"
    }
}
