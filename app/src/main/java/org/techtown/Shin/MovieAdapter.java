package org.techtown.Shin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder>{
    private List<MovieList> movies;
    private Context context;
    public MovieAdapter(List<MovieList> movies){
        this.movies=movies;
        //this.context=context;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        MovieList movie=movies.get(position);
        holder.num.setText(position+1+"");
        holder.title.setText(movie.getTitle());
        holder.director.setText(movie.getDirector());
        holder.released_date.setText(movie.getReleaseDate());
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView num;
        private TextView title;
        private TextView director;
        private TextView released_date;
        ItemViewHolder(final View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.num);
            title = itemView.findViewById(R.id.title);
            director = itemView.findViewById(R.id.director);
            released_date = itemView.findViewById(R.id.released_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* DetailActivity intent 호출 */
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    int pos = getAdapterPosition();
                    intent.putExtra("data_num", movies.get(pos).getNum());
                    intent.putExtra("data_title", movies.get(pos).getTitle());
                    intent.putExtra("data_description", movies.get(pos).getDescription());
                    intent.putExtra("data_director", movies.get(pos).getDirector());
                    intent.putExtra("data_producer", movies.get(pos).getProducer());
                    intent.putExtra("data_releasedate", movies.get(pos).getReleaseDate());
                    intent.putExtra("data_rtscore", movies.get(pos).getRt_score());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        void onBind(MovieList data) {
            title.setText(data.getTitle());
            director.setText(data.getDirector());
            director.setText(data.getDirector());
            released_date.setText(data.getReleaseDate());
        }
    }
}
