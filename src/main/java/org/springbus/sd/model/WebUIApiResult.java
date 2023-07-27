package org.springbus.sd.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIApiResult {

    private final List<PILImage> images = new ArrayList<>();
    public final Map<String, Object> parameters = new HashMap<>();
    public final Map<String, Object> info = new HashMap<>();


    public PILImage getImage() {
        if (images.size() > 0) {
            return this.images.get(0);
        }
        return null;
    }

    public List<PILImage> getImages() {
        return images;
    }

    public void addImage(String base64Img) {
        images.add(new PILImage(base64Img));
    }
}
