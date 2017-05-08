package com.example.kalpesh.interacter_mvp_dagger.mvp;

import android.util.Log;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MovieInteractor;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tezk on 05/05/17.
 */

public class MovieDetailPresenter implements IMovieDetailContract.IPresenter {

    MovieInteractor movieInteractor;
    Scheduler observeScheduler;
    Scheduler subscribeScheduler;

    public MovieDetailPresenter(MovieInteractor movieInteractor) {
        this(movieInteractor, Schedulers.io(), AndroidSchedulers.mainThread());
    }

    public MovieDetailPresenter(MovieInteractor movieInteractor, Scheduler observeScheduler, Scheduler subscribeScheduler) {
        this.movieInteractor = movieInteractor;
        this.observeScheduler = observeScheduler;
        this.subscribeScheduler = subscribeScheduler;
    }

    private IMovieDetailContract.IView view;
    @Override
    public void bind(IMovieDetailContract.IView view) {
        this.view = view;
    }

    @Override
    public void unBind() {
        this.view = null;
    }

    @Override
    public void retrieveDetails(String apiKey, Integer movieId) {
        view.onStartRetrieval();
        Log.i("MDP", "observer = "+observeScheduler);
        Log.i("MDP", "subscrib = "+subscribeScheduler);
        Observable<MovieDetails> movieDetails = movieInteractor.getMovieDetails(apiKey, movieId);
        movieDetails
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(new Observer<MovieDetails>() {
                    @Override
                    public void onCompleted() {
                        view.onSuccessfullRetrieval();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.onFailedRetrieval(e);
                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        view.onPassDetails(movieDetails);
                    }
                });
    }

    public IMovieDetailContract.IView getView() {
        return view;
    }
}
