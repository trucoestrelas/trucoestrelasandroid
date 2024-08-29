package com.mazer.trucoestrelas.ui.ranking

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.trucoestrelas.databinding.ItemRankingBinding
import com.mazer.trucoestrelas.getAvatarDrawable
import kotlin.random.Random


class RankingViewHolder (private val binding: ItemRankingBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(rankingUi: RankingUi, position: Int) {
        val context = binding.root.context
        binding.tvRank.text = "#${position+1}"
        binding.tvUserName.text = rankingUi.username
        binding.tvUserKda.text = "${rankingUi.numVitorias}V ${rankingUi.numDerrotas}D - ${rankingUi.totalPts} Pts"

        //Mock
        val randomNumber = Random.nextInt(1, 24)
        Glide.with(context).load(randomNumber.getAvatarDrawable()).into(binding.ivUserAvatar)
    }

}