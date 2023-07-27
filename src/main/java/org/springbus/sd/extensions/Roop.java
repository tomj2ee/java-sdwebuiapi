package org.springbus.sd.extensions;

import java.util.ArrayList;
import java.util.List;

public class Roop  implements  Plugin {
    public String img;
    public boolean enable = true;
    public String faces_index = "0";
    public String model = "";
    public String face_restorer_name = "GFPGAN";
    public float face_restorer_visibility = 1f;
    public String upscaler_name = "R-ESRGAN 4x+";
    public Float upscaler_scale = 1f;
    public Float upscaler_visibility = 1f;
    public boolean swap_in_source = false;
    public boolean swap_in_generated = true;


    public Object toDict() {
        List<Object> dict = new ArrayList<>();
        dict.add(this.img);
        dict.add(this.enable);
        dict.add(this.faces_index);
        dict.add(this.model);
        dict.add(this.face_restorer_name);
        dict.add(this.face_restorer_visibility);
        dict.add(this.upscaler_name);
        dict.add(this.upscaler_scale);
        dict.add(this.upscaler_visibility);
        dict.add(this.swap_in_source);
        dict.add(this.swap_in_generated);
        return dict;
    }

    @Override
    public String getName() {
        return "roop";
    }

}
