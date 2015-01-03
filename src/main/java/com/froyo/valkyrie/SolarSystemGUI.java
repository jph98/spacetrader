package com.froyo.valkyrie;

import com.froyo.valkyrie.entities.PlanetTile;
import com.froyo.valkyrie.entities.PlayerTile;
import com.froyo.valkyrie.entities.HumanPlayer;
import com.froyo.valkyrie.entities.SpaceTile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * SolarSystemGUI
 */
public class SolarSystemGUI extends Canvas {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String NAME = "TraderGalaxy";

    public static final int CELL_SIZE = 64;
    private final ImageHolder imageHolder;

    private JFrame frame;
    private GameContext context;

    public SolarSystemGUI(GameContext context) {

        this.imageHolder = new ImageHolder();
        this.context = context;

        // Add the white spacing (2) + border lip (2)
        int width = context.getWidth() * (CELL_SIZE + 2) + 2;
        int height = context.getHeight() * (CELL_SIZE + 2) + 2;

        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));

        frame = new JFrame(NAME);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);

        JPanel panel = new JPanel(new FlowLayout());

        JLabel turns = new JLabel("Turn Number 3");
        panel.add(turns);

        panel.setBackground(Color.black);

        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Set the canvas as focusable for input
        setFocusable(true);

        context.setState(GameState.TravellingBoard);
    }

    public void init() {
        render();
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

        if (context.getState().equals(GameState.TravellingBoard)) {
            renderTiles(g);
            renderPlayers(bs, g);
        } else if (context.getState().equals(GameState.VisitingPlanet)) {

            renderTiles(g);
            renderPlayers(bs, g);

            // Show the planet screen, placeholder
            String content = "";
            JOptionPane.showMessageDialog(null, new JTextArea(content), "Your title here bro", JOptionPane.PLAIN_MESSAGE);


        }
    }

    public void tick() {

        for (PlayerTile p: context.getPlayers()) {

            p.preMove();
            p.tick();
            p.postMove();
        }
    }

    public void renderPlayers(BufferStrategy bs, Graphics2D g) {

        BufferedImage image = null;

        for (HumanPlayer p: context.getPlayers()) {

            image = imageHolder.getImage("player.png");
            int xLocation = (p.getX() * CELL_SIZE) + (2 * p.getX());
            int yLocation = (p.getY() * CELL_SIZE) + (2 * p.getY());

            image = ImageUtils.rotateImage(image, p.getRotation());

            g.drawImage(image, xLocation, yLocation, image.getWidth(), image.getHeight(), null);
        }

        bs.show();
    }

    public void renderTiles(Graphics2D g) {

        int[][] board = context.getBoard();

        for (int x = 0; x < context.getWidth(); x++) {

            for (int y = 0; y < context.getHeight(); y++) {

                int tile = board[x][y];

                int xLocation = (x * CELL_SIZE) + (2 * x + 2);
                int yLocation = (y * CELL_SIZE) + (2 * y + 2);

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
        image = imageHolder.getImage(imageName);
        g.drawImage(image, xLocation, yLocation, image.getWidth(), image.getHeight(), null);
    }

    public InputHandler createInputHandler() {
        return new InputHandler(this);
    }
}
