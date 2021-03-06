package com.entranceplus.craw.utils;

import com.entranceplus.craw.Constants;
import com.entranceplus.craw.dao.CrawlerRepository;
import com.entranceplus.craw.dto.CustomResponse;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClientUtil extends CrawlerRepository {
    private static Gson gson;


    public HttpClientUtil() {
        gson = new Gson();
    }

    public String GET(String url) throws IOException {
        HttpClient httpClient;
        CustomResponse customResponse;
        HttpGet httpGet;
        HttpResponse httpResponse;
        StringBuffer response;
        String line;

        httpClient = HttpClientBuilder.create().build();

        httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", Constants.USER_AGENT);
        httpResponse = httpClient.execute(httpGet);
       if (httpResponse.getStatusLine().getStatusCode() == 200) {

            BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            response = new StringBuffer();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
           customResponse = getCustomResponseDAO().createCustomResponse(response.toString(),
                   true, httpResponse.getStatusLine().getStatusCode());
       } else {
           customResponse = getCustomResponseDAO().createCustomResponse("", false,
                   httpResponse.getStatusLine().getStatusCode());

        }
        return gson.toJson(customResponse);
    }
}
