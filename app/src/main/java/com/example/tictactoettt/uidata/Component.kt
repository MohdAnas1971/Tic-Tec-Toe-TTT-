package com.example.tictactoettt.uidata

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoettt.ui.theme.Aqua
import com.example.tictactoettt.ui.theme.GreenisYellow



@Composable
fun BordBase() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
drawLine(
    color = Color.Gray,
    strokeWidth = 5f,
    cap = StrokeCap.Round,
    start = Offset(x = size.width*1/3, y = 0f),
    end = Offset(x = size.width*1/3, y =size.height )
)
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*2/3, y = 0f),
            end = Offset(x = size.width*2/3, y =size.height )
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*1/3),
            end = Offset(x =size.width , y =size.height*1/3 )
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*2/3),
            end = Offset(x =size.width , y =size.height*2/3 )
        )

    }
}


@Composable
fun Cross(modifier: Modifier = Modifier) {

    Canvas(
modifier= Modifier
    .size(60.dp)
    .padding(5.dp)
    ){
        drawLine(
            color = GreenisYellow,
            strokeWidth =20f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x =size.width , y =size.height)
        )
        drawLine(
            color = GreenisYellow,
            strokeWidth =20f,
            cap = StrokeCap.Round,
            start = Offset(x =size.width , y = 0f ),
            end = Offset(x = 0f, y = size.height)
        )

    }
}

@Composable
fun Circl(modifier: Modifier = Modifier) {

    Canvas(
        modifier= Modifier
            .size(60.dp)
            .padding(5.dp)
    ){
drawCircle(
    color = Aqua,
    style = Stroke(width = 20f)
)

    }

}

//wining line

//Vertical line 1
@Composable
fun WinVertiLine1(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = size.width*1/6, y =size.height),
            end = Offset(x = size.width*1/6, y = 0f)
        )
    }
}
//Vertical line 2
@Composable
fun WinVertiLine2(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = size.width*1/2, y =size.height),
            end = Offset(x = size.width*1/2, y = 0f)
        )
    }
}
//Vertical line 3
@Composable
fun WinVertiLine3(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = size.width*5/6, y =size.height),
            end = Offset(x = size.width*5/6, y = 0f)
        )
    }
}

//horizontal line 1
@Composable
fun WinHoriLine1(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = 0f, y = size.width*1/6),
            end = Offset(x = size.width, y = size.width*1/6)
        )
    }
}

//horizontal line 2
@Composable
fun WinHoriLine2(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = 0f, y = size.width*1/2),
            end = Offset(x = size.width, y = size.width*1/2)
        )
    }
}

//horizontal line 3
@Composable
fun WinHoriLine3(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = 0f, y = size.width*5/6),
            end = Offset(x = size.width, y = size.width*5/6)
        )
    }
}


//Diagonal line 1
@Composable
fun WinDiagoLine1(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.width)
        )
    }
}


//Diagonal line 2
@Composable
fun WinDiagoLine2(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset( x = size.width, y =0f ),
            end = Offset(x = 0f, y = size.height)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Preview
@Composable
private fun testsr() {
BordBase()
    WinVertiLine1()
    WinVertiLine2()
    WinVertiLine3()
    WinHoriLine1()
    WinHoriLine2()
    WinHoriLine3()
    WinDiagoLine1()
    WinDiagoLine2()
}