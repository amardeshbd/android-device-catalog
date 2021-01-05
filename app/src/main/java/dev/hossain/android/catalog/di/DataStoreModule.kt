package dev.hossain.android.catalog.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.hossain.android.catalog.data.AppDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    @Provides
    internal fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    internal fun provideAndroidResoures(context: Context): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "device-catalog.db")
            // https://developer.android.com/training/data-storage/room/prepopulate#from-asset
            .createFromAsset("database/device-catalog.db")
            // https://developer.android.com/training/data-storage/room/migrating-db-versions#kotlin
            .fallbackToDestructiveMigration()
            .build()
    }
}
