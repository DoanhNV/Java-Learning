package doanhnv.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class URL_Class {

    static class Test_URLConstructor {

        public static void main(String[] args) throws MalformedURLException, URISyntaxException {
            URL url1 = new URL("http://google.com");
            System.out.println("url1: " + url1);
            URL url2 = new URL("http", "google.com", "/search");
            System.out.println("url2: " + url2);
            URL url3 = new URL("http", "google.com", 80, "/search");
            System.out.println("url3: " + url3);

            URL baseURL = new URL("http://google.com");
            URL url4 = new URL(baseURL, "/search");
            System.out.println("url4: " + url4);

            // Using URI
            URI uri = new URI("http", "google.com", "/search pqage", "name=doanh");
            System.out.println("URI create URL: " + uri.toURL());

            // Output
//            url1: http://google.com
//            url2: http://google.com/search
//            url3: http://google.com:80/search
//            url4: http://google.com/search
//            URI create URL: http://google.com/search%20pqage#name=doanh
        }
    }

    static class Test_ParseURL {
        public static void main(String[] args) throws IOException {
            URL url = new URL("http://oracle.com/search/title?name=doanh ngo van#loading");

            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("getHost: " + url.getHost());
            System.out.println("getPort: " + url.getPort());
            System.out.println("getDefaultPort: " + url.getDefaultPort());
            System.out.println("getFile: " + url.getFile());
            System.out.println("getPath: " + url.getPath());
            System.out.println("getAuthority: " + url.getAuthority());
            System.out.println("getQuery: " + url.getQuery());
            System.out.println("getRef: " + url.getRef());
            System.out.println("getUserInfo: " + url.getUserInfo());
            System.out.println("getContent: " + url.getContent());
        }
    }

    static class Test_ReadingDirectlyFromURL {
        public static void main(String[] args) throws IOException {
            URL url = new URL("http://google.com");
            InputStream inputStream = url.openStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);

            String content = "";
            int character = -1;
            while (( character = streamReader.read()) != -1) {
                content += (char) character;
            }
            System.out.println(content);
        }
    }

    static class Test_ConnectToAURL {
        public static void main(String[] args) throws IOException {
            URL googleURL = new URL("http://google.com");
            URLConnection urlConnection = googleURL.openConnection();
            urlConnection.connect();
            System.out.println("Response header: " + urlConnection.getHeaderFields());

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            String content = "";
            int tmpCharacter = -1;
            while ((tmpCharacter = inputStreamReader.read()) != -1) {
                content += (char) tmpCharacter;
            }
            inputStreamReader.close();
            System.out.println(content);

            // Convert to HttpURLConnection
            System.out.println("=============================================================================");
            URL googleURL2 = new URL("http://google.com");
            HttpURLConnection httpURLConnection = (HttpURLConnection) googleURL2.openConnection();
            System.out.println("Request properties: " + httpURLConnection.getRequestProperties());
            System.out.println("Response message: " + httpURLConnection.getResponseMessage());
            System.out.println("Response code: " + httpURLConnection.getResponseCode());

            InputStream httpInputStream = httpURLConnection.getInputStream();
            InputStreamReader httpInputStreamReader = new InputStreamReader(httpInputStream);
            String content1 = "";
            int tmpCharacter1 = -1;
            while ((tmpCharacter1 = httpInputStreamReader.read()) != -1) {
                content1 += (char) tmpCharacter1;
            }
            httpInputStreamReader.close();
            System.out.println(content1);
        }
    }
}
