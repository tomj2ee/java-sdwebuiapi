package org.springbus.sd.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
 
public class ImageUtil {
    public static String img2Base64(String src) {
        ByteArrayOutputStream baos = null;
        try {
            String suffix = src.substring(src.lastIndexOf(".") + 1);
            File imageFile = new File(src);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, suffix, baos);
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void base64ToImg(String base64String, String imgToPath) {
        ByteArrayInputStream bais = null;
        try {
            String suffix = imgToPath.substring(imgToPath.lastIndexOf(".") + 1);
            byte[] bytes = Base64.getDecoder().decode(base64String);
            bais = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(bais);
            File imageFile = new File(imgToPath);
            ImageIO.write(bufferedImage, suffix, imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String b64Img(String imgPath) {
        return "data:image/png;base64," + img2Base64(imgPath);
    }
}