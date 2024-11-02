package com.mertadali.artsharer.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.mertadali.artsharer.R
import com.mertadali.artsharer.adapter.ImageFragmentAdapter
import com.mertadali.artsharer.getOrAwaitValue
import com.mertadali.artsharer.launchFragmentInHiltContainer
import com.mertadali.artsharer.repo.FakeArtRepositoryAndroidTest
import com.mertadali.artsharer.view_model.ArtAllViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ImageApiFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun selectImage(){
        // seçim yapıldığında bir önceki fragmenta dönmesi lazım
        val navController = Mockito.mock(NavController::class.java)
        val selectedImageUrl = "test.com"

        val testViewModel = ArtAllViewModel(FakeArtRepositoryAndroidTest())

        launchFragmentInHiltContainer<ImageApiFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
            viewModel = testViewModel

            Espresso.onView(ViewMatchers.withId(R.id.recyclerViewApi)).perform(
                RecyclerViewActions.actionOnItemAtPosition<ImageFragmentAdapter.RowHolderApi>(0,ViewActions.click()))

            Mockito.verify(navController).popBackStack()

            assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedImageUrl)

        }

    }




}