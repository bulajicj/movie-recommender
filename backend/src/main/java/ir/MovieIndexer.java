package ir;

import com.movie_recommender.model.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MovieIndexer {
    private IndexWriter indexer;

    public MovieIndexer(String indexDirPath, boolean createNew)
            throws IOException {

        java.nio.file.Path indexDirectoryPath = java.nio.file.Paths.get(indexDirPath);
        Directory indexDir = FSDirectory.open(indexDirectoryPath);

        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        if (createNew) {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        }
        else {
            iwc.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
        }

        this.indexer = new IndexWriter(indexDir, iwc);

    }

    public void indexMovie(Movie movie) throws IOException {


        Document document = new Document();
        document.add(new TextField("title", movie.getTitle(), Field.Store.YES));
        document.add(new TextField("cast", movie.getCast(), Field.Store.YES));
        document.add(new TextField("genres", movie.getGenres(), Field.Store.YES));
        document.add(new TextField("plot", movie.getPlot(), Field.Store.YES));
        document.add(new IntPoint("releaseYear", movie.getReleaseYear()));
        document.add(new StoredField("releaseYear", movie.getReleaseYear()));
        document.add(new StoredField("wikiPage", movie.getWikiPage()));

        indexer.addDocument(document);

    }

    public void finishIndexing() throws IOException {
        indexer.close();
    }

    public void indexMovieData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser csvParser = new CSVParser(reader, csvFormat);

        for (CSVRecord record : csvParser) {
            int releaseYear = Integer.parseInt(record.get("Release Year"));
            String title = record.get("Title");
            String cast = record.get("Cast");
            String genres = record.get("Genre");
            String plot = record.get("Plot");
            String wikiPage = record.get("Wiki Page");


            Movie movie = new Movie(releaseYear, title, genres, plot, cast, wikiPage);
            this.indexMovie(movie);
        }

        csvParser.close();
        reader.close();
    }

    public static void main(String[] args) throws IOException {

        MovieIndexer mi = new MovieIndexer("movies_index", true);
        mi.indexMovieData("res/wiki_movies.csv");
        mi.finishIndexing();

    }
}
