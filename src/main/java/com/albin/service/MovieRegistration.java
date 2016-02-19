package com.albin.service;

import com.albin.model.Movie;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

/**
 * Created by albinblent on 2016-02-15.
 */
@Stateless
public class MovieRegistration {

    @Inject
    private Logger log;

    @Inject
    private EntityManager entityManager;

    @Inject
    private Event<Movie> movieEventSrc;

    public void register(Movie movie) throws Exception{
        log.info("MovieRegistration: Regestering " + movie.getName());
        entityManager.persist(movie);
        movieEventSrc.fire(movie);
    }
}
