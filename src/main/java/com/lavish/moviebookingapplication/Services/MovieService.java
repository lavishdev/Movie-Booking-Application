package com.lavish.moviebookingapplication.Services;

import com.lavish.moviebookingapplication.DTOs.Moviedto;
import com.lavish.moviebookingapplication.Models.Movie;
import com.lavish.moviebookingapplication.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movierepo;

    public Movie addMovie(Moviedto moviedto) {
        Movie movie = new Movie();
        movie.setName(moviedto.getName());
        movie.setDescription(moviedto.getDescription());
        movie.setDuration(moviedto.getDuration());
        movie.setGenre(moviedto.getGenre());
        movie.setLanguage(moviedto.getLanguage());
        movie.setReleaseDate(moviedto.getReleaseDate());

        return movierepo.save(movie);
    }

    public List<Movie> getallmovie() {
        return movierepo.findAll();
    }

    public List<Movie> getMoviesBygenre(String genre) {
        Optional<List<Movie>> listofmovieBox = movierepo.findByGenre(genre);
        if(listofmovieBox.isPresent()) {
            return listofmovieBox.get();
        }
        else throw new RuntimeException("No movies found for genre " + genre);
    }

    public List<Movie> getMoviesBytitle(String title) {
        Optional<List<Movie>> listofmovieBox = movierepo.findByName(title);
        if(listofmovieBox.isPresent()) {
            return listofmovieBox.get();
        }
        else throw new RuntimeException("No movies found for title " + title);

    }

    public Movie updateMovie(Long id, Moviedto moviedto) {
        Movie movie = movierepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie with id " + id + " not found"));
        movie.setName(moviedto.getName());
        movie.setDescription(moviedto.getDescription());
        movie.setDuration(moviedto.getDuration());
        movie.setGenre(moviedto.getGenre());
        movie.setLanguage(moviedto.getLanguage());
        movie.setReleaseDate(moviedto.getReleaseDate());
        return movierepo.save(movie);
    }

    public void deleteMovie(Long id) {
        movierepo.deleteById(id);
    }

    public List<Movie> getMoviesBylanguage(String language) {

        Optional<List<Movie>> listofmovieBox = movierepo.findBylanguage(language);
        if(listofmovieBox.isPresent()) {
            return listofmovieBox.get();
        }
        else throw new RuntimeException("No movies found for language " + language);
    }
}
