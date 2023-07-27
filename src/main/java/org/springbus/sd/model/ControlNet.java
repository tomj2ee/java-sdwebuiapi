package org.springbus.sd.model;

public class ControlNet {
    public String input_image;
    public String mask = "";
    public String module = "None";
    public String model = "None";
    public Float weight = 1.0f;
    public String resize_mode = "Resize and Fill";
    public Boolean lowvram = false;
    public Integer processor_res = 512;
    public Float threshold_a = 64f;
    public Float threshold_b = 64f;
    public Float guidance = 1.0f;
    public Float guidance_start = 0.0f;
    public Float guidance_end = 1.0f;
    public Integer control_mode = 0;
    public Boolean pixel_perfect = false;
}

