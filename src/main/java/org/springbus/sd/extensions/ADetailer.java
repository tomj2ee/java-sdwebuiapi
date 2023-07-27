package org.springbus.sd.extensions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ADetailer implements Plugin {

    public String ad_model = "None";
    public String ad_prompt = "";
    public String ad_negative_prompt = "";
    public Float ad_confidence = 0.3f;
    public Float ad_mask_min_ratio = 0.0f;
    public Float ad_mask_max_ratio = 1.0f;
    public Integer ad_dilate_erode = 4;
    public Integer ad_x_offset = 0;
    public Integer ad_y_offset = 0;
    //Literal["None", "Merge", "Merge and Invert"] = "None",
    public String ad_mask_merge_invert="None";
    public Integer ad_mask_blur = 4;
    public Float ad_denoising_strength = 0.4f;
    public boolean ad_inpaint_only_masked = true;
    public Integer ad_inpaint_only_masked_padding = 32;
    public boolean ad_use_inpaint_width_height = false;
    public Integer ad_inpaint_width = 512;
    public Integer ad_inpaint_height = 512;
    public boolean ad_use_steps = false;
    public Integer ad_steps = 28;
    public boolean ad_use_cfg_scale = false;
    public Float ad_cfg_scale = 7.0f;

    public boolean ad_use_noise_multiplier = false;
    public Float ad_noise_multiplier = 1.0f;
    public boolean ad_restore_face = false;
    public String ad_controlnet_model = "None";
    public String ad_controlnet_module = "None";
    public Float ad_controlnet_weight = 1.0f;
    public Float ad_controlnet_guidance_start = 0.0f;
    public Float ad_controlnet_guidance_end = 1.0f;
    public boolean is_api = true;

    @Override
    public Object toDict() {
        List<Object> dict=new ArrayList<>();
        dict.add(true);
        dict.add(this);
        return dict;
    }

    @JsonIgnore
    @Override
    public String getName() {
        return "ADetailer";
    }

}