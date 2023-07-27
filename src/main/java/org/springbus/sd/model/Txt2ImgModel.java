package org.springbus.sd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springbus.sd.extensions.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Txt2ImgModel {
    public boolean enable_hr = false;
    public Float denoising_strength = 0.7f;
    public int firstphase_width = 0;
    public int firstphase_height = 0;
    public Float hr_scale = 2f;
    public String hr_upscaler="latent";
    public int hr_second_pass_steps = 0;
    public int hr_resize_x = 0;
    public int hr_resize_y = 0;
    public String prompt = "";
    public List<String> styles = new ArrayList<>();
    public Long seed = -1L;
    public Long subseed = -1L;
    public float subseed_strength = 0.0f;
    public float seed_resize_from_h = 0f;
    public float seed_resize_from_w = 0;
    public String sampler_name = "Euler a";//,  # use this instead of sampler_index
    public Integer batch_size = 1;
    public Integer n_iter = 1;
    public Integer steps = 30;
    public Float cfg_scale = 7.0f;
    public Integer width = 512;
    public Integer height = 512;
    public boolean restore_faces = false;
    public boolean tiling = false;
    public boolean do_not_save_samples = false;
    public boolean do_not_save_grid = false;
    public String negative_prompt = "";
    public Float eta = 1.0f;
    public Integer s_churn = 0;
    public Integer s_tmax = 0;
    public Integer s_tmin = 0;
    public Integer s_noise = 1;
    public final Map<String, Object> override_settings = new HashMap<>();
    public   boolean override_settings_restore_afterwards = true;
    public final List<String> script_args = new ArrayList<>();// # List of arguments for the script "script_name"
    public String script_name = null;
    public boolean send_images = true;
    public boolean save_images = false;
    public final Map<String, Object> alwayson_scripts = new HashMap<>();
    public String sampler_index = "Euler a";


    public  Txt2ImgModel withExtensions(Plugin plugin) {
        Map<String, Object> args = new HashMap<>();
        args.put("args", plugin.toDict());
        alwayson_scripts.put(plugin.getName(), args);
        return this;
    }
}
