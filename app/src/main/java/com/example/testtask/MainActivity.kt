package com.example.testtask

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.testtask.databinding.ActivityMainBinding
import com.yuyakaido.android.cardstackview.*

class MainActivity : AppCompatActivity(), CardStackListener {

    lateinit var binding: ActivityMainBinding
    private val adapter = CardStackAdapter(EventList.events)
    private lateinit var layoutManager: CardStackLayoutManager
    private var weaponLevel = 5000
    private var peopleLevel = 5000
    private var rublesLevel = 5000
    private var natureLevel = 5000

    var numberOfCard = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCards()
        setupAtributtes()
    }

    private fun setupAtributtes(){
        binding.whiteWeapon.setImageLevel(weaponLevel)
        binding.whitePeople.setImageLevel(peopleLevel)
        binding.whiteRubles.setImageLevel(rublesLevel)
        binding.whiteNature.setImageLevel(natureLevel)
        Log.d(TAG, "${binding.whiteWeapon.height}")
    }

    private fun setupCards(){
        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
            setVisibleCount(1)
            setMaxDegree(70.0f)
        }

        binding.cardStack.layoutManager = layoutManager
        binding.cardStack.adapter = adapter
        binding.cardStack.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d(TAG, "onCardDisappeared  $position ")
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d(TAG, "onCardDragging  $direction  + $ratio ")
        if(direction == Direction.Right && ratio > 0.15 && direction != Direction.FREEDOM){
            binding.firstDesicion.text = EventList.events[numberOfCard].firstDecision
            binding.secondDesicion.text = ""
        }
        if( direction == Direction.Left && ratio > 0.15 && direction != Direction.FREEDOM ){
            binding.firstDesicion.text = ""
            binding.secondDesicion.text = EventList.events[numberOfCard].secondDecision
        }
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d(TAG, "onCardSwiped  $direction  ")
        binding.firstDesicion.text = ""
        binding.secondDesicion.text = ""
        atributesChange(direction, numberOfCard)

        numberOfCard++
    }

    private fun atributesChange(direction: Direction, numberOfCard: Int) {
        when(direction){
            Direction.Right -> {
                weaponLevel = weaponLevel + EventList.events[numberOfCard].firstDecisionWeaponResult
                peopleLevel = peopleLevel + EventList.events[numberOfCard].firstDecisionPeopleResult
                rublesLevel = rublesLevel + EventList.events[numberOfCard].firstDecisionRublesResult
                natureLevel = natureLevel + EventList.events[numberOfCard].firstDecisionNatureResult

                binding.whiteWeapon.setImageLevel(weaponLevel)
                binding.whitePeople.setImageLevel(peopleLevel)
                binding.whiteRubles.setImageLevel(rublesLevel)
                binding.whiteNature.setImageLevel(natureLevel)
            }
            Direction.Left -> {
                weaponLevel = weaponLevel + EventList.events[numberOfCard].secondDecisionWeaponResult
                peopleLevel = peopleLevel + EventList.events[numberOfCard].secondDecisionPeopleResult
                rublesLevel = rublesLevel + EventList.events[numberOfCard].secondDecisionRublesResult
                natureLevel = natureLevel + EventList.events[numberOfCard].secondDecisionNatureResult

                binding.whiteWeapon.setImageLevel(weaponLevel)
                binding.whitePeople.setImageLevel(peopleLevel)
                binding.whiteRubles.setImageLevel(rublesLevel)
                binding.whiteNature.setImageLevel(natureLevel)
            }

            else -> {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCardCanceled() {
        binding.firstDesicion.text = ""
        binding.secondDesicion.text = ""
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d(TAG, "onCardAppeared  $position  ")
        binding.textDate.text = EventList.events[position].date
    }

    override fun onCardRewound() {
        Log.d(TAG, "onCardRewound ")
    }

    companion object{
        val TAG = "mylogs"
    }

}


