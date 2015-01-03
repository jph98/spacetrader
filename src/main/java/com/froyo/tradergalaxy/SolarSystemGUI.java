package com.froyo.tradergalaxy;

import com.froyo.tradergalaxy.entities.PlanetTile;
import com.froyo.tradergalaxy.entities.PlayerTile;
import com.froyo.tradergalaxy.entities.HumanPlayer;
import com.froyo.tradergalaxy.entities.SpaceTile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * SolarSystemGUI
 */
public class SolarSystemGUI extends Canvas {

    private static Logger logger = LoggerFactory.getLogger(GameMain.class);

    private static final String NAME = "TraderGalaxy";
    private final ImageHolder imageHolder;

    private JFrame frame;
    private GameContext system;

    public SolarSystemGUI(GameContext system) {

        this.imageHolder = new ImageHolder();
        this.system = system;

        int width = system.getWidth() * 64 + 20;
        int height = system.getHeight() * 64 + 20;

        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void render() {

        BufferStrategy bs = getBufferStrategy();

        // Triple buffering, more processing power required, but lets pixelating
        // of images
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        renderTiles(g);

        // Render Entities
        renderPlayers(bs, g);
    }

    public void tick() {

        for (PlayerTile p: system.getPlayers()) {

            p.tick();
        }
    }

    public void renderPlayers(BufferStrategy bs, Graphics2D g) {

        BufferedImage image = null;

        for (HumanPlayer p: system.getPlayers()) {

            image = imageHolder.getImage("player.png");
            int xLocation = (p.getX() * 64) + (2 * p.getX());
            int yLocation = (p.getY() * 64) + (2 * p.getY());

            image = ImageUtils.rotateImage(image, p.getRotation());

            g.drawImage(image, xLocation, yLocation, image.getWidth(), image.getHeight(), null);
        }

        bs.show();
    }

    public void renderTiles(Graphics2D g) {

        int[][] board = system.getBoard();

        for (int x = 0; x < system.getWidth(); x++) {

            for (int y = 0; y < system.getHeight(); y++) {

                int tile = board[x][y];

                int xLocation = (x * 64) + (2 * x);
                int yLocation = (y * 64) + (2 * y);

                logger.debug("Placing " + TileResolver.resolve(tile) + " at " + x + "," + y + " - [" + xLocation + "," + yLocation + "]");

                if (tile == PlanetTile.TILE) {

                    // Render the space background for the planet first
                    drawTile(g, "space.png", xLocation, yLocation);
                    drawTile(g, "planet.png", xLocation, yLocation);

                } else if (tile == SpaceTile.TILE) {
                    drawTile(g, "space.png", xLocation, yLocation);
                }
            }
        }
    }

    private void drawTile(Graphics2D g, String imageName, int xLocation, int yLocation) {

        BufferedImage image = null;
        // 64 x 64
        //image = ImageIO.read(this.getClass().getResource(imageName));
        image = imageHolder.getImage(imageName);
        g.drawImage(image, xLocation, yLocation, image.getWidth(), image.getHeight(), null);
    }

    public InputHandler createInputHandler() {
        return new InputHandler(this);
    }

    public void init() {
        render();
    }
}
