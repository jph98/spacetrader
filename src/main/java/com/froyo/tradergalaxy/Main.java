package com.froyo.tradergalaxy;


import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private SolarSystem system;
    
    public Main() {

        // Create a 100 x 100 grid
        system = new SolarSystem(100, 100);

        Yaml yaml = new Yaml();

        Map config =(Map) yaml.load(this.getClass().getResourceAsStream("planets.yml"));

        // Create planets
        ArrayList<Object> planetNames = (ArrayList<Object>) config.get("planets");
        system.createPlanets(config, planetNames);

        int numConnections = (int) config.get("numconnections");

        // Create connections
        DirectedGraph<Planet,DefaultEdge> galaxy = system.getGalaxy();
        Set<Planet> planets = galaxy.vertexSet();
        for (Planet p: planets) {

            for (int n = 0; n < numConnections; n++) {
                system.createRandomConnection(planetNames, planets, p);
            }
        }

        SolarSystemGUI gui = new SolarSystemGUI();

        while (true) {
            gui.render(system);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            logger.info("Rendered...");
        }
    }

    public static void main( String[] args ) {
        
        new Main();
    }
    
}
