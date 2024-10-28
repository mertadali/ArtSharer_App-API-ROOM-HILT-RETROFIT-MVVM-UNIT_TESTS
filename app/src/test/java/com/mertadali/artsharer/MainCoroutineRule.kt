package com.mertadali.artsharer

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
Bu MainCoroutineRule sınıfı, birim testlerde kullanılan coroutine'lerin ana iş parçacığı (Main thread) üzerinde çalışmasını kontrol etmek için
 oluşturulmuş özel bir JUnit kuralıdır. Android projelerinde asenkron işlemler genellikle Dispatchers.Main üzerinde çalışır, ancak Main
  dispatcheri yalnızca gerçek bir Android ortamında mevcut olduğundan, testlerde doğrudan kullanılamaz. Bu nedenle, MainCoroutineRule,
   Dispatchers.Main'i test ortamında değiştirmeyi sağlar.
 */

@ExperimentalCoroutinesApi
class MainCoroutineRule (
    private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : TestWatcher(){

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }

}