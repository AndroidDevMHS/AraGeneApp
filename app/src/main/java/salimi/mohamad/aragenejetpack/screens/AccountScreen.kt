package salimi.mohamad.aragenejetpack.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel


@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: SmsViewModel,
    viewModelDataStore: DataStoreViewModel
) {
    var showExitDialog by remember { mutableStateOf(false) }
    var showChangeAccount by remember { mutableStateOf(false) }
    val phoneNum by viewModelDataStore.userPhoneNumber.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = "user",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            lineHeight = 30.sp,
            text = "آراژنی عزیز حساب کاربری شما با شماره همراه\n$phoneNum\nثبت شده است",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.sans_bold)),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        TextButton(onClick = {
            showChangeAccount = true
        }) {
            Text(
                text = "تغییر شماره همراه",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.sans_bold)),
                color = colorResource(R.color.blue2_logo)
            )
            Icon(
                Icons.Rounded.Edit,
                contentDescription = "Edit phone number",
                tint = colorResource(R.color.blue2_logo)
            )
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Button(
                onClick = {
                    showExitDialog = true
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text(
                    text = "خروج از حساب کاربری",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.sans_bold))
                )
            }
        }
        if (showExitDialog) {
            ExitAccountDialog({ showExitDialog = false }, navController, viewModelDataStore)
        }

        if (showChangeAccount)
            ChangeNumber(
                onDismiss = { showChangeAccount = false },
                viewModel = viewModel,
                dataStore = viewModelDataStore
            )
    }
}
