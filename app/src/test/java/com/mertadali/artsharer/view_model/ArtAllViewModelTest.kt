package com.mertadali.artsharer.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mertadali.artsharer.MainCoroutineRule
import com.mertadali.artsharer.getOrAwaitValueTest
import com.mertadali.artsharer.repository.FakeArtRepository
import com.mertadali.artsharer.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtAllViewModelTest {

    // InstantTaskExecutorRule JUnit testlerinde kullanılan bir kuraldır ve LiveData ile çalışan testlerin senkronize olarak çalışmasını sağlar.

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtAllViewModel

    @Before
    // Test Doubles  -> Threading, networking işlemleri olduğundan fake bir repo ile çalışacağız.
    fun setup(){
         viewModel = ArtAllViewModel(FakeArtRepository())
    }

    @Test
    fun `insert arts without year return error`(){
      viewModel.makeArt("Mona Lisa","Van Gogh","")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert arts without art  name return error`(){
        viewModel.makeArt("","Van Gogh","1890")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }

    @Test
    fun `insert arts without artist  name return error`(){
        viewModel.makeArt("Mona Lisa","","1890")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }



}