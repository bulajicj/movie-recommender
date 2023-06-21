package ir;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.movie_recommender.model.CombinedQuery;
import com.movie_recommender.model.Movie;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

public class MovieSearcher {

    private static final int NUM_HITS = 30;

    private IndexReader indexReader;
    private IndexSearcher indexSearcher;
    private StandardAnalyzer queryAnalyzer;


    public MovieSearcher(String indexDir) throws IOException {

        Path p = Paths.get(indexDir);

        this.indexReader = DirectoryReader.open(
                FSDirectory.open(p)
        );

        this.indexSearcher = new IndexSearcher(indexReader);
        this.queryAnalyzer = new StandardAnalyzer();


    }



    private Movie createMovieFromDocument(Document document) {
        int releaseYear = Integer.parseInt(document.get("releaseYear"));
        String title = document.get("title");
        String cast = document.get("cast");
        String genres = document.get("genres");
        String plot = document.get("plot");
        String wikiPage = document.get("wikiPage");
        return new Movie(releaseYear, title, genres, plot, cast, wikiPage);
    }
    public List<Movie> combinedSearch(CombinedQuery cq) throws IOException, ParseException {

        System.out.println(cq);
        BooleanQuery.Builder builder = new BooleanQuery.Builder();

        if (cq.getRelaseYearQuery() != null) {

            Query releaseYearQuery;
            String[] years = cq.getRelaseYearQuery().split(" TO ");
            if (years.length == 2) {

                int year1 = Integer.parseInt(years[0]);
                int year2 = Integer.parseInt(years[1]);
                releaseYearQuery = IntPoint.newRangeQuery("releaseYear", year1, year2);
            } else {
                int releaseYear = Integer.parseInt(cq.getRelaseYearQuery());
                releaseYearQuery = IntPoint.newExactQuery("releaseYear", releaseYear);
            }
            builder.add(releaseYearQuery, BooleanClause.Occur.MUST);
        }

        if (cq.getGenresQuery() != null ) {

            Query genreQuery = new QueryParser("genres", queryAnalyzer).parse(cq.getGenresQuery());
            builder.add(genreQuery, BooleanClause.Occur.MUST);
        }

        if (cq.getTitleQuery() != null) {

            Query titleQuery = new QueryParser("title", queryAnalyzer).parse(cq.getTitleQuery());
            builder.add(titleQuery, BooleanClause.Occur.MUST);
        }

        if (cq.getCastQuery() != null) {

            Query castQuery = new QueryParser("cast", queryAnalyzer).parse(cq.getCastQuery());
            builder.add(castQuery, BooleanClause.Occur.MUST);
        }

        if (cq.getPlotQuery() != null) {

            Query plotQuery = new QueryParser("plot", queryAnalyzer).parse(cq.getPlotQuery());
            builder.add(plotQuery, BooleanClause.Occur.MUST);
        }


        Query combinedQuery = builder.build();
        System.out.println(combinedQuery.toString());

        TopDocs topDocs = indexSearcher.search(combinedQuery, cq.getNumOfHits());
        ScoreDoc[] hits = topDocs.scoreDocs;
        List<Movie> movies = new ArrayList<>();

        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document doc = indexSearcher.doc(docId);
            Movie movie = createMovieFromDocument(doc);
            movies.add(movie);
        }

        return movies;
    }

    public void close() throws IOException {
        this.indexReader.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        try {
            MovieSearcher movieSearcher = new MovieSearcher("movies_index");
            //Searching movies by actor
//            List<Movie> moviesWithActor = movieSearcher.searchMoviesByActors("\"Angelina Jolie\" AND \"Brad Pitt\" ");
//            for (Movie movie : moviesWithActor) {
//                System.out.println(movie);
//            }


//            List<Movie> moviesWithGenre = movieSearcher.searchMoviesByGenres("action");
//            for (Movie movie : moviesWithGenre){
//                System.out.println(movie);
//            }

            List<Movie> moviesWithGenre = movieSearcher.combinedSearch(new CombinedQuery("2012", "shark", "Charlie O'Connell", null, null,30));
            for (Movie movie : moviesWithGenre){
                System.out.println(movie);
            }
            movieSearcher.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
