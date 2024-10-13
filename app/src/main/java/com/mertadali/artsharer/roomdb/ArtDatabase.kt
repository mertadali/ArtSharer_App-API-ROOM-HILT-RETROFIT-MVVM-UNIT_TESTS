package com.mertadali.artsharer.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArtDbModel::class], version = 1)
 abstract class ArtDatabase : RoomDatabase(){
     abstract fun artDao() : ArtDao
}