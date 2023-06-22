package com.movie_recommender.controller;
import com.movie_recommender.model.CombinedQuery;
import com.movie_recommender.model.Movie;
import ir.MovieSearcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080/", allowCredentials = "true")
public class SearchController {
    MovieSearcher ms;

    public SearchController() throws IOException {
        this.ms = new MovieSearcher("backend\\movies_index");
    }

    @GetMapping("/")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> combinedSearch(@RequestParam(value = "yearQuery", required = false) String yearQuery,
                                                      @RequestParam(value = "titleQuery", required = false) String titleQuery,
                                                      @RequestParam(value = "castQuery", required = false) String castQuery,
                                                      @RequestParam(value = "genresQuery", required = false) String genresQuery,
                                                      @RequestParam(value = "plotQuery", required = false) String plotQuery,
                                                      @RequestParam (value = "numOfHits", required=true) int numOfHits){
        try {
            CombinedQuery query = new CombinedQuery(yearQuery,titleQuery,castQuery,genresQuery,plotQuery, numOfHits);
            List<Movie> res = this.ms.combinedSearch(query);
            if (res.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
