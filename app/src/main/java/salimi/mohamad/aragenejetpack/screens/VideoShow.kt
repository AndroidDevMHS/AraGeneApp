@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.codegames.aparatview.AparatView
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.login.CheckConnectivityStatus
import salimi.mohamad.aragenejetpack.viewModel.UrlState
import salimi.mohamad.aragenejetpack.viewModel.VideoUrlViewModel


@Composable
fun VideoShow(
    navController: NavController,
    viewModel: VideoUrlViewModel
) {
    val context = LocalContext.current
    val videoU by viewModel.videoState.collectAsState()

    CheckConnectivityStatus {
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        viewModel.sendRequest()
    }


    var selectedVideoUrl by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }



    when (val state = videoU) {

        is UrlState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(80.dp))
            }
        }

        is UrlState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "خطا در دریافت اطلاعات\nلطفا اینترنت خود را چک کنید",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = colorResource(R.color.royal_red),
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    style = TextStyle(textDirection = TextDirection.Rtl)
                )
            }
        }

        is UrlState.Success -> {
            val videoUrls = state.videos.map { it.link }
            val txtButton = state.videos.map { it.title }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // تعداد ستون‌ها را به ۲ تنظیم می‌کنیم
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                Log.e("3030", videoUrls.toString())
                itemsIndexed(videoUrls) { index, link ->
                    ButtonShow(painterResource(R.drawable.video_player), txtButton[index]) {
                        selectedVideoUrl = link
                        showDialog = true
                    }
                }
            }
        }
    }

    if (showDialog && selectedVideoUrl != null) {
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        AparatWebView(videoUrl = selectedVideoUrl!!) { showDialog = false }}
    }
}


@Composable
fun ButtonShow(image: Painter, text: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .padding(10.dp)
            .height(130.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = true)
            .clip(RoundedCornerShape(12.dp)),

        ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = image,
                contentDescription = "superMix"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = text,
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
            )
        }
    }
}

@Composable
fun VideoDialog(videoUrl: String, onDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onDismissRequest = onDismiss,
        title = {},
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f / 3f)
            ) {
                VideoPlayer(videoUrl = videoUrl)
            }
        },
        confirmButton = {
            OutlinedButton(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "بستن ویدیو",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 20.sp,
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        }
    )
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    DisposableEffect(context) {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
            prepare()
            playWhenReady = true
        }

        onDispose {
            exoPlayer?.release()
        }
    }

    exoPlayer?.let { player ->
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    this.player = player
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun AparatWebView(videoUrl: String, onBackPress: () -> Unit) {
    BackHandler {
        onBackPress()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                AparatView(context).apply {
                    this.videoId = videoUrl
                    this.videoRatio = "16:9"
                    this.isFullScreen = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        // دکمه بازگشت
        IconButton(
            onClick = onBackPress,
            modifier = Modifier
                .width(150.dp)
                .align(Alignment.TopStart)
                .padding(top = 10.dp)

        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "بازگشت",
                    tint = Color.White
                )
                Text("بازگشت به صفحه قبل", color = Color.White)
            }
        }
    }
}
