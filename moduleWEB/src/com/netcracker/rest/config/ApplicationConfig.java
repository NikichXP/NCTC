/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.rest.config;

import java.util.Set;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.netcracker.rest.*;

/**
 *
 * @author NikichXP
 */
@ApplicationPath("api")
public class ApplicationConfig extends Application {
 
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
 
    /**
     * Method contains java classes which are included to ReST service
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(UserRest.class);
        resources.add(OrderRest.class);
    }
     
}
