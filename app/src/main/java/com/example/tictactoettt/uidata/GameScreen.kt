package com.example.tictactoettt.uidata

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoettt.UserAction
import com.example.tictactoettt.data.BoardCellValue
import com.example.tictactoettt.data.GameState
import com.example.tictactoettt.data.GameViewModel
import com.example.tictactoettt.data.VictoryType
import com.example.tictactoettt.ui.theme.Aqua
import com.example.tictactoettt.ui.theme.BlueCustom
import com.example.tictactoettt.ui.theme.GrayBackgraound
import com.example.tictactoettt.ui.theme.GreenisYellow


@Composable
fun GameScreen(
    viewModel: GameViewModel,
) {

    val state = viewModel.state


    val playerColor=if(state.currentTurn==BoardCellValue.CIRCLE) Aqua else{ GreenisYellow}

    // For all Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackgraound)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        // for hadder part
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Player 'X':${state.playerCrossCount} ", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = GreenisYellow )
            Text(text = "Draw: ${state.drawCount}", fontSize = 16.sp)
            Text(text = "Player 'O':${state.playerCircleCount}", fontSize = 16.sp,fontWeight = FontWeight.SemiBold, color = Aqua)
        }

        //Game hading
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Tic Tac Toe(TTT)",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                color = BlueCustom,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
        }

        // For Mieddel part
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp),
                    spotColor = playerColor
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackgraound),
            contentAlignment = Alignment.Center
        ) {

            // horizo and verti line on Board
            BordBase()
            // to make clickble all 9 box
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)
            ) {
                //Loop for every Box of Board
                viewModel.boardItems.forEach { (cellNo, boardCellvalue) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onAction(
                                        UserAction.BoardTapped(cellNo)
                                    )
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Animation when click on Box of Board
                            AnimatedVisibility(
                                //to check user clicking on empty Box
                                visible = viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                                // Animation Time
                                enter = scaleIn(tween(1000))
                            ) {
                                if (boardCellvalue == BoardCellValue.CIRCLE) {
                                    Circl()
                                } else if (boardCellvalue == BoardCellValue.CROSS) {
                                    Cross()
                                }
                            }
                        }
                    }
                }
            }
            //for Win Red line
            Column {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))
                ) {
                    DrawVictoryLine(state = state)
                }
            }
        }
        // Row for bottom part
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // to show turn of which Player
            Text(
                text = state.hintText,
                fontSize = 24.sp,
                color = playerColor,
                fontStyle = FontStyle.Italic
            )
            // Button for play Again
            Button(
                onClick = { viewModel.onAction(UserAction.PlayAgainButtonClicked) },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Play Again", fontSize = 16.sp)

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    GameScreen(viewModel = GameViewModel())
}

@Composable
fun DrawVictoryLine(
    state: GameState,
) {

    when (state.vicoryType) {
        VictoryType.HORIZONTAL1 -> WinHoriLine1()
        VictoryType.HORIZONTAL2 -> WinHoriLine2()
        VictoryType.HORIZONTAL3 -> WinHoriLine3()
        VictoryType.VERTICAL1 -> WinVertiLine1()
        VictoryType.VERTICAL2 -> WinVertiLine2()
        VictoryType.VERTICAL3 -> WinVertiLine3()
        VictoryType.DIAGONAL1 -> WinDiagoLine1()
        VictoryType.DIAGONAL2 -> WinDiagoLine2()
        VictoryType.NONE -> {}
    }
}