package salimi.mohamad.aragenejetpack.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import salimi.mohamad.aragenejetpack.screens.navGrph.SetupNavGraph
import salimi.mohamad.aragenejetpack.ui.theme.AraGeneJetPackTheme
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.FahliCheckDbViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<SmsViewModel> ()
            val viewModelDataStore by viewModels<DataStoreViewModel>()
            val viewModelDataBase by viewModels<FahliCheckDbViewModel>()
             AraGeneJetPackTheme {
                SetupNavGraph(context = this@MainActivity,viewModel,viewModelDataStore,viewModelDataBase)
            }

        }
    }
}
