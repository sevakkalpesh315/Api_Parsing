package com.example.kalpesh.interacter_mvp_dagger;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MovieInteractor;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.mvp.IMovieDetailContract;
import com.example.kalpesh.interacter_mvp_dagger.mvp.MovieDetailPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by tezk on 05/05/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailPresenterTest {
    @Mock
    IMovieDetailContract.IView view;
    @Mock
    MovieInteractor movieInteractor;

    static final String TEST_API = "TESTAPI";
    static final Integer TEST_MOVIE_ID = 666;
    MovieDetailPresenter movieDetailPresenter;


    @Before
    public void setUp() throws Exception {
        // Bind the Mockito
        MockitoAnnotations.initMocks(this);
        // Initialise the presenter
        movieDetailPresenter = new MovieDetailPresenter(movieInteractor, Schedulers.immediate(), Schedulers.immediate());
        movieDetailPresenter.bind(view);

    }

    @Test
    public void testPresenterBindsToView() {
        assertEquals(view, movieDetailPresenter.getView());
    }

    @Test
    public void testPresenterReturnsDataWithValidQuery() {
        // Setup a test MovieDetails object

        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setId(TEST_MOVIE_ID);
        Observable observable = Observable.just(movieDetails);
        when(movieInteractor.getMovieDetails(TEST_API, TEST_MOVIE_ID)).thenReturn(observable);

        movieDetailPresenter.retrieveDetails(TEST_API, TEST_MOVIE_ID);

        InOrder inOrder = Mockito.inOrder(movieInteractor);
        // Check interactor is called on
        inOrder.verify(movieInteractor, times(1)).getMovieDetails(TEST_API, TEST_MOVIE_ID);
        // Check calls to view are made
        InOrder viewInOrder = Mockito.inOrder(view);
        viewInOrder.verify(view, times(1)).onStartRetrieval();
        viewInOrder.verify(view, times(1)).onPassDetails(movieDetails);
        viewInOrder.verify(view, times(1)).onSuccessfullRetrieval();
    }


    @Test
    public void testUnbind() {
        movieDetailPresenter.unBind();
        assertNull(movieDetailPresenter.getView());
    }
}
