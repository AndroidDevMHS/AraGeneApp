package salimi.mohamad.aragenejetpack.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.screens.login.CheckConnectivityStatus
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.utils.isInternetAvailable
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.PlannerViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//var isItemAdded = MutableStateFlow(false)
@Composable
fun Planner(
    navController: NavController,
    viewModelSms: SmsViewModel,
    viewModelPlanner: PlannerViewModel,
    viewModelDataStore: DataStoreViewModel,
    context: Context
) {
    val phoneNum by viewModelDataStore.userPhoneNumber.collectAsState(initial = "")
    var itemsList by remember { mutableStateOf(emptyList<PlannerItem>()) }
    var hour by remember { mutableIntStateOf(0) }
    var minute by remember { mutableIntStateOf(0) }


    LaunchedEffect(key1 = true) {
        viewModelPlanner.allMessage.collectLatest { item ->
            itemsList = item
        }
    }
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH)


    if (itemsList.isNotEmpty()) {

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            itemsIndexed(itemsList) { _, item ->

                val now = Calendar.getInstance().time
                val itemDate = dateFormat.parse(item.time)
                val diff = itemDate!!.time - now.time

                val url = when (item.day) {
                    0 -> "mjb2lbe"
                    4 -> "dlrr6nj"
                    6 -> "tnx6b4v"
                    40 -> "yri7818"
                    100 -> "hrq89s3"
                    111 -> "hzt94q6"
                    147 -> "wvk0u2w"
                    210 -> "mcmzgi1"
                    else -> ""
                }

                if (diff > 0) {
                    val diffInSeconds = diff / 1000
                    val diffInMinutes = diffInSeconds / 60
                    minute = diffInMinutes.toInt()
                    hour = (diffInMinutes / 60).toInt()

                }

                if (now.before(itemDate)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            thickness = 1.dp,
                            color = colorResource(R.color.grey_logo)
                        )

                        Text(
                            text = if (hour > 1) "تا $hour ساعت دیگر " else " تا $minute دقیقه دیگر",
                            style = MaterialTheme.typography.bodySmall,
                            color = colorResource(R.color.blue_logo),
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            thickness = 1.dp,
                            color = colorResource(R.color.grey_logo)
                        )
                    }
                    ShowPlan(
                        item,
                        phoneNum,
                        url,
                        context,
                        viewModelPlanner,
                        viewModelSms,
                        navController
                    )
                } else {
                    viewModelPlanner.deleteGroup(item)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "موردی برای اقدام وجود ندارد",
                color = colorResource(R.color.blue_logo),
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )

        }
    }
}


@Composable
fun ShowPlan(
    item: PlannerItem,
    phoneNum: String,
    url: String,
    context: Context,
    viewModelPlanner: PlannerViewModel,
    viewModelSms: SmsViewModel,
    navController: NavController
) {
    var done by remember { mutableStateOf(false) }
    var showInternetDialog by remember { mutableStateOf(false) }
    var showVideoLinkItem by remember { mutableStateOf(false) }
    var showSonoItem by remember { mutableStateOf(false) }
    var showBuyItem by remember { mutableStateOf(false) }
    when (item.day) {
        0, 4, 6, 40, 100, 111, 147, 210 -> showVideoLinkItem = true
        45 -> showSonoItem = true
        227 -> showBuyItem = true
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = Modifier
                .background(
                    color = colorResource(R.color.light_blue).copy(0.7f),//Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .fillMaxWidth()
                .heightIn(min = 170.dp)
        ) {
            Column(
                modifier = Modifier
                    .heightIn(min = 170.dp)
                    .fillMaxSize()
                    .background(
                        color = colorResource(R.color.blue2_logo).copy(alpha = 0.4f),
                        shape = RoundedCornerShape(12.dp)
                    ),
            ) {
                Text(
                    text = "اقدام مورد نیاز",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.sans_bold)),
                        fontSize = 22.sp,
                        color = colorResource(R.color.blue_logo).copy(alpha = 2f)
                    )
                )
                Text(
                    text = item.message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    lineHeight = 28.sp,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.sans_bold)),
                        fontSize = 20.sp,
                        color = colorResource(R.color.blue_logo)
                    )
                )
                if (showVideoLinkItem) {
                    TextButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            navController.navigate(Screens.ShowAparatScreen.route + "/$url")
                        }) {
                        Text(
                            "مشاهده ویدیو آموزشی",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            color = colorResource(R.color.sunset).copy(alpha = 2f)
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(Modifier.clickable {
                        viewModelPlanner.updateDone(
                            itemId = item.planId,
                            done = !done
                        )
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Checkbox(
                                checked = item.done,
                                onCheckedChange = {
                                    viewModelPlanner.updateDone(itemId = item.planId, done = it)
                                    done = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(id = R.color.sunset),
                                    checkmarkColor = colorResource(id = R.color.blue_logo)
                                )
                            )
                            Text(
                                text = "انجام شد",
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                color = colorResource(R.color.blue_logo)
                            )
                        }
                    }
                    if (showSonoItem) {
                        Box {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Checkbox(
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.blue2_logo),
                                        checkmarkColor = colorResource(id = R.color.white)
                                    ),
                                    checked = item.showSono,
                                    onCheckedChange = {
                                        if (isInternetAvailable(context = context)) {
                                            viewModelPlanner.updateShowSono(
                                                itemId = item.planId,
                                                it
                                            )
                                            viewModelSms.sendSms(
                                                SmsRequest(
                                                    userName = "t.09199804478",
                                                    password = "sbe$830",
                                                    fromNumber = "10009611",
                                                    toNumbers = "09129430701",
                                                    messageContent = "کاربر به شماره همراه $phoneNum نیاز به سونوگراف دارد"
                                                )
                                            )
                                        } else {
                                            showInternetDialog = true
                                        }
                                    }
                                )
                                Text(
                                    text = "به سونوگراف دسترسی ندارم",
                                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                    if (showBuyItem) {
                        Box {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Checkbox(
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(id = R.color.blue2_logo),
                                        checkmarkColor = colorResource(id = R.color.white)
                                    ),
                                    checked = item.showBuyPack,
                                    onCheckedChange = {
                                        if (isInternetAvailable(context = context)) {
                                            viewModelPlanner.updateShowBuyPack(
                                                itemId = item.planId,
                                                it
                                            )
                                            viewModelSms.sendSms(
                                                SmsRequest(
                                                    userName = "t.09199804478",
                                                    password = "sbe$830",
                                                    fromNumber = "10009611",
                                                    toNumbers = "09129430701",
                                                    messageContent = "کاربر به شماره همراه $phoneNum دوره همزمان سازی را گذرانده نیاز به پک جدید دارد."
                                                )
                                            )

                                        } else {
                                            showInternetDialog = true
                                        }
                                    }
                                )
                                Text(
                                    text = "نیاز به پک همزمان سازی دارم",
                                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }

                if (showInternetDialog) {
                    CheckConnectivityStatus { showInternetDialog = false }
                }

            }
        }
    }
}