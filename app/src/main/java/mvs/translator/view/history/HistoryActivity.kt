package mvs.translator.view.history

import android.os.Bundle
import kotlinx.coroutines.launch
import mvs.translator.databinding.ActivityHistoryBinding
import mvs.translator.model.AppState
import mvs.translator.model.DataModel
import mvs.translator.view.base.BaseActivity
import mvs.translator.view.base.OnListItemClickListener

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override val viewModel: HistoryViewModel by scope.inject()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }
    private val onListItemClickListener: OnListItemClickListener =
        object : OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startDescriptionActivity(data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniViewModel()
        initViews()
        subscribeToNetworkChange(binding.historyContainer)
    }

    override fun onResume() {
        super.onResume()
        coroutineScope.launch {
            viewModel.getData("", false)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.submitList(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        viewModel.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
