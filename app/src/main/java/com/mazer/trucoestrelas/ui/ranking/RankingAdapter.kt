package com.mazer.trucoestrelas.ui.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.trucoestrelas.databinding.ItemRankingBinding
import com.mazer.trucoestrelas.databinding.ItemSeuRankingBinding

class RankingAdapter() :  RecyclerView.Adapter<RankingViewHolder>() {

    private var streamersList: ArrayList<RankingUi> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(streamersList[position], position)
    }

    fun setList(list: List<RankingUi>) {
        this.streamersList = ArrayList(list)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int =  streamersList.size
}