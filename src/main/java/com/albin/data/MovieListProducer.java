package com.albin.data;

import com.albin.model.Movie;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by albinblent on 2016-02-15.
 */
@RequestScoped
public class MovieListProducer {

    @Inject
    private MovieRepository movieRepository;

    private List<Movie> movies;

    @Inject
    private Logger log;

    @Produces
    @Named
    public List<Movie> getMovies(){ return movies; }

    public void onMovieListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Movie movie){
        log.info("Event in received by MovieListProducer. " + movie.getName() + " added to the list of movies");
        retrieveAllMoviesOrderdByName();
    }

    private void retrieveAllMoviesOrderdByName() {
        movies = movieRepository.findAllOrderdByName();
    }

    public void onMovieListChangedAlways(@Observes(notifyObserver = Reception.ALWAYS) final Movie movie){
        log.info("Always event called with " + movie.getName());
    }
}
