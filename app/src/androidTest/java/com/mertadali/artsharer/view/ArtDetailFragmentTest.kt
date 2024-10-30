package com.mertadali.artsharer.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.mertadali.artsharer.R
import com.mertadali.artsharer.getOrAwaitValue
import com.mertadali.artsharer.launchFragmentInHiltContainer
import com.mertadali.artsharer.repo.FakeArtRepositoryAndroidTest
import com.mertadali.artsharer.roomdb.ArtDbModel
import com.mertadali.artsharer.view_model.ArtAllViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
   lateinit var fragmentFactory : ArtFragmentFactory

   @Before
   fun setup(){
      hiltRule.inject()
   }


    @Test
    fun artDetailToImageApiScreen(){

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }

        // imageView tıklandığında
        Espresso.onView(ViewMatchers.withId(R.id.imageViewDetail)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())

    }

    @Test

    fun artDetailBackPressed(){

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }

        // Geri tuşuna basıldığında
       pressBack()

        Mockito.verify(navController).popBackStack()

    }

    @Test
    fun saveTest(){
        val testViewModel = ArtAllViewModel(FakeArtRepositoryAndroidTest())
        launchFragmentInHiltContainer<ArtDetailsFragment>(factory = fragmentFactory){
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.artNameDetail)).perform(ViewActions.replaceText("Mona Lisa"))
        Espresso.onView(ViewMatchers.withId(R.id.artistNameDetail)).perform(ViewActions.replaceText("Van Gogh"))
        Espresso.onView(ViewMatchers.withId(R.id.artYearDetail)).perform(ViewActions.replaceText("1890"))
        Espresso.onView(ViewMatchers.withId(R.id.saveButton)).perform(ViewActions.click())

        assertThat(testViewModel.artList.getOrAwaitValue()).contains(ArtDbModel(
            "Mona Lisa","Van Gogh",1890,""
        ))



    }



}