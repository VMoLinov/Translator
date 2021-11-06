package mvs.translator.view.descriptionscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import mvs.translator.R
import mvs.translator.databinding.ActivityDescriptionBinding
import mvs.translator.utils.INetworkStatus
import mvs.translator.utils.NetworkStatus
import mvs.translator.utils.AlertDialogFragment

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var network: mvs.translator.utils.INetworkStatus
    private val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        network = mvs.translator.utils.NetworkStatus(applicationContext)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
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

    private fun setData() {
        val bundle = intent.extras
        binding.descriptionHeader.text = bundle?.getString(WORD_EXTRA)
        binding.descriptionTextview.text = bundle?.getString(DESCRIPTION_EXTRA)
        val imageLink = bundle?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
        }
    }

    private fun startLoadingOrShowError() {
        if (network.isOnline()) {
            setData()
        } else {
            mvs.translator.utils.AlertDialogFragment.newInstance(
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
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
        }
    }
}
