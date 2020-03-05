package org.techtown.Shin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieList {
    private int num;
    public int getNum(){

        return num;
    }
    public void setNum(int num1){
        this.num=num1;
    }
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
//직렬화 안하고 일단 아래 3변수 해보기
    private String description;
    private String producer;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getRt_score() {
        return rt_score;
    }

    public void setRt_score(int rt_score) {
        this.rt_score = rt_score;
    }

    private int rt_score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}