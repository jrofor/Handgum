package com.example.roman.handgum.feature.revdetails.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.roman.handgum.feature.revdetails.R
import com.example.roman.handgum.feature.revdetails.RevDetailsViewModel

@Preview
@Composable
private fun MovieDetailsViewPreview() {
    RevDetailsView(
        showProgress = false,
        urlLink = "",
        displayTitle = "The Pope's Exorcist",
        mpaaRating = "R",
        publicationDate = "2023-04-14",
        headline = "‘The Pope’s Exorcist’ Review: A Head-Spinning Genre Mash-Up",
        summaryShort = "The buddy-priest action-comedy-horror hybrid we didn’t know we wanted has finally landed.",
        src = "https://static01.nyt.com/images/2023/04/15/multimedia/14popes-exorcist-review-ktfz/14popes-exorcist-review-ktfz-mediumThreeByTwo440.jpg",
        onBackClick = {},
        onWebClick = {},
        byline = "Elisabeth Vincentelli",
    )
}

@Composable
internal fun RevDetailsView(
    viewModel: RevDetailsViewModel
) {
    val state by viewModel.liveState.observeAsState(viewModel.createInitialState())

    RevDetailsView(
        showProgress = state.showProgress,
        urlLink = state.urlLink,
        src = state.src,
        displayTitle = state.displayTitle,
        mpaaRating = state.mpaaRating,
        byline = state.byline,
        publicationDate = state.publicationDate,
        headline = state.headline,
        summaryShort = state.summaryShort,
        onBackClick = viewModel::onBackPressed,
        onWebClick = viewModel::onWebPressed,
    )
}

@Composable
private fun RevDetailsView(
    showProgress: Boolean,
    urlLink: String,
    displayTitle: String,
    mpaaRating: String,
    byline: String,
    publicationDate: String,
    headline: String,
    summaryShort: String,
    src: String,
    onBackClick: () -> Unit,
    onWebClick: (urlLink: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.colorBackground))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        RevDetailsTopAppBar(
            onBackClick = onBackClick,
            urlLink = urlLink,
            displayTitle = displayTitle,
            onWebClick = onWebClick
        )
        if (showProgress) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            RevDetails(
                displayTitle,
                mpaaRating,
                byline,
                publicationDate,
                headline,
                summaryShort,
                src,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RevDetailsTopAppBar(
    onBackClick: () -> Unit,
    urlLink: String,
    displayTitle: String,
    onWebClick: (urlLink: String) -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = displayTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(onClick = { onWebClick(urlLink) }) {
                Icon(
                    imageVector = Icons.Filled.TravelExplore,
                    contentDescription = ""
                )
            }
        },
    )
}

@Composable
private fun RevDetails(
    displayTitle: String,
    mpaaRating: String,
    byline: String,
    publicationDate: String,
    headline: String,
    summaryShort: String,
    src: String,
) {
    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.space_16)),
        verticalArrangement = spacedBy(dimensionResource(id = R.dimen.space_8))
    ) {
        Text(
            text = displayTitle,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        AnimatedVisibility(visible = mpaaRating.isNotBlank()) {
            Text(
                text = stringResource(id = R.string.fragment_rev_details_mpaa_title, mpaaRating),
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.space_16)),
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = src,
            contentDescription = stringResource(id = R.string.fragment_rev_details_image_content_description),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .wrapContentWidth()
                .height(dimensionResource(id = R.dimen.multi_media_height))
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.space_16)),
        verticalArrangement = spacedBy(dimensionResource(id = R.dimen.space_16))
    ) {
        Text(
            text = headline,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = summaryShort,
            fontSize = 14.sp,
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.space_16)),
        horizontalArrangement = spacedBy(dimensionResource(id = R.dimen.space_8)),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = publicationDate,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
        )
        Icon(
            painter = painterResource(R.drawable.ic_outline_article_24),
            contentDescription = byline,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.ic_article_width))
        )
        Text(
            text = stringResource(id = R.string.fragment_rev_details_byline_title, byline),
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
        )
    }
}

