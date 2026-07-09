package com.demis.gymsharkshop.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.demis.gymsharkshop.presentation.theme.GsFallbackLabel
import com.demis.gymsharkshop.presentation.theme.GsFallbackStroke
import com.demis.gymsharkshop.presentation.theme.GsSurface
import com.demis.gymsharkshop.presentation.theme.GymsharkShopTheme

/**
 * Product image with the two-state fallback from the design:
 *  - null / blank url  -> fallback tile immediately (missing image)
 *  - load error        -> fallback tile via Coil's error slot (broken url)
 */
@Composable
fun ProductImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    if (imageUrl.isNullOrBlank()) {
        FallbackTile(modifier)
        return
    }
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
        loading = { Box(Modifier.fillMaxSize().background(GsSurface)) },
        error = { FallbackTile(Modifier.fillMaxSize()) },
    )
}

@Composable
private fun FallbackTile(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(GsSurface),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.BrokenImage,
                contentDescription = null,
                tint = GsFallbackStroke,
                modifier = Modifier.size(32.dp),
            )
            Text(
                text = "IMAGE UNAVAILABLE",
                color = GsFallbackLabel,
                fontSize = 8.sp,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

/** Blank URL renders the fallback tile — the deterministic missing/broken-image state. */
@Preview(widthDp = 200, heightDp = 160)
@Composable
private fun ProductImageFallbackPreview() {
    GymsharkShopTheme {
        ProductImage(imageUrl = "", modifier = Modifier.size(200.dp, 160.dp))
    }
}
