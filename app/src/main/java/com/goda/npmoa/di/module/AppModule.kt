package com.goda.npmoa.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.goda.npmoa.BuildConfig
import com.goda.npmoa.data_layer.local.db.AppDatabase
import com.goda.npmoa.data_layer.remote.network.ApiService
import com.goda.npmoa.di.ApiInfo
import com.goda.npmoa.di.DatabaseInfo
import com.goda.npmoa.di.PreferenceInfo
import com.goda.npmoa.utils.NYPConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    val httpLoggingInterceptor by lazy {
        return@lazy HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(
            ApiService::class.java)
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return NYPConstants.DB_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return NYPConstants.PREF_NAME
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@PreferenceInfo prefName: String, context: Context): SharedPreferences {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }
}