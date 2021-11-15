package mvs.translator.view.descriptionscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.launch
import mvs.translator.R
import mvs.translator.databinding.ActivityDescriptionBinding
import mvs.translator.model.AppState
import mvs.translator.model.DataModel
import mvs.translator.utils.AlertDialogFragment
import mvs.translator.utils.convertMeaningsToString
import mvs.translator.view.base.BaseActivity

class DescriptionActivity : BaseActivity<AppState, DescriptionInteractor>() {

    private lateinit var binding: ActivityDescriptionBinding
    override val viewModel: DescriptionViewModel by scope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        subscribeToNetworkChange(binding.descriptionScreenSwipeRefreshLayout)
        viewModel._mutableLiveData.observe(this, { renderData(it) })
        coroutineScope.launch {
            val bundle = intent.extras
            viewModel.getData(bundle?.getString(WORD_EXTRA).toString(), true)
        }
    }

    override fun renderData(appState: AppState) {
        if (appState is AppState.Simple) {
            val data = appState.data
            binding.descriptionHeader.text = data?.text
            binding.descriptionTextview.text = convertMeaningsToString(data?.meanings!!)
            val imageLink = data.meanings!![0].imageUrl
            if (imageLink.isBlank()) {
                stopRefreshAnimationIfNeeded()
            } else {
                useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
                stopRefreshAnimationIfNeeded()
            }
        } else super.renderData(appState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startLoadingOrShowError() {
        if (network.availableNetworks.value == true) {
            coroutineScope.launch {
                viewModel.getData(binding.descriptionHeader.text.toString(), true)
            }
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        val request = ImageRequest.Builder(this)
            .data("https:$imageLink")
            .target(
                onStart = {},
                onSuccess = { result ->
                    imageView.setImageDrawable(result)
                },
                onError = {
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            )
            .transformations(
                CircleCropTransformation(),
            )
            .build()
        coroutineScope.launch {
            ImageLoader(this@DescriptionActivity).enqueue(request)
        }
    }


    companion object {
        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"
        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        fun getIntent(
            context: Context,
            word: String,
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {}
}
