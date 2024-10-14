package com.mertadali.artsharer.repository

import androidx.lifecycle.LiveData
import com.mertadali.artsharer.model.ImageResponse
import com.mertadali.artsharer.roomdb.ArtDbModel
import com.mertadali.artsharer.util.Resource

/*
Bu proje için test yazacağız. Ancak internetten veri çekerken veya threading işlemlerinde test yazılmaz. Bu sebeple fake bir repo oluşturuyoruz.
 */

interface ArtRepositoryInterface {
    // -> Room

    suspend fun insertArts(art : ArtDbModel)

    suspend fun deleteArts(art: ArtDbModel)

     fun observeArts() : LiveData<List<ArtDbModel>>

     // -> APİ

     suspend fun searchImage(imageString: String) : Resource<ImageResponse>  //-> ERROR, LOADİNG, SUCCESS olabilir bu sebeple utilde bir resource kullanacağız.


}