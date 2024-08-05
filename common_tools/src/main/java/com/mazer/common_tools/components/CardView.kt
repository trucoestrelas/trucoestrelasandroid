package com.mazer.common_tools.components

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.mazer.common_tools.R
import com.mazer.common_tools.components.helper.CardViewHelper
import com.mazer.common_tools.databinding.ComponentCardBinding
import com.mazer.common_tools.entities.Card
import com.mazer.common_tools.getCardDrawable

class CardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ComponentCardBinding
    private var isSelected = false
    private var isEnabled = false
    var isUserHands = false

    private lateinit var mediaPlayer: MediaPlayer
    private var onCardSelectedListener: ((Card, Boolean) -> Unit)? = null

    private lateinit var card: Card

    init {
        handleViewAttibutes(attrs)
        init(context)
    }

    private fun handleViewAttibutes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CardView, 0, 0)
        isUserHands = CardViewHelper(typedArray).isUserHands ?: false
    }

    private fun init(context: Context) {
        try {
            binding = ComponentCardBinding.inflate(LayoutInflater.from(context), this, true)

            mediaPlayer = MediaPlayer.create(context, R.raw.touch_sound)
            isEnabled = true

            if (isUserHands){
                binding.ivCard.setOnClickListener {
                    it.isClickable = false
                    toggleCardSelected()
                    onCardSelectedListener?.invoke(card ,isSelected)
                    mediaPlayer.start()

                }
            }
        } catch (e: Exception) {
            throw Exception()
        }
        requestLayout()
    }

    private fun toggleCardSelected(){
        this.isSelected = !this.isSelected
        refreshCardPosition()
    }

    fun setCardSelected(isSelected: Boolean){
        if (this.isSelected != isSelected) {
            this.isSelected = isSelected
            refreshCardPosition()
        }
    }

    fun addOnCardSelectedListener(listener: ((Card, Boolean) -> Unit)?){
        onCardSelectedListener = listener
    }


    fun showCard(card: Card){
        this.card = card
        Glide.with(binding.root.context).load(card.getCardDrawable()).into(binding.ivCard)
    }

    private fun refreshCardPosition() {
        if (this.isEnabled) {
            if (this.isSelected) {
                slideCardVertically(-50f)
            } else {
                slideCardVertically(50f)
            }
        }
    }

    private fun slideCardVertically(slideDistance: Float, duration: Long = 200){
        post {
            val currentY = this.y
            val newY = currentY + slideDistance
            val animatorY = ObjectAnimator.ofFloat(this, "y", newY)
            animatorY.duration = duration
            animatorY.start()

            animatorY.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    binding.ivCard.isClickable = true
                }
            })
        }
    }

    fun playCardAnimation(toX: Float, toY: Float){
        this.isEnabled = false

        val animatorY = ObjectAnimator.ofFloat(this, "y", toY)
        val animatorX = ObjectAnimator.ofFloat(this, "x", toX)
        animatorX.duration = 100
        animatorY.duration = 100

        animatorX.start()
        animatorY.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mediaPlayer.release()
    }

}