package com.froyo.valkyrie.entities;

import com.froyo.valkyrie.GameContext;
import com.froyo.valkyrie.GameState;
import com.froyo.valkyrie.InputHandler;
import com.froyo.valkyrie.TileResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HumanPlayer
 */
public class HumanPlayer implements PlayerTile {

    private static Logger logger = LoggerFactory.getLogger(HumanPlayer.class);

    public static final int TILE = 2;
    public static final String DESCRIPTION = "HumanPlayer";

    private InputHandler handler;

    private String name;
    private int x;
    private int y;
    private GameContext context;

    public static final char U = 'u';
    public static final char D = 'd';
    public static final char L = 'l';
    public static final char R = 'r';

    private char direction = R;

    public HumanPlayer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setInputHandler(InputHandler handler) {
        this.handler = handler;
    }

    public void setGameContext(GameContext context) {
        this.context = context;
    }

    public void tick() {

        if (handler.up.isPressed()) {
            if (y == 0) return;
            direction = U;
            y -= 1;
        } if (handler.down.isPressed()) {
            if (y + 1 == context.getHeight()) return;
            direction = D;
            y += 1;
        } if (handler.left.isPressed()) {
            if (x == 0) return;
            direction = L;
            x -= 1;
        } if (handler.right.isPressed()) {
            if (x + 1 == context.getWidth()) return;
            direction = R;
            x += 1;
        }
    }

    @Override
    public void preMove() {

        //Check new pos
        int[][] board = context.getBoard();
        int tile = board[x][y];

        logger.info("Tile pre move is " + TileResolver.resolve(tile));
    }

    @Override
    public void postMove() {

        //Check new pos
        int[][] board = context.getBoard();
        int tile = board[x][y];

        logger.info("Tile post move is " + TileResolver.resolve(tile));
        if (tile == PlanetTile.TILE) {

            // Enter planet mode
            context.setState(GameState.VisitingPlanet);
        } else {
            context.setState(GameState.TravellingBoard);
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public double getRotation() {

        switch (direction) {

            case R: {
                return 0;
            }

            case L: {
                return 180;
            }

            case U: {
                return 270;
            }

            case D: {
                return 90;
            }
        }
        return 0;
    }
}
