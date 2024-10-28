package com.mertadali.artsharer.dependecy_injection

import android.content.Context
import androidx.room.Room
import com.mertadali.artsharer.roomdb.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppModuleForTest {


    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context,ArtDatabase::class.java)
            .allowMainThreadQueries().build()


}