package org.springbus.sd.model;

import org.springbus.sd.utils.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;

public class PILImage {
    private String base64Img;
    public PILImage(String base64Img){
        this.base64Img=base64Img;
    }
    public  void save(String imgPath){
        ImageUtil.base64ToImg(base64Img,imgPath);
    }
    public BufferedImage resize(int w,int h) {
        BufferedImage image = ImageUtil.base64ToImg(base64Img);
        BufferedImage bufferedImage = ImageUtil.reSize(image, w, h);
        return bufferedImage;
    }
    public void resizeAndSaveTo(int w,int h,String imgPath) throws IOException {
        BufferedImage image = ImageUtil.base64ToImg(base64Img);
        BufferedImage bufferedImage = ImageUtil.reSize(image, w, h);
        save(bufferedImage,imgPath);
    }
    public  void save(BufferedImage bufferedImage,String imgPath) throws IOException {
        ImageIO.write(bufferedImage,"png",new File(imgPath));
    }

}
