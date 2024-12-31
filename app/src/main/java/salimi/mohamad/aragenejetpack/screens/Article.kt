package salimi.mohamad.aragenejetpack.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.Sheet2
import salimi.mohamad.aragenejetpack.screens.login.CheckConnectivityStatus
import salimi.mohamad.aragenejetpack.viewModel.ArticleState
import salimi.mohamad.aragenejetpack.viewModel.VideoUrlViewModel

@Composable
fun Article(navController: NavController, viewModel: VideoUrlViewModel) {
    // Collecting articles state from ViewModel
    val articleState by viewModel.articleState.collectAsState()

    // Check network connectivity status (e.g., using a connectivity checker)
    CheckConnectivityStatus {
        navController.popBackStack()
    }

    // Fetch articles on initial composition
    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }

    // Handling different states of articles (loading, success, error)
    when (articleState) {
        is ArticleState.Loading -> {
            // Show loading indicator while fetching articles
            CircularProgressIndicator(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
        }

        is ArticleState.Success -> {
            // Display articles if fetching was successful
            val articles = (articleState as ArticleState.Success).articles
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(articles) { index, article ->
                    // Display each article as a clickable card
                    ArticleCard(article = article) {
                        ShowArticle(text = article.text) // This is correctly called within a composable
                    }
                }
            }
        }

        is ArticleState.Error -> {
            // Display error message if there was an error fetching articles
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
    // Card displaying article title and an icon (e.g., newspaper)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() } // Calling onClick in composable context
            .shadow(8.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Brush.linearGradient(listOf(Color.Gray, Color.White)))
        ) {
            // Icon for article
            Image(
                painter = painterResource(id = R.drawable.newspaper),
                contentDescription = "Newspaper Icon",
                modifier = Modifier.size(50.dp).padding(8.dp)
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.h66.copy(fontSize = 18.sp),
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ShowArticle(text: String) {
    // Displaying article content
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(
                text = text,
                textAlign = TextAlign.Justify,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontSize = 14.sp
            )
        }
    }
}