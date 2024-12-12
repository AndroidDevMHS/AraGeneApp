package salimi.mohamad.aragenejetpack.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import checkAndRequestNotificationPermission
import dagger.hilt.android.AndroidEntryPoint
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.PublicDialog
import salimi.mohamad.aragenejetpack.screens.navGrph.SetupNavGraph
import salimi.mohamad.aragenejetpack.ui.theme.AraGeneJetPackTheme
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.FahliCheckDbViewModel
import salimi.mohamad.aragenejetpack.viewModel.PlannerViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel


lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var dPermission = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (!isGranted) {
                dPermission.value = true
            }
        }
        setContent {
            val viewModelSms by viewModels<SmsViewModel>()
            val viewModelDataStore by viewModels<DataStoreViewModel>()
            val viewModelDataBase by viewModels<FahliCheckDbViewModel>()
            val viewModelPlanner by viewModels<PlannerViewModel>()



            AraGeneJetPackTheme {
                if (dPermission.value) {
                    PublicDialog(
                        { dPermission.value = false },
                        { checkAndRequestNotificationPermission(this) },
                        painterResource(R.drawable.exclamation),
                        "توجه",
                        "برای نمایش اعلان ها در قسمت چک لیست همزمان سازی نیاز به تایید دریافت اعلان ها است"
                    )
                }
                SetupNavGraph(
                    context = this@MainActivity,
                    viewModelSms,
                    viewModelDataStore,
                    viewModelDataBase,
                    viewModelPlanner
                )
            }

        }
        checkAndRequestNotificationPermission(this)
    }
}
