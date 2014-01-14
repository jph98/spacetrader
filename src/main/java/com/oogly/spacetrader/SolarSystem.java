/*******************************************************************************
 * Copyright (c) 1999 - 2011.
 * Global Relay Communications Inc.
 * All rights reserved.
 ******************************************************************************/
package com.oogly.spacetrader;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class SolarSystem {

    private DirectedGraph<Planet, DefaultEdge> galaxy;
    
    public SolarSystem() {
        
        Planet sun = new Planet("sun");
        Planet mercury = new Planet("mercury");
        Planet venus = new Planet("venus");
        Planet moon = new Planet("moon");
        Planet earth = new Planet("earth");
        Planet mars = new Planet("mars");
        Planet jupiter = new Planet("jupiter");
        
        galaxy = new DefaultDirectedGraph<Planet, DefaultEdge>(DefaultEdge.class);
        galaxy.addVertex(jupiter);
        galaxy.addVertex(mars);
        galaxy.addVertex(earth);
        galaxy.addVertex(moon);
        galaxy.addVertex(venus);
        galaxy.addVertex(mercury);
        galaxy.addVertex(sun);
        
        galaxy.addEdge(sun, mercury);
        galaxy.addEdge(mercury, venus);
        galaxy.addEdge(venus, moon);
        galaxy.addEdge(moon, earth);
        galaxy.addEdge(earth, mars);
        galaxy.addEdge(mars, jupiter);
        
        System.out.println(galaxy.toString());
    }
    
    
    public DirectedGraph<Planet, DefaultEdge> getGalaxy() {
        return galaxy;
    }
    
}
