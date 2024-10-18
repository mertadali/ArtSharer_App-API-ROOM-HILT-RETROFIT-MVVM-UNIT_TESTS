package com.mertadali.artsharer.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import javax.inject.Inject


/*
FragmentFactory'yi kullanma ihtiyacının ana nedeni, fragmentlere bağımlılık enjeksiyonu yapabilmek ve fragmentlerin test edilebilirliğini artırmaktır.
 Normalde Android, fragmentleri yalnızca boş bir yapıcıyla (constructor) oluşturur. Ancak bir fragment,
  farklı nesnelere veya verilere (örneğin bir repository, ViewModel ya da başka bir sınıfa) ihtiyaç duyduğunda, varsayılan yapı bu ihtiyacı karşılamaz.
   İşte tam bu noktada FragmentFactory devreye girer.
 */

class ArtFragmentFactory @Inject constructor(private val glide: RequestManager) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className){
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}