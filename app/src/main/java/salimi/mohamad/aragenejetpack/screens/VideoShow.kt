@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.login.CheckConnectivityStatus


@Composable
fun VideoShow(navController: NavController) {
    val context = LocalContext.current

    CheckConnectivityStatus{
        navController.popBackStack()
    }

    val videoUrls = listOf(
        "https://aragene.org/wp-content/uploads/2021/09/%D8%A7%D9%86%D8%AA%D8%AE%D8%A7%D8%A8-%D9%82%D9%88%DA%86_VP8.webm",
        "https://aragene.org/wp-content/uploads/2021/09/%D8%AC%D8%B1%D8%A7%D8%AD%DB%8C-%D9%84%D8%A7%D9%BE%D8%A7%D8%B1%D8%A7%D8%B3%DA%A9%D9%88%D9%BE%DB%8C_VP8.webm",
        "https://aragene.org/wp-content/uploads/2021/09/%DA%A9%D9%85%D8%A7%D9%84%DA%AF%D8%B1%D8%A7%DB%8C%DB%8C-%D8%AF%D8%A7%D9%85%D8%AF%D8%A7%D8%B1%D9%87%D8%A7_VP8.webm"
    )
    val txtButton = listOf("انتخاب قوچ", "تلقیح با لاپاراسکوپی", "کمالگرایی")
    var selectedVideoUrl by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    // دکمه‌ها برای انتخاب ویدیو
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // تعداد ستون‌ها را به ۲ تنظیم می‌کنیم
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        videoUrls.forEachIndexed { index, url ->
            item {
                ButtonShow(painterResource(R.drawable.video_player), txtButton[index]) {
                    selectedVideoUrl = url
                    showDialog = true
                }
            }
        }
    }

    // دیالوگ برای نمایش ویدیو
    if (showDialog && selectedVideoUrl != null) {
        VideoDialog(videoUrl = selectedVideoUrl!!) {
            showDialog = false
        }
    }
}


@Composable
fun ButtonShow(image: Painter, text: String, onClick: () -> Unit) {
    val gradientBox = Brush.horizontalGradient(
        colors = listOf(
            colorResource(
                R.color.blue_logo
            ),
            colorResource(R.color.blue2_logo)
        )

    )
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .padding(10.dp)
            .size(130.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = true)
            .clip(RoundedCornerShape(12.dp)),

        ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize()
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
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily(Font(R.font.sans_bold))
            )
        }

    }


}

@Composable
fun VideoDialog(videoUrl: String, onDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        onDismissRequest = onDismiss,
        title = {},
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4 / 4.5f) // نسبت ابعاد ویدیو (16:9)
                    .padding(5.dp) // کاهش فضای اطراف PlayerView
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

@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    DisposableEffect(context) {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
            prepare()
            playWhenReady = false
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
                    this.useController = true
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
        )
    }
}

/*

@Composable
fun VideoShow() {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("video")
        val videoUrl = "https://aragene.org/wp-content/uploads/2021/09/%D8%A7%D9%86%D8%AA%D8%AE%D8%A7%D8%A8-%D9%82%D9%88%DA%86_VP8.webm"
        VideoPlayer(videoUrl = videoUrl)
        VideoPlayerScreen(videoUrl)
    }
}

@Composable
fun VideoPlayer(videoUrl: String) {
    val context = LocalContext.current
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    DisposableEffect(context) {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            // بارگذاری آدرس ویدیو
            setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
            prepare()
            playWhenReady = false
        }

        onDispose {
            exoPlayer?.release()
        }
    }

    // نمایش ویدیو در PlayerView
    exoPlayer?.let { player ->
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    this.player = player
                    this.useController = true // نمایش کنترل‌های پخش (Play, Pause, Seek)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun VideoPlayerScreen(videoUrl: String) {
    val context = LocalContext.current
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    // ایجاد و راه‌اندازی ExoPlayer
    DisposableEffect(key1 = videoUrl) {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false // شروع پخش خودکار
        }

        onDispose {
            exoPlayer?.release() // آزادسازی منابع
        }
    }

    // نمایش PlayerView در Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                useController = true // نمایش کنترل‌های پخش
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}*/
