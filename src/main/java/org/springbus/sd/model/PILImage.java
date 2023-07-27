package org.springbus.sd.model;

import org.springbus.sd.utils.ImageUtil;

public class PILImage {
    private String base64Img;
    public PILImage(String base64Img){
        this.base64Img=base64Img;
    }
    public  void save(String imgPath){
        ImageUtil.base64ToImg(base64Img,imgPath);
    }
}
