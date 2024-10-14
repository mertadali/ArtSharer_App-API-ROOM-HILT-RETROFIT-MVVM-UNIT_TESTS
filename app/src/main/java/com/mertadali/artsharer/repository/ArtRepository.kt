package com.mertadali.artsharer.repository

import androidx.lifecycle.LiveData
import com.mertadali.artsharer.api.RetrofitAPI
import com.mertadali.artsharer.model.ImageResponse
import com.mertadali.artsharer.roomdb.ArtDao
import com.mertadali.artsharer.roomdb.ArtDbModel
import com.mertadali.artsharer.util.Resource
import javax.inject.Inject

// View model dışında bir sınıftan faydalanmak Test yazmamıza kolaylık sağlayacak.

class ArtRepository @Inject constructor(private val artDao: ArtDao, private val retrofitAPI: RetrofitAPI) : ArtRepositoryInterface{
    override suspend fun insertArts(art: ArtDbModel) {
        artDao.insertArts(art)
    }

    override suspend fun deleteArts(art: ArtDbModel) {
        artDao.deleteArts(art)
    }

    override fun observeArts(): LiveData<List<ArtDbModel>> {
        return artDao.observeArts()
    }



    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
      return try {
          val response = retrofitAPI.imageSearch(imageString)
          if (response.isSuccessful){
              response.body()?.let {
                  return@let Resource.success(it)
              } ?: Resource.error("Error",null)

          }else{
              Resource.error("Error",null)
          }

      }catch (e : Exception){
          Resource.error("No valid data!, Please check your connection",null)
      }
    }
}