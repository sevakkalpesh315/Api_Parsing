package com.example.kalpesh.interacter_mvp_dagger;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MovieInteractorImpl;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.mvp.IMovieDetailContract;
import com.example.kalpesh.interacter_mvp_dagger.mvp.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.kalpesh.interacter_mvp_dagger.model.Constants.API_KEY;

public class DetailsActivity extends AppCompatActivity implements IMovieDetailContract.IView {

    IMovieDetailContract.IPresenter presenter;

    @BindView(R.id.tvTitle)TextView tvTitle;
    @BindView(R.id.tvTagline)TextView tvTagline;
    @BindView(R.id.tvOverview)TextView tvOverview;
    @BindView(R.id.ivPoster)ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Integer movieID = getIntent().getIntExtra("movieid", 0);
        Log.i("DA","id = "+movieID);
        presenter = new MovieDetailPresenter(new MovieInteractorImpl(),  AndroidSchedulers.mainThread(), Schedulers.io());
        presenter.bind(this);
        presenter.retrieveDetails(API_KEY, movieID);
    }

    @Override
    public void onStartRetrieval() {
        Log.i("DA", "onStart");
    }

    @Override
    public void onSuccessfullRetrieval() {
        Log.i("DA", "retrieved");
    }

    @Override
    public void onFailedRetrieval(Throwable e) {
        Log.i("DA", "failed : "+e.getMessage());
    }

    @Override
    public void onPassDetails(MovieDetails movieDetails) {
        tvTitle.setText(movieDetails.getTitle());
        tvTagline.setText(movieDetails.getTagline());
        tvOverview.setText(movieDetails.getOverview());
        Log.i("DA", movieDetails.getPosterPath());
        Picasso.with(this).load(Uri.parse("http://image.tmdb.org/t/p/w780"+movieDetails.getPosterPath())).into(ivPoster);
    }
}
