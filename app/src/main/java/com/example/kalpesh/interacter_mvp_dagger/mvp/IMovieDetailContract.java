package com.example.kalpesh.interacter_mvp_dagger.mvp;

import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;

/**
 * Created by tezk on 05/05/17.
 */

public interface IMovieDetailContract {
    public interface IView {
        public void onStartRetrieval() ;
        public void onSuccessfullRetrieval() ;
        public void onFailedRetrieval(Throwable e) ;
        public void onPassDetails(MovieDetails movieDetails) ;
    }

    public interface IPresenter {
        public void bind(IView view) ;
        public void unBind() ;
        public void retrieveDetails(String apiKey, Integer movieId) ;
    }
}
