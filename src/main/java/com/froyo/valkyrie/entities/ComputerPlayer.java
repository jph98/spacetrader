package com.froyo.valkyrie.entities;

/**
 * ComputerPlayer
 */
public class ComputerPlayer {

    public static final int TILE = 2;

    public static final String DESCRIPTION = "ComputerPlayer";

    private String name;

    private int x;
    private int y;

    public ComputerPlayer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {

       // TODO: Move the player according to some sort of AI
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
