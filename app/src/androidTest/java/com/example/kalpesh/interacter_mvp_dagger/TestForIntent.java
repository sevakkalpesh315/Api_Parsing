package com.example.kalpesh.interacter_mvp_dagger;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.example.kalpesh.interacter_mvp_dagger.model.Movie;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by tezk on 07/05/17.
 */

public class TestForIntent {
    List <Movie> movies;
    @Rule
    public IntentsTestRule intentsTestRule = new IntentsTestRule(MainActivity.class);

    @Test
    public void testForIntentOnClick() throws Exception {
        movies = getMovies();
        onView(withText(movies.get(0).getTitle())).perform(click());
        intended(toPackage("com.example.kalpesh.interacter_mvp_dagger"));
    }

    private List<Movie> getMovies() throws Exception {
        // Helper function - will pause until data is loaded, if movies already loaded, do nothing
        if (movies!=null)
            return movies;
        MainActivity activity = (MainActivity) intentsTestRule.getActivity();
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
}
