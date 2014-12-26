/*******************************************************************************
 * Copyright (c) 1999 - 2011.
 * Global Relay Communications Inc.
 * All rights reserved.
 ******************************************************************************/
package com.oogly.spacetrader;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

public class SolarSystem {

    private DirectedGraph<Planet, DefaultEdge> galaxy;
    
    public SolarSystem() {

        galaxy = new DefaultDirectedGraph<Planet, DefaultEdge>(DefaultEdge.class);

    }

    public Planet addPlanetVertex(String name) {

        Planet p = new Planet(name);
        galaxy.addVertex(p);
        return p;
    }

    public void connectPlanets(Planet origin, Planet destination) {

        galaxy.addEdge(origin, destination);
    }


    public void display() {

        // TODO: Maintain a list of visited nodes
        for (Planet planet: galaxy.vertexSet()) {

            System.out.println("Found planet " + planet.toString());

            for (DefaultEdge edge: galaxy.outgoingEdgesOf(planet)) {

                Planet edgeSource = galaxy.getEdgeSource(edge);
                Planet edgeTarget = galaxy.getEdgeTarget(edge);
                System.out.println("\t " + edgeSource + " -> " + edgeTarget);
            }
        }

    }
    
    public DirectedGraph<Planet, DefaultEdge> getGalaxy() {
        return galaxy;
    }
    
}
