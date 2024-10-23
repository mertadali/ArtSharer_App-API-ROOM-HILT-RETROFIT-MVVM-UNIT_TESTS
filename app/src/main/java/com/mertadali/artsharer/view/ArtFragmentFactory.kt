package com.mertadali.artsharer.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.mertadali.artsharer.adapter.ArtFragmentAdapter
import com.mertadali.artsharer.adapter.ImageFragmentAdapter
import javax.inject.Inject


/*
FragmentFactory'yi kullanma ihtiyacının ana nedeni, fragmentlere bağımlılık enjeksiyonu yapabilmek ve fragmentlerin test edilebilirliğini artırmaktır.
 Normalde Android, fragmentleri yalnızca boş bir yapıcıyla (constructor) oluşturur. Ancak bir fragment,
  farklı nesnelere veya verilere (örneğin bir repository, ViewModel ya da başka bir sınıfa) ihtiyaç duyduğunda, varsayılan yapı bu ihtiyacı karşılamaz.
   İşte tam bu noktada FragmentFactory devreye girer.
 */

class ArtFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val artRecyclerAdapter  : ArtFragmentAdapter,
    private val imageRecyclerAdapter : ImageFragmentAdapter) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}