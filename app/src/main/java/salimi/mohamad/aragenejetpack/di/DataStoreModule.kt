package salimi.mohamad.aragenejetpack.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import salimi.mohamad.aragenejetpack.data.dataStore.DataStoreRepositoryImpl
import salimi.mohamad.aragenejetpack.data.repository.DataStoreRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ):DataStoreRepository=DataStoreRepositoryImpl(app)
}