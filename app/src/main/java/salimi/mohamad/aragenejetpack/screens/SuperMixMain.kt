package salimi.mohamad.aragenejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R

@Preview(showBackground = true)
@Composable
fun SuperMixScreen() {

    val longText1 = buildAnnotatedString {
        // وسط‌چین کردن فقط این خط
        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = colorResource(R.color.blue_logo)
            )
        ) {
            append("ﺑﺎ عرض ﺳﻼم\n")
        }
        pop() // پایان استایل پاراگراف

        // خطوط دیگر بدون وسط‌چین
        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "این پروتکل به عنوان راهنما و دستورالعمل استفاده از سوپرمیکس ۲۰ درصد، برای حصول افزایش وزن بیشتر در مقایسه " +
                        "با روشهای رایج پرواربندی بره تنظیم شده است. قبل از توضیح روش پرواربندی، لازم است چند نکته را مد نظر داشته باشید:\n" +
                        "اول اینکه سوپرمیکس 20% به عنوان یک مکمل پروتئینه-معدنی با فرمولاسیون خاص شرکت آراژن می باشد و به تنهایی " +
                        "پاسخگوی نیاز بره های پرواری نیست.\n\n" +
                        "ثانیا روش تغذیه با سوپرمیکس 20% سیستم تغذیه ای جدیدی است که در این دستورالعمل به تفصیل به آن پرداخته شده است. " +
                        "بنابراین، لطفاً خارج از دستورالعمل حاضر برای تغذیه دام\u200Cهایتان اقدامی انجام ندهید و صرفا با کارشناسان شرکت مشورت " +
                        "نمایید.\n\n" +
                        "ثالثا لطفاً توجه کنید که از لحاظ زمانبندی، سیستم تغذیه آراژن به ۲ دوره سازگاری (عادت دهی) و دوره اصلی پرواربندی " +
                        "تقسیم می\u200Cشود.\n\n" +
                        "در هر دو دوره، بره\u200Cهای پرواری از کنسانتره تهیه شده با سوپرمیکس و علوفه تغذیه می\u200C شوند و یکی از اهداف و " +
                        "ویژگی های این سیستم پرواربندی این است که با رسیدن به انتهای دوره سازگاری، میزان مصرف روزانه علوفه به حداقل\n" +
                        "برسد. از طرف دیگر، توجه داشته باشید که برای ساخت کنسانتره، به دانه کامل (سالم) غلات نیاز است. غلات مورد استفاده " +
                        "برای کنسانتره شامل گندم، جو و ذرت می باشد.\n"
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
            append(
                "نگران استفاده از دانه کامل برای تغذیه بره ها نباشید چرا که تحقیقات و بررسی\u200Cهای ما نشان داده است که طبق این\n" +
                        "روش میزان دفع دانه کامل در مدفوع نزدیک به صفر است و هضم و جذب دانه\u200Cها به صورت کامل صورت می\u200Cگیرد.\n\n" +
                        "میزان خوراک مصرفی روزانه برههای پرواری حدود 4 درصد وزن بدن میباشد و مصرف کنسانتره به صورت متوسط\n" +
                        "بسته به وزن شروع پروار بین ۱.4 الی ۲ کیلوگرم در روز و ۱۲۰ الی ۱۴۰ کیلوگرم در طی دوره پرواربندی می\u200Cباشد. از\n" +
                        "این مقدار ۱۲۰ الی ۱۴۰ کیلوگرم کنسانتره حدود ۲۰ درصد سوپرمیکس، ۲۰ درصد گندم، 25 درصد جو و 35 درصد\n" +
                        "ذرت می\u200Cباشد.\n"
            )
        }
        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 18.sp,
                color = Color.Red // رنگ قرمز
            )
        ) {
            append("احتیاط")
        }
        pop()
        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "دوباره تاکید می\u200Cشود که غلات باید به صورت کامل و سالم بوده و از غلات آرد شده استفاده نکنید.\n" +
                        "بسته به وزن شروع و پایان پرواربندی، مقدار یونجه و کاه مورد نیاز هر دام در طول دوره ۱۵ الی ۲۰ کیلوگرم در نظر\n\n" +
                        "گرفته شود.\n"
            )
        }
        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = colorResource(R.color.blue_logo)
            )
        ) {
            append("خرید و انتخاب بره برای پرواربندی\n")
        }
        pop()
        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "توجه کنید که برای پرواربندی، بره\u200Cهایی را تهیه نمایید که شیر سوز نباشند. بره های شیرسوز دارای جثه کوچک،" +
                        "استخوان بندی ضعیف پشم\u200Cهای ژولیده و درهم رفته و رنگ پریده هستند. در نژادهای دنبه\u200Cدار بره\u200Cهای شیر سوز این" +
                        "نژادها دارای دنبه\u200Cهای تحلیل رفته و بسیار کوچک هستند. یکی از راه\u200Cهای تشخیص شیرسوز بودن بره، وزن بسیار کم" +
                        "آن نسبت به سن بره می\u200Cباشد. توجه کنید که در بره\u200Cهای نژادهای درشت جثه میزان وزن\u200Cگیری روزانه در حدود ۲۵۰" +
                        "گرم و در نژادهای کوچک جثه میزان وزنگیری بره باید در حدود ۱۵۰ گرم باشد تا بتوان گفت که بره شیرسوز نیست.\n" +
                        "برای مثال در نظر بگیرید که بره چهار ماهه\u200Cای از نژاد افشاری دارای وزن ۱۶ کیلوگرم است. بنابراین، این بره که وزن" +
                        "تولدی در حدود ۴ کیلوگرم داشته و در طول ۴ ماه از زمان تولد تنها ۱۲ کیلو افزایش وزن داشته است. به این معنا که هر" +
                        "ماه ۳ کیلو افزایش وزن داشته و روزانه تقریبا تنها ۱۰۰ گرم به وزن آن افزوده شده است. بنابراین، این بره یک بره " +
                        "شیرسوز می\u200Cباشد. درحالیکه بره 4 ماهه معمولی از نژاد افشاری بایست حداقل 30 تا 35 کیلوگرم وزن داشته باشد.\n\n" +
                        "اولین نکته\u200Cای که باید در خرید بره\u200Cها در نظر بگیرید، ترشحات چشم و بینی می\u200Cباشد. به این معنا که بره دارای ترشحات " +
                        "چشم و بینی می\u200Cتواند علائمی از مبتلا بودن به انگل و یا پنومونی از خود نشان دهد که در بسیاری از موارد توسط " +
                        "دامپزشک قابل درمان است، اما در پرواربندی می\u200Cتواند مدت زمان پرواربندی را افزایش دهد، پس بنابراین، اگر " +
                        "می\u200Cخواهید بره پای پروار بخرید، حیوان دارای ترشحات بینی و چشم را انتخاب نکنید. بره\u200Cها نباید دارای غده\u200C های چرکی" +
                        "در قسمت کشاله ران یا سردست و اطراف گوش باشند. توجه داشته باشید که بره\u200Cها نباید دارای عفونت\u200Cهای مفاصل باشند، " +
                        "این عفونت\u200Cها بیشتر در قسمت بالای سم ایجاد شده و موجب لنگش در بره\u200Cها می\u200Cشوند و به احتمال زیاد توانایی آلوده " +
                        "کردن کل گله را دارند.\n\n" +
                        "دهان بره\u200Cها را به صورت دقیق بررسی کرده تا از عدم وجود زخم در قسمت لب، لثه و یا زبان مطمئن شوید، این زخم\u200Cها " +
                        "نشان دهنده بیماری\u200Cهای مانند تب برفکی، طاعون نشخوار کنندگان کوچک، و اکتیما هستند. هر سه این بیماری\u200Cها موجب " +
                        "تلفات شده و افزایش وزن روزانه بره\u200Cها را به شدت تحت تاثیر قرار می\u200Cدهند.\n\n" +
                        "بره\u200Cها را از لحاظ عدم وجود کنه در قسمت داخلی پاهای جلویی و قسمت داخلی گوش\u200Cها و دنبالچه حیوان مورد بررسی " +
                        "قرار دهید. سعی کنید بره\u200Cهایی تهیه کنید که نیاز به درمان خاصی نداشته باشند. مثلاً بره\u200Cها دچار بیماری\u200Cهای پوستی مانند " +
                        "قارچ و جرب نباشند. هرچند این بیماری\u200Cها قابل درمان هستند، ولی هزینه کارگری و دارو و درمان را به واحد تولیدی " +
                        "تحمیل می\u200Cکنند.\n\n" +
                        "نکته مهم در خرید بره\u200Cهای پرواری، توجه به اسکلت آنها می\u200Cباشد، هرچه اندازه اسکلت بره\u200Cها درشت تر باشد، میانگین " +
                        "افزایش وزن روزانه آنها بیشتر خواهد بود. \n\n"
            )
        }
        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = colorResource(R.color.blue_logo)
            )
        ) {
            append("وزن شروع پرواربندی\n")
        }
        pop()
        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "برای پرواربندی توصیه می\u200Cشود از بره\u200Cهای بزرگ جثه از نژاد افشاری، قزل افشار، قره قزل، لری بختیاری، شال،" +
                        "مغانی و... استفاده شود. نژادهای خارجی مانند لاکن، رومن و اصف که در دامداری کنونی کشور جایگاه پیدا کردند نیز" +
                        "جزو نژادهای مناسب پروار محسوب می\u200Cشوند. وزن شروع برای پرواربندی در نژادهای بزرگ جثه بین 35 تا 37 " +
                        "کیلوگرم مناسب است.\n" +
                        "اما برای انتخاب بره به منظور پرواربندی یک قانون کلی را در نظر بگیرید و آن اینکه، بره\u200Cهایی که دارای استخوان " +
                        "بندی درشت و سالم هستند ولی از لحاظ عضلانی لاغر می\u200Cباشند، اولویت اول در خرید و انتخاب بره پای پروار هستند.\n" +
                        "توجه نمایید که این نکته برای دام\u200Cهای کوچک جثه نیز صادق است.\n"
            )
        }

        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "برای پرواربندی توصیه می\u200Cشود از بره\u200Cهای بزرگ جثه از نژاد افشاری، قزل افشار، قره قزل، لری بختیاری، شال،" +
                        "مغانی و... استفاده شود. نژادهای خارجی مانند لاکن، رومن و اصف که در دامداری کنونی کشور جایگاه پیدا کردند نیز" +
                        "جزو نژادهای مناسب پروار محسوب می\u200Cشوند. وزن شروع برای پرواربندی در نژادهای بزرگ جثه بین 35 تا 37 " +
                        "کیلوگرم مناسب است.\n" +
                        "اما برای انتخاب بره به منظور پرواربندی یک قانون کلی را در نظر بگیرید و آن اینکه، بره\u200Cهایی که دارای استخوان " +
                        "بندی درشت و سالم هستند ولی از لحاظ عضلانی لاغر می\u200Cباشند، اولویت اول در خرید و انتخاب بره پای پروار هستند.\n" +
                        "توجه نمایید که این نکته برای دام\u200Cهای کوچک جثه نیز صادق است.\n"
            )
        }

        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 18.sp,
                color = Color.Red // رنگ قرمز
            )
        ) {
            append("نکته\n")
        }
        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "هدف از پرواربندی، تولید گوشت لخم می\u200Cباشد. پس در انتخاب بره\u200Cها حتماً به این نکته توجه کنید که به هیچ عنوان " +
                        "چاق نباشند.\n"
            )
        }
        pop()

        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "نژادهای کوچک جثه ایرانی مانند نژاد زل، نایینی، تالشی، بلوچی و ... می\u200Cباشند و نژاد رومانوف نیز به عنوان یک نژاد" +
                        "کوچک جثه خارجی مطرح است. برای دامهای کوچک جثه پرواربندی را از وزن\u200Cهای پایین\u200Cتر به عنوان مثال ۲۰ تا ۲۵ " +
                        "کیلو شروع کنید. این نژادها افزایش وزن روزانه بالایی ندارند ولی در مقابل مقدار خوراک مصرفی آنها نیز کمتر است.\n" +
                        "ترجیحا بره\u200Cها را در ساعات ابتدایی روز خریداری کنید. چون در این ساعات تشخیص یک سری از بیماری\u200Cها ساده\u200Cتر" +
                        "بوده و فروشنده فرصت چندانی برای خوراندن خوراک بیش از حد به منظور سنگین\u200Cتر شدن حیوان را ندارد. دوباره تاکید " +
                        "می\u200Cکنیم که ملاک انتخاب بره، جثه درشت و لاغر بودن آن است. به عنوان مثال از خرید بره\u200Cهایی که مثلاً ۳۵ کیلو وزن " +
                        "دارند ولی به شدت چاق هستند خودداری کنید.\n" +
                        "بره\u200Cهایی که برای دوره پرواربندی در نظر گرفته می\u200Cشوند دو حالت دارند: یا بره\u200Cهایی هستند که از زمان تولد در " +
                        "دامداری خودتان بودند و اکنون تصمیم به پرواربندی آنها گرفته\u200Cاید و یا بره\u200Cهایی هستند که به منظور پرواربندی خریداری " +
                        "شدند.\n" +
                        "پس از آنکه تصمیم به پرواربندی در دامداری گرفتید و بره\u200Cها برای پرواربندی حاضر شدند، باید اقداماتی را قبل از " +
                        "شروع دوره انجام دهید.\n"
            )
        }

        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = colorResource(R.color.blue_logo)
            )
        ) {
            append("اقدامات مربوط به حمل و نقل\n")
        }
        pop()
        // کلمه با رنگ متفاوت
        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black // رنگ قرمز
            )
        ) {
            append(
                "در صورتی که بره\u200Cها را خریداری کردید و می\u200Cخواهید آنها را به دامداری خودتان حمل کنید، در نظر بگیرید که کاهش " +
                        "وزن و پنومونی دو مشکل عمده در طی فرایند حمل و نقل می\u200Cباشد\u200C. برای کنترل پنومونی می\u200Cتوانید از تزریق اکسی " +
                        "تتراسایکلین ۲۰ درصد ال ای (طولانی الاثر) به میزان توصیه شده توسط شرکت سازنده در بروشور دارو، استفاده " +
                        "کنید. بهتر است این دارو را یک روز قبل از بارگیری حیوان تزریق کنید. توجه کنید که یکی از عوامل اصلی پنومونی\n" +
                        "در معرض باد سرد قرار گرفتن حیوان می\u200Cباشد. بنابراین، برای حمل و نقل در صورت امکان از ماشین\u200Cهای دارای چادر " +
                        "استفاده کنید. عموما کاهش وزن به دلیل حمل و نقل به خاطر دفع ادرار و تخلیه روده حیوان می\u200Cباشد. اما کاهش وزن " +
                        "بیشتر که به دلیل از دست دادن مقداری از عضلات است، توسط مولتی ویتامین\u200Cهای دارای آمینو اسید قابل کنترل است. " +
                        "یکی از این مولتی ویتامین\u200Cها که پاسخ دهی مناسبی داشته است، مولتی آمینوجکت شرکت رویان دارو می\u200Cباشد. لطفا " +
                        "مصرف این دارو نیز مطابق با توصیه شرکت سازنده انجام گیرد. در صورت امکان قبل از حمل و نقل، در آبخوری " +
                        "بره\u200Cهای پای پروار خریداری شده، مقداری مولتی ویتامین دارای الکترولیت (که عموماً برای طیور استفاده می\u200Cگردد) حل " +
                        "شود. این مورد نیز می\u200Cتواند کاهش وزن ناشی از حمل و نقل را در حیوان تا حدی کنترل کند.\n"
            )
        }
        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = colorResource(R.color.blue_logo)
            )
        ) {
            append("اقدامات شروع پرواربندی\n")
        }
        pop()

        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "اولین اقدامی که برای شروع پرواربندی باید انجام دهید، هویت گذاری (با استفاده از پلاک گوش) و وزنکشی می\u200Cباشد.\n" +
                        "وزن کشی بره\u200Cها را به صورت شکم خالی (18 ساعت قطع خوراک و آب) انجام دهید و وزن تک تک بره\u200Cها را با " +
                        "شماره گوش آنها بنویسید.\n" +
                        "پلاک گوش\u200Cهای پلاستیکی در دامداری\u200Cهایی که از تور مرغی و فنس برای جداسازی فضاها استفاده نمی\u200Cکنند، مناسب " +
                        "است. چنانچه در دامداری\u200Cتان از توری\u200Cهای فلزی استفاده می\u200Cکنید، بهتر است از پلاک گوش فلزی برای هویتگذاری " +
                        "بره\u200Cها استفاده کنید. چنانچه می\u200Cخواهید از پلاک گوش پلاستیکی در دام\u200Cهایتان استفاده کنید، بهتر است پلاک\u200Cها را در " +
                        "قسمت جلویی گوش نصب کنید.\n"
            )
        }
    }
    val longText2 = buildAnnotatedString {
        pushStyle(ParagraphStyle(textAlign = TextAlign.Center))
        withStyle(
            style = SpanStyle(
                fontSize = 20.sp,
                color = colorResource(R.color.blue_logo)
            )
        ) {
            append("کنترل انگل\u200Cهای خارجی\n")
        }
        pop()

        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(
                "مهمترین انگل\u200Cهای خارجی که در بره رویت می\u200Cشوند کنه، جرب و کک می\u200Cباشند. کنه موجب ایجاد زردی در بره می\u200Cشود که باعث ایجاد تب و تلفات بالا در بره می¬گردد. جرب نیز آسایش حیوان را به شدت تحت تاثیر قرار داده و باعث کاهش کیفیت پوست و پشم حیوان می\u200Cشود. همچنین امکان سرایت جرب به انسان و حیوانات دیگر بسیار بالاست. کک انگل خارجی دیگری است که می\u200Cتواند موجب کمخونی شدید شود. محل تخمریزی این انگل بستر دامداری بوده و برای کنترل آن باید بستر تخلیه شده و دام سمپاشی شود. هنگام خرید سموم توجه داشته باشید که قابلیت استفاده برای دام و جایگاه را به صورت همزمان داشته باشند."
            )
        }


        withStyle(
            style = SpanStyle(
                fontSize = 17.sp,
                color = Color.Black
            )
        ) {
            append(" (دﻣﺎي  4 ﺗﺎ 8 درﺟﻪ ﺳﺎﻧﺘﯽﮔﺮاد) ﻗﺮار دﻫﯿﺪ. \n\n")
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = longText1,
                lineHeight = 30.sp,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 20.sp,
                    textDirection = TextDirection.Rtl, textAlign = TextAlign.Justify
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painter = painterResource(R.drawable.img_3), "")
                Image(painter = painterResource(R.drawable.img_4), "")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = longText2,
                lineHeight = 30.sp,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.sans_bold)),
                    fontSize = 20.sp,
                    textDirection = TextDirection.Rtl, textAlign = TextAlign.Justify
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}