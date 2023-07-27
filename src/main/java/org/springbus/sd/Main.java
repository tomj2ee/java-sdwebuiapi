package org.springbus.sd;

import org.springbus.sd.extensions.ADetailer;
import org.springbus.sd.extensions.ControlNetUnit;
import org.springbus.sd.model.ControlNet;
import org.springbus.sd.model.PILImage;
import org.springbus.sd.model.Txt2ImgModel;
import org.springbus.sd.model.WebUIApiResult;
import org.springbus.sd.utils.ImageUtil;

import java.util.List;

public class Main {
    static String baseURL = "http://localhost:7860";

    static String prompt = "masterpiece, best quality, wall paper, (8k, best quality, masterpiece:1.2),a woman,garden,Sexy, mature woman, wedding dress, smile,happy,flower,look at viewer,  full body, <lora:lye_v4:1>";
    static String nsfw = "nsfw,(nipples),leakage points, big boobs, leather, net lining, mesh lining, paintings, big head, sketches, naked, (worst quality:2), (low quality:2), (normal quality:2), lowres, bad anatomy, bad hands, normal quality, ((monochrome)), ((grayscale)), futanari, full-package_futanari, penis_from_girl, newhalf, nipplepierces, glans penis, collapsed eyeshadow, multiple eyeblows, vaginas in breasts, pink hair, holes on breasts, ng_deepnegative_v1_75t, skin spots, acnes, skin blemishes, age spot, glans, nsfw, nipples,extra fingers, ((extra arms)), (extra legs), mutated hands,(fused fingers), (too many fingers), (long neck:1.3)";


    static void createText2Img() throws Exception {
        WebUIApi webUIApi = new WebUIApi(baseURL);
        Txt2ImgModel txt2ImgModel = new Txt2ImgModel();
        txt2ImgModel.prompt = prompt;
        txt2ImgModel.negative_prompt = nsfw;
        txt2ImgModel.batch_size = 4;
        txt2ImgModel.width = 512;
        txt2ImgModel.height = 768;
        WebUIApiResult r = webUIApi.txt2Img(txt2ImgModel);
        List<PILImage> imageList = r.getImages();
        int ix = 0;
        for (PILImage image : imageList) {
            image.save("./outs/imgs/a-" + ix + ".png");
            ix++;
        }
    }

    static void crateWithControlNet() throws Exception {
        ControlNet net = new ControlNet();
        net.model = "control_v11p_sd15_canny [d14c016b]";
        net.module = "canny";
        net.input_image = ImageUtil.b64Img("./imgs/a.jpeg");
        ControlNetUnit controlNetUnit = new ControlNetUnit(net);

        WebUIApi webUIApi = new WebUIApi(baseURL);
        Txt2ImgModel txt2ImgModel = new Txt2ImgModel();
        txt2ImgModel.prompt = prompt;
        txt2ImgModel.negative_prompt = nsfw;
        txt2ImgModel.batch_size = 4;
        txt2ImgModel.width = 512;
        txt2ImgModel.height = 768;
        txt2ImgModel.withExtensions(controlNetUnit);
        WebUIApiResult r = webUIApi.txt2Img(txt2ImgModel);
        List<PILImage> imageList = r.getImages();
        int ix = 0;
        for (PILImage image : imageList) {
            image.save("./outs/imgs/controlnet-" + ix + ".png");
            ix++;
        }
    }

    static void crateWithADetailer() throws Exception {
        ADetailer aDetailer = new ADetailer();
        aDetailer.ad_model = "face_yolov8n.pt";
        WebUIApi webUIApi = new WebUIApi(baseURL);
        Txt2ImgModel txt2ImgModel = new Txt2ImgModel();
        txt2ImgModel.prompt = prompt;
        txt2ImgModel.negative_prompt = nsfw;
        txt2ImgModel.batch_size = 4;
        txt2ImgModel.width = 512;
        txt2ImgModel.height = 768;
        txt2ImgModel.withExtensions(aDetailer);
        WebUIApiResult r = webUIApi.txt2Img(txt2ImgModel);
        List<PILImage> imageList = r.getImages();
        int ix = 0;
        for (PILImage image : imageList) {
            image.save("./outs/imgs/aDetailer-" + ix + ".png");
            ix++;
        }

    }

    static void getSample() throws Exception {
        WebUIApi webUIApi = new WebUIApi(baseURL);
        String r = webUIApi.getSamplers();
        System.out.println(r);
    }

    static void getLora() throws Exception {
        WebUIApi webUIApi = new WebUIApi(baseURL);
        String r = webUIApi.getLoras();
        System.out.println(r);
    }

    public static void main(String[] args) throws Exception {
        // getSample();
        //getLora();
        //crateWithControlNet();
        // crateWithADetailer();
    }
}