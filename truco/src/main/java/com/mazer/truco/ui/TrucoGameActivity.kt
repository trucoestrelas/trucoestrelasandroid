package com.mazer.truco.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.mazer.common_tools.animateToConstraints
import com.mazer.common_tools.collapseAllCardsOnDeck
import com.mazer.common_tools.components.CardView
import com.mazer.common_tools.getAvatarDrawable
import com.mazer.common_tools.getViewCardsCoordinates
import com.mazer.common_tools.spawnView
import com.mazer.network.WebSocketManager
import com.mazer.truco.R
import com.mazer.truco.databinding.ActivityGameBinding
import com.mazer.truco.domain.GameRules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TrucoGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding


    private lateinit var webSocketManager: WebSocketManager
    private val scope = CoroutineScope(Dispatchers.Main)

    private var axisXChat = 0f
    private var axisYChat = 0f

    private var maoPlayer1 = arrayListOf<CardView>()
    private var selectedCard = -1

    private var handsHashMapCoordinate : HashMap<Int, List<Pair<Float,Float>>> = HashMap()


    //MOCKS
    private var roundNumber = 1
    private var pontosTimeAzul = 0
    private var pontosTimeVermelho = 0
    private val quantPlayers = 4
    private val playerReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout_table_truco)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        webSocketManager = WebSocketManager()

 /*       binding.rootLayoutTableTruco.post {
            getViewCoordinates()
            val a = "asdas"
            val b = this.handsHashMapCoordinate
            val c = ""

            setupView()
        }

        webSocketManager = WebSocketManager()*/



        /*        maoPlayer1.add(Card("K", "Ouros"))
                maoPlayer1.add(Card("Q", "Espadas"))
                maoPlayer1.add(Card("3", "Paus"))*/

        /*        for (i in 1..3){
                    val cardView = CardView(this).apply {
                        id = View.generateViewId()
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        elevation = 16f
                    }
                    maoPlayer1.add(cardView)
                }*/


        //binding.ivPlayer1Mao1.showCard(maoPlayer1[0])
        //binding.ivPlayer1Mao2.showCard(maoPlayer1[1])
        //binding.ivPlayer1Mao3.showCard(maoPlayer1[2])

        /*binding.ivPlayer1Mao1.visibility = View.VISIBLE
        binding.ivPlayer1Mao2.visibility = View.VISIBLE
        binding.ivPlayer1Mao3.visibility = View.VISIBLE

        binding.ivPlayer2Mao1.visibility = View.VISIBLE
        binding.ivPlayer2Mao2.visibility = View.VISIBLE
        binding.ivPlayer2Mao3.visibility = View.VISIBLE*/


    }

    private fun getViewCoordinates() {
        binding.rootLayoutTableTruco.post {
            handsHashMapCoordinate[1] = binding.rootLayoutTableTruco.getViewCardsCoordinates(
                binding.ivPlayer1Mao1 as CardView,
                binding.ivPlayer1Mao2 as CardView,
                binding.ivPlayer1Mao3 as CardView
            )
            handsHashMapCoordinate[2] = binding.rootLayoutTableTruco.getViewCardsCoordinates(
                binding.ivPlayer2Mao1 as CardView,
                binding.ivPlayer2Mao2 as CardView,
                binding.ivPlayer2Mao3 as CardView
            )
            handsHashMapCoordinate[3] = binding.rootLayoutTableTruco.getViewCardsCoordinates(
                binding.ivPlayer3Mao1 as CardView,
                binding.ivPlayer3Mao2 as CardView,
                binding.ivPlayer3Mao3 as CardView
            )
            handsHashMapCoordinate[4] = binding.rootLayoutTableTruco.getViewCardsCoordinates(
                binding.ivPlayer4Mao1 as CardView,
                binding.ivPlayer4Mao2 as CardView,
                binding.ivPlayer4Mao3 as CardView
            )
            val a = ""
        }

    }

    private fun handleGameState(state: GameUIState){
        when (state){
            is GameUIState.Init -> {
                setupInitState(state.gameRules)
            }
            is GameUIState.MatchStarted -> {
                setupMatchStartedState()
            }
            is GameUIState.RoundStarted -> {
                setupRoundStartedState()
            }
            is GameUIState.PlayerTurn -> {
                setupPlayerTurnState()
            }
            is GameUIState.EndingTurn -> {
                setupPlayerEndingTurnState()
            }
            is GameUIState.RoundEnded -> {
                setupRoundEndedState()
            }
            is GameUIState.MatchEnded -> {
                setupMatchEndedState()
            }
            is GameUIState.ChatMessageReceived -> {
                setupMessageState()
            }
        }
    }

    private fun setupInitState(gameRules: GameRules) {
        //show label waiting for players on a view
        binding.tvStatusGame?.text  = getString(R.string.waiting_for_players)

        //show game ID? on a view
        binding.tvGameId?.text = getString(R.string.game_id, gameRules.gameId)

        //load avatars view
        gameRules.playerList.forEachIndexed { index, player ->
            when (index) {
                0 -> {
                    //Player 1 - User - Blue Team
                    Glide.with(binding.root.context).load(player.avatarIndex.getAvatarDrawable()).into(binding.ivPlayer1Profile)
                }
                1 -> {
                    //Player 2 - Red Team
                    Glide.with(binding.root.context).load(player.avatarIndex.getAvatarDrawable()).into(binding.ivPlayer2Profile)
                }
                2 -> {
                    //Player 3 - Blue Team
                }
                3 -> {
                    //Player 4 = Red Team
                }
            }
        }
    }


    private fun setupMatchStartedState() {
        this.pontosTimeAzul = 0
        this.pontosTimeVermelho = 0
    }

    private fun setupRoundStartedState(){
        //show Round Number
        //setup PÉ label
        //shuffle and give cards to all, starting after PÉ
        //show Vira
    }

    private fun setupPlayerTurnState(){
        //check which player's turn
        //show players turn label indications
        //start his timer
        //if player's turn, enable action Buttons -> else disable action buttons
    }

    private fun setupPlayerEndingTurnState() {
        //show winner card
        //refresh turn counter to winner team
        //
    }

    private fun setupRoundEndedState(){
        //show winner's round
        //refresh points
    }

    private fun setupMatchEndedState(){
        //show dialog win/lose
    }

    private fun setupMessageState() {
        //add message to list of chat
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

        binding.btnGo.setOnClickListener {
            /*scope.launch {
                webSocketManager.sendMessage(0, false)
            }*/
        }

        /* binding.ivPlayer1Mao1.addOnCardSelectedListener { card, isSelected ->
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
                 //binding.ivPlayer1Mao1.setCardSelected(false)
                 //binding.ivPlayer1Mao2.setCardSelected(false)
                 binding.btnAction.isEnabled = true
                 selectedCard = 2
             }else{
                 binding.btnAction.text = getString(R.string.btn_jogar)
                 binding.btnAction.isEnabled = false
                 selectedCard = -1
             }
         }*/

        binding.btnActionRecolher.setOnClickListener {
            binding.ivCardVira.visibility = View.GONE
            binding.frameDeck.collapseAllCardsOnDeck(binding.rootLayoutTableTruco)
        }

        binding.btnActionDar.setOnClickListener {
            val cardView  = binding.frameDeck.spawnView(binding.rootLayoutTableTruco)
            cardView.animateToConstraints(binding.framePlayer1)
            //maoPlayer1.giveCardsTo(binding.frameDeck, binding.framePlayer1, binding.rootLayoutTableTruco)
        }

        //PLAY CARD
        binding.btnAction.setOnClickListener {
            val dX = binding.viewEndPositionTeamBlue.x
            val dY = binding.viewEndPositionTeamBlue.y
            when (selectedCard) {
                0 -> {
                    //binding.ivPlayer1Mao1.playCardAnimation(dX, dY)
                }
                1 -> {
                    //binding.ivPlayer1Mao2.playCardAnimation(dX, dY)
                }
                2 -> {
                    //binding.ivPlayer1Mao3.playCardAnimation(dX, dY)
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupChatUI() {
        binding.layoutChat.setOnTouchListener { view, event ->
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
        }
    }
}