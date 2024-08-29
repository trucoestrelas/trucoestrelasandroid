package com.mazer.trucoestrelas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mazer.trucoestrelas.databinding.ActivityMainBinding
import com.mazer.trucoestrelas.ui.play.fodinha.FodinhaFragment
import com.mazer.trucoestrelas.ui.play.truco.TrucoFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }


    private fun setupView(){
        setupBottomNavigationView()
    }


    private fun setupBottomNavigationView() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }
}



/*import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mazer.truco.ui.TrucoGameActivity
import com.mazer.trucoestrelas.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    *//*    private lateinit var webSocketManager: WebSocketManager
        private val scope = CoroutineScope(Dispatchers.Main)

        private var axisXChat = 0f
        private var axisYChat = 0f

        private var maoPlayer1 = arrayListOf<Card>()
        private var selectedCard = -1

        //MOCKS
        private var roundNumber = 1
        private var pontosTimeAzul = 0
        private var pontosTimeVermelho = 0*//*

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val it = Intent(this, TrucoGameActivity::class.java)
        startActivity(it)

        *//*

                webSocketManager = WebSocketManager()


                setupView()

                maoPlayer1.add(Card("K", "Ouros"))
                maoPlayer1.add(Card("Q", "Espadas"))
                maoPlayer1.add(Card("3", "Paus"))

                binding.ivPlayer1Mao1.showCard(maoPlayer1[0])
                binding.ivPlayer1Mao2.showCard(maoPlayer1[1])
                binding.ivPlayer1Mao3.showCard(maoPlayer1[2])

                binding.ivPlayer1Mao1.addOnCardSelectedListener { card, isSelected ->
                    if (isSelected) {
                        binding.btnAction.text = getString(R.string.btn_jogar_carta, card.label, card.naipe)
                        binding.ivPlayer1Mao2.setCardSelected(false)
                        binding.ivPlayer1Mao3.setCardSelected(false)
                        binding.btnAction.isEnabled = true
                        selectedCard = 0
                    }else{
                        binding.btnAction.text = getString(R.string.btn_jogar)
                        binding.btnAction.isEnabled = false
                        selectedCard = -1

                    }
                }
                binding.ivPlayer1Mao2.addOnCardSelectedListener { card, isSelected ->
                    if (isSelected) {
                        binding.btnAction.text = getString(R.string.btn_jogar_carta, card.label, card.naipe)
                        binding.ivPlayer1Mao1.setCardSelected(false)
                        binding.ivPlayer1Mao3.setCardSelected(false)
                        binding.btnAction.isEnabled = true
                        selectedCard = 1
                    }else{
                        binding.btnAction.text = getString(R.string.btn_jogar)
                        binding.btnAction.isEnabled = false
                        selectedCard = -1
                    }
                }
                binding.ivPlayer1Mao3.addOnCardSelectedListener { card, isSelected ->
                    if (isSelected) {
                        binding.btnAction.text = getString(R.string.btn_jogar_carta, card.label, card.naipe)
                        binding.ivPlayer1Mao1.setCardSelected(false)
                        binding.ivPlayer1Mao2.setCardSelected(false)
                        binding.btnAction.isEnabled = true
                        selectedCard = 2
                    }else{
                        binding.btnAction.text = getString(R.string.btn_jogar)
                        binding.btnAction.isEnabled = false
                        selectedCard = -1
                    }
                }


                //PLAY CARD
                binding.btnAction.setOnClickListener {
                    val dX = binding.viewEndPositionTeamBlue.x
                    val dY = binding.viewEndPositionTeamBlue.y
                    when (selectedCard) {
                        0 -> {
                            binding.ivPlayer1Mao1.playCardAnimation(dX, dY)
                        }
                        1 -> {
                            binding.ivPlayer1Mao2.playCardAnimation(dX, dY)
                        }
                        2 -> {
                            binding.ivPlayer1Mao3.playCardAnimation(dX, dY)
                        }
                    }
                }

            }

            override fun onStart() {
                super.onStart()
                webSocketManager.start()
            }

            override fun onStop() {
                super.onStop()
                webSocketManager.close()
            }

            private fun setupView(){
                setupChatUI()

                val button = findViewById<Button>(R.id.btn_go)
                button.setOnClickListener {
                    scope.launch {
                        webSocketManager.sendMessage(0, false)
                    }
                }
            }

            @SuppressLint("ClickableViewAccessibility")
            private fun setupChatUI(){
                val draggableView: View = findViewById(R.id.layout_chat)
                draggableView.setOnTouchListener { view, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            axisXChat = view.x - event.rawX
                            axisYChat = view.y - event.rawY
                        }
                        MotionEvent.ACTION_MOVE -> {
                            view.animate()
                                .x(event.rawX + axisXChat)
                                .y(event.rawY + axisYChat)
                                .setDuration(0)
                                .start()
                        }
                    }
                    true
                }*//*
    }


}*/
