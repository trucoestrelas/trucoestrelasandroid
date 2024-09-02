package com.mazer.trucoestrelas.ui.queue

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.android.material.textview.MaterialTextView
import com.mazer.truco.ui.TrucoGameActivity
import com.mazer.trucoestrelas.R
import com.mazer.trucoestrelas.databinding.FragmentQueueBinding

class QueueActivity : AppCompatActivity() {
    private lateinit var binding: FragmentQueueBinding
    private var secondsElapsed = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentQueueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        startTimer(binding.tvWaitingTime)
        setAnimationLoading()
        setupEvents()
    }

    private fun setupEvents() {
        binding.btnQuitQueue.setOnClickListener {
            finish()
        }
    }

    private fun partidaEcontrada(){
        binding.btnQuitQueue.isEnabled = false
        binding.animLoading.setMinAndMaxFrame(241,390)
        binding.tvStatusQueue.text = getString(R.string.partida_encontrada)
        binding.tvWaitingTime.text = getString(R.string.game_starting)
        binding.animLoading.addAnimatorUpdateListener {
            if (binding.animLoading.frame == 390){
                binding.animLoading.pauseAnimation()
                startGame()
            }
        }
    }

    private fun queueError(msg: String){
        binding.animLoading.setMinAndMaxFrame(500,820)
        binding.tvStatusQueue.text = msg
        binding.animLoading.addAnimatorUpdateListener {
            if (binding.animLoading.frame == 820){
                binding.animLoading.pauseAnimation()
                finish() //or restart
            }
        }
    }

    private fun startGame() {
        val it = Intent(this, TrucoGameActivity::class.java)
        startActivity(it)
    }

    private fun setAnimationLoading(){
        binding.animLoading.setMinAndMaxFrame(0,241)
    }
    private fun startTimer(timerTextView: MaterialTextView) {
        // Usando o lifecycleScope da Activity
        lifecycleScope.launch {
            while (true) {
                val text = getString(R.string.queue_waiting_time, formatTime(secondsElapsed))
                timerTextView.text = text
                delay(1000)
                secondsElapsed++
                if (secondsElapsed > 7){
                    partidaEcontrada()
                    break
                }
            }
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val secondsRemaining = seconds % 60
        return String.format("%02d:%02d", minutes, secondsRemaining)
    }

}