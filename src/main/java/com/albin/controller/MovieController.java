package com.albin.controller;

import com.albin.model.Movie;
import com.albin.service.MovieRegistration;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by albinblent on 2016-02-16.
 */
@Model
public class MovieController {

    @Inject
    private MovieRegistration movieRegistration;

    @Produces
    @Named
    private Movie newMovie;

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    public void initNewMovie(){
        newMovie = new Movie();
    }

    public void registerNewMovie(){
        try {
            movieRegistration.register(newMovie);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Movie registered successfully");
            facesContext.addMessage(null, message);
            initNewMovie();
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error registering movie!", "Movie registration unsuccessful");
            facesContext.addMessage(null, message);
        }
    }
}
