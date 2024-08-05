package com.mazer.common_tools.components

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.mazer.common_tools.databinding.ComponentTimerBinding

class TimerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: ComponentTimerBinding
    private var timer: CountDownTimer? = null

    private var onTimerEnded: (() -> Unit)? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        try {
            binding = ComponentTimerBinding.inflate(LayoutInflater.from(context), this, true)

        } catch (e: Exception) {
            throw Exception()
        }
        requestLayout()
    }

    fun addOnTimerEndListener(listener: (() -> Unit)?){
        onTimerEnded = listener
    }

    fun startTimer() {
        binding.lottieTimerAnimation.playAnimation()
        timer = object : CountDownTimer(16000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvCountdown.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                onTimerEnded?.invoke()
                binding.lottieTimerAnimation.pauseAnimation()
                binding.tvCountdown.text = "0"
            }
        }.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        timer?.cancel()
    }
}