package com.albin.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Created by albinblent on 2016-02-15.
 */
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager entityManager;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext(){ return FacesContext.getCurrentInstance(); }
}
