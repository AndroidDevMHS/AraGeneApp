package salimi.mohamad.aragenejetpack.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.utils.loginSms
import salimi.mohamad.aragenejetpack.utils.randomFourDigitNumber
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel

@SuppressLint("InlinedApi")

@Composable
fun PublicNoteDialog(
    onDismiss: () -> Unit,
    image: Painter,
    title: String,
    text: String
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Card(
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(8.dp),
                        painter = image,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Center
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            onDismiss()
                        }) {
                        Text(
                            text = "متوجه شدم",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 19.sp,
                            color = colorResource(R.color.green_dark)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PublicDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    image: Painter,
    title: String,
    text: String
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Card(
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(8.dp),
                        painter = image,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Center
                        )
                    )
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
                            fontSize = 19.sp,
                            color = colorResource(R.color.green_dark)
                        )
                    }
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text(
                            text = "تایید",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InternetAlertDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(
            onDismissRequest = {
                Toast.makeText(
                    context,
                    "لطفا دستگاه خود را به اینترنت متصل کنید",
                    Toast.LENGTH_LONG
                ).show()
            },
        ) {
            Card(
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                IconButton(
                    onClick = { onDismiss() }) {
                    Icon(Icons.Rounded.Clear, "")
                }}
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(8.dp),
                        painter = painterResource(R.drawable.no_internet),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "اتصال به اینترنت",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "لطفا دستگاه خود را به اینترنت متصل کنید",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Center
                        )
                    )
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
                            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                            context.startActivity(intent)
                        }) {
                        Text(
                            text = "اینترنت WIFI",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            val intent = Intent(Settings.ACTION_DATA_USAGE_SETTINGS)
                            context.startActivity(intent)
                        }
                    ) {
                        Text(
                            text = "اینترنت موبایل",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ExitAccountDialog(
    onDismiss: () -> Unit,
    navController: NavController,
    viewModel: DataStoreViewModel
) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Card(
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(8.dp),
                        painter = painterResource(R.drawable.exclamation),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "اخطار مهم",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "در صورت خروج از حساب کاربری تمامی اطلاعات شما حذف خواهد شد",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            textAlign = TextAlign.Center
                        )
                    )
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
                            fontSize = 19.sp,
                            color = colorResource(R.color.green_dark)
                        )
                    }
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        onClick = {
                            viewModel.clearData()
                            navController.navigate(Screens.Login.route) {
                                popUpTo(0) {
                                    inclusive = true
                                } // پاک کردن تمامی صفحات از Back Stack
                            }
                        }
                    ) {
                        Text(
                            text = "تایید",
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}

//

@Composable
fun ChangeNumber(onDismiss: () -> Unit, viewModel: SmsViewModel, dataStore: DataStoreViewModel) {

    val keyboardController = LocalSoftwareKeyboardController.current

    var otpAuth by remember { mutableIntStateOf(0) }
    var otpValues by remember { mutableStateOf(List(4) { "" }) }
    var isError by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    var txtError by remember { mutableStateOf("") }
    var fourTextFilledView by remember { mutableStateOf(false) }

    var countdownTime by remember { mutableIntStateOf(90) }
    var isResendEnabled by remember { mutableStateOf(false) }
    val focusRequesters = List(4) { FocusRequester() }

    var btnEnable by remember { mutableStateOf(false) }


    // شمارش معکوس
    LaunchedEffect(countdownTime) {
        if (countdownTime > 0) {
            kotlinx.coroutines.delay(1000L) // تأخیر یک ثانیه
            countdownTime -= 1
        } else {
            isResendEnabled = true
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Card(
                modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(top = 10.dp),
                        painter = painterResource(R.drawable.change_account),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        if (!fourTextFilledView) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "لطفا شماره همراه خود را وارد کنید",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                                    fontWeight = FontWeight.Thin
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                modifier = Modifier.padding(
                                    start = 5.dp,
                                    end = 5.dp,
                                    bottom = 10.dp
                                ),
                                value = phoneNumber,
                                onValueChange = { phoneNumber = it },
                                label = { Text(text = "شماره همراه") },
                                textStyle = TextStyle(
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 6.sp,
                                    textDirection = TextDirection.Ltr
                                ),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() },
                                ),
                                isError = isError, // Highlight TextField in red if there's an error
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(R.color.blue2_logo),
                                    focusedLabelColor = colorResource(R.color.blue2_logo),
                                    cursorColor = colorResource(R.color.blue2_logo),
                                    disabledSuffixColor = colorResource(R.color.blue2_logo),
                                )
                            )
                        } else {

                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "کد ارسالی به شماره $phoneNumber  را وارد کنید",
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        fontSize = 17.sp,
                                        fontFamily = FontFamily(Font(R.font.sans_bold)),
                                        fontWeight = FontWeight.Thin
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "شماره اشتباه است",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            fourTextFilledView = false
                                        }, style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        color = colorResource(R.color.blue2_logo),
                                        fontFamily = FontFamily(
                                            Font(R.font.sans_bold)
                                        ),
                                        fontSize = 17.sp,
                                        textDecoration = TextDecoration.Underline
                                    )
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.Center,

                                    ) {
                                    otpValues.forEachIndexed { index, _ ->
                                        OutlinedTextField(
                                            value = otpValues[index], // مقدار مربوط به این فیلد
                                            onValueChange = { newValue ->
                                                otpValues = otpValues.toMutableList().apply {
                                                    this[index] =
                                                        newValue.take(1) // فقط یک کاراکتر مجاز است
                                                }
                                                if (newValue.isNotEmpty() && index < focusRequesters.size - 1) {
                                                    focusRequesters[index + 1].requestFocus()
                                                } else if (newValue.isEmpty() && index > 0) {
                                                    focusRequesters[index - 1].requestFocus()
                                                }
                                            },
                                            textStyle = TextStyle(
                                                fontSize = 18.sp,
                                                textAlign = TextAlign.Center
                                            ),
                                            keyboardOptions = KeyboardOptions.Default.copy(
                                                keyboardType = KeyboardType.Number
                                            ),
                                            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                                            isError = isError,
                                            modifier = Modifier
                                                .size(65.dp)
                                                .padding(1.dp)
                                                .focusRequester(focusRequesters[index])
                                        )
                                    }
                                }
                            }


                        }
                        if (isError) {
                            Text(
                                text = txtError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(start = 8.dp, top = 4.dp)
                            )
                        }
                    }
                }

                if (fourTextFilledView) {
                    if (isResendEnabled) {
                        otpAuth = randomFourDigitNumber()
                        Text(
                            text = "ارسال مجدد کد",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.sendSms(
                                        SmsRequest(
                                            userName = "t.09199804478",
                                            password = "sbe$830",
                                            fromNumber = "10009611",
                                            toNumbers = phoneNumber,
                                            messageContent = " به آراژن ویرا خوش آمدید کد تایید شما $otpAuth"
                                        )
                                    )
                                    countdownTime = 90
                                    isResendEnabled = false
                                }, style = TextStyle(
                                textAlign = TextAlign.Center,
                                color = colorResource(R.color.blue2_logo),
                                fontFamily = FontFamily(
                                    Font(R.font.sans_bold)
                                ), fontSize = 16.sp, textDecoration = TextDecoration.Underline
                            )
                        )
                    } else {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "زمان باقی مانده: $countdownTime ثانیه", style = TextStyle(
                                fontFamily = FontFamily(
                                    Font(R.font.sans_bold)
                                ), fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        )
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
                            fontSize = 19.sp,
                            color = colorResource(R.color.green_dark)
                        )
                    }
                    TextButton(
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        enabled = btnEnable,
                        onClick = {
                            if (!fourTextFilledView) {
                                otpAuth = randomFourDigitNumber()
                                if (phoneNumber.length == 11 && phoneNumber.isNotEmpty()) {
                                    loginSms(phoneNumber, otpAuth, viewModel)
                                    fourTextFilledView = true
                                    btnEnable = false
                                } else {
                                    isError = true
                                    txtError = "شماره وارد شده اشتباه است"
                                }
                            } else {
                                val otpCode = otpValues.joinToString("").toInt()
                                if (otpCode == otpAuth) {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        dataStore.savePhoneNumber(phoneNumber)
                                    }
                                    onDismiss()
                                } else {
                                    isError = true
                                    txtError = "کد وارد شده صحیح نیست"
                                }

                            }

                        }
                    ) {
                        var textBtn by remember { mutableStateOf("") }
                        if (!fourTextFilledView) {
                            textBtn = "دریافت رمز"
                            btnEnable = phoneNumber.length == 11
                        } else {
                            textBtn = "تایید"
                            btnEnable = otpValues.all { it.isNotEmpty() }
                        }
                        Text(
                            text = textBtn,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 18.sp,
                        )
                    }
                }

            }

        }

    }
}

@Composable
fun Spinner(
    items: List<List<Int>>, // هر سطر شامل یک لیست از اعداد است
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(56.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(5.dp),
                color = colorResource(R.color.blue_logo2)
            )
            .clickable { expanded = !expanded }
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                tint = colorResource(R.color.green_logo),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = selectedItem.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                .padding(start = 12.dp, end = 12.dp)
        ) {
            items.forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { item ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .clickable {
                                    onItemSelected(item)
                                    expanded = false
                                }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(R.color.black)
                            )
                        }
                    }
                }
            }
        }
    }
}