package com.mazer.trucoestrelas.ui.play.truco

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mazer.trucoestrelas.databinding.FragmentTrucoBinding
import com.mazer.trucoestrelas.ui.queue.QueueActivity

class TrucoFragment: Fragment() {

    private lateinit var binding: FragmentTrucoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTrucoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQuickPlayTrucoBot.setOnClickListener {
            val it = Intent(requireActivity(), QueueActivity::class.java)
            startActivity(it)

/*            val fragment = QueueFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .addToBackStack(null)
                .commit()*/
        }
    }
}