package mvs.translator.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mvs.translator.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val viewModel: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getStateLiveData().observe(this) { renderData(it) }
    }

    abstract fun renderData(appState: T)
}
