package salimi.mohamad.aragenejetpack.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens


@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun Home(navController: NavController) {
    //val navController = rememberNavController()
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    DisposableEffect(context) {
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp), // فاصله‌ی سکشن‌ها,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // سوپرمیکس
            SectionWithCards(
                title = "سوپرمیکس",
                backgroundColor =colorResource(R.color.blue_logo).copy(alpha = 0.4f),

                cardItems = listOf(
                    CardItem(
                        painter = painterResource(R.drawable.feed_blue2),
                        text = "جیره روزانه",
                        route = Screens.SuperMixCalculator.route
                    ),
                    CardItem(
                        painter = painterResource(R.drawable.supermix_yellom_blue),
                        text = "دستورالعمل",
                        route = Screens.SuperMix.route
                    )
                ),
                navController = navController
            )
            Spacer(modifier = Modifier.height(55.dp))
            // همزمان‌سازی
            SectionWithCards(
                title = "همزمان‌سازی",
                backgroundColor =colorResource(R.color.blue2_logo).copy(alpha = 0.4f),

                cardItems = listOf(
                    CardItem(
                        painter = painterResource(R.drawable.checklist_f),
                        text = "چک لیست",
                        route = Screens.FahliCheckBox.route
                    ),
                    CardItem(
                        painter = painterResource(R.drawable.ampoule_by),
                        text = "دستورالعمل",
                        route = Screens.FahliMainScreen.route
                    )
                ),
                navController = navController
            )

            Spacer(modifier = Modifier.height(55.dp))
            SectionWithCards(
                title = "آموزش",
                backgroundColor = colorResource(R.color.sunset).copy(alpha = 0.6f),
                cardItems = listOf(
                    CardItem(
                        painter = painterResource(R.drawable.news_report_by),
                        text = "مقالات",
                        route = Screens.Article.route
                    ),
                    CardItem(
                        painter = painterResource(R.drawable.video),
                        text = "ویدئو",
                        route = Screens.VideoShow.route
                    )
                ),
                navController = navController
            )
        }
    }
}

@Composable
fun SectionWithCards(
    title: String,
    backgroundColor: Color,
    cardItems: List<CardItem>,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 10.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 16.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                color = Color.Black,
                textDirection = TextDirection.Rtl,
                textAlign = TextAlign.Start
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (50).dp)
                .zIndex(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            cardItems.forEach { item ->
                MainScreenItemsRe(
                    image = item.painter,
                    text = item.text,
                    page = item.route,
                    navController = navController
                )
            }

        }
    }
}

data class CardItem(
    val painter: Painter,
    val text: String,
    val route: String
)

@Composable
fun MainScreenItemsRe(
    image: Painter,
    text: String,
    page: String,
    navController: NavController
) {
    Card(
        onClick = { navController.navigate(page) },
        modifier = Modifier
            .width(170.dp)
            .height(140.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(30.dp), clip = true)
            .clip(RoundedCornerShape(30.dp)),
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(85.dp).padding(top=5.dp),
                painter = image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                textAlign = TextAlign.Center
            )
        }
    }
}
