package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
 
/**
 * @author Crunchify.com
 * Getting text from URL: Send HTTP request GET/POST in Java - bufferedReader.read()
 */
 
public class response {
 
    public static void main(String[] args) {
        crunchifyPrint("\nOutput: \n" + crunchifyGETCallURLUtil("https://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt"));
    }
 
    public static String crunchifyGETCallURLUtil(String crunchifyURL) {
		crunchifyPrint("Requested URL:" + crunchifyURL);
 
		// A mutable sequence of characters. This class provides an API compatible with StringBuffer,
		// but with no guarantee of synchronization.
        StringBuilder crunchifyStringBuilder = new StringBuilder();
        URLConnection crunchifyURLConnection = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(crunchifyURL);
            crunchifyURLConnection = url.openConnection();
            if (crunchifyURLConnection != null)
            	// Set 5 second Read timeout
                crunchifyURLConnection.setReadTimeout(5 * 1000);
 
            if (crunchifyURLConnection != null && crunchifyURLConnection.getInputStream() != null) {
                in = new InputStreamReader(crunchifyURLConnection.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
 
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        crunchifyStringBuilder.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:" + crunchifyURL, e);
        }
 
        return crunchifyStringBuilder.toString();
    }
 
	private static void crunchifyPrint(String print) {
		System.out.println(print);
	}
 
}
