package com.muffls.tap.main.ui

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.muffls.tap.main.ui.di.MainInjectorProvider
import com.philipphofmann.android.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tapping.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TappingActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<TapViewModel>

    private lateinit var viewModel: TapViewModel

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { viewState ->
                tapCount.text = viewState.tapCount
                tapLevel.text = viewState.level
                levelBar.progress = viewState.progress
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tapping)

        (application as MainInjectorProvider).getMainInjector().inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TapViewModel::class.java)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                viewModel.addTap()
            }
        }

        return super.onTouchEvent(event)
    }
}
