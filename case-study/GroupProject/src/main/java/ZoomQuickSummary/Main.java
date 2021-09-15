package ZoomQuickSummary;

import java.io.*;

public class Main {
	private static String username = System.getProperty("user.name");
	
    public static void main(String[] args) throws IOException {
    	DownloadChat.downloadChat();
        Parser.modifyText("/Users/"+ username +"/Documents/saved_chat.txt");
        SendEmail emailSender = new SendEmail();
        emailSender.sendEmail("/Users/"+ username +"/Documents/meeting_chat.html");
    }
}
