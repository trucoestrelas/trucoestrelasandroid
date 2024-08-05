package com.mazer.common_tools

import com.mazer.common_tools.R

fun Int.getAvatarDrawable(): Int{
    when(this){
        1 -> return R.drawable.avatar_1
        2 -> return R.drawable.avatar_2
        3 -> return R.drawable.avatar_3
        4 -> return R.drawable.avatar_4
        5 -> return R.drawable.avatar_5
        6 -> return R.drawable.avatar_6
        7 -> return R.drawable.avatar_7
        8 -> return R.drawable.avatar_8
        9 -> return R.drawable.avatar_9
        10 -> return R.drawable.avatar_10
        11 -> return R.drawable.avatar_11
        12 -> return R.drawable.avatar_12
        13 -> return R.drawable.avatar_13
        14 -> return R.drawable.avatar_14
        15 -> return R.drawable.avatar_15
        16 -> return R.drawable.avatar_16
        17 -> return R.drawable.avatar_17
        18 -> return R.drawable.avatar_18
        19 -> return R.drawable.avatar_19
        20 -> return R.drawable.avatar_20
        21 -> return R.drawable.avatar_21
        22 -> return R.drawable.avatar_22
        else -> return R.drawable.default_avatar
    }
}