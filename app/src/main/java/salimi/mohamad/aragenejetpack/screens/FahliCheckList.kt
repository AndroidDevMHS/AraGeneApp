@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.flow.collectLatest
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.FahliCheckList
import salimi.mohamad.aragenejetpack.helper.JalaliDatePickerDialog
import salimi.mohamad.aragenejetpack.helper.convertPersianToGregorian
import salimi.mohamad.aragenejetpack.helper.scheduleNotificationsForGroup
import salimi.mohamad.aragenejetpack.viewModel.FahliCheckDbViewModel

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FahliCheckList(viewModelDataBase: FahliCheckDbViewModel) {
    val context = LocalContext.current
    var itemsList by remember { mutableStateOf(emptyList<FahliCheckList>()) }
    var addDialog by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        viewModelDataBase.allCheckList.collectLatest { item ->
            itemsList = item
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (itemsList.isEmpty()) {
            val text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.sans_bold))
                    )
                ) { append("+\n") }
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.sans_bold))
                    )
                ) { append("افزودن گروه جدید برای همزمان سازی") }

            }
            ClickableText(
                text = text,
                onClick = { addDialog = true },
                style = TextStyle(textAlign = TextAlign.Center)
            )


        } else {
            AnimatedVisibility(visible = itemsList.isNotEmpty()) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                ) {
                    items(items = itemsList, key = { it.id }) { group ->
                        LazyItem(group, viewModelDataBase, { addDialog = true })
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                addDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 25.dp, bottom = 25.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "افزودن",
                tint = MaterialTheme.colorScheme.onPrimary // رنگ آیکون)
            )
        }
        if (addDialog) {
            DialogForAdd({ addDialog = false }, viewModelDataBase, context)
        }
    }

}


@Composable
fun LazyItem(group: FahliCheckList, viewModelDataBase: FahliCheckDbViewModel, onEdit: () -> Unit) {
    var openDialog by remember { mutableStateOf(false) }
    val gradientBox = Brush.horizontalGradient(
        colors = listOf(
            colorResource(R.color.light_blue),
            colorResource(R.color.white)
        )

    )
    val gradientBoxInverse = Brush.horizontalGradient(
        colors = listOf(
            colorResource(R.color.white),
            colorResource(R.color.white),
            colorResource(R.color.white),
            colorResource(R.color.white),
            colorResource(R.color.white),
            colorResource(R.color.white),
            colorResource(R.color.light_blue)
        )

    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .border(width = 0.2.dp, brush = gradientBox, shape = RoundedCornerShape(12.dp))
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp), clip = true)
                .clip(RoundedCornerShape(12.dp))

        ) {
            Row(Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxHeight()
                        .background(brush = gradientBoxInverse)
                        .padding(start = 10.dp, top = 20.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "نام گروه: ",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                            )
                        )
                        Text(
                            text = group.groupName,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "تعداد دام ها: ",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Text(
                            text = "${group.countOfGroup} راس",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = " شروع همزمان سازی: ",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Text(
                            text = group.dateOfStartShamsi,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .weight(0.1f)
                        .fillMaxHeight()
                        .background(brush = gradientBox),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = {
                        //onEdit()
                    }) {
                        Icon(
                            Icons.Sharp.Edit,
                            contentDescription = "",
                            tint = colorResource(R.color.green_dark)

                        )

                    }
                    IconButton(onClick = {
                        openDialog = true


                    }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "",
                            tint = colorResource(R.color.royal_red)
                        )

                    }
                }

            }

        }
    }
    if (openDialog) {
        PublicDialog(
            { openDialog = false },
            {
                viewModelDataBase.deleteGroup(group)
            },
            painterResource(R.drawable.trash),
            "حذف گروه",
            "آیا مطمئنید گروه مورد نظر حذف شود؟"
        )
    }

}

@Composable
fun DialogForAdd(
    onDismiss: () -> Unit,
    viewModelDataBase: FahliCheckDbViewModel,
    context: Context
) {
    var groupName by remember { mutableStateOf("") }
    var groupCount by remember { mutableIntStateOf(0) }
    var dateOfStater by remember { mutableStateOf("") }
    var dateOfStartMiladi by remember { mutableStateOf("") }
    val openCalender = remember { mutableStateOf(false) }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(
            onDismissRequest = {},
        ) {
            Card(
                modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(8.dp),
                        painter = painterResource(R.drawable.sheep),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "افزودن گروه جدید همزمان سازی",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(0.25f),
                        text = "نام گروه: ", style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        )
                    )
                    OutlinedTextField(
                        value = groupName,
                        onValueChange = { groupName = it },
                        placeholder = {
                            Text(
                                "نام گروه ؟",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        },
                        modifier = Modifier.weight(0.75f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "تعداد گروه: ",
                        modifier = Modifier.weight(0.25f), style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        )
                    )
                    var groupCountInput by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = groupCountInput,
                        onValueChange = {   // Allow only digits and update groupCountInput
                            if (it.all { char -> char.isDigit() }) {
                                groupCountInput = it
                                groupCount =
                                    it.toIntOrNull() ?: 0 // Update the actual groupCount variable
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number // This will bring up the numeric keyboard
                        ),
                        placeholder = {
                            Text(
                                "تعداد همزمان سازی در این گروه؟",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        },
                        modifier = Modifier.weight(0.75f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(0.35f),
                        text = "تاریخ شروع: ", style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        )
                    )

                    Text(
                        text = dateOfStater,
                        modifier = Modifier.weight(0.65f),
                        textAlign = TextAlign.Center
                    )
                    IconButton(onClick = { openCalender.value = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "date")
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            onDismiss()
                        }) {
                        Text(
                            text = "لغو",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            if (groupCount >= 1 && groupName.isNotEmpty() && dateOfStater.isNotEmpty()) {
                                val newGroup = FahliCheckList(
                                    groupName = groupName,
                                    countOfGroup = groupCount,
                                    dateOfStartShamsi = dateOfStater,
                                    dateOfStartMiladi = dateOfStartMiladi
                                )
                                viewModelDataBase.addNewGroup(
                                    newGroup
                                )
                                scheduleNotificationsForGroup(
                                    context = context,
                                    group = newGroup
                                )
                                onDismiss()
                            } else {
                                Toast.makeText(
                                    context,
                                    "لطفا تمامی اطلاعات خواسته شده را وارد کنید",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text(
                            text = "تایید",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
    JalaliDatePickerDialog(
        openDialog = openCalender,
        onConfirm = {
            dateOfStater = it.toString()
            dateOfStartMiladi = convertPersianToGregorian(it.toString())
        },
        onSelectDay = { dateOfStater = it.toString() })
}
