package com.froyo.valkyrie;


import com.froyo.valkyrie.entities.HumanPlayer;
import com.froyo.valkyrie.entities.PlanetTile;
import com.froyo.valkyrie.entities.Product;
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

    private SolarSystemGUI gui;
    private GameContext context;
    private long gameStartTime;

    public Main() {

    }

    public void setup() {

        Yaml yaml = new Yaml();
        Map config =(Map) yaml.load(this.getClass().getResourceAsStream("game.yml"));

        int height = (int) config.get("board.height");
        int width = (int) config.get("board.width");

        this.context = new GameContext(width, height);

        // Create our basic GUI
        gui = new SolarSystemGUI(context);

        // Create planets and connections in context
        createPlanets(config);

        // Create players in context
        createPlayers();

        renderBoard();

    }

    private void renderBoard() {
        gui.init();
        gui.render();
    }

    private void createPlayers() {

        InputHandler handler = gui.createInputHandler();

        HumanPlayer p = new HumanPlayer(0, 0);
        p.setInputHandler(handler);
        p.setGameContext(context);

        context.addPlayer(p);
    }

    private void createPlanets(Map planetsConfig) {

        ArrayList<Object> planetNames = (ArrayList<Object>) planetsConfig.get("planets");
        context.createPlanetsFromList(planetsConfig, planetNames);
        int numConnections = (int) planetsConfig.get("num.connections");

        DirectedGraph<PlanetTile,DefaultEdge> galaxy = context.getGalaxy();
        Set<PlanetTile> planets = galaxy.vertexSet();
        for (PlanetTile p: planets) {

            for (int n = 0; n < numConnections; n++) {
                context.createRandomConnection(planetNames, planets, p);
            }

            // Initialise the planet with missions, market and engineering bay

            Yaml yaml = new Yaml();
            Map productsConfig =(Map) yaml.load(this.getClass().getResourceAsStream("products.yml"));

            ArrayList<Object> products = (ArrayList<Object>) productsConfig.get("products");
            for (Object product: products) {
                logger.info("Found product: " + (Product) product);
            }

            p.createMarket(products);
            p.createEngineeringBay();
            p.createMissions();
        }
    }

    public void loopContinuous() {

        this.gameStartTime = System.currentTimeMillis();

        long lastTime = System.nanoTime();

        // How many ns in a tick
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();

        // How many ns have gone by so far
        double delta = 0;

        gui.init();

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                gui.tick();
                delta -= 1;
                shouldRender = true;
            }

            // Otherwise we end up with huge frame rate
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
            }

            if (shouldRender) {
                frames++;
                gui.render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                logger.debug("ticks: " + ticks + ", frames: " + frames);
                frames = 0;
                ticks = 0;
            }

            checkGameEvents();
        }
    }

    private void checkGameEvents() {

        long startTime = this.gameStartTime;
        long now = System.currentTimeMillis();

        // Duummy event for now
        if (now - startTime > 60000) {
            logger.info("Game has been running for 60 seconds");
        }
    }

    public static void main( String[] args ) {
        
        Main main = new Main();
        main.setup();
        //main.loopContinuous();
    }
}
