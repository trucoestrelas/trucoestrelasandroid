package com.mazer.common_tools.components.helper

import android.content.res.TypedArray
import com.mazer.common_tools.R

internal class CardViewHelper(attibutes: TypedArray) {
    val isUserHands: Boolean? = attibutes.getBoolean(R.styleable.CardView_is_user_hands, false)
}