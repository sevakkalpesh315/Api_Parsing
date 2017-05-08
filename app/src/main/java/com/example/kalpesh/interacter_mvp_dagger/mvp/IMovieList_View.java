package com.example.kalpesh.interacter_mvp_dagger.mvp;

import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;

import java.util.List;

/**
 * Created by kalpesh on 11/03/2017.
 */

public interface IMovieList_View {


    void onFetchDataStarted();

    void onFetchDataSuccess(MoviesResponse response);

    void onFetchDataCompleted();

    void onFetchDataError(Throwable e);

    //void onFetchDataSuccess(String title, String releaseDate, String overview, String voteAverage);


    //void updateUi(List<Movie> books);
    void updateUi(List<Movie> books);

    void getAllDetails(String movieName);

}
