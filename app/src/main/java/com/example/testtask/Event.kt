package com.example.testtask

data class Event(
    val idImage: Int,
    val text: String,                    //то отображается на карточке
    val author: String,

    val date: String,
    val firstDecision: String,           // отображается вне карточки
    val secondDecision: String,

    val firstDecisionWeaponResult: Int,
    val firstDecisionPeopleResult: Int,
    val firstDecisionRublesResult: Int,  //результаты первого решения
    val firstDecisionNatureResult: Int,

    val secondDecisionWeaponResult: Int,
    val secondDecisionPeopleResult: Int,
    val secondDecisionRublesResult: Int, //результаты второго решения
    val secondDecisionNatureResult: Int,
)
