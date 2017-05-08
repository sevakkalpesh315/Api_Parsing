package com.example.kalpesh.interacter_mvp_dagger.module;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MovieInteractor;
import com.example.kalpesh.interacter_mvp_dagger.interacter.MovieInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kalpesh on 11/03/2017.
 */

@Module
public class Movielist_Module {

    @Provides
    public MovieInteractor providesBooksInteractor() {
        return new MovieInteractorImpl();
    }
}
