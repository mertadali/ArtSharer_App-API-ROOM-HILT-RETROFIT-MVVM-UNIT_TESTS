package com.mertadali.artsharer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mertadali.artsharer.model.ImageResponse
import com.mertadali.artsharer.roomdb.ArtDbModel
import com.mertadali.artsharer.util.Resource

class FakeArtRepository : ArtRepositoryInterface {

    private val arts = mutableListOf<ArtDbModel>()
    private val liveData = MutableLiveData<List<ArtDbModel>>(arts)


    override suspend fun insertArts(art: ArtDbModel) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArts(art: ArtDbModel) {
        arts.remove(art)
        refreshData()
    }

    override fun observeArts(): LiveData<List<ArtDbModel>> {
        return liveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(0,0, listOf()))
    }

    private fun refreshData(){
        liveData.postValue(arts)
    }
}