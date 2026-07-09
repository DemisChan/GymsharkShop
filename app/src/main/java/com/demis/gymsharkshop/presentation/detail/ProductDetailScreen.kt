package com.demis.gymsharkshop.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.demis.gymsharkshop.domain.ProductLabel
import com.demis.gymsharkshop.presentation.components.HtmlText
import com.demis.gymsharkshop.presentation.components.LabelBadge
import com.demis.gymsharkshop.presentation.components.ProductImage
import com.demis.gymsharkshop.presentation.model.ProductUi
import com.demis.gymsharkshop.presentation.theme.Archivo
import com.demis.gymsharkshop.presentation.theme.GsBlack
import com.demis.gymsharkshop.presentation.theme.GsLime
import com.demis.gymsharkshop.presentation.theme.GsText
import com.demis.gymsharkshop.presentation.theme.GsTextDim
import com.demis.gymsharkshop.presentation.theme.GsTextMuted
import com.demis.gymsharkshop.presentation.theme.GymsharkShopTheme
import com.demis.gymsharkshop.presentation.theme.SpaceGrotesk

@Composable
fun ProductDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    ProductDetailContent(
        state = viewModel.state,
        onBack = onBack,
        modifier = modifier,
    )
}

@Composable
fun ProductDetailContent(
    state: ProductDetailUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier
        .fillMaxSize()
        .background(GsBlack)) {
        when (state) {
            is ProductDetailUiState.Content -> ProductDetail(state.product, onBack)
            ProductDetailUiState.NotFound -> NotFound(onBack)
        }
    }
}

@Composable
private fun ProductDetail(product: ProductUi, onBack: () -> Unit) {
    Column(Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Box(Modifier
            .fillMaxWidth()
            .aspectRatio(4f / 4.2f)) {
            ImageCarousel(
                images = product.images.ifEmpty { listOf(product.imageUrl) },
                modifier = Modifier.fillMaxSize(),
            )
            BackButton(onBack, Modifier
                .statusBarsPadding()
                .padding(14.dp))
        }
        Column(Modifier.padding(20.dp, 20.dp, 20.dp, 40.dp)) {
            if (product.labels.isNotEmpty()) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(bottom = 12.dp),
                ) {
                    product.labels.forEach { LabelBadge(it) }
                }
            }
            Text(
                product.name, color = GsText, fontFamily = Archivo,
                fontWeight = FontWeight.Black, fontStyle = FontStyle.Italic,
                fontSize = 26.sp, lineHeight = 25.sp, letterSpacing = (-0.5).sp,
                modifier = Modifier.padding(bottom = 8.dp),
            )
            Text(
                product.colour, color = GsTextDim, fontFamily = Archivo,
                fontWeight = FontWeight.Medium, fontSize = 12.sp, letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                product.priceLabel, color = GsText, fontFamily = Archivo,
                fontWeight = FontWeight.ExtraBold, fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 22.dp),
            )
            HtmlText(
                html = product.descriptionHtml,
                color = GsTextMuted,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = SpaceGrotesk, fontSize = 13.sp, lineHeight = 21.sp,
                ),
            )
        }
    }
}

/**
 * Swipeable gallery over the product's media images, with page-dot indicators.
 * A single image (or the featured fallback) renders as one page with no dots.
 */
@Composable
private fun ImageCarousel(images: List<String>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Box(modifier) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            ProductImage(images[page], Modifier.fillMaxSize())
        }
        if (images.size > 1) {
            Row(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                repeat(images.size) { index ->
                    val selected = index == pagerState.currentPage
                    Box(
                        Modifier
                            .size(if (selected) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(if (selected) GsLime else GsText.copy(alpha = 0.4f)),
                    )
                }
            }
        }
    }
}

@Composable
private fun BackButton(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(GsBlack.copy(alpha = 0.6f))
            .clickable(onClick = onBack),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Back",
            tint = GsText, modifier = Modifier.size(22.dp),
        )
    }
}

@Composable
private fun NotFound(onBack: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "This product couldn't be loaded.", color = GsTextDim, fontFamily = Archivo,
            fontWeight = FontWeight.Medium, fontSize = 14.sp, textAlign = TextAlign.Center,
        )
        Text(
            "GO BACK", color = GsLime, fontFamily = Archivo,
            fontWeight = FontWeight.ExtraBold, fontSize = 12.sp, letterSpacing = 2.sp,
            modifier = Modifier
                .padding(top = 18.dp)
                .clickable(onClick = onBack),
        )
    }
}

private val sampleProductUi = ProductUi(
    id = 1, name = "Vital Seamless Leggings", priceLabel = "£45.00", colour = "Black",
    imageUrl = "", images = listOf("", "", ""),
    labels = listOf(ProductLabel.GOING_FAST, ProductLabel.RECYCLED_NYLON),
    descriptionHtml = "<p><b>Sweat-wicking</b>, squat-proof leggings built for training.</p>" +
            "<ul><li>Four-way stretch</li><li>High-waisted</li></ul>",
)

@Preview(widthDp = 380, heightDp = 900)
@Composable
private fun ProductDetailContentPreview() {
    GymsharkShopTheme {
        ProductDetailContent(
            state = ProductDetailUiState.Content(sampleProductUi),
            onBack = {},
        )
    }
}

@Preview(widthDp = 380, heightDp = 400)
@Composable
private fun ProductDetailNotFoundPreview() {
    GymsharkShopTheme {
        ProductDetailContent(state = ProductDetailUiState.NotFound, onBack = {})
    }
}