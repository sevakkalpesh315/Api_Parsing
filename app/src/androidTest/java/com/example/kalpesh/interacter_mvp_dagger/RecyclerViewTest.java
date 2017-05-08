package com.example.kalpesh.interacter_mvp_dagger;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.kalpesh.interacter_mvp_dagger.model.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by tezk on 06/05/17.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {
    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

    List <Movie> movies;

    @Before
    public void setUp() throws Exception {
        movies = getMovies();
    }

    @Test
    public void checkRecyclerViewIsVisible() throws InterruptedException {
        Thread.sleep(1000);
        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()));
    }

    private List<Movie> getMovies() throws Exception {
        // Helper function - will pause until data is loaded, if movies already loaded, do nothing
        MainActivity activity = (MainActivity) activityTestRule.getActivity();
        List<Movie> movies = activity.getMovies();
        int counter = 0;
        while (movies == null && counter++ < 500) {
            Thread.sleep(100);
            movies = activity.getMovies();
        }
        if (counter >= 500) {
            throw (new Exception("Timeout waiting on data"));
        }
        return movies;
    }

    @Test
    public void checkAllItemsAreLoaded() throws Exception {
        // Scroll through all the items and check each item of the data list is displayed
        movies = getMovies();
        for (int counter = 0; counter < movies.size(); counter++) {
            onView(withId(R.id.movies_recycler_view)).perform(RecyclerViewActions.scrollToPosition(counter));
            onView(withText(movies.get(counter).getTitle())).check(matches(isDisplayed()));
        }
    }

    public void testClickOpensNewWindow() throws Exception {
        // Test that clicking an item opens the new activity
        movies = getMovies();
        //Click on the first in list
        onView(withText(movies.get(0).getTitle())).perform(click());
        // See if the Tagline text view in the detail activity is visible
        Thread.sleep(200);
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
        Thread.sleep(1000);
    }
}
