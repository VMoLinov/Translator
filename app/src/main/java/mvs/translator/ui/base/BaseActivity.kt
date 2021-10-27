package mvs.translator.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mvs.translator.R
import mvs.translator.model.data.AppState
import mvs.translator.utils.network.isOnline
import mvs.translator.utils.ui.AlertDialogFragment
import mvs.translator.viewmodel.BaseViewModel
import mvs.translator.viewmodel.Interactor

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T>

    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(applicationContext)
        viewModel._mutableLiveData.observe(this) { renderData(it) }
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    private fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun renderData(appState: T)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}
