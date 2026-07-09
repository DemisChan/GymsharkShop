package com.demis.gymsharkshop.presentation.splash

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demis.gymsharkshop.presentation.theme.Archivo
import com.demis.gymsharkshop.presentation.theme.GsBlack
import com.demis.gymsharkshop.presentation.theme.GsLime
import com.demis.gymsharkshop.presentation.theme.GsTextDim
import com.demis.gymsharkshop.presentation.theme.SpaceGrotesk

@Composable
fun SplashScreen(onEnter: () -> Unit) {
    val pulse = rememberInfiniteTransition(label = "pulse")
    val alpha by pulse.animateFloat(
        initialValue = 0.35f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(900), RepeatMode.Reverse), label = "alpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF181818), GsBlack)
                )
            )
            .clickable(onClick = onEnter),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Wordmark — Archivo Black italic, ~4° shear. Replace with your licensed logo asset.
            Text(
                text = "GYMSHARK",
                color = GsLime,
                fontFamily = Archivo,
                fontWeight = FontWeight.Black,
                fontStyle = FontStyle.Italic,
                fontSize = 42.sp,
                letterSpacing = (-1.5).sp,
                // Design shows a ~4° skew; Compose text has no shear, so italic carries it.
                // For an exact match, swap this Text for your licensed logo asset (Image).
            )
            Box(
                Modifier.padding(vertical = 18.dp).width(40.dp).height(3.dp).background(GsLime)
            )
            Text(
                text = "TRAIN. REPEAT.",
                color = GsTextDim,
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                letterSpacing = 3.sp,
            )
        }
        Text(
            text = "TAP TO ENTER",
            color = GsLime,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            letterSpacing = 2.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 54.dp)
                .alpha(alpha),
        )
    }
}
@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen {}
}
