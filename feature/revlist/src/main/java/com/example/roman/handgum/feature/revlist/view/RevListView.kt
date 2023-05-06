package com.example.roman.handgum.feature.revlist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.roman.handgum.commonentity.ui.models.ReviewModel
import com.example.roman.handgum.feature.revlist.RevListViewModel
import com.example.roman.handgum.feature.revlist.R

private const val GRID_CELLS_FIXED_COUNT = 2

@Preview(
    showSystemUi = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun MoviesViewPreview() {
    RevListView(
        showProgress = false,
        reviews = mutableListOf<ReviewModel>().apply {
            add(
                ReviewModel(
                    displayTitle = "Big George Foreman: The Miraculous Story of the Once and Future Heavyweight Champion of the World",
                    mpaaRating = "PG-13",
                    publicationDate = "2023-04-27",
                    headline = "‘Big George Foreman’ Review: Not the Biopic a Two-time Champ Deserves",
                    summaryShort = "The fictionalized film, with the boxer himself as one of its executive producers, crams a lot of events into its running time, leaving its charismatic cast on the ropes.",
                    src = "https://static01.nyt.com/images/2023/04/27/multimedia/27big-george-foreman-review-lkhz/27big-george-foreman-review-lkhz-mediumThreeByTwo440.jpg",
                    byline = "Glenn Kenny",
                )
            )
        },
        missingDataNotice = false,
        onReviewClick = {},
        onRefreshClick = {},
    )
}

@Composable
internal fun RevListView(
    viewModel: RevListViewModel,
) {
    val state by viewModel.liveState.observeAsState(viewModel.createInitialState())

    RevListView(
        showProgress = state.showProgress,
        reviews = state.reviews,
        missingDataNotice = state.missingDataNotice,
        onReviewClick = viewModel::onReviewPressed,
        onRefreshClick = viewModel::onRefresh
    )
}

@Composable
private fun RevListView(
    showProgress: Boolean,
    reviews: List<ReviewModel>,
    missingDataNotice: Boolean,
    onReviewClick: (url: String) -> Unit,
    onRefreshClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (missingDataNotice) {
            SwipeRefresh(showProgress, onRefreshClick) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(id = R.dimen.space_16)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.fragment_rev_list_err_data_is_empty),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        } else {
            SwipeRefresh(showProgress, onRefreshClick) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = GRID_CELLS_FIXED_COUNT),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(reviews) { reviews ->
                        ReviewView(
                            review = reviews,
                            onClick = onReviewClick,
                            modifier = Modifier.padding(12.dp),
                        )
                    }
                }
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeRefresh(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    var isRefreshing by remember { mutableStateOf(refreshing) }
    LaunchedEffect(key1 = refreshing) {
        isRefreshing = refreshing
    }
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    Box(
        modifier = Modifier.pullRefresh(pullRefreshState),
    ) {
        content.invoke()
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = isRefreshing,
            state = pullRefreshState,
        )
    }
}

@Composable
fun ReviewView(review: ReviewModel, onClick: (url: String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.space_4)))
            .clickable(onClick = { onClick(review.url) })
    ) {
        AsyncImage(
            model = review.src,
            contentDescription = stringResource(id = R.string.fragment_rev_list_image_content_description),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = review.displayTitle.uppercase(),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x00000000),
                            Color(0x20000000),
                        ),
                    ),
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen.space_8),
                    vertical = dimensionResource(id = R.dimen.space_4),
                ),
        )
    }
}
