package io.simpolor.http.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpUtil {
    private static final String DEFAULT_ENCODING = "UTF-8";

    private String url;
    private MultipartEntityBuilder params;

    /**
     * @param url
     *            접속할 URL
     */
    public HttpUtil(String url) {
        this.url = url;
        params = MultipartEntityBuilder.create();
    }

    /**
     * Map으로 한꺼번에 파라메터 혹 추가하는 메소드
     *
     * @param param
     *            파라미터들이 담긴 맵, 파라미터는 UTF-8로 인코딩 됨
     * @return
     */
    public HttpUtil addParam(Map<String, Object> param) {
        return addParam(param, DEFAULT_ENCODING);
    }

    /**
     * Map으로 한꺼번에 파라메터 혹 추가하는 메소드
     *
     * @param param
     *            파라미터들이 담긴 맵
     * @param encoding
     *            파라미터를 인코딩할  charset
     * @return
     */
    public HttpUtil addParam(Map<String, Object> param, String encoding) {
        for (Map.Entry<String, Object> e : param.entrySet()) {
            if (e.getValue() instanceof File) {
                addParam(e.getKey(), (File) e.getValue(), encoding);
            } else {
                addParam(e.getKey(), (String) e.getValue(), encoding);
            }
        }
        return this;
    }

    /**
     * 문자열 파라미터를 추가하는 메소드
     *
     * @param name
     *            추가할 파라미터의 이름
     * @param value
     *            추가할 파라미터의 값
     * @return
     */
    public HttpUtil addParam(String name, String value) {
        return addParam(name, value, DEFAULT_ENCODING);
    }

    public HttpUtil addParam(String name, String value, String encoding) {
        params.addPart(name, new StringBody(value, ContentType.create("text/plain", encoding)));

        return this;
    }

    /**
     * 업로드할 파일 파라미터를 추가하는 메소드
     *
     * @param name
     * @param file
     * @return
     */
    public HttpUtil addParam(String name, File file) {
        return addParam(name, file, DEFAULT_ENCODING);
    }

    public HttpUtil addParam(String name, File file, String encoding) {
        if (file.exists()) {
            try {
                params.addPart(name, new FileBody(file, ContentType.create("application/octet-stream"),
                        URLEncoder.encode(file.getName(), encoding)));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        return this;
    }

    /**
     * 타겟 URL로 POST 요청을 하는 메소드
     *
     * @return 요청결과
     * @throws Exception
     */
    public String submit() throws Exception {
        CloseableHttpClient http = HttpClients.createDefault();
        StringBuffer result = new StringBuffer();

        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(params.build());

            CloseableHttpResponse response = http.execute(post);

            try {
                HttpEntity res = response.getEntity();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(res.getContent(), Charset.forName("UTF-8")));

                String buffer = null;
                while ((buffer = br.readLine()) != null) {
                    result.append(buffer).append("\r\n");
                }
            } finally {
                response.close();
            }
        } finally {
            http.close();
        }

        return result.toString();
    }

}