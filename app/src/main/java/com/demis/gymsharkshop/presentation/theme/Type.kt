package com.demis.gymsharkshop.presentation.theme

import com.demis.gymsharkshop.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Archivo = FontFamily(
    Font(R.font.archivo_regular, FontWeight.Normal),
    Font(R.font.archivo_medium, FontWeight.Medium),
    Font(R.font.archivo_semibold, FontWeight.SemiBold),
    Font(R.font.archivo_bold, FontWeight.Bold),
    Font(R.font.archivo_extrabold, FontWeight.ExtraBold),
    Font(R.font.archivo_black, FontWeight.Black),
    Font(R.font.archivo_black_italic, FontWeight.Black, FontStyle.Italic),
)

val SpaceGrotesk = FontFamily(
    Font(R.font.space_grotesk_regular, FontWeight.Normal),
    Font(R.font.space_grotesk_medium, FontWeight.Medium),
    Font(R.font.space_grotesk_semibold, FontWeight.SemiBold),
    Font(R.font.space_grotesk_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    // Product name on cards
    titleMedium = TextStyle(
        fontFamily = Archivo, fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 18.sp
    ),
    // Product title on detail
    headlineMedium = TextStyle(
        fontFamily = Archivo, fontWeight = FontWeight.Black, fontStyle = FontStyle.Italic,
        fontSize = 26.sp, lineHeight = 25.sp, letterSpacing = (-0.5).sp
    ),
    // Price
    titleLarge = TextStyle(
        fontFamily = Archivo, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp
    ),
    // UPPERCASE section labels (SELECT SIZE, NEW IN…)
    labelLarge = TextStyle(
        fontFamily = Archivo, fontWeight = FontWeight.ExtraBold, fontSize = 11.sp, letterSpacing = 2.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Archivo, fontWeight = FontWeight.ExtraBold, fontSize = 8.sp, letterSpacing = 1.sp
    ),
    // Body / descriptions
    bodyMedium = TextStyle(
        fontFamily = SpaceGrotesk, fontWeight = FontWeight.Normal, fontSize = 13.sp, lineHeight = 21.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SpaceGrotesk, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, letterSpacing = 2.sp
    ),
)
