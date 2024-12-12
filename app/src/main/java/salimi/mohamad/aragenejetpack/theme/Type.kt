package salimi.mohamad.aragenejetpack.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import salimi.mohamad.aragenejetpack.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,

        ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.sans_bold))),
)