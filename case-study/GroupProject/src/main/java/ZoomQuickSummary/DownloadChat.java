package ZoomQuickSummary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class DownloadChat {
	
	private static String username = System.getProperty("user.name");
    
    private static String getDownloadLink() throws IOException {
		File file = new File("/Users/"+ username +"/chat_download_link.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
  
		String host = null;
		host = br.readLine();
		br.close();
		return host;
	}
    
    public static void downloadChat() {
    	String downloadLink = null;
    	
    	try {
			downloadLink = getDownloadLink();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Unable to get download link");
		}
    	
    	try (BufferedInputStream inputStream = new BufferedInputStream(new URL(downloadLink).openStream());
		  FileOutputStream fileOS = new FileOutputStream("/Users/"+ username +"/Documents/saved_chat.txt")) {
		    byte data[] = new byte[1024];
		    int byteContent;
		    while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
		        fileOS.write(data, 0, byteContent);
		    }
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to download chat");
		}
    }
}
