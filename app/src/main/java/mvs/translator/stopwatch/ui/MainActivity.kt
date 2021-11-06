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
        binding.apply {
            viewModel.liveData.observe(this@MainActivity) {
                textTime.text = it[0]
                textTimeSecond.text = it[1]
            }
            buttonStart.setOnClickListener { viewModel.start() }
            buttonPause.setOnClickListener { viewModel.pause() }
            buttonStop.setOnClickListener { viewModel.stop() }
        }
    }
}
