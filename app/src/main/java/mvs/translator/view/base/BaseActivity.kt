package mvs.translator.view.base

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import mvs.translator.R
import mvs.translator.databinding.LoadingLayoutBinding
import mvs.translator.model.AppState
import mvs.translator.model.DataModel
import mvs.translator.utils.AlertDialogFragment
import mvs.translator.utils.network.NetworkStatus
import mvs.translator.utils.network.OnlineRepository
import mvs.translator.viewmodel.BaseViewModel
import mvs.translator.viewmodel.Interactor
import org.koin.androidx.scope.ScopeActivity

abstract class BaseActivity<T : AppState, I : Interactor<T>> : ScopeActivity() {

    private lateinit var binding: LoadingLayoutBinding
    abstract val viewModel: BaseViewModel<T>
    lateinit var network: OnlineRepository
    internal val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingLayoutBinding.inflate(layoutInflater)
        viewModel._mutableLiveData.observe(this) { renderData(it) }
        network = NetworkStatus(applicationContext)
    }

    protected fun subscribeToNetworkChange(view: View) {
        val snack = Snackbar.make(
            view,
            resources.getString(R.string.dialog_message_device_is_offline),
            Snackbar.LENGTH_INDEFINITE
        )
        network.availableNetworks.observe(this, {
            if (it == false) snack.show()
            else snack.dismiss()
        })
    }

    protected open fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    protected fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToAdapter(data: List<DataModel>)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}
