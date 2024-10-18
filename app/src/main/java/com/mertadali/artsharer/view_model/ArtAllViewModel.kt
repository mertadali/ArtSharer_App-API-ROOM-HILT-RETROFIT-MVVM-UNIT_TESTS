package com.mertadali.artsharer.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertadali.artsharer.model.ImageResponse
import com.mertadali.artsharer.repository.ArtRepositoryInterface
import com.mertadali.artsharer.roomdb.ArtDbModel
import com.mertadali.artsharer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

// ViewModelInject deprecated olduğu için.

class ArtAllViewModel @Inject constructor(private val repository: ArtRepositoryInterface) : ViewModel(){

    // ArtFragment



    val artList = repository.observeArts()

    // ImageApiFragment

    private val images = MutableLiveData<Resource<ImageResponse>>()           // -> Encapsulation
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    // ArtDetailsFragment

    private var insertArtMsg = MutableLiveData<Resource<ArtDbModel>>()
    val insertArtMessage :LiveData<Resource<ArtDbModel>>
        get() = insertArtMsg

    fun resetInsertMessage(){
        insertArtMsg = MutableLiveData<Resource<ArtDbModel>>()
    }

    // Setter process

    fun setSelectedImage(url : String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art : ArtDbModel) =  viewModelScope.launch{
        repository.deleteArts(art)
    }

    fun insertArt(art: ArtDbModel) = viewModelScope.launch{
        repository.insertArts(art)
    }

    fun makeArt(artName : String, artistName : String, artYear : String){
        if (artName.isEmpty() || artistName.isEmpty() || artYear.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter information",null))
            return
        }

        val artYearInt = try {
            artYear.toInt()
        }catch (e : Exception){
            insertArtMsg.postValue(Resource.error("Please enter number!",null))
            return
        }

        val art = ArtDbModel(artName,artistName,artYearInt,selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))



    }

    fun searchForImage(searchString: String){
        if (searchString.isEmpty()){
            return
        }else{
            images.value = Resource.loading(null)
            viewModelScope.launch {
                val response = repository.searchImage(searchString)
                images.value = response
            }

        }


    }






























}