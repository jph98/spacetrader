package com.froyo.tradergalaxy;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolarSystem {

    private static Logger logger = LoggerFactory.getLogger(SolarSystem.class);

    private int width;
    private int height;

    private DirectedGraph<Planet, DefaultEdge> galaxy;
    
    public SolarSystem(int width, int height) {

        this.width = width;
        this.height = height;
        galaxy = new DefaultDirectedGraph<Planet, DefaultEdge>(DefaultEdge.class);

    }

    public Planet addPlanetVertex(String name) {

        int x = (int) (Math.random() * (this.height - 0));
        int y = (int) (Math.random() * (this.width - 0));

        Planet p = new Planet().setName(name)
                               .setX(x)
                               .setY(y);

        galaxy.addVertex(p);

        logger.info("Added planet " + p.getName() + " at [" + p.getX() + "," + p.getY() + "]");

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
                logger.info("\t " + edgeSource + " -> " + edgeTarget);
            }
        }

    }

    public void createPlanets(Map config, List<Object> planetNames) {

        for (Object n: planetNames) {

            String name = (String) n;
            System.out.println("Found " + name);

            addPlanetVertex(name);
        }
    }

    public Planet getPlanetByName(String name) {

        Set<Planet> planets = galaxy.vertexSet();
        for (Planet p: planets) {

            if (p.getName().equals(name)) {

                return p;
            }
        }
        return null;
    }

    public void createRandomConnection(ArrayList<Object> planetNames, Set<Planet> planets, Planet p) {

        // Create two connections randomly
        double v = Math.random() * (planets.size() - 0);
        String name = (String) planetNames.get((int) v);

        // Get planet by name from planets
        Planet planet = getPlanetByName(name);

        // Connect
        if (!p.getName().equals(planet.getName())) {
            connectPlanets(p, planet);
        }
    }
    
    public DirectedGraph<Planet, DefaultEdge> getGalaxy() {
        return galaxy;
    }
    
}
