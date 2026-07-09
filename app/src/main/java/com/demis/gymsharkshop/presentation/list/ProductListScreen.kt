package com.demis.gymsharkshop.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demis.gymsharkshop.domain.ProductLabel
import com.demis.gymsharkshop.presentation.components.LabelBadge
import com.demis.gymsharkshop.presentation.components.ProductImage
import com.demis.gymsharkshop.presentation.model.ProductUi
import com.demis.gymsharkshop.presentation.theme.Archivo
import com.demis.gymsharkshop.presentation.theme.GsBlack
import com.demis.gymsharkshop.presentation.theme.GsBorder
import com.demis.gymsharkshop.presentation.theme.GsLime
import com.demis.gymsharkshop.presentation.theme.GsText
import com.demis.gymsharkshop.presentation.theme.GsTextDim
import com.demis.gymsharkshop.presentation.theme.GymsharkShopTheme

@Composable
fun ProductListScreen(
    onProductClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductListContent(
        state = state,
        onProductClick = onProductClick,
        onRetry = viewModel::load,
        modifier = modifier,
    )
}

@Composable
fun ProductListContent(
    state: ProductListUiState,
    onProductClick: (Long) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
            .background(GsBlack)
    ) {
        Header(
            subtitle = when (state) {
                is ProductListUiState.Content -> "NEW IN · ${state.items.size} ITEMS"
                else -> "NEW IN"
            },
        )
        when (state) {
            ProductListUiState.Loading -> CenterBox { CircularProgressIndicator(color = GsLime) }
            is ProductListUiState.Content -> ProductGrid(state.items, onProductClick)
            ProductListUiState.Empty -> CenterMessage("No products available right now.", onRetry)
            is ProductListUiState.Error -> CenterMessage(state.message, onRetry)
        }
    }
}

@Composable
private fun Header(subtitle: String) {
    Column {
        Text(
            "GYMSHARK",
            color = GsText,
            fontFamily = Archivo,
            fontWeight = FontWeight.Black,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            letterSpacing = (0.5).sp,
            maxLines = 1,
            modifier = Modifier.padding(20.dp, 16.dp, 20.dp, 14.dp),
        )
        Text(
            subtitle,
            color = GsTextDim,
            fontFamily = Archivo,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 11.sp,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 6.dp),
        )
    }
}

@Composable
private fun ProductGrid(items: List<ProductUi>, onProductClick: (Long) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp, 4.dp, 20.dp, 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items, key = { it.id }) { product ->
            ProductCard(product) { onProductClick(product.id) }
        }
    }
}

@Composable
private fun ProductCard(product: ProductUi, onClick: () -> Unit) {
    Column(Modifier.clickable(onClick = onClick)) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3.2f)
                .clip(RoundedCornerShape(14.dp))
                .border(1.dp, GsBorder, RoundedCornerShape(14.dp)),
        ) {
            ProductImage(product.imageUrl, Modifier.fillMaxSize())
            if (product.labels.isNotEmpty()) {
                FlowRow(
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(9.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    product.labels.forEach { LabelBadge(it) }
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Column(Modifier.padding(end = 12.dp)) {
                Text(
                    product.name, color = GsText, fontFamily = Archivo,
                    fontWeight = FontWeight.Bold, fontSize = 14.sp,
                )
                Text(
                    product.colour, color = GsTextDim, fontFamily = Archivo,
                    fontWeight = FontWeight.Medium, fontSize = 11.sp,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
            Text(
                product.priceLabel, color = GsLime, fontFamily = Archivo,
                fontWeight = FontWeight.ExtraBold, fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun CenterBox(content: @Composable () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { content() }
}

@Composable
private fun CenterMessage(message: String, onRetry: () -> Unit) {
    CenterBox {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                message, color = GsTextDim, fontFamily = Archivo,
                fontWeight = FontWeight.Medium, fontSize = 14.sp, textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp),
            )
            Text(
                "RETRY", color = GsLime, fontFamily = Archivo,
                fontWeight = FontWeight.ExtraBold, fontSize = 12.sp, letterSpacing = 2.sp,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, GsLime, RoundedCornerShape(10.dp))
                    .clickable(onClick = onRetry)
                    .padding(horizontal = 22.dp, vertical = 11.dp),
            )
        }
    }
}

internal val sampleProductsUi = listOf(
    ProductUi(
        id = 1, name = "Vital Seamless Leggings", priceLabel = "£45.00", colour = "Black",
        imageUrl = "", images = emptyList(),
        labels = listOf(ProductLabel.GOING_FAST, ProductLabel.RECYCLED_NYLON),
        descriptionHtml = "<b>Sweat-wicking</b> and squat-proof.",
    ),
    ProductUi(
        id = 2, name = "Power Hoodie", priceLabel = "£60.00", colour = "Onyx Grey",
        imageUrl = "", images = emptyList(), labels = listOf(ProductLabel.NEW),
        descriptionHtml = "Relaxed fit.",
    ),
    ProductUi(
        id = 3, name = "Everyday Tee", priceLabel = "£22.00", colour = "White",
        imageUrl = "", images = emptyList(), labels = emptyList(),
        descriptionHtml = "Soft cotton.",
    ),
)

@Preview(widthDp = 380, heightDp = 820)
@Composable
private fun ProductListContentPreview() {
    GymsharkShopTheme {
        ProductListContent(
            state = ProductListUiState.Content(sampleProductsUi),
            onProductClick = {}, onRetry = {},
        )
    }
}

@Preview(widthDp = 380, heightDp = 360)
@Composable
private fun ProductListErrorPreview() {
    GymsharkShopTheme {
        ProductListContent(
            state = ProductListUiState.Error("No internet connection.\nCheck your network and try again."),
            onProductClick = {}, onRetry = {},
        )
    }
}

@Preview(widthDp = 380, heightDp = 360)
@Composable
private fun ProductListLoadingPreview() {
    GymsharkShopTheme {
        ProductListContent(state = ProductListUiState.Loading, onProductClick = {}, onRetry = {})
    }
}