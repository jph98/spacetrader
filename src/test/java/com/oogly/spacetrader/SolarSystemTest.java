/*******************************************************************************
 * Copyright (c) 1999 - 2011.
 * Global Relay Communications Inc.
 * All rights reserved.
 ******************************************************************************/
package com.oogly.spacetrader;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Before;
import org.junit.Test;

public class SolarSystemTest {

    private SolarSystem system;
    
    @Before
    public void before() {
        system = new SolarSystem();
    }
    
    @Test
    public void testSystemSize() {
        
        DirectedGraph<Planet, DefaultEdge> galaxy = system.getGalaxy();
        
        assertThat(galaxy.vertexSet().size(), equalTo(7));
        assertThat(galaxy.edgeSet().size(), equalTo(6));
    }
    
    @Test
    public void testContains() {
        
        DirectedGraph<Planet, DefaultEdge> galaxy = system.getGalaxy();
        
        assertThat(galaxy.vertexSet(), hasItem(new Planet("mercury")));
        assertThat(galaxy.vertexSet(), hasItem(new Planet("sun")));
        assertThat(galaxy.vertexSet(), hasItem(new Planet("earth")));
    }
    
    @Test
    public void testNotContains() {
        
        DirectedGraph<Planet, DefaultEdge> galaxy = system.getGalaxy();
        
        assertThat(galaxy.vertexSet(), not(hasItem(new Planet("pluto"))));
    }
}
