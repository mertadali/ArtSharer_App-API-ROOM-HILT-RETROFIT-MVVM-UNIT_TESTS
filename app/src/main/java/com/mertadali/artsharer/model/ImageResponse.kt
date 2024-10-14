package com.mertadali.artsharer.model

// -> Pixabay API için 3 tane temel cevap dönecek, hits bir liste

data class ImageResponse(
    val total : Int,
    val totalHits : Int,
    val hits : List<ImageResultList>
)
