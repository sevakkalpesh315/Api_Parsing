package com.example.kalpesh.interacter_mvp_dagger.service;

import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiInterface {
    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Observable<MovieDetails> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
