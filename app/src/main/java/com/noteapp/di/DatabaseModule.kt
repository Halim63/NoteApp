package com.noteapp.di

import android.app.Application
import androidx.room.Room
import com.noteapp.repos.NoteDao
import com.noteapp.repos.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(context: Application): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "image_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideImageDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.getNoteDao()
    }
}