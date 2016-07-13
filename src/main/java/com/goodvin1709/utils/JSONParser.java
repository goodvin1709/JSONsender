package com.goodvin1709.utils;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.List;

public class JSONParser {
    private static final Logger LOGGER = Logger.getLogger(JSONParser.class);
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public static JSONObject makeHttpRequest(String url, MethodType method, List<NameValuePair> params) {
        try {
            if (method.equals(MethodType.POST)) {
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader(new Header() {
                    @Override
                    public String getName() {
                        return "Accept";
                    }

                    @Override
                    public String getValue() {
                        return "application/json";
                    }

                    @Override
                    public HeaderElement[] getElements() throws ParseException {
                        return new HeaderElement[0];
                    }
                });
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method.equals(MethodType.GET)) {
                HttpClient httpClient = HttpClients.createDefault();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("Internet Error: No Internet connection." + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            LOGGER.error("Buffer Error : Error converting result " + e.toString());
        }
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            LOGGER.error("JSON Parser: + Error parsing data " + e.toString());
        }
        return jObj;
    }
}