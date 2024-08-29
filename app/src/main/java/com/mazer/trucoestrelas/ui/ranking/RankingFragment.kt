package com.mazer.trucoestrelas.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mazer.trucoestrelas.databinding.FragmentRankingBinding

class RankingFragment : Fragment() {

    private var _binding: FragmentRankingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[RankingViewModel::class.java]

        _binding = FragmentRankingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val adapter = RankingAdapter()
        binding.rvRanking.adapter = adapter
        adapter.setList(getTop100Ranking())

    }

    private fun getTop100Ranking(): List<RankingUi> {
        return listOf(
            RankingUi("player9753", 69, 27, 1000),
            RankingUi("pro7086", 99, 32, 990),
            RankingUi("champ5479", 52, 4, 980),
            RankingUi("user7693", 57, 43, 970),
            RankingUi("pro2791", 90, 1, 960),
            RankingUi("gamer4726", 66, 48, 950),
            RankingUi("gamer2449", 77, 32, 940),
            RankingUi("pro3177", 85, 26, 930),
            RankingUi("champ7696", 70, 9, 920),
            RankingUi("gamer6422", 86, 34, 910),
            RankingUi("user4450", 85, 5, 900),
            RankingUi("gamer7379", 90, 43, 890),
            RankingUi("champ2761", 99, 41, 880),
            RankingUi("gamer8164", 54, 12, 870),
            RankingUi("pro6139", 100, 19, 860),
            RankingUi("user1565", 63, 41, 850),
            RankingUi("gamer3798", 99, 12, 840),
            RankingUi("pro7940", 98, 4, 830),
            RankingUi("gamer1564", 85, 8, 820),
            RankingUi("gamer1792", 100, 14, 810),
            RankingUi("player6732", 79, 39, 800),
            RankingUi("champ5878", 68, 18, 790),
            RankingUi("pro5618", 87, 50, 780),
            RankingUi("user6285", 75, 0, 770),
            RankingUi("champ9010", 52, 9, 760),
            RankingUi("player2699", 74, 2, 750),
            RankingUi("player2562", 51, 48, 740),
            RankingUi("user6072", 58, 32, 730),
            RankingUi("player5097", 61, 1, 720),
            RankingUi("champ2853", 83, 2, 710),
            RankingUi("gamer4667", 99, 44, 700),
            RankingUi("gamer7448", 69, 9, 690),
            RankingUi("pro8352", 58, 28, 680),
            RankingUi("pro2715", 64, 46, 670),
            RankingUi("pro3478", 63, 19, 660),
            RankingUi("champ3771", 60, 0, 650),
            RankingUi("user1044", 83, 48, 640),
            RankingUi("champ6166", 83, 37, 630),
            RankingUi("player2758", 63, 17, 620),
            RankingUi("gamer4383", 52, 8, 610),
            RankingUi("champ9685", 95, 47, 600),
            RankingUi("player6047", 60, 3, 590),
            RankingUi("user2728", 67, 35, 580),
            RankingUi("champ8994", 65, 32, 570),
            RankingUi("user4561", 55, 11, 560),
            RankingUi("player3985", 59, 40, 550),
            RankingUi("champ4498", 54, 24, 540),
            RankingUi("gamer7872", 57, 39, 530),
            RankingUi("gamer6458", 64, 17, 520),
            RankingUi("user8637", 63, 7, 510),
            RankingUi("gamer3810", 82, 14, 500),
            RankingUi("champ4951", 96, 24, 490),
            RankingUi("gamer9936", 71, 13, 480),
            RankingUi("user9918", 93, 25, 470),
            RankingUi("player7049", 84, 40, 460),
            RankingUi("player6908", 88, 48, 450),
            RankingUi("gamer8950", 64, 10, 440),
            RankingUi("gamer8647", 67, 38, 430),
            RankingUi("champ9464", 77, 4, 420),
            RankingUi("player9252", 51, 20, 410),
            RankingUi("user3447", 66, 13, 400),
            RankingUi("champ8482", 60, 26, 390),
            RankingUi("gamer5524", 94, 13, 380),
            RankingUi("gamer1224", 54, 32, 370),
            RankingUi("player9964", 54, 24, 360),
            RankingUi("champ1429", 61, 15, 350),
            RankingUi("player9218", 63, 33, 340),
            RankingUi("champ5204", 75, 28, 330),
            RankingUi("champ3910", 54, 50, 320),
            RankingUi("player8256", 65, 21, 310),
            RankingUi("gamer9359", 76, 4, 300),
            RankingUi("user4087", 86, 45, 290),
            RankingUi("pro9691", 50, 48, 280),
            RankingUi("pro8783", 80, 37, 270),
            RankingUi("gamer7064", 71, 19, 260),
            RankingUi("champ1043", 72, 44, 250),
            RankingUi("user6037", 73, 42, 240),
            RankingUi("champ1983", 74, 50, 230),
            RankingUi("gamer9003", 98, 29, 220),
            RankingUi("user3295", 57, 46, 210),
            RankingUi("user3780", 75, 31, 200),
            RankingUi("champ7946", 50, 8, 190),
            RankingUi("player2902", 80, 27, 180),
            RankingUi("gamer9503", 77, 39, 170),
            RankingUi("pro9588", 69, 4, 160),
            RankingUi("user4691", 58, 43, 150),
            RankingUi("gamer5490", 71, 15, 140),
            RankingUi("gamer5303", 68, 27, 130),
            RankingUi("champ4766", 93, 10, 120),
            RankingUi("user5349", 91, 26, 110),
            RankingUi("player1734", 88, 21, 100),
            RankingUi("pro2613", 73, 19, 90),
            RankingUi("player8686", 59, 15, 80),
            RankingUi("player5220", 50, 17, 70),
            RankingUi("pro1726", 68, 8, 60),
            RankingUi("champ8890", 85, 38, 50),
            RankingUi("pro4892", 96, 19, 40),
            RankingUi("gamer2612", 63, 24, 30),
            RankingUi("player4283", 95, 30, 20),
            RankingUi("gamer1144", 63, 34, 10)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}