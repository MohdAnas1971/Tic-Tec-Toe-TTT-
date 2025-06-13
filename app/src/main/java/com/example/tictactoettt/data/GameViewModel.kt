package com.example.tictactoettt.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tictactoettt.UserAction

class GameViewModel : ViewModel() {
    // variable for Box
    var state by mutableStateOf(GameState())

    // initilise all box with number
    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,

        )

    // Action that user will perform
    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValuesToBoard(action.cellNo)
            }

            is UserAction.PlayAgainButtonClicked -> {
                state.hasWon=false
                gameReset()
            }
        }
    }

    // set all box empty
    private fun gameReset() {
        // Loop on all box value and set NONE
        boardItems.forEach { (i, _) ->
            boardItems[i] = BoardCellValue.NONE
        }
        state = state.copy(
            hintText = "Player 'O' turn",
            currentTurn = BoardCellValue.CIRCLE
        )
    }

    // To check for victory (return true)else(return false)
    private fun checkForVictory(boardCellValue: BoardCellValue): Boolean {
        when {
            //HORIZONTAL1
            checkValueForVic(1, 2, 3, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.HORIZONTAL1)
                return true
            }
            //HORIZONTAL2
            checkValueForVic(4, 5, 6, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.HORIZONTAL2)
                return true
            }
            //HORIZONTAL3
            checkValueForVic(7, 8, 9, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.HORIZONTAL3)
                return true
            }
            //VERTICAL1
            checkValueForVic(1, 4, 7, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.VERTICAL1)
                return true
            }
            //VERTICAL2
            checkValueForVic(2, 5, 8, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.VERTICAL2)
                return true
            }
            //VERTICAL3
            checkValueForVic(3, 6, 9, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.VERTICAL3)
                return true
            }
            //DIAGONAL1
            checkValueForVic(1, 5, 9, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.DIAGONAL1)
                return true
            }
            //DIAGONAL2
            checkValueForVic(3, 5, 7, boardCellValue) -> {
                state = state.copy(vicoryType = VictoryType.DIAGONAL2)
                return true
            }

            else -> return false
        }
    }

    //to compare value of box for Victory
    private fun checkValueForVic(a: Int, b: Int, c: Int, boardCellValue: BoardCellValue): Boolean {
        return boardItems[a] == boardCellValue && boardItems[b] == boardCellValue && boardItems[c] == boardCellValue
    }

    // Insert X or o in box of board
    private fun addValuesToBoard(cellNo: Int) {
        //Return if no empty space
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            return
        }
        // to check Current Turn== Circle
        if (state.currentTurn == BoardCellValue.CIRCLE) {
            boardItems[cellNo] = BoardCellValue.CIRCLE
   //check victory after this move
            if (checkForVictory(BoardCellValue.CIRCLE)) {
                state = state.copy(
                    hintText = "Player 'O' WON",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else {
                // to check board is empty  or not
                state = if (!hasBoardFull()) {//board Empty
                    state.copy(
                        hintText = "Player 'X' Turn",
                        currentTurn = BoardCellValue.CROSS
                    )
                } else {//board Full
                    state.copy(
                        hintText = "Game Draw",
                        drawCount = state.drawCount + 1
                    )
                }
            }
            // Current turn== Cross
        } else if (state.currentTurn == BoardCellValue.CROSS) {
            boardItems[cellNo] = BoardCellValue.CROSS
            //check victory after this move
            if (checkForVictory(BoardCellValue.CROSS)) {
                state = state.copy(
                    hintText = "Player 'X' WON",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            } else {
                // to check board is  empty or not
                state = if (!hasBoardFull()) { //board Empty
                    state.copy(
                        hintText = "Player 'O' turn",
                        currentTurn = BoardCellValue.CIRCLE
                    )
                } else {// board Full
                    state.copy(
                        hintText = "Game Draw",
                        drawCount = state.drawCount + 1
                    )
                }
            }
        }
    }

    // to check board has filled or not(any null box here or not)
    private fun hasBoardFull(): Boolean {
        return !boardItems.containsValue(BoardCellValue.NONE)
    }
}



