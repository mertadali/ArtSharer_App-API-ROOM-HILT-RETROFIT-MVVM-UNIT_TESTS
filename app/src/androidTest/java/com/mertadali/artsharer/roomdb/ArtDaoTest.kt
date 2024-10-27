package com.mertadali.artsharer.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mertadali.artsharer.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest                // -> Halen Unit testing işlemi yaptığımız için.
@ExperimentalCoroutinesApi
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao : ArtDao
    private lateinit var roomDatabase: ArtDatabase


    // -> Test içinde database builder yerine inMemoryDatabaseBuilder kullanıyoruz sebebi ramde tutulur ve silinir.
    // -> context testlerde  ApplicationProvider.getApplicationContext() şeklinde alınabilir.

    @Before
    fun setup(){
        roomDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),ArtDatabase::class.java)
            .allowMainThreadQueries().build()

        dao = roomDatabase.artDao()

    }
    @After
    fun tearDown(){      // tear down ( Sökmek demek)
        roomDatabase.close()

    }

    @Test
    fun insertToDatabase() = runTest{
        val exampleArt = ArtDbModel("Mona Lisa","Van Gogh",1890,"www.deneme.com",1)
        dao.insertArts(exampleArt)

        // Eklenmiş mi kontrolü

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)
    }

    @Test
    fun deleteFromDatabase() = runTest{

        val exampleArt = ArtDbModel("Mona Lisa","Van Gogh",1890,"www.deneme.com",1)
        dao.insertArts(exampleArt)
        dao.deleteArts(exampleArt)

        // silinmiş  mi kontrolü

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)




    }
}
