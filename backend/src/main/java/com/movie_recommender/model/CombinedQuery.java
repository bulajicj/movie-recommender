package com.movie_recommender.model;

public class CombinedQuery {
    String relaseYearQuery;
    String titleQuery;
    String castQuery;
    String genresQuery;
    String plotQuery;

    int numOfHits;

    public CombinedQuery(String relaseYearQuery, String titleQuery, String castQuery, String genresQuery, String plotQuery, int numOfHits) {
        this.relaseYearQuery = relaseYearQuery;
        this.titleQuery = titleQuery;
        this.castQuery = castQuery;
        this.genresQuery = genresQuery;
        this.plotQuery = plotQuery;
        this.numOfHits = numOfHits;
    }

    public String getRelaseYearQuery() {
        return relaseYearQuery;
    }

    public void setRelaseYearQuery(String relaseYearQuery) {
        this.relaseYearQuery = relaseYearQuery;
    }

    public String getTitleQuery() {
        return titleQuery;
    }

    public void setTitleQuery(String titleQuery) {
        this.titleQuery = titleQuery;
    }

    public String getCastQuery() {
        return castQuery;
    }

    public void setCastQuery(String castQuery) {
        this.castQuery = castQuery;
    }

    public String getGenresQuery() {
        return genresQuery;
    }

    public void setGenresQuery(String genresQuery) {
        this.genresQuery = genresQuery;
    }

    public String getPlotQuery() {
        return plotQuery;
    }

    public void setPlotQuery(String plotQuery) {
        this.plotQuery = plotQuery;
    }

    public int getNumOfHits() {
        return numOfHits;
    }

    public void setNumOfHits(int numOfHits) {
        this.numOfHits = numOfHits;
    }

    @Override
    public String toString() {
        return "CombinedQuery{" +
                "relaseYearQuery='" + relaseYearQuery + '\'' +
                ", titleQuery='" + titleQuery + '\'' +
                ", castQuery='" + castQuery + '\'' +
                ", genresQuery='" + genresQuery + '\'' +
                ", plotQuery='" + plotQuery + '\'' +
                ", numOfHits=" + numOfHits +
                '}';
    }
}
