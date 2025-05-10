package salimi.mohamad.aragenejetpack.screens.login

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.utils.randomFourDigitNumber
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel


@Composable
fun OtpAuthScreen(
    onLoginSuccess: () -> Unit,
    phoneNumber: String,
    otp: Int,
    viewModel: SmsViewModel
) {
    var otpAuth by remember { mutableIntStateOf(otp) }
    var otpValues by remember { mutableStateOf(List(4) { "" }) }
    var isError by remember { mutableStateOf(false) }
    var txtError by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val view = LocalView.current
    var isKeyboardOpen by remember { mutableStateOf(false) }
    var countdownTime by remember { mutableIntStateOf(90) }
    var isResendEnabled by remember { mutableStateOf(false) }
    val focusRequesters = List(4) { FocusRequester() }

    // شمارش معکوس
    LaunchedEffect(countdownTime) {
        if (countdownTime > 0) {
            kotlinx.coroutines.delay(1000L) // تأخیر یک ثانیه
            countdownTime -= 1
        } else {
            isResendEnabled = true
        }
    }
    LaunchedEffect(view) {
        val rootView = view.rootView
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardOpen = keypadHeight > screenHeight * 0.15
        }
        rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)
    }

    val verticalArrangement = if (isKeyboardOpen) Arrangement.Top else Arrangement.Center

    Column(
        modifier = Modifier
            .padding(top = 80.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_phone_sms_orange),
            contentDescription = "pic",
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(0.7f) // یا 0.8f برای عرض بیشتر
                .aspectRatio(1.5f), // نسبت عرض به ارتفاع - بسته به تصویرت تنظیم کن
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "کد پیامک شده به شماره $phoneNumber را وارد کنید",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp,

            )
        val isOtpFilled = otpValues.all { it.isNotEmpty() }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    shape = MaterialTheme.shapes.medium,
                    value = value,
                    isError = isError,
                    onValueChange = { newValue ->
                        otpValues =
                            otpValues.toMutableList().apply { this[index] = newValue.take(1) }
                        if (newValue.length == 1 && index < focusRequesters.size - 1) {
                            focusRequesters[index + 1].requestFocus()
                        } else if (newValue.isEmpty() && index > 0) {
                            focusRequesters[index - 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .size(65.dp)
                        .padding(3.dp)
                        .focusRequester(focusRequesters[index]),
                    textStyle = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    )
                )

            }
        }
        if (isError) {
            Text(
                text = txtError,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        if (isResendEnabled) {
            otpAuth = randomFourDigitNumber()
            Text(
                text = "ارسال مجدد کد",
                modifier = Modifier
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
                    color = colorResource(R.color.blue2_logo),
                    fontFamily = FontFamily(
                        Font(R.font.sans_bold)
                    ), fontSize = 16.sp, textDecoration = TextDecoration.Underline
                )
            )
        } else {
            Text(
                text = "زمان باقی مانده: $countdownTime ثانیه", style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.sans_bold)
                    ), fontSize = 18.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.medium, // گوشه‌های گرد (می‌توانید این را با RoundRectangleShape تغییر دهید)
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary, // رنگ پس‌زمینه
                contentColor = MaterialTheme.colorScheme.onPrimary // رنگ متن
            ),
            enabled = isOtpFilled,
            onClick = {
                val otpCode = otpValues.joinToString("").toInt()
                if (otpCode == otpAuth) {
                    onLoginSuccess()
                } else {
                    isError = true
                    txtError = "کد وارد شده صحیح نمی باشد"
                }
            },
        ) {
            Text(
                "ورود",
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontSize = 20.sp
            )
        }

    }
}
