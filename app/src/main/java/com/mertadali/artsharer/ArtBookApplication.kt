package com.mertadali.artsharer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*

Hilt'te Application sınıfı, uygulama genelinde kullanılacak bağımlılıkların yaşam döngüsünü yönetmek için gereklidir. Hilt'in temel amacı,
 bağımlılıkları lifecycle-aware (yaşam döngüsünü bilen) bir şekilde yönetmek olduğundan, uygulama seviyesinde bağımlılıkları tanımlamak için
 bir Application sınıfına ihtiyaç duyar

 */

@HiltAndroidApp
class ArtBookApplication : Application()
