package salimi.mohamad.aragenejetpack.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.superMixCalculator
import salimi.mohamad.aragenejetpack.screens.login.PhoneNumberVisualTransformation

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7
)
@Composable
fun SuperMixFormulaScreen() {
    var count by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }
    var full by remember { mutableStateOf(0) }
    var consantere by remember { mutableStateOf(0) }
    var younje by remember { mutableStateOf(0) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val dayItem1 = listOf(
        listOf(30, 31, 32, 33, 34, 35),
        listOf(36, 37, 38, 39, 40, 41), listOf(42, 43, 44, 45, 46, 47)
    )
    var selectedItemRem1 by remember { mutableIntStateOf(35) }

    val dayItem2 = listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 10),
        listOf(11, 12, 13, 14, 15),
        listOf(16, 17, 18, 19, 20),
        listOf(21, 22, 23, 24, 25),
        listOf(26)
    )
    var selectedItemRem2 by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
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
                        .padding(vertical = 18.dp),
                    text = "لطفا وزن شروع و روز پرواربندی را انتخاب کنین",
                    color = colorResource(R.color.white),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 18.sp
                )


                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 10.dp),
                            verticalArrangement = Arrangement.SpaceAround,// فاصله بین اسپینرها
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "وزن شروع:",
                                color = colorResource(R.color.white),
                                textAlign = TextAlign.Center,
                                style = TextStyle(textDirection = TextDirection.Rtl),
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontSize = 17.sp
                            )
                            Text(
                                text = "روز:",
                                color = colorResource(R.color.white),
                                textAlign = TextAlign.Center,
                                style = TextStyle(textDirection = TextDirection.Rtl),
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontSize = 17.sp
                            )
                            Text(
                                text = "تعداد دام:",
                                color = colorResource(R.color.white),
                                textAlign = TextAlign.Center,
                                style = TextStyle(textDirection = TextDirection.Rtl),
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontSize = 17.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxHeight(),
                        ) {
                            Spinner(items = dayItem1, selectedItemRem1) { selectedItemRem1 = it }
                            Spinner(items = dayItem2, selectedItemRem2) {
                                selectedItemRem2 = it
                            }
                            OutlinedTextField(
                                shape = MaterialTheme.shapes.medium,
                                value = count,
                                onValueChange = {
                                    count = it
                                },
                                label = { Text(text = "تعداد") },
                                textStyle = TextStyle(
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 6.sp,
                                    textDirection = TextDirection.Ltr
                                ),
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.5f),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() },
                                ),

                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(R.color.blue2_logo),
                                    focusedLabelColor = colorResource(R.color.blue2_logo),
                                    unfocusedBorderColor =colorResource(R.color.blue2_logo) ,
                                    unfocusedContainerColor = colorResource(R.color.white),
                                    focusedContainerColor =  colorResource(R.color.white),
                                    cursorColor = colorResource(R.color.blue2_logo),
                                    disabledSuffixColor = colorResource(R.color.blue2_logo),
                                )
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                modifier = Modifier
                                    .clickable {
                                        val x = superMixCalculator(
                                            count = count.toInt(),
                                            day = selectedItemRem2,
                                            weight = selectedItemRem1
                                        )
                                        Log.e("3030", x.toString())
                                    }
                                    .width(100.dp)
                                    .height(48.dp)
                                    .background(
                                        color = colorResource(R.color.blue2_logo),
                                        shape = RoundedCornerShape(
                                            bottomEnd = 12.dp,
                                            topStart = 30.dp
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
                }
            }
        }

     //   Text(text = "مقدار کل خوراک مصرفی روزانه در روز $selectedItemRem2 برابر با ${x.}")
    }

}
