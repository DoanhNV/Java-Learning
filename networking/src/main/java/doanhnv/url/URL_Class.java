package doanhnv.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
}
