package salimi.mohamad.aragenejetpack.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.roundTwo
import salimi.mohamad.aragenejetpack.data.superMixCalculator

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun SuperMixFormulaScreen() {
    var count by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var full by remember { mutableFloatStateOf(0f) }
    var consantere by remember { mutableFloatStateOf(0f) }
    var younje by remember { mutableFloatStateOf(0f) }
    val perCons = roundTwo(consantere / 3)
    val perYounj = roundTwo( younje / 3)
    val keyboardController = LocalSoftwareKeyboardController.current
    val dayItem1 = listOf(
        listOf(30, 31, 32, 33, 34, 35),
        listOf(36, 37, 38, 39, 40, 41),
        listOf(42, 43, 44, 45, 46, 47),
        listOf(48, 49, 50, 51, 52, 53)
    )
    var selectedItemRem1 by remember { mutableStateOf("انتخاب کنید") }

    val dayItem2 = listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20)

    )
    var selectedItemRem2 by remember { mutableStateOf("انتخاب کنید") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(R.color.blue_logo),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp, horizontal = 12.dp),
                    text = "لطفا وزن شروع و روز پرواربندی را انتخاب کنید",
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 18.sp
                )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "وزن شروع:",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .padding(12.dp)
                        )
                        Spinner(items = dayItem1, selectedItemRem1) {
                            selectedItemRem1 = it
                            show = false
                        }
                    }
                Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "روز:",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .padding(12.dp)
                        )
                        Spinner(items = dayItem2, selectedItemRem2) {
                            selectedItemRem2 = it
                            show = false
                        }
                    }
                Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .height(55.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "تعداد دام:",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .padding(12.dp)
                        )

                        OutlinedTextField(
                            shape = RoundedCornerShape(5.dp),
                            value = count,
                            onValueChange = {
                                count = toPersianNumbers(it)
                                show = false
                            },
                            placeholder = {
                                Text(
                                    text = "تعداد بره\u200Cپرواری",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontSize = 17.sp
                                )
                            },
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 6.sp,
                                textDirection = TextDirection.Rtl
                            ),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth(0.55f)
                                .height(55.dp)
                                .align(Alignment.CenterVertically),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { keyboardController?.hide() },
                            ),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = colorResource(R.color.white),
                                unfocusedTextColor = colorResource(R.color.white),
                                focusedBorderColor = colorResource(R.color.blue_logo2),
                                focusedLabelColor = colorResource(R.color.blue_logo2),
                                unfocusedBorderColor = colorResource(R.color.blue_logo2),
                                cursorColor = colorResource(R.color.blue_logo2),
                                disabledSuffixColor = colorResource(R.color.blue_logo2),
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                Box(
                            modifier = Modifier
                                .clickable {
                                    keyboardController?.hide()
                                    if (
                                        selectedItemRem1 != "انتخاب کنید" &&
                                        selectedItemRem2 != "انتخاب کنید" &&
                                        count != "" &&
                                        count.toInt() >= 1
                                    ) {
                                        val x = superMixCalculator(
                                            count = count.toInt(),
                                            day = selectedItemRem2.toInt(),
                                            weight = selectedItemRem1.toInt()
                                        )
                                        full = x[0]
                                        consantere = x[1]
                                        younje = x[2]
                                        show = true
                                    } else {
                                        showError = true
                                    }
                                }
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(
                                    color = colorResource(R.color.blue2_logo),
                                    shape = RoundedCornerShape(
                                        bottomEnd = 12.dp,
                                        bottomStart = 12.dp,
                                        topStart = 30.dp,
                                        topEnd = 30.dp
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "دریافت جیره",
                                color = colorResource(R.color.white),
                                textAlign = TextAlign.Center,
                                style = TextStyle(textDirection = TextDirection.Rtl),
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontSize = 17.sp
                            )
                        }

                    }
                }
            if (showError) {
                PublicNoteDialog(image = painterResource(R.drawable.exclamation),
                    text = "لطفا تمامی موارد خواسته شده را وارد کنید و سپس اقدام به دریافت جیره نمایید",
                    title = "اطلاعات ناقص",
                    onDismiss = { showError = false })
            }}
        Spacer(modifier = Modifier.height(2.dp))
        if (show) {
            val text = buildAnnotatedString {
                append("مقدار کل خوراک مصرفی روزانه در روز ")
                withStyle(style = SpanStyle(color = colorResource(R.color.royal_red))) {
                    append(selectedItemRem2)
                }
                append("ام برای تعداد ")
                withStyle(style = SpanStyle(color = colorResource(R.color.royal_red))) {
                    append(count)
                }
                append(" راس گوسفند برابر با ")
                withStyle(style = SpanStyle(color = colorResource(R.color.royal_red))) {
                    append("$full")
                }
                append(" کیلوگرم می باشد. ازین مقدار باید")
                withStyle(style = SpanStyle(color = colorResource(R.color.royal_red))) {
                    append(" ${if (consantere < 1) (consantere * 1000).toInt() else consantere}${if (consantere < 1) " گرم" else " کیلوگرم"}")
                }
                append(" کنسانتره و ")
                withStyle(style = SpanStyle(color = colorResource(R.color.royal_red))) {
                    append("${if (younje < 1) (younje * 1000).toInt() else younje}${if (younje < 1) " گرم" else " کیلوگرم"}")
                }
                append(" یونجه مصرف شود.")
            }
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                style = TextStyle(textDirection = TextDirection.Rtl),
                fontSize = 20.sp,
                modifier = Modifier.padding(12.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Min)
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(R.color.blue_logo),
                            shape = RoundedCornerShape(12.dp)
                        )

                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp, horizontal = 12.dp),
                        text = "مقدار خوراک تعیین شده در بالا باید در سه وعده به دام ها داده شود",
                        color = colorResource(R.color.white),
                        textAlign = TextAlign.End,
                        fontFamily = FontFamily(Font(R.font.sans_bold)),
                        fontSize = 18.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${if (perCons < 1) (perCons * 1000).toInt() else perCons}${if (perCons < 1) " گرم کنسانتره" else " کیلوگرم کنسانتره"} و ${if (perYounj < 1) (perYounj * 1000).toInt() else perYounj}${if (perYounj < 1) " گرم یونجه" else " کیلوگرم یونجه"}",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                        Text(
                            text = "صبح: ",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(12.dp)
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${if (perCons < 1) (perCons * 1000).toInt() else perCons}${if (perCons < 1) " گرم کنسانتره" else " کیلوگرم کنسانتره"} و ${if (perYounj < 1) (perYounj * 1000).toInt() else perYounj}${if (perYounj < 1) " گرم یونجه" else " کیلوگرم یونجه"}",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                        Text(
                            text = "ناهار: ",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(12.dp)
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${if (perCons < 1) (perCons * 1000).toInt() else perCons}${if (perCons < 1) " گرم کنسانتره" else " کیلوگرم کنسانتره"} و ${if (perYounj < 1) (perYounj * 1000).toInt() else perYounj}${if (perYounj < 1) " گرم یونجه" else " کیلوگرم یونجه"}",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                        Text(
                            text = "شام: ",
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 18.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}


fun toPersianNumbers(input: String): String {
    val englishNumbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val persianNumbers = arrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
    var output = input
    englishNumbers.forEachIndexed { index, c ->
        output = output.replace(c, persianNumbers[index])
    }
    return output
}