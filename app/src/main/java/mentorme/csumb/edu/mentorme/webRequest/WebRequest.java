package mentorme.csumb.edu.mentorme.webRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by benitosanchez on 10/28/16.
 */

public class WebRequest {

    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public WebRequest() { }

    public String makeWebServiceCall(String url, int requestMethod) {
        return this.makeWebServiceCall(url, requestMethod, null);
    }

    public String makeWebServiceCall(String urlAddress, int requestMethod, HashMap<String, String> params) {

        URL url;
        String response = "";

        try {
            url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(15001);
            connection.setConnectTimeout(15001);
            connection.setDoInput(true);

            if (requestMethod == POST) {
                connection.setRequestMethod("POST");
            } else if(requestMethod == GET) {
                connection.setRequestMethod("GET");
            }

            if (params != null) {
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                StringBuilder requestResult = new StringBuilder();
                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (first)
                        first = false;
                    else
                        requestResult.append("&");

                    requestResult.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    requestResult.append("=");
                    requestResult.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }

                writer.write(requestResult.toString());
                writer.flush();
                writer.close();
                outputStream.close();
            }

            int reqResponseCode = connection.getResponseCode();

            if (reqResponseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
            } else {
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
