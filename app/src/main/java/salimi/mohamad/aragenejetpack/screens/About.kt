@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.utils.isInternetAvailable

@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun About() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(top = 20.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "شرکتی پیشرو، با هدف اجرای پروژه های اصلاح نژادی متنوع و نیز بهینه سازی تولیدات دامی و کشاورزی",
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(
                textAlign = TextAlign.Center

            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = " شماره تماس با کارشناسان:",
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(
                textAlign = TextAlign.Center,
                textDirection = TextDirection.Rtl
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            modifier = Modifier.clickable {
                makePhoneCall(context)
            },
            text = "024-91010313",
            fontSize = 20.sp,
            color = colorResource(R.color.blue2_logo),
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(

                textAlign = TextAlign.Center,
                textDirection = TextDirection.Rtl
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "آدرس سایت:",
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(
                textAlign = TextAlign.Center,
                textDirection = TextDirection.Rtl
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "www.aragene.ir",
            modifier = Modifier.clickable {
                if (isInternetAvailable(context)) {
                    openWebsite(context, "www.aragene.ir")
                } else {
                    Toast.makeText(
                        context,
                        "لطفا دستگاه خود را به اینترنت متصل کنید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            fontSize = 18.sp,
            color = colorResource(R.color.blue2_logo),
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(
                textAlign = TextAlign.Center,
                textDirection = TextDirection.Rtl
            )
        )

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "آدرس صفحه اینستاگرام:",
            fontSize = 20.sp,
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(
                textAlign = TextAlign.Center,
                textDirection = TextDirection.Rtl
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "aragene.vira",
            modifier = Modifier
                .height(48.dp)
                .clickable {
                    if (isInternetAvailable(context)) {
                        openInstagram(context, "aragene.vira")
                    } else {
                        Toast
                            .makeText(
                                context,
                                "لطفا دستگاه خود را به اینترنت متصل کنید",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                },
            fontSize = 18.sp,
            color = colorResource(R.color.blue2_logo),
            fontFamily = FontFamily(
                Font(R.font.sans_bold)
            ),
            style = TextStyle(
                textAlign = TextAlign.Center,
                textDirection = TextDirection.Rtl
            )
        )
    }

}

private fun makePhoneCall(context: Context) {
    val callIntent = Intent(Intent.ACTION_DIAL)
    callIntent.data = Uri.parse("tel: 02491010313")
    context.startActivity(callIntent)
}

@SuppressLint("QueryPermissionsNeeded")
private fun openInstagram(context: Context, username: String) {
    val uri = Uri.parse("http://instagram.com/_u/$username")
    val instagramIntent = Intent(Intent.ACTION_VIEW, uri)
    instagramIntent.setPackage("com.instagram.android")

    if (instagramIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(instagramIntent)
    } else {
        val webUri = Uri.parse("http://instagram.com/$username")
        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
        context.startActivity(webIntent)
    }
}

private fun openWebsite(context: Context, url: String) {
    val websiteIntent = Intent(Intent.ACTION_VIEW)
    websiteIntent.data = Uri.parse("https://$url/")
    context.startActivity(websiteIntent)
}
