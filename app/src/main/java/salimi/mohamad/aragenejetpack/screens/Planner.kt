package salimi.mohamad.aragenejetpack.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.PlannerItem
import salimi.mohamad.aragenejetpack.viewModel.PlannerViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun Planner(navController: NavController, viewModel: PlannerViewModel) {
    var itemsList by remember { mutableStateOf(emptyList<PlannerItem>()) }
    var hour by remember { mutableIntStateOf(0) }
    val gradientBox = Brush.horizontalGradient(
        colors = listOf(
            colorResource(
                R.color.blue_logo
            ),
            colorResource(R.color.blue2_logo)
        )

    )
    LaunchedEffect(key1 = true) {
        viewModel.allMessage.collectLatest { item ->
            itemsList = item
        }
    }
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH)

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        itemsIndexed(itemsList) { index, item ->
            val now = Calendar.getInstance().time
            val itemDate = dateFormat.parse(item.time)
            val diff = itemDate.time - now.time

            if (diff> 0) {
                val diffInSeconds = diff / 1000
                val diffInMinutes = diffInSeconds / 60
                hour=(diffInMinutes/60).toInt()

            }
            Log.e("3636", "item$diff")
            if (now.before(itemDate)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // خط قبل از تاریخ
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                        color = colorResource(R.color.grey_logo)
                    )

                    // متن تاریخ وسط خط
                    Text(
                        text = "تا $hour ساعت دیگر ",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(R.color.blue_logo),
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                    )

                    // خط بعد از تاریخ
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 1.dp,
                        color = colorResource(R.color.grey_logo)
                    )
                }
                ShowPlan(item.message)
            } else {
                viewModel.deleteGroup(item)
            }
        }
    }
}


@Composable
fun ShowPlan(text: String) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(12.dp), clip = true),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.light_blue).copy(0.7f),//Color.Black.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp)
                    ),
            ) {
                Text(
                    text = "اقدام مورد نیاز",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.sans_bold)),
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.sans_bold)),
                        fontSize = 18.sp
                    )
                )
            }

        }
    }
}