package com.example.kalpesh.interacter_mvp_dagger.interacter;

import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;

import rx.Observable;

/**
 * Created by kalpesh on 11/03/2017.
 */

public interface MovieInteractor {
    Observable<MoviesResponse> getTopRatedMovies(String apiKey);
    Observable<MovieDetails> getMovieDetails(String apiKey, Integer movieId) ;
}
