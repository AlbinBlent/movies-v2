package com.albin.data;

import com.albin.model.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by albinblent on 2016-02-15.
 */
@ApplicationScoped
public class MovieRepository {

    @Inject
    private EntityManager entityManager;

    public Movie findById(Long id) { return entityManager.find(Movie.class, id); }


    public List<Movie> findAllOrderdByName() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteria = cb.createQuery(Movie.class);
        Root<Movie> movie = criteria.from(Movie.class);

        criteria.select(movie).orderBy(cb.asc(movie.get("name")));
        return entityManager.createQuery(criteria).getResultList();
    }

    public Movie findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> criteria = cb.createQuery(Movie.class);
        Root<Movie> movie = criteria.from(Movie.class);

        criteria.select(movie).where(cb.equal(movie.get("name"), name));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}
