package com.mazer.trucoestrelas.ui.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mazer.trucoestrelas.R
import com.mazer.trucoestrelas.databinding.FragmentPlayBinding
import com.mazer.trucoestrelas.ui.ViewPagerAdapter
import com.mazer.trucoestrelas.ui.play.fodinha.FodinhaFragment
import com.mazer.trucoestrelas.ui.play.truco.TrucoFragment

class PlayFragment : Fragment() {


    private var _binding: FragmentPlayBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[PlayViewModel::class.java]

        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayoutView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupTabLayoutView() {
        val fragments = listOf(
            TrucoFragment(),
            FodinhaFragment(),
        )

        val adapter = ViewPagerAdapter(requireActivity(), fragments)
        binding.viewPager.adapter = adapter

        val tabNames = listOf(getString(com.mazer.common_tools.R.string.aba_truco), getString(com.mazer.common_tools.R.string.aba_fodinha))

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}