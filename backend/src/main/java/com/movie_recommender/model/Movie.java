package com.movie_recommender.model;

import java.util.List;

public class Movie {

    int releaseYear;
    String title;
    String genres;
    String plot;
    String cast;
    String wikiPage;



    public Movie(int releaseYear, String title, String genres, String plot, String cast, String wikiPage) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.genres = genres;
        this.plot = plot;
        this.cast = cast;
        this.wikiPage = wikiPage;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getWikiPage() {
        return wikiPage;
    }

    public void setWikiPage(String wikiPage) {
        this.wikiPage = wikiPage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", genres='" + genres + '\'' +
//                ", plot='" + plot + '\'' +
                ", cast='" + cast + '\'' +
                ", wikiPage='" + wikiPage + '\'' +
                '}';
    }
}
