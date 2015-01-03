package com.froyo.valkyrie;

import com.froyo.valkyrie.entities.PlanetTile;
import com.froyo.valkyrie.entities.SpaceTile;
import com.froyo.valkyrie.util.RandomUtil;
import com.google.common.collect.Lists;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.froyo.valkyrie.entities.*;

public class GameContext {

    private static Logger logger = LoggerFactory.getLogger(GameContext.class);

    private int widthInCells;
    private int heightInCells;

    private int[][] board;

    private DirectedGraph<PlanetTile, DefaultEdge> galaxy;
    private List<HumanPlayer> players = Lists.newArrayList();
    private GameState state;

    public GameContext(int width, int height) {

        this.widthInCells = width;
        this.heightInCells = height;

        this.board = new int[width][height];

        for (int x = 0; x < width; x++) {

            for (int y = 0; y < height; y++) {

                board[x][y] = SpaceTile.TILE;
            }
        }

        galaxy = new DefaultDirectedGraph<PlanetTile, DefaultEdge>(DefaultEdge.class);

    }

    public PlanetTile addRandomPlanet(String name) {

        int x = RandomUtil.numberInRange(0, this.widthInCells - 1);
        int y = RandomUtil.numberInRange(0, this.heightInCells - 1);

        PlanetTile p = new PlanetTile().setName(name)
                               .setX(x)
                               .setY(y);

        board[x][y] = PlanetTile.TILE;

        galaxy.addVertex(p);

        logger.info("Added planet " + p.getName() + " at [" + p.getX() + "," + p.getY() + "]");

        return p;
    }

    public void connectPlanets(PlanetTile origin, PlanetTile destination) {

        galaxy.addEdge(origin, destination);
    }


//    public void display() {
//
//        for (PlanetTile planet: galaxy.vertexSet()) {
//
//            System.out.println("Found planet " + planet.toString());
//
//            for (DefaultEdge edge: galaxy.outgoingEdgesOf(planet)) {
//
//                PlanetTile edgeSource = galaxy.getEdgeSource(edge);
//                PlanetTile edgeTarget = galaxy.getEdgeTarget(edge);
//                logger.info("\t " + edgeSource + " -> " + edgeTarget);
//            }
//        }
//
//    }

    public void createPlanetsFromList(Map config, List<Object> planetNames) {

        int size = planetNames.size();
        int totalCells = widthInCells * heightInCells;

        int totalPlanetsToPlace = (totalCells / 100) * 5;

        for (int n = 0; n < totalPlanetsToPlace; n++) {

            String name = (String) planetNames.get(n);
            System.out.println("Found " + name);
            addRandomPlanet(name);

            if (totalPlanetsToPlace == 0 || n == size) {
                break;
            }
        }
    }

    public PlanetTile getPlanetByName(String name) {

        Set<PlanetTile> planets = galaxy.vertexSet();
        for (PlanetTile p: planets) {

            if (p.getName().equals(name)) {

                return p;
            }
        }
        return null;
    }

    public void createRandomConnection(ArrayList<Object> planetNames, Set<PlanetTile> planets, PlanetTile p) {

        // Create two connections randomly
        int v = RandomUtil.numberInRange(0, planets.size() - 1);
        String name = (String) planetNames.get(v);

        // Get planet by name from planets
        PlanetTile planet = getPlanetByName(name);

        // Connect
        if (!p.getName().equals(planet.getName())) {
            connectPlanets(p, planet);
        }
    }
    
    public DirectedGraph<PlanetTile, DefaultEdge> getGalaxy() {
        return galaxy;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getWidth() {
        return widthInCells;
    }

    public int getHeight() {
        return heightInCells;
    }

    public List<HumanPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(HumanPlayer p) {
        this.players.add(p);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
