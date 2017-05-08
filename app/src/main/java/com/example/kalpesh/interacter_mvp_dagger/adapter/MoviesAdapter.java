package com.example.kalpesh.interacter_mvp_dagger.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kalpesh.interacter_mvp_dagger.DetailsActivity;
import com.example.kalpesh.interacter_mvp_dagger.R;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.utilities.ItemClickListener;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    String title, description;
    private int rowLayout;
    int size = 0;
    private Context context;
    //CompositeSubscription compositeSubscription= new CompositeSubscription();

    //  private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";
//    private ApiInterface apiService =
//            ApiClient.getClient().create(ApiInterface.class);

    /**
     * 3 Implement interface: this will create two methods click and long click
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        /**
         * 1 Add itemClickListerner Interface
         * 2 Create object
         */
        private ItemClickListener clickListener;


        TextView movieDescription;
        TextView rating;

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        /**
         * 4 click methods
         *
         * @param view
         */
        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);

            /**
             * 5 Most important: add Listener
             */
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public MoviesAdapter(String name, int rowLayout, Context context) {
        this.title = name;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        //  holder.movieTitle.setText(title);
        holder.movieTitle.setText(movies.get(position).getTitle());

        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());

//        /**
//         * 6 Call click here
//         */
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
//
                } else {
                    //clicked
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("movieid", movies.get(position).getId());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}