package com.demis.gymsharkshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demis.gymsharkshop.presentation.theme.GsBlack
import com.demis.gymsharkshop.presentation.theme.GsTextMuted
import com.demis.gymsharkshop.presentation.theme.GymsharkShopTheme

/**
 * Renders an HTML string as formatted text (bold, italics, lists, links) instead of raw tags.
 * Uses [AnnotatedString.fromHtml] — the dependency-free path on this Compose toolchain.
 */
@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
) {
    val annotated = remember(html) { AnnotatedString.fromHtml(html) }
    Text(
        text = annotated,
        modifier = modifier,
        color = color,
        style = style,
    )
}

@Preview(widthDp = 340, backgroundColor = 0xFF0A0A0A, showBackground = true)
@Composable
private fun HtmlTextPreview() {
    GymsharkShopTheme {
        HtmlText(
            html = "<p><b>Sweat-wicking</b>, squat-proof leggings.</p>" +
                "<ul><li>Four-way stretch</li><li>High-waisted</li></ul>",
            color = GsTextMuted,
            modifier = Modifier.background(GsBlack).padding(16.dp),
        )
    }
}