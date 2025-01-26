@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.screens.login

import android.content.Context
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.helper.ConnectionsStatus
import salimi.mohamad.aragenejetpack.helper.currentConnectivityStatus
import salimi.mohamad.aragenejetpack.helper.observeConnectivityAsFlow
import salimi.mohamad.aragenejetpack.screens.PublicNoteDialog
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.utils.randomFourDigitNumber
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: SmsViewModel, context: Context) {

    var phoneNumber by rememberSaveable { mutableStateOf("09") }
    var isError by remember { mutableStateOf(false) }
    var check by remember { mutableStateOf(false) }
    var txtError by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val view = LocalView.current
    var isKeyboardOpen by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    CheckConnectivityStatus(){
        navController.popBackStack()
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
            .fillMaxSize()
            .padding(top = 40.dp, start = 10.dp, end = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide() // Hide the keyboard
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_phone_vib),
            contentDescription = "",
            modifier = Modifier
                .height(150.dp)
                .width(160.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "آراژنی عزیز لطفا شماره تلفن همراه خود را وارد کنید",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone Number TextField
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            // TextField for Phone Number
            OutlinedTextField(
                shape = MaterialTheme.shapes.medium,
                value = formatPhoneNumber(phoneNumber),
                onValueChange = {
                    isError = false // Reset error when user types
                    if (it.replace(
                            "_",
                            ""
                        ).length <= 11 && it.all { char -> char.isDigit() || char == '_' }
                    ) {
                        phoneNumber = it.replace("_", "")
                    }
                },
                visualTransformation = PhoneNumberVisualTransformation(),
                label = { Text(text = "شماره همراه") },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    letterSpacing = 6.sp,
                    textDirection = TextDirection.Ltr
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
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
                    cursorColor = colorResource(R.color.blue2_logo), disabledSuffixColor = colorResource(R.color.blue2_logo),
                    focusedTextColor = colorResource(R.color.black),
                    unfocusedTextColor = colorResource(R.color.black)
                )
            )

            // Error Message
            if (isError) {
                Text(
                    text = txtError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(start = 8.dp, top = 4.dp)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val annotatedText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                        append(
                            "ورود به برنامه به معنای پذیرش تمامی "
                        )
                    }
                    pushStringAnnotation(tag = "terms", annotation = "قوانین")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.error,//Color.Blue, // رنگ متفاوت
                            textDecoration = TextDecoration.Underline // زیر خط دار
                        )
                    ) {
                        append("قوانین")
                    }
                    pop()
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                        append(
                            " می باشد"
                        )
                    }
                }

                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "terms",
                            start = offset,
                            end = offset
                        )
                        check = true
                    }
                )
                if (check) {
                    PublicNoteDialog(
                        onDismiss = { check = false },
                        title = "مقررات",
                        text = "از شماره تلفن همراه شماره فقط برای زمانی که تیک مربوط به درخواست یا خرید پک همزمان سازی علامت زده شود، برای شناسایی استفاده خواهد شد و تمامی قوانین حریم خصوصی شما رعایت خواهد شد.",
                        image = painterResource(R.drawable.ic_phone_vib)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        var btnEnable by remember { mutableStateOf(false) }
        btnEnable = phoneNumber.isNotEmpty() && phoneNumber.length == 11

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = btnEnable,
            shape = MaterialTheme.shapes.medium, // گوشه‌های گرد (می‌توانید این را با RoundRectangleShape تغییر دهید)
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.blue2_logo),//MaterialTheme.colorScheme.primary, // رنگ پس‌زمینه
                contentColor = colorResource(R.color.white)// رنگ متن
            ),
            onClick = {
                val otpCode = randomFourDigitNumber()
                    if (phoneNumber.length == 11 && phoneNumber.startsWith("09")) {
                        keyboardController?.hide()
                        viewModel.sendSms(
                            SmsRequest(
                                userName = "t.09199804478",
                                password = "sbe$830",
                                fromNumber = "10009611",
                                toNumbers = phoneNumber,
                                messageContent = " به آراژن ویرا خوش آمدید کد تایید شما $otpCode"
                            )
                        )
                        navController.navigate(Screens.OtpAuth.route + "/$phoneNumber/$otpCode")
                    } else {
                        isError = true
                        txtError = "شماره همراه وارد شده معتبر نیست."
                    }
                      },
        ) {
            Text(
                "دریافت رمز یکبار مصرف",
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontSize = 20.sp
            )
        }

    }

}
fun formatPhoneNumber(input: String): String {
    return input.padEnd(11, '_')
}

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString(text.text.padEnd(11, '_')),
            OffsetMapping.Identity
        )
    }
}

@Composable
fun CheckConnectivityStatus(onDismiss:()->Unit){
    val connection by connectivityStatus()
    val isConnected=connection === ConnectionsStatus.Available
    if (!isConnected){
        salimi.mohamad.aragenejetpack.screens.InternetAlertDialog(onDismiss=onDismiss)
    }
}
@Composable
fun connectivityStatus():State<ConnectionsStatus>{
    val mCtx= LocalContext.current
    return produceState(initialValue = mCtx.currentConnectivityStatus) {
        mCtx.observeConnectivityAsFlow().collect{value=it}
    }
}