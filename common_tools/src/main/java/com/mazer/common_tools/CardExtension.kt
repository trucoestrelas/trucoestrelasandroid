package com.mazer.common_tools

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.transition.TransitionManager
import com.mazer.common_tools.entities.Card
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Placeholder
import androidx.core.animation.doOnEnd
import com.mazer.common_tools.components.CardView

const val SUIT_CLUBS = "Paus"
const val SUIT_DIAMONDS = "Ouros"
const val SUIT_HEARTS = "Copas"
const val SUIT_SPADES = "Espadas"


fun View.moveToAnimation(toX: Float, toY: Float, duration: Long = 100, onAnimationEnd: (() -> Unit)? =  null){
    val animatorY = ObjectAnimator.ofFloat(this, "y", toY)
    val animatorX = ObjectAnimator.ofFloat(this, "x", toX)
    animatorX.duration = duration
    animatorY.duration = duration
    animatorX.start()
    animatorY.start()

    animatorY.addListener(object: AnimatorListenerAdapter(){
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            onAnimationEnd?.invoke()
        }
    })
}

//junta todas as cartas (CardView) da mesa na view
//destróis as Views
fun View.collapseAllCardsOnDeck(rootView: ViewGroup){
    val cardsOnTable = rootView.findVisibleCardViews()
    cardsOnTable.forEach {
        it.moveToAnimation(this.x, this.y){
            rootView.removeView(it)
        }

    }
}

/*fun List<CardView>.giveCardsTo(deckView: View, userHandView: View, rootLayout: ViewGroup){
    *//*var lastCardView: View = userHandView
    this.forEachIndexed { index, cardView ->
        //cardView.toAbove(deckView, rootLayout)
        cardView.moveToAnimation(lastCardView.x, lastCardView.y,400){

        }
    }*//*
}*/

fun View.animateToConstraints(destinationView: View, duration: Long = 500L) {
    val parent = this.parent as? ConstraintLayout ?: return
    val destinationParent = destinationView.parent as? ConstraintLayout ?: return

    // Clone the current state of the parent ConstraintLayout
    val initialConstraintSet = ConstraintSet().apply { clone(parent) }

    // Clone the destination state from the destination ConstraintLayout
    val finalConstraintSet = ConstraintSet().apply { clone(destinationParent) }

    // Apply the initial constraints
    initialConstraintSet.applyTo(parent)

    // Create the animator
    val animator = ObjectAnimator.ofFloat(0f, 1f)
    animator.duration = duration
    animator.addUpdateListener { valueAnimator ->
        val progress = valueAnimator.animatedValue as Float
        // Begin a delayed transition for smooth animation
        TransitionManager.beginDelayedTransition(parent)
        if (progress == 1f) {
            // Apply the final constraints when the animation is complete
            finalConstraintSet.applyTo(parent)
        }
    }
    animator.start()
}

fun View.spawnView(rootLayout: ViewGroup): CardView{
    val myView = this
    val cardView = CardView(rootLayout.context).apply {
        id = View.generateViewId()
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        elevation = 16f
    }
    if (rootLayout is ConstraintLayout) {
        rootLayout.addView(cardView)
        ConstraintSet().apply {
            clone(rootLayout)
            connect(cardView.id, ConstraintSet.TOP, myView.id, ConstraintSet.TOP, 0)
            connect(cardView.id, ConstraintSet.START, myView.id, ConstraintSet.START, 0)
            connect(cardView.id, ConstraintSet.END, myView.id, ConstraintSet.END, 0)
            connect(cardView.id, ConstraintSet.BOTTOM, myView.id, ConstraintSet.BOTTOM, 0)
            applyTo(
                rootLayout
            )

        }
    }
    return cardView
}

/*fun View.toAbove(destinyView: View, rootLayout: ViewGroup){
    val myView = this
    if (rootLayout is ConstraintLayout) {
        ConstraintSet().apply {
            clone(rootLayout)
            connect(myView.id, ConstraintSet.TOP, destinyView.id, ConstraintSet.TOP, 0)
            connect(myView.id, ConstraintSet.START, destinyView.id, ConstraintSet.START, 0)
            connect(myView.id, ConstraintSet.END, destinyView.id, ConstraintSet.END, 0)
            connect(myView.id, ConstraintSet.BOTTOM, destinyView.id, ConstraintSet.BOTTOM, 0)
            applyTo(rootLayout)
        }
        rootLayout.addView(myView)
    }
}

fun View.toRightOf(destinyView: View, rootLayout: ViewGroup){
    val myView = this
    if (rootLayout is ConstraintLayout) {

        ConstraintSet().apply {
            clone(rootLayout)
            connect(myView.id, ConstraintSet.TOP, destinyView.id, ConstraintSet.TOP, 0)
            connect(myView.id, ConstraintSet.START, destinyView.id, ConstraintSet.END, 16)
            connect(myView.id, ConstraintSet.BOTTOM, destinyView.id, ConstraintSet.BOTTOM, 0)
            applyTo(rootLayout)
        }
    }
}*/

//Pega todas as cartas (CardView) que estão visiveis na tela
fun View.findVisibleCardViews(): List<CardView> {
    val visibleCards = mutableListOf<CardView>()
    if (this is ViewGroup){
        for (i in 0..this.childCount){
            val layout = this.getChildAt(i)
            if (layout is CardView)
                visibleCards.add(layout)
        }
    }
    return visibleCards
}


fun View.getViewCardsCoordinates(vararg placeholderCard: CardView?): List<Pair<Float,Float>> {
    val listCoordinates = arrayListOf<Pair<Float,Float>>()
    placeholderCard.forEach {
        if (it != null) {
            listCoordinates.add(Pair(it.x, it.y))
        }

    }
    return listCoordinates
}

//Pega todas as cartas do usuario
fun View.getUserCards(): List<CardView> {
    val userCards = mutableListOf<CardView>()
    if (this is CardView && this.isUserHands) {
        userCards.add(this)
    }
    return userCards
}


fun Card.getCardDrawable(): Int {
    when(this.label.uppercase()){
        "A" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_ace
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_ace
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_ace
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_ace
                }
            }
        }
        "2" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_2
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_2
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_2
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_2
                }
            }
        }
        "3" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_3
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_3
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_3
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_3
                }
            }
        }
        "4" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_4
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_4
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_4
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_4
                }
            }
        }
        "5" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_5
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_5
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_5
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_5
                }
            }
        }
        "6" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_6
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_6
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_6
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_6
                }
            }
        }
        "7" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_7
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_7
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_7
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_7
                }
            }
        }
        "Q" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_q
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_q
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_q
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_q
                }
            }
        }
        "J" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_j
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_j
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_j
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_j
                }
            }
        }
        "K" -> {
            when (this.naipe) {
                SUIT_DIAMONDS -> {
                    return R.drawable.diamonds_k
                }
                SUIT_SPADES -> {
                    return R.drawable.spades_k
                }
                SUIT_HEARTS -> {
                    return R.drawable.hearts_k
                }
                SUIT_CLUBS -> {
                    return R.drawable.clubs_k
                }
            }
        }
        else -> {
            return R.drawable.back
        }
    }
    return R.drawable.back
}