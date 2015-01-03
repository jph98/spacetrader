package com.froyo.valkyrie;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * ImageHolder
 */
public class ImageHolder {

    private static Logger logger = LoggerFactory.getLogger(GameMain.class);

    private Map<String, BufferedImage> imageMap = Maps.newHashMap();

    public ImageHolder() {

        try {
            imageMap.put("player.png", ImageIO.read(this.getClass().getResource("player.png")));
            imageMap.put("planet.png", ImageIO.read(this.getClass().getResource("planet.png")));
            imageMap.put("space.png", ImageIO.read(this.getClass().getResource("space.png")));

        } catch (IOException e) {
            logger.error("IOException loading image " + e);
        }
    }

    public BufferedImage getImage(String imageName) {
        return imageMap.get(imageName);
    }
}
