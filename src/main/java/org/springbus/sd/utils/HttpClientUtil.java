package org.springbus.sd.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class HttpClientUtil {

    private static HttpClient getClient() throws NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
                (arg0, arg1) -> true).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new
                SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();
        return client;
    }

    public static Map postAndGetApiResult(String url, String payload,
                                          String username, String password) throws Exception {
        HttpClient client = getClient();
        HttpClientContext context = null;
        if (username != null && password != null) {
            context = HttpClientContext.create();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(AuthScope.ANY, creds);
            context.setCredentialsProvider(credsProvider);
        }

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Connection", "keep-alive");
        HttpEntity httpEntity = new StringEntity(payload, StandardCharsets.UTF_8);
        httpPost.setEntity(httpEntity);
        HttpResponse response = client.execute(httpPost, context);
        String data = EntityUtils.toString(response.getEntity());
        return JacksonUtil.jsonToObject(data, Map.class);

    }

    public static String getAndGetApiResult(String url, String username, String password) throws Exception {
        HttpClient client = getClient();
        HttpClientContext context = null;
        if (username != null && password != null) {
            context = HttpClientContext.create();
            UsernamePasswordCredentials creds = new UsernamePasswordCredentials(username, password);
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(AuthScope.ANY, creds);
            context.setCredentialsProvider(credsProvider);
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
        httpGet.addHeader("Accept", "*/*");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Connection", "keep-alive");
        HttpResponse response = client.execute(httpGet, context);
        String json = EntityUtils.toString(response.getEntity());
        return json;

    }
}
