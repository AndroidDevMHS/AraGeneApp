package salimi.mohamad.aragenejetpack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.viewModel.VideoUrlViewModel


@Composable
fun ArticleTxtShow(viewModel: VideoUrlViewModel) {

    val article by viewModel.selectedArticle.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 16.dp, end = 16.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = article?.title ?: "بدون عنوان",
                    textAlign = TextAlign.Center,
                    lineHeight = 28.sp,
                    style = TextStyle(textDirection = TextDirection.Rtl),
                    color = colorResource(id = R.color.blue_logo),
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = article?.text ?: "متنی برای نمایش وجود ندارد",
                textAlign = TextAlign.Justify,
                lineHeight = 28.sp,
                style = TextStyle(textDirection = TextDirection.Rtl),
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontSize = 18.sp,
            )
        }
    }
}