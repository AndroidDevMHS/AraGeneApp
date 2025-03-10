package salimi.mohamad.aragenejetpack.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.R

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FahliMainHelp() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val non = listOf( buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 17.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.sans_bold))
                    )
                ) {
                    append("لطفا یک مرحله را انتخاب کنید")
                }
            })

            Text(
                text = "مراحل همزمان\u200Cسازی:",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 20.sp,
                    textDirection = TextDirection.Rtl,
                    textAlign = TextAlign.Justify
                )
            )
            val sections = listOf(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.green_dark)
                        )
                    ) {
                        addStringAnnotation(
                            tag = "title",
                            annotation = "واکسیناسیون",
                            start = 0,
                            end = "واکسیناسیون".length
                        )
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            color = colorResource(R.color.black),

                            )
                    ) {
                        append(
                            "واکسیناسیون گله را قبل از شروع همزمان\u200Cسازی انجام دهید به صورتی\u200Cکه تمامی واکسن\u200Cها تا دو هفته قبل از شروع عملیات همزمان\u200Cسازی پایان یابد. \n" +
                                    "توجه کنید که در سیستم دامپزشکی دو نوع واکسن وجود دارد: واکسن\u200Cهایی که متولی تزریق آن اداره دامپزشکی می\u200Cباشد و واکسن\u200Cهایی که خود دامدار باید تزریق کند. \n" +
                                    "از ده روز قبل از شروع همزمان\u200Cسازی تا آخر دوره آبستنی تزریق واکسن\u200Cهای شاربن، بروسلوز به هیچ عنوان نباید انجام شود، بنابراین، اگر می\u200Cخواهید واکسیناسیون انجام دهید این واکسن\u200Cها را دو هفته قبل از شروع همزمان\u200Cسازی به گله تزریق کنید. \n" +
                                    "تزریق واکسن\u200Cهای تب برفکی عموما در دوران آبستنی مشکل ساز نیستند مگر این\u200Cکه بر اساس بروشور شرکت سازنده، منع مصرف در دوران آبستنی وجود داشته باشد. پیشنهاد می\u200Cشود واکسن تب برفکی در 60 روز اول پس از جفت\u200Cگیری و ماه آخر آبستنی تزریق نشود.\n"
                        )
                        withStyle(
                            style = SpanStyle(
                                fontSize = 17.sp,
                                color = colorResource(R.color.royal_red)
                            )
                        ) {
                            append(" اکیدا توصیه می\u200Cشود ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 17.sp,
                                color = colorResource(R.color.black)
                            )
                        ) {
                            append("واکسن\u200Cهای پی\u200Cپی آر و آبله قبل از شروع همزمان\u200Cسازی به دام\u200Cها تزریق شوند. \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 17.sp,
                                color = colorResource(R.color.royal_red)
                            )
                        ) {
                            append(" توجه کنید که")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 17.sp,
                                color = colorResource(R.color.black)
                            )
                        ) {
                            append(" طبق پروتکل\u200Cهای بهداشتی، تزریق واکسن های پی پی آر و آبله (واکسن\u200Cهای تولید موسسه رازی) در 40 روز آخر آبستنی منع تزریق دارند.\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 17.sp,
                                textDecoration = TextDecoration.Underline,
                                color = colorResource(R.color.black)
                            )
                        ) {
                            append("مجددا تاکید می\u200Cشود که 3 هفته پیش از شروع همزمان\u200Cسازی واکسن\u200Cهای مربوط به بیماری\u200Cهای رایج منطقه (پی پی آر، آبله و تب برفکی) تزریق شود.\n\n")
                        }
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "پشم چینی و سم چینی",
                        start = 0,
                        end = "پشم چینی و سم چینی".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "سم چینی قوچ ها باید حداقل 3 هفته قبل از فصل تولید مثل کوتاه شود.\n" +
                                    "پشم چینی باید 6-8 هفته قبل از تولید مثل زمانی که فصل تولید مثل در مرداد تا مهر است انجام شود.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "انگل تراپی",
                        start = 0,
                        end = "انگل تراپی".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "در صورت لزوم 2 تا 4 هفته قبل از همزمان\u200Cسازی انجام شود و تا یک هفته قبل عملیاتی صورت نگیرد.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "هویت گزاری",
                        start = 0,
                        end = "هویت گزاری".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold))
                        )
                    ) {
                        append(
                            "بهتر است برای ثبت اطلاعات گله قبل از عملیات همزمان\u200Cسازی دام\u200Cهایی که پلاک ندارند هویت\u200Cگزاری شوند.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "از شیر گیری",
                        start = 0,
                        end = "از شیر گیری".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold))
                        )
                    ) {
                        append(
                            "دام\u200Cهایی که شیرده هستند باید قبل از عملیات همزمان\u200Cسازی شیرشان خشک شود.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "سونوگرافی",
                        start = 0,
                        end = "سونوگرافی".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold))
                        )
                    ) {
                        append(
                            "برای اطمینان از خالی بودن دام\u200Cها بهتر است قبل از عملیات همزمان\u200Cسازی دام\u200Cها سونوگرافی شوند.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "عفونت زدایی",
                        start = 0,
                        end = "عفونت زدایی".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "ﺑﺎ ﺗﻮﺟﻪ ﺑﻪ وﺟﻮد ﻋﻔﻮﻧﺖﻫﺎي اﺣﺘﻤﺎﻟﯽ ﺳﯿﺴﺘﻢ ﺗﻮﻟﯿﺪﻣﺜﻠﯽ در ﻣﯿﺶﻫﺎي ﻣﻮﻟﺪ ﻏﯿﺮ آﺑﺴﺘﻦ، بهتر است یک هفته ﭘﯿﺶ از ﺷﺮوع ﺑﺮﻧﺎﻣﻪ\u200Cﻫﺎي ﻫﻤﺰﻣﺎنﺳﺎزي،  به ازای هر ده کیلوگرم وزن زنده حیوان، یک سی\u200Cسی\"اﮐﺴﯽ ﺗﺘﺮاﺳﺎﯾﮑﻠﯿﻦ 20  درﺻﺪ ال آ\" ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺗﺰرﯾﻖ ﺷﻮد. به عنوان مثال یک میش 50 کیلوگرمی به 5 سی\u200Cسی دارو نیاز دارد.\n"
                        )
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.royal_red),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("توجه فرمایید که تزریق داخل رگی اکسی تترا سایکلین می\u200Cتواند موجب مرگ حیوان گردد.\n\n")
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "فلاشینگ",
                        start = 0,
                        end = "فلاشینگ".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﺟﻬﺖ اﻟﻘﺎي تخمک\u200Cرﯾﺰي ﺑﯿﺸﺘﺮ در ﻣﯿﺶﻫﺎ و ﺑﺰﻫﺎي ﻣﺎده، از ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ اﺳﺘﻔﺎده ﺷﻮد. ﺑﻬﺘﺮﯾﻦ ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ ﻣﻘﺪار 450 اﻟﯽ 500 ﮔﺮم ﺟﻮ ﯾﺎ ذرت ﺑﻠﻐﻮر ﺷﺪه و 200 گرم کنجاله سویا به همراه مکمل فسفر 90درصد ﻣﯽﺑﺎﺷﺪ ﮐﻪ سه ﻫﻔﺘﻪ ﭘﯿﺶ از ﺷﺮوع ﻫﻤﺰﻣﺎنﺳﺎزي ﺗﺎ ﺳﻪ ﻫﻔﺘﻪ ﭘﺲ از آن ﺑﻪ ﺻﻮرت ﺗﺪرﯾﺠﯽ اﺳﺘﻔﺎده ﺷﻮد.\n"
                        )
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.royal_red),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("نکته: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ ﺗﻨﻬﺎ در ﻣﯿﺶﻫﺎي ﺑﺎ اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ ﭘﺎﯾﯿﻦ (ﻻﻏﺮ) ﻣﻮﺛﺮ ﺧﻮاﻫﺪ ﺑﻮد و در ﺻﻮرت ﻣﻨﺎﺳﺐ ﺑﻮدن اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ، ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ به مدت یک هفته قبل تا3 هفته بعد از قوچ اندازی انجام شود.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "استفاده از مکمل\u200Cهای معدنی-ویتامینه:",
                        start = 0,
                        end = "استفاده از مکمل\u200Cهای معدنی-ویتامینه:".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "رﻋﺎﯾﺖ اﺻﻮل ﺗﻐﺬﯾﻪاي و وﺟﻮد ﻣﻘﺎدﯾﺮ ﮐﺎﻓﯽ اﻧﺮژي، ﭘﺮوﺗﺌﯿﻦ، ﻣﻮاد ﻣﻌﺪﻧﯽ و وﯾﺘﺎﻣﯿﻨﯽ در ﺟﯿﺮه ﻣﯿﺶﻫﺎ و ﻧﺮﻫﺎي ﻣﻮﻟﺪ ﻧﻘﺶ ﻣﻬﻤﯽ در میزان ﻓﺤﻠﯽ، درصد آﺑﺴﺘﻨﯽ، رﺷﺪ و ﻧﻤﻮ ﺟﻨﯿﻦ و ﻧﺮخ دو ﻗﻠﻮزاﯾﯽ دارد. ﮐﻤﺒﻮد ﻣﻮاد ﻣﻌﺪﻧﯽ ﮐﻢ ﻣﺼﺮف ﻫﻤﺎﻧﻨﺪ آﻫﻦ، ﻣﺲ، ﺳﻠﻨﯿﻮم، روي و... ﻣﯽﺗﻮاﻧﺪ ﻋﻤﻠﮑﺮد ﺗﻮﻟﯿﺪﻣﺜﻠﯽ دامﻫﺎي ﻣﻮﻟﺪ را ﺑﺸﺪت ﺗﺤﺖ ﺗﺎﺛﯿﺮ ﻗﺮار دﻫﺪ. \n" +
                                    "در نظر داشته باشید که مکمل های معدنی-ویتامینه فرموله شده توسط شرکت آراژن تمام کمبودهای ویتامینه و معدنی دام را در تمام طول مدت همزمان\u200Cسازی و آبستنی می\u200Cتواند تامین کند.\n\n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "تقویت قوچ",
                        start = 0,
                        end = "تقویت قوچ".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                            fontSize = 17.sp,
                            color = colorResource(R.color.black)
                        )
                    ) {
                        append(
                            "پیش از همزمانسازی می توان از مکمل مخصوص قوچ و تزریق هورمون وتارولین برای تقویت قوچ ها استفاده کرد (سه تزریق با فاصله 48 ساعت، هر تزریق شامل 3 سی سی وتارولین)."
                        )
                    }
                })

            val sections2 = listOf(
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز صفر",
                        start = 0,
                        end = "روز صفر".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "ﻗﺮار دادن سیدر ﺗﻮﺳﻂ اﭘﻠﯿﮑﺎﺗﻮر در داﺧﻞ واژن و ﺗﺰرﯾﻖ 2 ﺳﯽﺳﯽ از\" ﻫﻮرﻣﻮن روز 1 \" ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ. ﺑﻬﺘﺮﯾﻦ ﻣﺤﻞ ﺗﺰرﯾﻖ، ﭘﺸﺖ ﻋﻀﻠﻪ ران ﺑﻪ ﺻﻮرﺗﯽ ﮐﻪ ﺣﯿﻮان اﯾﺴﺘﺎده ﺑﺎﺷﺪ.\n" +
                                    "پیشنهاد می\u200Cشود از اﺳﭙﺮي اﮐﺴﯽ ﺗﺘﺮا ﺳﺎﯾﮑﯿﻠﯿﻦ (او ﺗﯽ ﺳﯽ) ﭘﯿﺶ از ﻗﺮار دادن سیدر در اپلیکاتور اﺳﺘﻔﺎده کنید، یعنی مقداری اکسی تتراسایکلین را بر روی سیدر اسپری نمایید.\n" +
                                    "تزریق ای- سلنیوم و ویتامین آ-د3- ای به مقدار 5 سی سی از هر کدام به صورت زیر جلدی.\n" +
                                    "برای افزایش چندقلوزایی می توان سه نوبت به میش ها فسفر ب12 تزریق کرد (روز سیدر گزاری- روز سوم- روز سیدر برداری).\n"
                        )
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.royal_red),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("نکته: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("\nبه قوچ ها نیز می توان ویتامین فسفر ب 12، ای- سلنیوم و یا ویتامین آ-د3- ای در روز سیدر گزاری و سیدر برداری تزریق کرد.")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.royal_red),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("نکته: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("میزان تزریق با توجه به دستوالعمل کارخانه سازننده ویتامین.")
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز یک",
                        start = 0,
                        end = "روز یک".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "امروز کار خاصی نباید انجام دهید."
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز دوم",
                        start = 0,
                        end = "روز دوم".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "امروز کار خاصی نباید انجام دهید."
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز سوم",
                        start = 0,
                        end = "روز سوم".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "به میش ها فسفر ب12 تزریق شود."
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز چهارم",
                        start = 0,
                        end = "روز چهارم".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "امروز کار خاصی نباید انجام دهید."
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز پنجم",
                        start = 0,
                        end = "روز پنجم".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "امروز کار خاصی نباید انجام دهید."
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز ششم",
                        start = 0,
                        end = "روز ششم".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "امروز کار خاصی نباید انجام دهید."
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "روز هفتم",
                        start = 0,
                        end = "روز هفتم".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append(
                            "ﭘﺲ از ﺑﯿﺮون ﮐﺸﯿﺪن سیدر در روز هفتم، ﺗﺰرﯾﻘﺎت زﯾﺮ را ﺑﻼﻓﺎﺻﻠﻪ اﻧﺠﺎم دﻫﯿﺪ:\n" +
                                    "ﺗﺰرﯾﻖ مقدار مشخص شده  از ﻫﻮرﻣﻮن شیشه\u200Cای \"روز  7\" به مقدار یک و دو دهم سی سی.\n" +
                                    "ﺗﺰرﯾﻖ 2 ﺳﯽﺳﯽ از ﻫﻮرﻣﻮن ﺗﺮﮐﯿﺒﯽ روز 7 (ﭘﻮدری +ویال شیشه\u200Cای 50 سی سی): ﺟﻬﺖ ﺗﻬﯿﻪ اﯾﻦ ﻣﺤﻠﻮل، ﺑﺎ اﺳﺘﻔﺎده از ﺳﺮﻧﮓ 10 سی سی بزرگ قرار داده شده در بسته، 2ﺳﯽ ﺳﯽ از ﻣﺤﻠﻮل داﺧﻞ شیشه بزرگ را ﮐﺸﯿﺪه و وارد ویال ﺣﺎوي هورمون ﭘﻮدری ﻧﻤﺎﯾﯿﺪ. ﭼﻨﺪﯾﻦ ﺑﺎر ﺷﯿﺸﻪ را به آرامی به مدت یک دقیقه ﺳﺮوﺗﻪ ﻧﻤﺎﯾﯿﺪ ﺗﺎ ﭘﻮدر ﮐﺎﻣﻼ ﺣﻞ ﺷﻮد. از ﺗﮑﺎن دادن ﺷﺪﯾﺪ ﺧﻮدداري ﮐﻨﯿﺪ. ﭘﺲ از ﺣﻞ ﺷﺪن ﭘﻮدر، ﻣﺤﻠﻮل را ﺑﻪ ویال بزرگ اﻧﺘﻘﺎل دﻫﯿﺪ و ﭼﻨﺪ ﺑﺎر به آرامی ﺳﺮ و ﺗﻪ ﻧﻤﺎﯾﯿﺪ. ﺳﭙﺲ از اﯾﻦ ﻣﺤﻠﻮل 2 ﺳﯽﺳﯽ ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺑﻪ میش¬ها ﺗﺰرﯾﻖ ﻧﻤﺎﯾﯿﺪ.\n" +
                                    "تزریق ای- سلنیوم و ویتامین آ-د3- ای به صورت زیر جلدی.\n" +
                                    "تزریق فسفر ب12 به صورت عضلانی.\n"
                        )
                    }
                }

            )

            val sections3 = listOf(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            fontFamily = FontFamily(Font(R.font.sans_bold))
                        )
                    ) {
                        append(
                            "قوچ اندازی از 24 ساعت ﭘﺲ از ﺧﺎرج ﮐﺮدن سیدر و اﻧﺠﺎم ﺗﺰرﯾﻘﺎت ﻫﻮرﻣﻮﻧﯽ روز آخر، به صورت کنترل شده، انجام می\u200Cشود. \n" +
                                    "ﺣﺘﯽ اﻻﻣﮑﺎن ﻧﺴﺒﺖ ﻗﻮچ (کار و بالغ) ﺑﻪ ﻣﯿﺶ حداکثر یک به پنج را رﻋﺎﯾﺖ ﻓﺮﻣﺎﯾﯿﺪ، اﮔﺮﭼﻪ، ﺑﺴﺘﻪ ﺑﻪ ﺗﻮان ﻗﻮچ اﯾﻦ ﻧﺴﺒﺖ ﻣﯽﺗﻮاﻧﺪ ﺑﯿﺸﺘﺮ یا کمتر ﮔﺮدد.\n" +
                                    "توصیه می\u200Cشود قوچ اندازی دوم 5 ساعت پس از قوچ اندازی اول ترجیحا با یک قوچ دیگر انجام شود.\n" +
                                    "نکته: از ﺟﺎﺑﺠﺎﯾﯽ ﻣﯿﺶﻫﺎ ﺑﻪ ﻣﺮﺗﻊ پس از ﻗﻮچ اﻧﺪازی ﺗﺎ 7 روز ﭘﺲ از آن اﮐﯿﺪا ﺧﻮدداری ﻓﺮﻣﺎﯾﯿﺪ. پس از قوچ\u200Cاندازی، انتقال میش\u200Cها در اوایل بهار به مراتع موجب برگشت فحلی خواهد شد. \n" +
                                    "نکته: بهتر است پس از هر سری قوچ اندازی به قوچ\u200Cها سه روز استراحت داده شود.\n" +
                                    "در مورد نحوه قوچ اندازی به نکات زیر توجه کنید:\n" +
                                    "1)\tهر قوچ در فصل تولید مثل هر روز می\u200Cتواند حداکثر 5 بار جفتگیری موفق و در خارج از فصل تولید مثل 3 بار جفتگیری موفق داشته باشد، بنابراین، از اینکه یک قوچ با یک میش فحل چند بار جفتگیری کند باید جلوگیری کنید. \n" +
                                    "2)\tبهتر است 24 ساعت پس از سیدر برداری، قوچ یا قوچ¬ها را در گله رها کرده و بالای سر گله ایستاده و هر میشی که جفتگیری کرد با اسپری رنگی علامت زده و از گله جدا کنید. مجددا تاکید می\u200Cشود که اجازه ندهید قوچ\u200Cها به دفعات زیاد با یک میش جفتگیری کنند.\n" +
                                    "3)\tپس از اینکه تمام میش\u200Cها یکبار جفتگیری کردند حدودا 5 ساعت پس از قوچ اندازی اول، اجازه دهید بار دوم جفتگیری مجدد با قوچ دیگری انجام شود. \n"
                        )
                    }
                },
                buildAnnotatedString {
                    addStringAnnotation(
                        tag = "title",
                        annotation = "ﻧﮑﺎت ﭘس از ﻗوچ اﻧدازی",
                        start = 0,
                        end = "ﻧﮑﺎت ﭘس از ﻗوچ اﻧدازی".length
                    )
                    withStyle(
                        style = SpanStyle(
                            fontSize = 17.sp,
                            color = colorResource(R.color.black),
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    ) {
                        append("از اﻧﺠﺎم واﮐﺴﯿﻨﺎﺳﯿﻮن و ﯾﺎ ﻋﻤﻠﯿﺎت ﭘﺸﻢﭼﯿﻨﯽ، ﺳﻢﭼﯿﻨﯽ، اﻧﮕﻞزداﯾﯽ، ﺗﻐﯿﯿﺮات ﺷﺪﯾﺪ ﺟﯿﺮه، انتقال میش\u200Cها به مراتع بهاره و.....ﺗﺎ ﯾﮏ ﻣﺎه ﭘﺲ از ﻗﻮچ اﻧﺪازي ﺧﻮدداري ﻓﺮﻣﺎﯾﯿﺪ.\n" +
                                "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﺟﻬﺖ اﻓﺰاﯾﺶ دوﻗﻠﻮزاﯾﯽ و ﮐﺎﻫﺶ ﺑﺮﮔﺸﺖ ﻓﺤﻠﯽ، ﺑﻪ وﯾﮋه در ﺷﺮاﯾﻂ ﺗﻨﺶ ﮔﺮﻣﺎﯾﯽ ﺗﺎﺑﺴﺘﺎن، ﻣﻘﺪار 3 سی سی ﻫﻮرﻣﻮن ﮔﻨﺎدورﻟﯿﻦ (وﺗﺎروﻟﯿﻦ) ﺑﻪ ﺻﻮرت عضلانی، سه روز ﭘﺲ از ﻗﻮچ اﻧﺪازي ﺑﻪ ﻫﻤﻪ ﻣﯿﺶﻫﺎ ﺗﺰرﯾﻖ ﺷﻮد. \n" +
                                "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد، دوازده ﺗﺎ بیست و یک روز ﭘﺲ از ﻗﻮچ اﻧﺪازي، ﻗﻮچﻫﺎي ﻣﻮﻟﺪ را در ﺑﯿﻦ ﻣﯿﺶﻫﺎ ﻧﮕﻬﺪاري ﻧﻤﺎﯾﯿﺪ.\n")
                    }
                }
            )
            var selectedOption by remember { mutableStateOf<List<AnnotatedString>>(non) }
            var selectedTitle by remember { mutableStateOf("") }
            val listState = rememberLazyListState()


            data class Option(
                val label: String,
                val longText: List<AnnotatedString>,
                val title: String = ""
            )

            val options = listOf(
                Option("مرحله اول", sections, "عملیات پیش از همزمان‌سازی"),
                Option("مرحله دوم", sections2, "همزمان‌سازی"),
                Option("مرحله سوم", sections3, "قوچ اندازی و مراقبت\u200Cهای بعد از قوچ اندازی"),
            )
            Row {
                options.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == option.longText,
                            onClick = {
                                selectedOption = option.longText
                                selectedTitle = option.title
                                CoroutineScope(Dispatchers.Main).launch {
                                    listState.scrollToItem(0)
                                }
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colorResource(R.color.blue_logo),
                                unselectedColor = Color.Black
                            )
                        )
                        Text(
                            text = option.label,
                            modifier = Modifier.padding(start = 8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.sans_bold)),
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = selectedTitle,
                color = colorResource(R.color.blue_logo),
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // یک متغیر برای ذخیره عنوان جاری
                var currentTitle: String? = null

                // استفاده از stickyHeader خارج از items
                selectedOption.forEach { annotatedString ->
                    val title = annotatedString.getStringAnnotations(
                        start = 0,
                        end = annotatedString.text.length
                    ).firstOrNull { it.tag == "title" }?.item

                    // اگر عنوان جدیدی پیدا شد
                    if (title != null && title != currentTitle) {
                        currentTitle = title
                        stickyHeader {
                            Text(
                                text = title,
                                color = colorResource(R.color.green_dark),
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.sans_bold)),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                                    .padding(8.dp)
                            )
                        }
                    }

                    // نمایش متن معمولی برای هر annotatedString
                    item {
                        Text(
                            text = annotatedString,
                            lineHeight = 28.sp,
                            fontSize = 19.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

/*



    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("ﻧﮑﺎت ﭘﯾش از اﻧﺟﺎم همزﻣﺎن ﺳﺎزی\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append("-\tﻟﻄﻔﺎ ﺑﻼﻓﺎﺻﻠﻪ ﭘﺲ از درﯾﺎﻓﺖ ﺑﺴﺘﻪ ﻫﻤﺰﻣﺎﻧﺴﺎزي، ﺗﻤﺎﻣﯽ ﻣﺤﺘﻮﯾﺎت را به جز ")
    }

    // کلمه با رنگ متفاوت
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("داخل یخچال معمولی")
    }

    // ادامه خط با رنگ اصلی
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(" (دﻣﺎي  4 ﺗﺎ 8 درﺟﻪ ﺳﺎﻧﺘﯽﮔﺮاد) ﻗﺮار دﻫﯿﺪ. \n\n")
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("ﻫﺸﺪار")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            " ﯾﺦ زدن ﻫﻮرﻣﻮنﻫﺎ ﻣﻮﺟﺐ از ﺑﯿﻦ رﻓﺘﻦ ﮐﺎراﯾﯽ آﻧﻬﺎ ﺧﻮاﻫﺪ ﺷﺪ.\n\n" +
                    "-\tﻫﺮﮔﻮﻧﻪ ﻋﻤﻠﯿﺎت واﮐﺴﯿﻨﺎﺳﯿﻮن، ﭘﺸﻢﭼﯿﻨﯽ، اﻧﮕﻞزداﯾﯽ، ﭘﻼكﮐﻮﺑﯽ و .... را "
        )
    }
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("ﺗﺎ ﯾﮏ ﻫﻔﺘﻪ ﭘﯿﺶ از ﺷﺮوع ﺑﺮﻧﺎﻣﻪ ﻫﻤﺰﻣﺎنﺳﺎزي ﺗﮑﻤﯿﻞ ﻧﻤﺎﯾﯿﺪ")
    }
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            ". اﻧﺠﺎم ﭼﻨﯿﻦ ﻋﻤﻠﯿﺎﺗﯽ در ﺣﯿﻦ ﯾﺎ ﭘﺲ از ﻫﻤﺰﻣﺎنﺳﺎزي ﻣﯽﺗﻮاﻧﺪ ﻣﻮﺟﺐ ﮐﺎﻫﺶ ﻋﻤﻠﮑﺮد ﺗﻮﻟﯿﺪﻣﺜﻠﯽ دامﻫﺎ ﮔﺮدد. برای انجام از شیر گیری موفق قسمت پروتکل از شیر گیری این دستور العمل را مطالعه نمایید.\n"
        )
    }

    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "-\tپیشنهاد میشود عملیات همزمان\u200Cسازی  "
        )
    }
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("حداقل 2 ماه")
    }
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "  پس از زایش و ترجیجا پس از قطع شیر بره ها انجام شود.\n\n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("ﻫﺸﺪار ﻣﻬﻢ")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            " از ﻋﺪم آﺑﺴﺘﻨﯽ دامﻫﺎي ﺧﻮد ﺑﺎ اﻧﺠﺎم ﺳﻮﻧﻮﮔﺮاﻓﯽ ﺗﻮﺳﻂ ﻣﺘﺨﺼﺺ ﻣﺠﺮب، اﻃﻤﯿﻨﺎن ﺣﺎﺻﻞ ﻓﺮﻣﺎﯾﯿﺪ. در غیر این صورت اﻧﺠﺎم پروتکل ﻫﻤﺰﻣﺎنﺳﺎزي احتمال ﺳﻘﻂ ﺟﻨﯿﻦ در دامﻫﺎي آﺑﺴﺘﻦ را افزایش ﺧﻮاﻫﺪ داد.\n\n" +
                    "-\t(اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ) دامﻫﺎ در زﻣﺎن ﺷﺮوع ﻫﻤﺰﻣﺎنﺳﺎزي و ﺗﺎ ﯾﮏ ﻣﺎه ﭘﺲ از آن، راﺑﻄﻪ ﻣﺴﺘﻘﯿﻤﯽ ﺑﺎ درصد آﺑﺴﺘﻨﯽ و ﭼﻨﺪﻗﻠﻮزاﯾﯽ دارد. ﺣﯿﻮاﻧﺎت ﺑﺴﯿﺎر ﭼﺎق ﯾﺎ ﺑﺴﯿﺎر ﻻﻏﺮ ﻣﻌﻤﻮﻻ فحلی و درصد آﺑﺴﺘﻨﯽ ﭘﺎﯾﯿﻨﯽ دارﻧﺪ. ﺑﻬﺘﺮﯾﻦ" +
                    " اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ در زﻣﺎن ﺷﺮوع ﻫﻤﺰﻣﺎنﺳﺎزي ﺑﯿﻦ  دو تا سه ﻣﯽﺑﺎﺷﺪ. \n\n" +
                    "-\tاﮐﯿﺪا ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﭘﯿﺶ از ﺷﺮوع ﺑﺮﻧﺎﻣﻪ ﻫﻤﺰﻣﺎنﺳﺎزي، پنج ﺳﯽﺳﯽ \"اي-ﺳﻠﻨﯿﻮم\" و  پنج ﺳﯽﺳﯽ \"وﯾﺘﺎﻣﯿﻦ آ-د-س- ای\" ﺑﻪ ﺻﻮرت زﯾﺮ ﺟﻠﺪي ﺑﻪ ﻫﻤﻪ دامﻫﺎي ﻣﺎده و ﻫﻤﭽﻨﯿﻦ ﺑﻪ ﻗﻮچﻫﺎي ﻣﻮﻟﺪ ﺗﺰرﯾﻖ ﺷﻮد. ﻋﻼوه ﺑﺮ اﯾﻦ، ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﻣﻘﺪار پنج ﺳﯽﺳﯽ \"وﯾﺘﺎﻣﯿﻦ فسفر+B12\" 4 نوبت به فاصله 5 روز ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺑﻪ میش ها و ﻗﻮچﻫﺎي ﻣﻮﻟﺪ ﺗﺰرﯾﻖ ﺷﻮد. \n\n"
        )
    }

    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("ﻫﺸﺪار")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            " ﺗﺰرﯾﻖ داﺧﻞ رﮔﯽ \"اي-ﺳﻠﻨﯿﻮم\" و \"آ-د-س – ای\" ﻣﯽﺗﻮاﻧﺪ ﻣﻮﺟﺐ ﻣﺮگ ﺣﯿﻮان ﺷﻮد.\n\n" +
                    "-\tﺑﺎ ﺗﻮﺟﻪ ﺑﻪ وﺟﻮد ﻋﻔﻮﻧﺖﻫﺎي اﺣﺘﻤﺎﻟﯽ ﺳﯿﺴﺘﻢ ﺗﻮﻟﯿﺪ ﻣﺜﻠﯽ در ﻣﯿﺶﻫﺎي ﻣﻮﻟﺪ ﻏﯿﺮ آﺑﺴﺘﻦ، بهتر است یک هفته ﭘﯿﺶ از ﺷﺮوع ﺑﺮﻧﺎﻣﻪ ﻫﺎي ﻫﻤﺰﻣﺎن ﺳﺎزي،  به ازای هر ده کیلوگرم وزن زنده حیوان، یک سی سی\"اﮐﺴﯽ ﺗﺘﺮاﺳﺎﯾﮑﻠﯿﻦ 20  درﺻﺪ ال آ\" ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺗﺰرﯾﻖ ﺷﻮد. به عنوان مثال یک میش 50 کیلوگرمی به به 5 سی سی دارو نیاز دارد.\n"

        )
    }

    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = colorResource(R.color.royal_red)
        )
    ) {
        append("توجه فرماییدکه تزریق داخل رگی اکسی تترا سایکلین می تواند موجب مرگ حیوان گردد.\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append(
            "-\tتوجه کنید که از روش ﻫﻤﺰﻣﺎنﺳﺎزي اﺧﺘﺼﺎﺻﯽ ﻣﯽﺗﻮاﻧﯿﺪ در ﻣﯿﺶﻫﺎي زاﯾﺶ ﮐﺮده (ﯾﮏ و ﻧﯿﻢ ﻣﺎه ﭘﺲ از زاﯾﺶ ﺑﻪ ﺷﺮط داﺷﺘﻦ وﺿﻌﯿﺖ ﺑﺪﻧﯽ ﻣﻄﻠﻮب) اﺳﺘﻔﺎده ﻧﻤﺎﯾﯿﺪ. اگرچه شیرده بودن میش ها میتواند باعث کاهش درصد آبستنی شود. \n\n" +
                    "-\tﭘﯿﺶ از ﺷﺮوع ﻫﻤﺰﻣﺎنﺳﺎزي، ﺗﻤﺎﻣﯽ ﻗﻮچﻫﺎي ﻣﻮﻟﺪ و ﺑﺮهﻫﺎي ﻧﺮ را از ﻣﯿﺶﻫﺎ ﺟﺪا ﮐﺮده و ﺣﺘﯽ اﻻﻣﮑﺎن ﺑﻪ ﻣﺤﻠﯽ دورﺗﺮ ﻣﻨﺘﻘﻞ ﻧﻤﺎﯾﯿﺪ. ﻫﻤﭽﻨﯿﻦ، ﺳﻼﻣﺖ ﻋﻤﻮﻣﯽ (اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ، وﺿﻌﯿﯿﺖ دﻧﺪانﻫﺎ، وﺿﻌﯿﺖ ﭘﺎﻫﺎ و ﺳﻢﻫﺎ، ﻣﺼﺮف ﺧﻮراك و...) و وﺿﻌﯿﺖ ﺟﻨﺴﯽ ﻧﺮﻫﺎي ﻣﻮﻟﺪ (اﻧﺪازه و ﺗﻘﺎرن ﺑﯿﻀﻪﻫﺎ و وﺟﻮد ﺗﻮدهﻫﺎي اﺣﺘﻤﺎﻟﯽ در آﻧﻬﺎ و ﻣﯿﻞ ﺟﻨﺴﯽ)، را ﮐﻨﺘﺮل ﻧﻤﻮده و در ﺻﻮرت ﻣﺸﺎﻫﺪه ﻫﺮﮔﻮﻧﻪ ﻣﺸﮑﻞ ﺑﺎ ﻣﺘﺨﺼﺼﯿﻦ ﺷﺮﮐﺖ ﻣﺸﻮرت ﻧﻤﺎﯾﯿﺪ.\n" +
                    "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﺟﻬﺖ اﻟﻘﺎي تخمک\u200Cرﯾﺰي ﺑﯿﺸﺘﺮ در ﻣﯿﺶﻫﺎ و ﺑﺰﻫﺎي ﻣﺎده، از ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ اﺳﺘﻔﺎده ﺷﻮد. ﺑﻬﺘﺮﯾﻦ ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ ﻣﻘﺪار 450 اﻟﯽ 500 ﮔﺮم ﺟﻮ ﯾﺎ ذرت ﺑﻠﻐﻮر ﺷﺪه و 200 گرم کنجاله سویا به همراه مکمل فسفر 90درصد ﻣﯽﺑﺎﺷﺪ ﮐﻪ سه ﻫﻔﺘﻪ ﭘﯿﺶ از ﺷﺮوع ﻫﻤﺰﻣﺎنﺳﺎزي ﺗﺎ ﺳﻪ ﻫﻔﺘﻪ ﭘﺲ از آن ﺑﻪ ﺻﻮرت ﺗﺪرﯾﺠﯽ اﺳﺘﻔﺎده ﺷﻮد.\n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("ﻫﺸﺪار")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ ﺗﻨﻬﺎ در ﻣﯿﺶﻫﺎي ﺑﺎ اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ ﭘﺎﯾﯿﻦ (ﻻﻏﺮ) ﻣﻮﺛﺮ ﺧﻮاﻫﺪ ﺑﻮد و در ﺻﻮرت ﻣﻨﺎﺳﺐ ﺑﻮدن اﻣﺘﯿﺎز وﺿﻌﯿﺖ ﺑﺪﻧﯽ، ﺗﻐﺬﯾﻪ ﻓﻼﺷﯿﻨﮓ به مدت یک هفته قبل تا 2 هفته بعد از قوچ اندازی انجام شود.\n\n" +
                    "-\tرﻋﺎﯾﺖ اﺻﻮل ﺗﻐﺬﯾﻪاي و وﺟﻮد ﻣﻘﺎدﯾﺮ ﮐﺎﻓﯽ اﻧﺮژي، ﭘﺮوﺗﺌﯿﻦ، ﻣﻮاد ﻣﻌﺪﻧﯽ و وﯾﺘﺎﻣﯿﻨﯽ در ﺟﯿﺮه ﻣﯿﺶﻫﺎ و ﻧﺮﻫﺎي ﻣﻮﻟﺪ ﻧﻘﺶ ﻣﻬﻤﯽ در میزان ﻓﺤﻠﯽ، درصد آﺑﺴﺘﻨﯽ، رﺷﺪ و ﻧﻤﻮ ﺟﻨﯿﻦ و ﻧﺮخ دو ﻗﻠﻮزاﯾﯽ دارد. ﮐﻤﺒﻮد ﻣﻮاد ﻣﻌﺪﻧﯽ ﮐﻢ ﻣﺼﺮف ﻫﻤﺎﻧﻨﺪ آﻫﻦ، ﻣﺲ، ﺳﻠﻨﯿﻮم، روي و...ﻣﯽﺗﻮاﻧﺪ ﻋﻤﻠﮑﺮد ﺗﻮﻟﯿﺪﻣﺜﻠﯽ دامﻫﺎي ﻣﻮﻟﺪ را ﺑﺸﺪت ﺗﺤﺖ ﺗﺎﺛﯿﺮ ﻗﺮار دﻫﺪ. \n" +
                    "در نظر داشته باشید که مکمل های معدنی ویتامینه فرموله شده توسط شرکت آراژن تمام کمبود های ویتامینه و معدنی دام را در تمام طول مدت همزمان سازی و آبستنی میتواند تامین کند .\n"

        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("ﻧﮑﺎت در زﻣﺎن اﻧﺟﺎم همزﻣﺎنﺳﺎزی\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "-\t در زﻣﺎن اﻧﺘﻘﺎل ﻫﻮرﻣﻮنﻫﺎ و اﺳﻔﻨﺞ / سیدر از ﯾﺨﭽﺎل ﺑﻪ ﻣﺤﻞ داﻣﺪاري، ﺣﺘﻤﺎ از ﯾﻮﻧﻮﻟﯿﺖ ﺣﺎوي ﯾﺦ اﺳﺘﻔﺎده ﮔﺮدد.\n\n" +
                    "-\tدﻣﺎي ﺑﺎﻻ و ﺗﺎﺑﺶ ﻧﻮر ﺧﻮرﺷﯿﺪ در ﺣﯿﻦ ﺣﻤﻞ، ﻣﯽﺗﻮاﻧﺪ ﻣﻮﺟﺐ از ﺑﯿﻦ رﻓﺘﻦ ﮐﺎراﯾﯽ ﻫﻮرﻣﻮنﻫﺎ ﮔﺮدد. \n"

        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("توجه")

    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append("بعضا بر اساس تصمیم متخصص شرکت از هورمون هایی استفاده می شود که برای حمل آنها نیازی به یخ نیست در این صورت کارشناسان شرکت با شما در تماس خواهند بود.\n\n")
    }

    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("هشدار")

    }
    pop()

    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "آﻣﺎده ﮐﺮدن ﻫﻮرﻣﻮنﻫﺎ و ﺗﺰرﯾﻘﺎت آﻧﻬﺎ ﺑﺎﯾﺪ در زﯾﺮ ﺳﺎﯾﻪ اﻧﺠﺎم ﮔﯿﺮد و از ﺗﺎﺑﺶ ﻧﻮر ﺧﻮرﺷﯿﺪ ﺑﻪ آﻧﻬﺎ اﺟﺘﻨﺎب ﮔﺮدد.\n\n"

        )
    }

    pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 40.sp))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("روش همزمان سازی اختصاصی گله شما\n")

    }
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = colorResource(R.color.green_dark)
        )
    ) {
        append("روز اول\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "ﻗﺮار دادن سیدر ﺗﻮﺳﻂ اﭘﻠﯿﮑﺎﺗﻮر در داﺧﻞ واژن و ﺗﺰرﯾﻖ 2 ﺳﯽﺳﯽ از\" ﻫﻮرﻣﻮن روز1 \" ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺑﻬﺘﺮﯾﻦ ﻣﺤﻞ ﺗﺰرﯾﻖ، ﭘﺸﺖ ﻋﻀﻠﻪ ران ﺑﻪ ﺻﻮرﺗﯽ ﮐﻪ ﺣﯿﻮان اﯾﺴﺘﺎده ﺑﺎﺷﺪ.\n"
        )
    }

}
val longText2 = buildAnnotatedString {
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("هشدار مجدد")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append("ﺗﻤﺎﻣﯽ داروها به جز سیدر ﭘﯿﺶ از اﺳﺘﻔﺎده ﺑﺎﯾﺪ در ﯾﺨﭽﺎل ﻧﮕﻬﺪاري ﺷﻮﻧﺪ.\n")
    }


    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "از اﺳﭙﺮي اﮐﺴﯽ ﺗﺘﺮا ﺳﺎﯾﮑﯿﻠﯿﻦ (او ﺗﯽ ﺳﯽ) ﺟﻬﺖ ﺿﺪﻋﻔﻮﻧﯽ سیدر ﭘﯿﺶ از ﻗﺮار دادن آﻧﻬﺎ در واژن اﺳﺘﻔﺎده بکنید. \n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = colorResource(R.color.green_dark)
        )
    ) {
        append("روز هفتم\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "ﭘﺲ از ﺑﯿﺮون ﮐﺸﯿﺪن سیدر در روز هفتم ﺗﺰرﯾﻘﺎت زﯾﺮ را ﺑﻼﻓﺎﺻﻠﻪ اﻧﺠﺎم دﻫﯿﺪ:\n" +
                    "1) ﺗﺰرﯾﻖ مقدار مشخص شده  از ﻫﻮرﻣﻮن شیشه ای \"روز  7\" .\n" +
                    "2) ﺗﺰرﯾﻖ 2 ﺳﯽﺳﯽ از ﻫﻮرﻣﻮن ﺗﺮﮐﯿﺒﯽ روز 7 (ﭘﻮدري + ویال شیشه ای 50 سی سی): ﺟﻬﺖ ﺗﻬﯿﻪ اﯾﻦ ﻣﺤﻠﻮل، ﺑﺎ اﺳﺘﻔﺎده از ﺳﺮﻧﮓ، 2ﺳﯽ ﺳﯽ از ﻣﺤﻠﻮل داﺧﻞ ویال بزرگ را ﮐﺸﯿﺪه و وارد ﺷﯿﺸﻪ ﺣﺎوي ﭘﻮدر ﻧﻤﺎﯾﯿﺪ. ﭼﻨﺪﯾﻦ ﺑﺎر ﺷﯿﺸﻪ را ﺳﺮوﺗﻪ ﻧﻤﺎﯾﯿﺪ ﺗﺎ ﭘﻮدر ﮐﺎﻣﻼ ﺣﻞ ﺷﻮد. از ﺗﮑﺎن دادن ﺷﺪﯾﺪ ﺧﻮدداري ﮐﻨﯿﺪ. ﭘﺲ از ﺣﻞ ﺷﺪن ﭘﻮدر، ﻣﺤﻠﻮل را ﺑﻪ ویال بزرگ اﻧﺘﻘﺎل دﻫﯿﺪ و ﭼﻨﺪ ﺑﺎر به آرامی ﺳﺮ و ﺗﻪ ﻧﻤﺎﯾﯿﺪ. ﺳﭙﺲ از اﯾﻦ ﻣﺤﻠﻮل 2 ﺳﯽﺳﯽ ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺑﻪ میش ها ﺗﺰرﯾﻖ ﻧﻤﺎﯾﯿﺪ.\n\n" +
                    "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﻗﺒﻞ از شروع کار، ﺑﺎ ﮐﺎرﺷﻨﺎﺳﺎن ﺷﺮﮐﺖ ﻣﺸﻮرت ﻧﻤﺎﯾﯿﺪ.\n"
        )
    }

}

val longText3 = buildAnnotatedString {

    pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 40.sp))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("ﻧﮑﺎت زﻣﺎن ﻗوچ اﻧدازی\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 17.sp,
            color = Color.Black
        )
    ) {
        append(
            "قوچ اندازی از 24 ساعت ﭘﺲ از ﺧﺎرج ﮐﺮدن سیدر و اﻧﺠﺎم ﺗﺰرﯾﻘﺎت ﻫﻮرﻣﻮﻧﯽ روز آخر، انجام\n" +
                    " می شود. \n" +
                    "ﺣﺘﯽ اﻻﻣﮑﺎن ﻧﺴﺒﺖ ﻗﻮچ ﺑﻪ ﻣﯿﺶ حداکثر یک به پنج را رﻋﺎﯾﺖ ﻓﺮﻣﺎﯾﯿﺪ، اﮔﺮﭼﻪ، ﺑﺴﺘﻪ ﺑﻪ ﺗﻮان ﻗﻮچ اﯾﻦ ﻧﺴﺒﺖ ﻣﯽﺗﻮاﻧﺪ ﺑﯿﺸﺘﺮ یا کمتر ﮔﺮدد.\n" +
                    "توصیه می شود قوچ اندازی دوم 7 ساعت پس از قوچ اندازی اول ترجیحا با یک قوچ دیگر انجام شود.\n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("هشدار")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append("از ﺟﺎﺑﺠﺎﯾﯽ ﻣﯿﺶﻫﺎ ﺑﻪ ﻣﺮﺗﻊ در روز ﻗﻮچ اﻧﺪازي ﺗﺎ 7 روز ﭘﺲ از آن اﮐﯿﺪا ﺧﻮدداري ﻓﺮﻣﺎﯾﯿﺪ.\n")
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("هشدار")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append("بهتر است پس از هر سری قوچ اندازی به قوچ ها سه روز استراحت داده شود.\n")
    }

    pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 40.sp))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("ﻧﮑﺎت نحوه قوچ اندازی\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append(
            "1)\tهر قوچ در فصل تولید مثل هر روز میتواند حداکثر 5 بار جفت گیری موفق و در خارج از فصل تولید مثل 3 بار جفت گیری موفق داشته باشد، بنابراین از اینکه یک قوچ با یک میش فحل چند بار جفت گیری کند باید جلوگیری کنید. \n" +
                    "2)\tبهتر است 24 ساعت پس از سیدر برداری قوچ ها را در گله رها کرده و بالای سر گله ایستاده و هر میشی که جفتگیری کرد با اسپری رنگی علامت زده و از گله جدا کنید. \n" +
                    "3)\tپس از اینکه تمام میش ها یکبار جفت گیری کردند حدودا 7 ساعت بعد از قوچ اندازی اول اجازه دهید بار دوم جفت گیری مجدد با قوچ دیگری انجام شود. \n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 40.sp))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("ﻧﮑﺎت ﭘس از ﻗوچ اﻧدازی\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append(
            "از اﻧﺠﺎم واﮐﺴﯿﻨﺎﺳﯿﻮن و ﯾﺎ ﻋﻤﻠﯿﺎت ﭘﺸﻢﭼﯿﻨﯽ، ﺳﻢﭼﯿﻨﯽ، اﻧﮕﻞزداﯾﯽ، ﺗﻐﯿﯿﺮات ﺷﺪﯾﺪ ﺟﯿﺮه و.....ﺗﺎ ﯾﮏ ﻣﺎه ﭘﺲ از ﻗﻮچ اﻧﺪازي ﺧﻮدداري ﻓﺮﻣﺎﯾﯿﺪ.\n" +
                    "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﺟﻬﺖ اﻓﺰاﯾﺶ دوﻗﻠﻮزاﯾﯽ و ﮐﺎﻫﺶ ﺑﺮﮔﺸﺖ ﻓﺤﻠﯽ، ﺑﻪ وﯾﮋه در ﺷﺮاﯾﻂ ﺗﻨﺶ ﮔﺮﻣﺎﯾﯽ ﺗﺎﺑﺴﺘﺎن، ﻣﻘﺪار 3 سی سی ﻫﻮرﻣﻮن ﮔﻨﺎدورﻟﯿﻦ (وﺗﺎروﻟﯿﻦ) ﺑﻪ ﺻﻮرت عضلانی،  سه روز ﭘﺲ از ﻗﻮچ اﻧﺪازي ﺑﻪ ﻫﻤﻪ ﻣﯿﺶﻫﺎ ﺗﺰرﯾﻖ ﺷﻮد. \n" +
                    "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد، دوازده ﺗﺎ بیست و یک روز ﭘﺲ از ﻗﻮچ اﻧﺪازي، ﻗﻮچﻫﺎي ﻣﻮﻟﺪ را در ﺑﯿﻦ ﻣﯿﺶﻫﺎ ﻧﮕﻬﺪاري ﻧﻤﺎﯾﯿﺪ.\n" +
                    " ﺟﯿﺮهﻫﺎي ﻓﻼﺷﯿﻨﮓ را ﺗﺎ سه ﻫﻔﺘﻪ ﭘﺲ از ﻗﻮچ اﻧﺪازي اداﻣﻪ دﻫﯿﺪ.\n" +
                    "ﺗﻮﺻﯿﻪ ﻣﯽﺷﻮد ﮐﻪ 45 روز ﭘﺲ از ﻗﻮچ اﻧﺪازي، ﺟﻬﺖ ﺗﺸﺨﯿﺺ آﺑﺴﺘﻨﯽ، ﺳﻮﻧﻮﮔﺮاﻓﯽ اﻧﺠﺎم ﮔﯿﺮد. \n" +
                    "دقت کنید ﻓﺮﻣﻮﻻﺳﯿﻮن ﺟﯿﺮه ﻣﯿﺶﻫﺎي ﺗﮏﻗﻞ و ﭼﻨﺪﻗﻞ از ﻣﺎه ﺳﻮم آﺑﺴﺘﻨﯽ زﯾﺮ ﻧﻈﺮ ﻣﺘﺨﺼﺺ ﺗﻐﺬﯾﻪ اﻧﺠﺎم ﮔﯿﺮد. \n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Red // رنگ قرمز
        )
    ) {
        append("هشدار")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append("اﺣﺘﯿﺎﺟﺎت ﺗﻐﺬﯾﻪاي ﻣﯿﺶﻫﺎ و ﺷﯿﺸﮏﻫﺎي ﺗﮏﻗﻞ و ﭼﻨﺪ ﻗﻞ ﺗﻔﺎوت زیادی ﺑﺎ ﯾﮑﺪﯾﮕﺮ داﺷﺘﻪ و ﻋﺪم اﺳﺘﻔﺎده از ﺟﯿﺮهﻫﺎي اﺧﺘﺼﺎﺻﯽ ﻣﻮﺟﺐ ﮐﺎﻫﺶ دوﻗﻠﻮزاﯾﯽ، اﻓﺰاﯾﺶ ﻣﺮده¬زاﯾﯽ و ﺗﻮﻟﺪ ﺑﺮهﻫﺎي ﺿﻌﯿﻒ ﺧﻮاﻫﺪ ﺷﺪ.\n")
    }
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append("لطفا ﺟﻬﺖ ﭘﯿﺸﮕﯿﺮي از ﺳﻘﻂﻫﺎي ﻋﻔﻮﻧﯽ، پنج ﺳﯽﺳﯽ اﮐﺴﯽ ﺗﺘﺮا ﺳﺎﯾﮑﯿﻠﯿﻦ ال آ 20 درﺻﺪ ﺑﻪ ﺻﻮرت ﻋﻀﻼﻧﯽ ﺑﻪ ﺗﻤﺎﻣﯽ ﻣﯿﺶﻫﺎ ﺗﺰرﯾﻖ ﻧﻤﺎﯾﯿﺪ و ﺗﺰرﯾﻘﺎت اي- ﺳﻠﻨﯿﻮم و آ-د-س در ﻣﺎه آﺧﺮ آﺑﺴﺘﻨﯽ ﺗﮑﺮار ﺷﻮد.\n"
        )
    }
    pushStyle(ParagraphStyle(textAlign = TextAlign.Center, lineHeight = 40.sp))
    withStyle(
        style = SpanStyle(
            fontSize = 20.sp,
            color = colorResource(R.color.blue_logo)
        )
    ) {
        append("پروتکل از شیر گیری\n")
    }
    pop()
    withStyle(
        style = SpanStyle(
            fontSize = 18.sp,
            color = Color.Black
        )
    ) {
        append(
            "برای انجام از شیر گیری بره ها به چند نکته توجه نمایید: \n" +
                    "1)\tبره باید حداقل 50 روز شیر بخورد بنابراین از شیرگیری زودهنگام بره یا آبستن سازی مادر میتواند باعث اختلال در رشد حیوان شود.\n" +
                    "2)\tبرای اینکه در مدت زمان اقتصادی بتوانید بره را از شیر بگیرید باید نکات تغذیه ای را رعایت کنید. بره در این سن نیاز به مکملهای معدنی ویتامینه در کنار خوراک مصرفی دارد که برای تهیه این مکملها میتوانید با شرکت آراژن در تماس باشید.\n" +
                    "3)\t50 تا 75 روز بعد از زایمان زمان از شیر گیری بره است.\n" +
                    "4)\tبرای این منظور ابتدا به مدت 3 روز، هر روز یک بار صبح و یک بار شب به مدت 2 ساعت بره را پیش مادرش رها کنید. \n" +
                    "5)\tسپس به مدت سه روز روزانه یک مرتبه به مدت 2 ساعت بره در کنار مادرش قرار بگیرد. \n" +
                    "6)\tبرای سه مرتبه یک روز در میان بره از مادرش به مدت 2 ساعت شیر بخورد. \n" +
                    "7)\tسپس هر دو روز یک بار (برای دو سری) بره از مادر شیر بخورد.\n" +
                    "8)\tبعد از این مدت بره از مادر کلا جدا شود. \n" +
                    "9)\tدقت کنید در طول این مدت پستانهای میش چک شود تا از در صورت وجود ورم پستان و عفونتهای مربوطه اقدام لازم در زمان مناسب انجام شود.\n" +
                    "10)\tترجیها در زمان شروع از شیرگیری و پایان آن به تمامی بره ها مولتی ویتامین تزریق نمایید. تزریق به صورت زیرجلدی و طبق توصیه شرکت سازنده انجام شود.\n" +
                    "واکسن هایی که باید زده شوند یا ممنوعیت تزریق دارند:\n" +
                    "همراهان گرامی لطفا به این موضوع توجه کنید که دامهای همزمان سازی شده نباید درگیر بیماری شوند، بنابراین به عنوان یک قانون کلی واکسیناسیون گله را قبل از شروع همزمان سازی انجام دهید. \n" +
                    "توجه کنید که در سیستم دامپزشکی دو نوع واکسن داریم، واکسن هایی که متولی تزریق آن اداره دامپزشکی می\u200Cباشد و واکسن هایی که خود دامدار باید تزریق کند. \n" +
                    "از ده روز قبل از شروع همزمان سازی تا آخر دوره آبستنی تزریق واکسن های شاربن، بروسلوز به هیچ عنوان نباید انجام شود، بنابراین اگر میخواهید واکسیناسیون انجام دهید این واکسن ها را دو هفته قبل از شروع همزمان سازی به گله تزریق کنید. \n" +
                    "برای واکسن های ppr و آبله نیز به طور اکید توصیه می\u200Cشود قبل از شروع همزمان سازی به دامها تزریق شوند. \n" +
                    "توجه کنید که طبق پروتکل\u200Cهای بهداشتی تزریق واکسن های ppr و آبله (واکسن\u200Cهای تولید موسسه رازی) در 40 روز آخر آبستنی منع تزریق دارند، ولی باز هم توصیه ما این است که واکسیناسیون را قبل از شروع همزمان سازی انجام دهید. \n" +
                    "واکسن\u200Cهای تب برفکی عموما در دوران آبستنی مشکل ساز نیستند مگر اینکه بر اساس بروشور واکسن منع مصرف در دوران آبستنی وجود داشته باشد. پیشنهاد می\u200Cشود واکسن تب برفکی 40 روز اول پس از جفت گیری و ماه آخر آبستنی تزریق نشود.\n" +
                    "دوباره تاکید می\u200Cکنیم که لطفا قبل از شروع همزمان سازی واکسن\u200Cهای مربوط به بیماری\u200Cهای رایج منطقه (ppr ، آبله و تب برفکی) تزریق شود.\n" +
                    "محلول تزریقی ای+سلنیوم، حدودا بیست روز مانده به زایمان به میش ها تزریق شود (بر اساس بروشور دارو) همچنین تزریق 1 دوز تزریقی ای+سلنیوم به بره در روز اول تولد و یک دوز در 15 روزگی فراموش نشود. \n" +
                    "\n" +
                    "در ﺻﻮرت ﻧﯿﺎز ﺑﻪ ﻫﺮﮔﻮﻧﻪ راﻫﻨﻤﺎﯾﯽ ﺑﺎ ﮐﺎرﺷﻨﺎﺳﺎن ﺷﺮﮐﺖ ﺗﻤﺎس ﺣﺎﺻﻞ ﻓﺮﻣﺎﯾﯿﺪ.\n" +
                    "\n" +
                    "لطفا برای گرفتن نتایج بهتر در روند کار ، بعد از همزمان سازی تعداد دام های فحل و آبستن خود  را ثبت کرده و به کارشناسان ما اطلاع دهید\n" +
                    "                                                                                                                       \n" +
                    "\"موفقیت نتیجه تصمیم و تلاش هوشمندانه شما در زمان مناسب می\u200Cباشد\"\n"

        )
    }
}*/
