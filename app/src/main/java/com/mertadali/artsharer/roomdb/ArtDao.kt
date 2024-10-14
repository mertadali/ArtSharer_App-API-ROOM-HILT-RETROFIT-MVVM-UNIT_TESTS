package com.mertadali.artsharer.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDao {

    // Suspend function -> using with Coroutines to make asynchronous process. At the same time it help resume & pause.
    //  suspend kelimesi ise bu fonksiyonun kotlin coroutines kullanarak asenkron çalışacağını belirtir

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArts(art : ArtDbModel)

    @Delete
    suspend fun deleteArts(art : ArtDbModel)

    @Query("SELECT * FROM artsTable")
     fun observeArts() : LiveData<List<ArtDbModel>>



}