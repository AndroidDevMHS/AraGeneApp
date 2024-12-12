package salimi.mohamad.aragenejetpack.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ایجاد DataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")

// کلید برای ذخیره وضعیت لاگین
val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")

// خواندن وضعیت لاگین
fun Context.isLoggedIn(): Flow<Boolean> {
    return dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN_KEY] ?: false
    }
}

// ذخیره وضعیت لاگین
suspend fun Context.setLoggedIn(isLoggedIn: Boolean) {
    dataStore.edit { preferences ->
        preferences[IS_LOGGED_IN_KEY] = isLoggedIn
    }
}
