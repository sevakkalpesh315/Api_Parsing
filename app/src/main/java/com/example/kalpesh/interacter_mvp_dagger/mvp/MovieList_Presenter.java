package com.example.kalpesh.interacter_mvp_dagger.mvp;

import android.util.Log;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MovieInteractor;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;

/**
 * Created by kalpesh on 11/03/2017.
 */

public class MovieList_Presenter implements IMovieList_Presenter{
    private Scheduler mainScheduler, ioScheduler;
    private IMovieList_View view;

    MovieInteractor interactor;

    public MovieList_Presenter(MovieInteractor interactor, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.interactor = interactor;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void bind(IMovieList_View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void getList(List<Movie> movie){
        Observable.from(movie)
                .filter(new Func1<Movie, Boolean>() {
                    @Override
                    public Boolean call(Movie movie) {
                        return movie.getVoteAverage()>8.3;
                    }
                })
              .limit(3)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Movie movie) {
                        /**
                         * TODO: Call the adapter here by passing only required fields
                         */
                        Log.i("Values ",""+ movie.getTitle());
                        Log.i("Values ",""+ movie.getReleaseDate());
                        Log.i("Values ",""+ movie.getOverview());
                        Log.i("Values ",""+ movie.getVoteAverage());

                        // view.getAllDetails(movie.getTitle());
                       //   view.updateUi(movie);

                    }
                });
    }


    @Override
    public void performSearch(String apiKey) {
        view.onFetchDataStarted();
        interactor.getTopRatedMovies(apiKey)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<MoviesResponse>() {
                    @Override
                    public void onCompleted() {

                            view.onFetchDataCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                            view.onFetchDataError(e);
                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {

                            view.onFetchDataSuccess(moviesResponse);
                            //  movies = moviesResponse.getResults();
                            //   getList(moviesResponse.getResults());
                            view.updateUi(moviesResponse.getResults());

                            //  iView_movieList.displayRecyclerView_MovieList(movies);
                            //  iView_movieList.dismissProgressDialog();
                        }

                });

    }

}
