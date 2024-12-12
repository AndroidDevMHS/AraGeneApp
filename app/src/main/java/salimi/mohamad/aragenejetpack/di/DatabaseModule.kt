package salimi.mohamad.aragenejetpack.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import salimi.mohamad.aragenejetpack.data.db.FahliDatabase
import salimi.mohamad.aragenejetpack.data.db.PlannerDao
import salimi.mohamad.aragenejetpack.data.repository.PlannerRepository
import salimi.mohamad.aragenejetpack.utils.Constants.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        FahliDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: FahliDatabase) = database.checkListDao()

    @Singleton
    @Provides
    fun providePlannerDao(database: FahliDatabase): PlannerDao {
        return database.plannerDao()
    }

    @Singleton
    @Provides
    fun provideRepository(plannerDao: PlannerDao)=PlannerRepository(plannerDao)
}