package com.demis.gymsharkshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demis.gymsharkshop.domain.ProductLabel
import com.demis.gymsharkshop.presentation.theme.Archivo
import com.demis.gymsharkshop.presentation.theme.GsBlack
import com.demis.gymsharkshop.presentation.theme.GsLime
import com.demis.gymsharkshop.presentation.theme.GymsharkShopTheme

/**
 * Small outlined pill for a product [ProductLabel]. Exhaustive `when` guarantees every enum
 * value has a display string; [ProductLabel.UNKNOWN] is already filtered out by the mapper,
 * so it renders nothing here as a defensive fallback.
 */
@Composable
fun LabelBadge(label: ProductLabel, modifier: Modifier = Modifier) {
    val text = when (label) {
        ProductLabel.GOING_FAST -> "GOING FAST"
        ProductLabel.NEW -> "NEW"
        ProductLabel.POPULAR -> "POPULAR"
        ProductLabel.LIMITED_EDITION -> "LIMITED EDITION"
        ProductLabel.RECYCLED_NYLON -> "RECYCLED NYLON"
        ProductLabel.RECYCLED_POLYESTER -> "RECYCLED POLYESTER"
        ProductLabel.UNKNOWN -> return
    }
    Text(
        text = text,
        color = GsLime,
        fontFamily = Archivo,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 8.sp,
        letterSpacing = 1.sp,
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, GsLime, RoundedCornerShape(5.dp))
            .padding(horizontal = 6.dp, vertical = 3.dp),
    )
}

@Preview(backgroundColor = 0xFF0A0A0A, showBackground = true)
@Composable
private fun LabelBadgePreview() {
    GymsharkShopTheme {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.background(GsBlack).padding(12.dp),
        ) {
            ProductLabel.entries.forEach { LabelBadge(it) }
        }
    }
}