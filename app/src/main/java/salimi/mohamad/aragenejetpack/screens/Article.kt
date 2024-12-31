package salimi.mohamad.aragenejetpack.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import salimi.mohamad.aragenejetpack.data.model.Sheet2
import salimi.mohamad.aragenejetpack.screens.login.CheckConnectivityStatus
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.viewModel.ArticleState
import salimi.mohamad.aragenejetpack.viewModel.VideoUrlViewModel

@Composable
fun Article(navController: NavController, viewModel: VideoUrlViewModel) {

    val articleState by viewModel.articleState.collectAsState()

    CheckConnectivityStatus {
        navController.popBackStack()
    }


    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }


    when (articleState) {
        is ArticleState.Loading -> {
            // Show loading indicator while fetching articles
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        is ArticleState.Success -> {
            val articles = (articleState as ArticleState.Success).articles
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                itemsIndexed(articles) { index, article ->
                    Log.e("1372", article.text)
                    ArticleCard(article = article) {
                        viewModel.selectArticle(article)
                        navController.navigate(Screens.ArticleTxtShow.route)
                    }
                }
            }

        }

        is ArticleState.Error -> {
            val errorMessage = (articleState as ArticleState.Error).message
            Text(
                text = "خطا در دریافت مقالات: $errorMessage",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ArticleCard(article: Sheet2, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() }
            .shadow(6.dp, RoundedCornerShape(12.dp), clip = true),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.onSecondary)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon for article
            Image(
                painter = painterResource(id = R.drawable.newspaper),
                contentDescription = "Newspaper Icon",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            )
            Text(
                text = article.title,
                style = TextStyle(
                    textDirection = TextDirection.Rtl,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 18.sp
                ),
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
