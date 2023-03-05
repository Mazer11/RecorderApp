package com.internship.recorderapp.di

import android.content.Context
import androidx.room.Room
import com.internship.recorderapp.RecorderApplication
import com.internship.recorderapp.data.local.datastore.DataStoreRepository
import com.internship.recorderapp.data.local.room.LocalDataRepository
import com.internship.recorderapp.data.local.room.LocalDatabase
import com.internship.recorderapp.data.remote.retrofit.RetrofitApi
import com.internship.recorderapp.data.remote.retrofit.RetrofitRepository
import com.internship.recorderapp.data.usecases.*
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.vk.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): RetrofitApi =
        retrofit.create(RetrofitApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofitRepository(api: RetrofitApi): RetrofitRepository {
        return RetrofitRepository(api)
    }

    @Provides
    @Singleton
    fun providePostRecordUseCase(repository: RetrofitRepository): PostRecordUseCase {
        return PostRecordUseCase(repository)
    }
}