package ZoomQuickSummary;
import java.io.*;

public class Parser {
	
	private static String username = System.getProperty("user.name");
	
    static public String modifyText(String filename) {
        String htmlPath = filename.substring(0, filename.lastIndexOf('/') + 1);
        final String htmlPath1 = htmlPath + "meeting_chat.html";
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filename));
            BufferedReader entireFile = new BufferedReader(
                    new FileReader(filename));

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(htmlPath1));

            StringBuilder actionItems = new StringBuilder();
            int aiCount = 0;
            StringBuilder notes = new StringBuilder();
            int noteCount = 0;
            StringBuilder questions = new StringBuilder();
            int questionCount = 0;
            StringBuilder links = new StringBuilder();
            int linkCount = 0;


            actionItems.append("<h3> Action Items </h3>\n");
            notes.append("<h3> Notes </h3>\n");
            questions.append("<h3> Questions </h3>\n");
            links.append("<h3> Links </h3>\n");

            String s;
            bw.write("<!DOCTYPE = HTML>");
            bw.write("<html lang = en>");
            bw.write("<body style = \"font-family:arial\">");
            bw.write("<h2 style=\"color: blue\" > Zoom Quick Summary </h2>\n");
            bw.write("<h3 style=\"font-weight: bold\">"+ getMeetingName() +"</h3>");
            while ((s = br.readLine()) != null) {
                String same = s.toLowerCase();
                if (same.contains("http")) {
                    links.append("<div> " + s.substring(same.indexOf("http")) + "</div>" + "\n");

                    linkCount++;
                } else if (same.contains("?")) {
                    if (same.contains("#bold")) {
                        questions.append("<b> " + s.substring(same.indexOf("#bold") + 5) + "</b>" + "\n");
                    } else if (same.contains("#b")) {
                        questions.append("<b> " + s.substring(same.indexOf("#b") + 2) + "</b>" + "\n");
                    } else {
                        questions.append("<div> " + s.substring(same.lastIndexOf(":") + 1) + "</div>" + "\n");
                    }
                    questionCount++;
                } else if (same.contains("#note")) {
                    if (same.contains("#bold")) {
                        notes.append("<b> " + s.substring(same.indexOf("#bold") + 5) + "</b>" + "\n");
                    } else if (same.contains("!b")) {
                        notes.append("<b> " + s.substring(same.indexOf("#b") + 2) + "</b>" + "\n");
                    } else {
                        notes.append("<div> " + s.substring(same.indexOf("#note") + 5) + "</div>" + "\n");
                    }
                    noteCount++;
                } else if (same.contains("#n")) {
                    if (same.contains("#bold")) {
                        notes.append("<b> " + s.substring(same.indexOf("#bold") + 5) + "</b>" + "\n");
                    } else if (same.contains("#b")) {
                        notes.append("<b> " + s.substring(same.indexOf("#b") + 2) + "</b>" + "\n");
                    } else {
                        notes.append("<div> " + s.substring(same.indexOf("#n") + 2) + "</div>" + "\n");
                    }
                    noteCount++;
                } else if (same.contains("#action")) {
                    if (same.contains("#bold")) {
                        actionItems.append("<b> " + s.substring(same.indexOf("#bold") + 5) + "</b>" + "\n");
                    } else if (same.contains("#b")) {
                        actionItems.append("<b> " + s.substring(same.indexOf("#b") + 2) + "</b>" + "\n");
                    } else {
                        actionItems.append("<div> " + s.substring(same.indexOf("#action") + 7) + "</div>" + "\n");
                    }
                    aiCount++;
                } else if (same.contains("#a")) {
                    if (same.contains("#bold")) {
                        actionItems.append("<b> " + s.substring(same.indexOf("#bold") + 5) + "</b>" + "\n");
                    } else if (same.contains("#b")) {
                        actionItems.append("<b> " + s.substring(same.indexOf("#b") + 2) + "</b>" + "\n");
                    } else {
                        actionItems.append("<div> " + s.substring(same.indexOf("#a") + 2) + "</div>" + "\n");
                    }
                    aiCount++;
                } else if (same.contains("#bold")) {
                    notes.append("<b> " + s.substring(same.indexOf("#bold") + 5) + "</b>" + "\n");
                } else if (same.contains("#b")) {
                    notes.append("<b>" + s.substring(same.indexOf("#b") + 2) + "</b>" + "\n");
                }

            }


            if (aiCount > 0)
                bw.write(actionItems.toString());
            if (noteCount > 0)
                bw.write(notes.toString());
            if (questionCount > 0)
                bw.write(questions.toString());
            if (linkCount > 0)
                bw.write(links.toString());


            bw.write("<h3>Full Meeting Chat History</h3>\n");
            while ((s = entireFile.readLine()) != null) {
                bw.write("<div>" + s + "</div>\n");
            }
            bw.write("</body>");
            br.close();
            entireFile.close();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String htmlPath11 = htmlPath1;
        return htmlPath11;
    }
    
    private static String getMeetingName() throws IOException {
		File file = new File("/Users/"+ username +"/meeting_name.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
  
		String name = null;
		name = br.readLine();
		br.close();
		return name;
	}
}
