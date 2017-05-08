package com.example.kalpesh.interacter_mvp_dagger;

import android.support.test.rule.ActivityTestRule;

import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by tezk on 06/05/17.
 */

public class DetailViewTest {
    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(DetailsActivity.class);

    MovieDetails testMovieDetails;
    final static String testTitle = "TESTTITLE";
    final static String testTagline = "TESTTAGLINE";
    final static String testOverview = "TESTOVERVIEW";

    @Before
    public void setUp() throws Exception {
        testMovieDetails = new MovieDetails();
        testMovieDetails.setTitle(testTitle);
        testMovieDetails.setTagline(testTagline);
        testMovieDetails.setOverview(testOverview);
        testMovieDetails.setPosterPath("");
    }

    @Test
    public void checkViewsAreVisible() {
        // Check that the views are displayed
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.tvTagline)).check(matches(isDisplayed()));
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()));
        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()));
    }

    @Test
    public void testViewsCanBeSet() {
        // Get the activity
        final DetailsActivity activity = (DetailsActivity) activityTestRule.getActivity();

        // We can only update views on the Activities UI thread
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onPassDetails(testMovieDetails);
            }
        });

        // Check views are showing the test data
        onView(withText(testTitle)).check(matches(isDisplayed()));
        onView(withText(testTagline)).check(matches(isDisplayed()));
        onView(withText(testOverview)).check(matches(isDisplayed()));
    }
}
