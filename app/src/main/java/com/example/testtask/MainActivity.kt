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
    private var adapter = CardStackAdapter()

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
        CardStackAdapter()
        setupAtributtes()
    }

    private fun setupAtributtes(){
        binding.whiteWeapon.setImageLevel(weaponLevel)
        binding.whitePeople.setImageLevel(peopleLevel)
        binding.whiteRubles.setImageLevel(rublesLevel)
        binding.whiteNature.setImageLevel(natureLevel)
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
        adapter.setData(EventList.events)
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
        atributesCheck(numberOfCard)
        numberOfCard++
    }

    private fun atributesCheck(numberOfCard: Int) {
        if(weaponLevel < 0 ){ loseofWeapon() }
        if(peopleLevel < 0){ loseofPeople() }
        if(rublesLevel < 0){ loseofRubles() }
        if(natureLevel < 0){ loseofNature() }

    }

    private fun loseofNature() {

    }

    private fun loseofRubles() {
        TODO("Not yet implemented")
    }

    private fun loseofPeople() {
        TODO("Not yet implemented")
    }

    private fun loseofWeapon() {
        //EventList.events.add(numberOfCard, EventList.loseOfWeapon)

    }

    private fun atributesChange(direction: Direction, numberOfCard: Int) {
        when(direction){
            Direction.Right -> {
                weaponLevel += EventList.events[numberOfCard].firstDecisionWeaponResult
                peopleLevel += EventList.events[numberOfCard].firstDecisionPeopleResult
                rublesLevel += EventList.events[numberOfCard].firstDecisionRublesResult
                natureLevel += EventList.events[numberOfCard].firstDecisionNatureResult

                binding.whiteWeapon.setImageLevel(weaponLevel)
                binding.whitePeople.setImageLevel(peopleLevel)
                binding.whiteRubles.setImageLevel(rublesLevel)
                binding.whiteNature.setImageLevel(natureLevel)
            }
            Direction.Left -> {
                weaponLevel += EventList.events[numberOfCard].secondDecisionWeaponResult
                peopleLevel += EventList.events[numberOfCard].secondDecisionPeopleResult
                rublesLevel += EventList.events[numberOfCard].secondDecisionRublesResult
                natureLevel += EventList.events[numberOfCard].secondDecisionNatureResult

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


