package com.emplk.sharedflow_demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emplk.sharedflow_demo.databinding.ActivityMainBinding
import com.emplk.sharedflow_demo.util.Event.Companion.observeEvent
import com.emplk.sharedflow_demo.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.onButtonClicked()
        }

        viewModel.viewEventLiveData.observeEvent(this) { event ->
            when (event) {
                is MainViewEvent.DisplayText -> Toast.makeText(this, "Here it is: ⌚", LENGTH_LONG).show()
            }
        }
    }
}