package com.internship.recorderapp.di

import android.content.Context
import androidx.room.Room
import com.internship.recorderapp.RecorderApplication
import com.internship.recorderapp.data.local.datastore.DataStoreRepository
import com.internship.recorderapp.data.local.room.LocalDataRepository
import com.internship.recorderapp.data.local.room.LocalDatabase
import com.internship.recorderapp.data.usecases.DeleteRecordUseCase
import com.internship.recorderapp.data.usecases.InsertNewRecordUseCase
import com.internship.recorderapp.data.usecases.RecordsUseCases
import com.internship.recorderapp.data.usecases.SelectAllUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApp(@ApplicationContext app: Context) = app as RecorderApplication

    @Provides
    @Singleton
    fun provideLocalDb(app: RecorderApplication): LocalDatabase {
        return Room.databaseBuilder(
            app,
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRoomRepository(db: LocalDatabase): LocalDataRepository {
        return LocalDataRepository(db.roomDao)
    }

    @Provides
    @Singleton
    fun provideRecordsUseCases(repository: LocalDataRepository): RecordsUseCases {
        return RecordsUseCases(
            insertNewRecordUseCase = InsertNewRecordUseCase(repository),
            selectAllUseCase = SelectAllUseCase(repository),
            deleteRecordUseCase = DeleteRecordUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDatastore(app: RecorderApplication): DataStoreRepository {
        return DataStoreRepository(app)
    }

}