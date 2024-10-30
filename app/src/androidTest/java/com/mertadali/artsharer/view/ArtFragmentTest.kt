package com.mertadali.artsharer.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.mertadali.artsharer.R
import com.mertadali.artsharer.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


@MediumTest
@HiltAndroidTest
class ArtFragmentTest {

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtToArtDetails(){

        // fake oluşturmak gibi mock oluşturuyuoruz. buda mock ile navigation ile geçişi fake bir işlem gibi test ediyoruz.

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)

        }

        // Fab tuşuna basıldığınd ageçiş testi için

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())



    }
}