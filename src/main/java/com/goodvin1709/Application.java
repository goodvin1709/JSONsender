package com.goodvin1709;

import com.goodvin1709.utils.JSONParser;
import com.goodvin1709.utils.MethodType;
import com.goodvin1709.utils.TimeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);
    private static final String URL = "https://192.168.1.1";
    private static final MethodType method = MethodType.POST;
    private static List<NameValuePair> params = new ArrayList<>();
    static {
        params.add(new BasicNameValuePair("paramName","ParamValue"));
    }

    public static void main(String[] args) {
        System.out.println(TimeUtils.messageForCurrentTime("Application started."));

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject response = JSONParser.makeHttpRequest(URL,method,params);
                System.out.println(TimeUtils.messageForCurrentTime("Response:"));
                if (response.length()>0) {
                    Iterator<String> jsonElementIterator = response.keys();
                    while (jsonElementIterator.hasNext()) {
                        String jsonElement = jsonElementIterator.next();
                        try {
                            System.out.println(TimeUtils.messageForCurrentTime(jsonElement.concat(" : ").concat(response.getString(jsonElement))));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    System.out.println(TimeUtils.messageForCurrentTime("Response is Empty"));
                }
            }
        }).start();
    }
}
