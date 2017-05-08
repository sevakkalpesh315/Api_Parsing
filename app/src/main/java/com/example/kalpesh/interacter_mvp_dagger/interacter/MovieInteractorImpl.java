package com.example.kalpesh.interacter_mvp_dagger.interacter;

import com.example.kalpesh.interacter_mvp_dagger.model.Constants;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;
import com.example.kalpesh.interacter_mvp_dagger.service.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by kalpesh on 11/03/2017.
 */

public class MovieInteractorImpl implements MovieInteractor {

    private ApiInterface service;

    public MovieInteractorImpl() {
        // Configure Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                // Base URL can change for endpoints (dev, staging, live..)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(ApiInterface.class);
    }

    @Override
    public Observable<MoviesResponse> getTopRatedMovies(String apiKey) {
        return service.getTopRatedMovies(apiKey);

    }

    @Override
    public Observable<MovieDetails> getMovieDetails(String apiKey, Integer movieId) {
        return service.getMovieDetails(movieId, apiKey);
    }
}
