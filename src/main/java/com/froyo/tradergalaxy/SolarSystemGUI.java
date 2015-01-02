package com.froyo.tradergalaxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * SolarSystemGUI
 */
public class SolarSystemGUI extends Canvas {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String NAME = "Game";
    public static final int WIDTH = 304;
    public static final int HEIGHT = 211;
    public static final int SCALE = 3;

    private final JFrame frame;

    public SolarSystemGUI() {

        setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void render(SolarSystem system) {

        BufferStrategy bs = getBufferStrategy();

        // Triple buffering, more processing power required, but lets pixelating
        // of images
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // Tiles
        //level.renderTiles(screen, xOffset, yOffset);
        renderTiles(g);

        // Entities
        //level.renderEntities(screen);

        BufferedImage image = null;
        try {

            image = ImageIO.read(this.getClass().getResource("player.png"));
            g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            g.dispose();
            bs.show();

        } catch (IOException e) {
            logger.error("IOException loading image " + e);
        }

    }

    private void renderTiles(Graphics g) {

        for (int y = 1; y < 600; y += 65) {

            for (int x = 1; x < 850; x += 65) {

                BufferedImage image = null;
                try {

                    // 64 x 64
                    image = ImageIO.read(this.getClass().getResource("space.png"));
                    g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);

                } catch (IOException e) {
                    logger.error("IOException loading image " + e);
                }
            }
        }
    }
}
