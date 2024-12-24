package salimi.mohamad.aragenejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens


@Composable
fun Home(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                ),
                //elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp)
                    .padding(top = 30.dp)
                    .shadow(
                        0.dp,
                        shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
                        clip = true
                    )
            ) {
                Text(
                    text = "دستورالعمل ها",
                    modifier = Modifier
                        .fillMaxWidth()
                        // .background(color = MaterialTheme.colorScheme.primary)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    style = TextStyle(
                        textDirection = TextDirection.Rtl,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.sans_bold))
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                    //.background(
                    //   color = MaterialTheme.colorScheme.primary,
                    // )
                    ,
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start = 20.dp, end = 5.dp, bottom = 8.dp)
                    ) {
                        MainScreenItemsRe(
                            painterResource(R.drawable.super_mix),
                            "سوپر میکس",
                            Screens.SuperMix.route,
                            navController
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(end = 20.dp, start = 5.dp, bottom = 8.dp)
                    ) {
                        MainScreenItemsRe(
                            painterResource(R.drawable.hormon),
                            "پک همزمان سازی",
                            Screens.FahliMainScreen.route,
                            navController
                        )
                    }


                }
            }

            MainScreenItemNo(
                image = painterResource(R.drawable.planner),
                text = "چک لیست همزمان سازی",
                navController = navController,
                page = Screens.FahliCheckBox.route
            )
            MainScreenItemNo(
                image = painterResource(R.drawable.video_player),
                text = "ویدیو آموزشی",
                navController = navController,
                page = Screens.VideoShow.route
            )
            MainScreenItemNo(
                image = painterResource(R.drawable.newspaper),
                text = "مقالات کاربردی",
                navController = navController,
                page = Screens.Article.route
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun MainScreenItemsRe(image: Painter, text: String, page: String, navController: NavController) {
    val gradientBox = Brush.horizontalGradient(
        colors = listOf(
            colorResource(
                R.color.blue_logo
            ),
            colorResource(R.color.blue2_logo)
        )

    )
    Card(
        onClick = { navController.navigate(page) },
        modifier = Modifier
            .size(170.dp)
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
                modifier = Modifier.size(80.dp),
                painter = image,
                contentDescription = "superMix"
            )
            Text(
                text = text,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily(Font(R.font.sans_bold))
            )
        }

    }


}

@Composable
fun MainScreenItemNo(image: Painter, text: String, page: String, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .fillMaxWidth()
            .height(140.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp), clip = true),
        onClick = { navController.navigate(page) }

    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = image,
                contentDescription = "superMix"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily(Font(R.font.sans_bold))
            )
        }
    }
}