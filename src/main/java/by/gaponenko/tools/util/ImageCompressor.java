package by.gaponenko.tools.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageCompressor {
    static Logger logger = LogManager.getLogger();

    private static final String IMAGE_TYPE = "jpg";
    private static final int IMAGE_WIDTH_AVATAR = 300;
    private static final int IMAGE_HEIGHT_AVATAR = 300;
    private static final int IMAGE_WIDTH_PHOTO = 600;
    private static final int IMAGE_HEIGHT_PHOTO = 600;

    private ImageCompressor() {
    }

    public static InputStream compressAvatar(InputStream image) {
        InputStream newImage = null;
        try {
            BufferedImage img = ImageIO.read(image);
            BufferedImage compressedImg = Scalr.resize(img, Scalr.Method.QUALITY, IMAGE_WIDTH_AVATAR, IMAGE_HEIGHT_AVATAR);
            ByteArrayOutputStream imgByte = new ByteArrayOutputStream();
            ImageIO.write(compressedImg, IMAGE_TYPE, imgByte);
            newImage = new ByteArrayInputStream(imgByte.toByteArray());
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return newImage;
    }

    public static InputStream compressPhoto(InputStream image) {
        InputStream newImage = null;
        try {
            BufferedImage img = ImageIO.read(image);
            BufferedImage compressedImg = Scalr.resize(img, Scalr.Method.QUALITY, IMAGE_WIDTH_PHOTO, IMAGE_HEIGHT_PHOTO);
            ByteArrayOutputStream imgByte = new ByteArrayOutputStream();
            ImageIO.write(compressedImg, IMAGE_TYPE, imgByte);
            newImage = new ByteArrayInputStream(imgByte.toByteArray());
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return newImage;
    }
}
