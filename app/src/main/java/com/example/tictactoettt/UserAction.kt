package com.example.tictactoettt

sealed class UserAction {
    object PlayAgainButtonClicked: UserAction()
    data class BoardTapped(val cellNo:Int):UserAction()

}