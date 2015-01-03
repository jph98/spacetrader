package com.froyo.tradergalaxy;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * ImageUtils
 */
public class ImageUtils {


    public static BufferedImage rotateImage(BufferedImage image, double rotation) {

        double rotationInRadians = Math.toRadians(rotation);

        AffineTransform transform = new AffineTransform();
        transform.rotate(rotationInRadians , image.getWidth()/2, image.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(image, null);
    }
}
