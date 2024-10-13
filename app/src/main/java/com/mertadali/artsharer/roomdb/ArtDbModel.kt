package com.mertadali.artsharer.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("artsTable")
data class ArtDbModel(
    val artName : String?,
    val artistName : String?,
    val artYear : Int?,
    val imageUrl : String?,

    @PrimaryKey(autoGenerate = true)
    val uuid : Int? = null
)
