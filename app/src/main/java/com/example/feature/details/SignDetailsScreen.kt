package com.example.feature.details

import android.net.Uri
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.designsystem.component.TiflyBackground
import com.example.core.designsystem.icon.TiflyIcons
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.designsystem.theme.TiflyTypography
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.MappingTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionOverrides
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
internal fun SignDetailsRoute(
    signId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignDetailsViewModel = hiltViewModel(),
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SignDetailsScreen(
        signDetailUiState = state,
        modifier = modifier,
        onBackClick = onBackClick
    )

}


@VisibleForTesting
@Composable
internal fun SignDetailsScreen(
    onBackClick: () -> Unit,
    signDetailUiState: SignDetailUiState,
    modifier: Modifier,
) {
    SignDetailsBody(
        modifier = modifier,
        signDetailUiState = signDetailUiState,
        onBackClick = onBackClick
    )
}


@Composable
fun SignDetailsBody(
    onBackClick: () -> Unit,
    signDetailUiState: SignDetailUiState,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.padding(start = 25.dp , end  = 25.dp),
    ) {

        SignDetailHeader(
            onBackClick
        )
        SignContent( modifier = modifier, signDetailUiState = signDetailUiState)
        when (signDetailUiState ) {
            is SignDetailUiState.Success -> {
                signDetailUiState.signs[0].gif?.let { Video(it, modifier) }
            }
            else -> {}
        }

    }
}


@Composable
fun Video(
    url: String,
    modifier : Modifier
) {

    val low_speed = 0.5f
    val normal_speed = 1f
    var playbackSpeed by remember { mutableStateOf(normal_speed) }


    Column {

        OutlinedCard(
            modifier = modifier
                .height(390.dp)
                .fillMaxSize()
        ) {



            val context = LocalContext.current
            val trackSelector = DefaultTrackSelector(context, AdaptiveTrackSelection.Factory())

            val url = url
            val exoPlayer = ExoPlayer.Builder(context).setTrackSelector(trackSelector).build()
            exoPlayer.playWhenReady= true

            val mediaItem = MediaItem.fromUri(Uri.parse(url))
            exoPlayer.setMediaItem(mediaItem)
            val playerView = StyledPlayerView(context)
            playerView.player = exoPlayer
            playerView.apply {
                this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
            }




            DisposableEffect(
                AndroidView(
                    modifier = modifier.height(390.dp),
                    factory = { playerView }
                )
            ) {


                exoPlayer.prepare()
                playerView.hideController()
                exoPlayer.setPlaybackSpeed(playbackSpeed)

                exoPlayer.playWhenReady= true

                onDispose {
                    exoPlayer.release()
                    exoPlayer.repeatMode
                }
            }
        }



        
    }




}

@Composable
fun SignDetailHeader(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = modifier.clickable { onBackClick() },
            imageVector = TiflyIcons.Close,
            contentDescription = "d",
        )
        Divider(
            modifier = modifier.padding(top = 15.dp),
            color = Color.LightGray,
            thickness = 0.5.dp
        )

    }
}

@Composable
fun SignContent(
    signDetailUiState: SignDetailUiState,
    modifier: Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Row(
            modifier = Modifier.padding(bottom = 10.dp),
        ) {

            Text (
                text = "Comment dire ",
                style = TiflyTypography.headlineSmall
            )

            when ( signDetailUiState ) {
                is SignDetailUiState.Success -> {
                    Log.d("Mosab", signDetailUiState.signs[0].mot_fr.toString())
                    Text (
                        text = signDetailUiState.signs[0].mot_fr.toString(),
                        style = TiflyTypography.headlineSmall
                    )
                }
                is SignDetailUiState.Loading -> {}
                is SignDetailUiState.Error -> {}
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            when ( signDetailUiState ) {
                is SignDetailUiState.Success -> {
                    Text (
                        text = signDetailUiState.signs[0].mot_ar.toString()+" ",
                        style = TiflyTypography.headlineSmall
                    )
                }
                is SignDetailUiState.Loading -> {}
                is SignDetailUiState.Error -> {}
            }

            Text (
                text = "كيفاش نقول"+" ",
                style = TiflyTypography.headlineSmall
            )
        }
    }
}


@Preview
@Composable
fun PreviewSignDetailScreen() {
    TiflyTheme {
        TiflyBackground  {
            SignDetailsRoute("1", {})
        }
    }

}

fun DefaultTrackSelector.generateQualityList(): ArrayList<Pair<String, TrackSelectionOverrides.Builder>> {
    //Render Track -> TRACK GROUPS (Track Array)(Video,Audio,Text)->Track
    val trackOverrideList = ArrayList<Pair<String, TrackSelectionOverrides.Builder>>()

    val renderTrack = this.currentMappedTrackInfo
    val renderCount = renderTrack?.rendererCount ?: 0
    for (rendererIndex in 0 until renderCount) {
        if (isSupportedFormat(renderTrack, rendererIndex)) {
            val trackGroupType = renderTrack?.getRendererType(rendererIndex)
            val trackGroups = renderTrack?.getTrackGroups(rendererIndex)
            val trackGroupsCount = trackGroups?.length!!
            if (trackGroupType == C.TRACK_TYPE_VIDEO) {
                for (groupIndex in 0 until trackGroupsCount) {
                    val videoQualityTrackCount = trackGroups[groupIndex].length
                    for (trackIndex in 0 until videoQualityTrackCount) {
                        val isTrackSupported = renderTrack.getTrackSupport(
                            rendererIndex,
                            groupIndex,
                            trackIndex
                        ) == C.FORMAT_HANDLED
                        if (isTrackSupported) {
                            val track = trackGroups[groupIndex]
                            val trackName =
                                "${track.getFormat(trackIndex).width} x ${track.getFormat(trackIndex).height}"
                            if (track.getFormat(trackIndex).selectionFlags==C.SELECTION_FLAG_AUTOSELECT){
                                trackName.plus(" (Default)")
                            }
                            val trackBuilder =
                                TrackSelectionOverrides.Builder()
                                    .clearOverridesOfType(C.TRACK_TYPE_VIDEO)
                                    .addOverride(TrackSelectionOverrides.TrackSelectionOverride(track,
                                        listOf(trackIndex)))
                            trackOverrideList.add(Pair(trackName, trackBuilder))
                        }
                    }
                }
            }
        }
    }
    return trackOverrideList
}

fun isSupportedFormat(mappedTrackInfo: MappingTrackSelector.MappedTrackInfo?, rendererIndex: Int): Boolean {
    val trackGroupArray = mappedTrackInfo?.getTrackGroups(rendererIndex)
    return if (trackGroupArray?.length == 0) {
        false
    } else mappedTrackInfo?.getRendererType(rendererIndex) == C.TRACK_TYPE_VIDEO || mappedTrackInfo?.getRendererType(
        rendererIndex
    ) == C.TRACK_TYPE_AUDIO || mappedTrackInfo?.getRendererType(rendererIndex) == C.TRACK_TYPE_TEXT
}

