package kr.trip.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class ImageController {

	public static String encodeImage(byte[] imageBytes) {
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	public static BufferedImage base64ToImage(String base64Image) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        return ImageIO.read(bis);
    }
	
}
