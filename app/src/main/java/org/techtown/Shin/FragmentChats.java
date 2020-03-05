package org.techtown.Shin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChats extends Fragment {
    final String BASE_URL = "https://ghibliapi.herokuapp.com/";
    RecyclerView recyclerView;
    List<MovieList> movies=new ArrayList<>();
    MovieAdapter movieAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.fragment2,container,false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter( movies);
        recyclerView.setAdapter(movieAdapter);
        getMovies();
        return rootView;
    }
    private void getMovies(){
        //gson을 사용하는 ApiService인터페이스가
        ApiService apiInterface = ServiceGenerator.createService(ApiService.class);
        Call<List<MovieList>> call = apiInterface.getMovies();
        call.enqueue(new Callback<List<MovieList>>() {
            @Override
            public void onResponse(Call<List<MovieList>> call, Response<List<MovieList>> response) {
                //비동기 처리를 위해 Callback 구현
                if(response.isSuccessful()) {
                    for(MovieList movie: response.body()){
                        movies.add(movie);
                    }
                    movieAdapter.notifyDataSetChanged();
                }else{
                    Log.d("TAG", response.message());
                }
            }
//Retrofit에서 응답받은 데이터 처리하기
            @Override
            public void onFailure(Call<List<MovieList>> call, Throwable t) {
                System.out.println("error + Connect Server Error  " );

            }

//            @Override
//            public void onFailure(Call<List<MovieList>> call, Throwable t) {
//                Log.d("TAG", t.getMessage());
//            }

        });
    }
}
