package mvs.translator.stopwatch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import mvs.translator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.liveData.observe(this, { binding.textTime.text = it })
        binding.apply {
            buttonStart.setOnClickListener { viewModel.start() }
            buttonPause.setOnClickListener { viewModel.pause() }
            buttonStop.setOnClickListener { viewModel.stop() }
        }
    }
}
