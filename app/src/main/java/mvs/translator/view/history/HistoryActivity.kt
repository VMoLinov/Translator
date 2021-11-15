package mvs.translator.view.history

import android.os.Bundle
import kotlinx.coroutines.launch
import mvs.translator.databinding.ActivityHistoryBinding
import mvs.translator.model.AppState
import mvs.translator.view.base.BaseActivity

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override val viewModel: HistoryViewModel by scope.inject()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

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

    override fun setDataToAdapter(data: List<mvs.translator.model.DataModel>) {
        adapter.setData(data)
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
