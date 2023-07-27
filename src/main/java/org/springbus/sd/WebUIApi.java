package org.springbus.sd;

import org.springbus.sd.model.Img2ImgModel;
import org.springbus.sd.model.Txt2ImgModel;
import org.springbus.sd.model.WebUIApiResult;
import org.springbus.sd.utils.HttpClientUtil;
import org.springbus.sd.utils.JacksonUtil;

import java.util.List;
import java.util.Map;

public class WebUIApi {
    private String baseurl = "http://localhost:7660";

    private String userName;
    private String passWord;


    public WebUIApi(String baseurl) {
        this.baseurl = baseurl + "/sdapi/v1";
    }

    public WebUIApi() {
        this.baseurl = baseurl + "/sdapi/v1";
    }

    public void setAuth(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }


    public WebUIApiResult txt2Img(Txt2ImgModel txt2ImgModel) throws Exception {
        String url = baseurl + "/txt2img";
        String payload = JacksonUtil.toJsonString(txt2ImgModel);
        System.out.println(payload);
        Map result = HttpClientUtil.postAndGetApiResult(url, payload, userName, passWord);
        return toApiResult(result);
    }
    public WebUIApiResult img2Img(Img2ImgModel img2ImgModel) throws Exception {
        String url = baseurl + "/img2img";
        String payload = JacksonUtil.toJsonString(img2ImgModel);
        Map result = HttpClientUtil.postAndGetApiResult(url, payload, userName, passWord);
        return toApiResult(result);
    }


    private WebUIApiResult toApiResult(Map<String, Object> result) {
        Object o = result.get("images");
        WebUIApiResult rs = new WebUIApiResult();
        if (o == null) {
            o = result.get("image");
        }
        if (o != null && o instanceof List) {
            List<String> imgs = (List<String>) o;
            for (String img : imgs) {
                rs.addImage(img);
            }
        }
        Object info = result.get("info");
        if (info instanceof String) {
            rs.info.putAll(JacksonUtil.jsonToObject(String.valueOf(info), Map.class));
        }

        Object parameters = result.get("parameters");
        if (parameters != null) {
            if (parameters instanceof String) {
                rs.parameters.putAll(JacksonUtil.jsonToObject(String.valueOf(parameters), Map.class));
            } else if (parameters instanceof Map) {
                rs.parameters.putAll((Map<? extends String, ?>) parameters);
            }
        }
        return rs;
    }


    public WebUIApiResult pngInfo(String image) throws Exception {
        String payload = "{\"image\":" + "\"" + image + "\" }";

        String url = baseurl + "/png-info";
        Map result = HttpClientUtil.postAndGetApiResult(url, payload, userName, passWord);
        return toApiResult(result);
    }

    /**
     * param model "clip" or "deepdanbooru"
     */
    public WebUIApiResult interrogate(String image, String model) throws Exception {
        String payload = " {\"image\":\"" + image + "\", \"model\": " + model + "\"} ";
        String url = baseurl + "/interrogate";
        Map result = HttpClientUtil.postAndGetApiResult(url, payload, userName, passWord);
        return toApiResult(result);
    }

    public String interrupt() throws Exception {
        String url = baseurl + "/interrupt";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }


    public String skip() throws Exception {
        String url = baseurl + "/skip";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }


    public String getOptions() throws Exception {
        String url = baseurl + "/options";
        return HttpClientUtil.getAndGetApiResult(url,  userName, passWord);
    }

    public Map setOptions(Map<String, Object> options) throws Exception {
        String url = baseurl + "/options";
        String payload = JacksonUtil.toJsonString(options);
        return HttpClientUtil.postAndGetApiResult(url, payload, userName, passWord);
    }


    public String getCmdFlags() throws Exception {
        String url = baseurl + "/cmd-flags";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getProgress() throws Exception {
        String url = baseurl + "/progress";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getSamplers() throws Exception {
        String url = baseurl + "/samplers";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getSdVae() throws Exception {
        String url = baseurl + "/sd-vae";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getUpscalers() throws Exception {
        String url = baseurl + "/upscalers";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getLatentUpscaleModes() throws Exception {
        String url = baseurl + "/latent-upscale-modes";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getLoras() throws Exception {
        String url = baseurl + "/loras";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getSdModels() throws Exception {
        String url = baseurl + "/sd-models";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getHypernetworks() throws Exception {
        String url = baseurl + "/hypernetwork";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getFaceRestorers() throws Exception {
        String url = baseurl + "/face-restorers";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getRealesrganModels() throws Exception {
        String url = baseurl + "/realesrgan-models";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getPromptStyles() throws Exception {
        String url = baseurl + "/prompt-styles";
        String payload = "{}";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getArtistCategories() throws Exception {
        String url = baseurl + "/artist-categories";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getArtists() throws Exception {
        String url = baseurl + "/artists";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String refreshCheckpoints() throws Exception {
        String url = baseurl + "/refresh-checkpoints";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getScripts() throws Exception {
        String url = baseurl + "/scripts";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getEmbeddings() throws Exception {
        String url = baseurl + "/embeddings";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

    public String getMemory() throws Exception {
        String url = baseurl + "/memory";
        return HttpClientUtil.getAndGetApiResult(url, userName, passWord);
    }

}
