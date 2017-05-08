package com.example.kalpesh.interacter_mvp_dagger.mvp;

import com.example.kalpesh.interacter_mvp_dagger.model.Movie;

import java.util.List;

/**
 * Created by kalpesh on 14/03/2017.
 */

public interface IMovieList_Presenter {
    void bind(IMovieList_View view);
    void unbind();
    void performSearch(String apiKey);
    void getList(List<Movie> movie);
}
