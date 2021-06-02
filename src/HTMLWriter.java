import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HTMLWriter {
    File htmlWriter;
    PrintWriter out;
    ArrayList<DataObject> dataObjectArr;
    String RootFolderName;

    public void SetupInitialHTMLSettings(ArrayList<DataObject> dataObjectArrCopy, String searchTerm) throws FileNotFoundException {
        dataObjectArr = dataObjectArrCopy;

        if (dataObjectArr.size() != 0) {
            for (int i = dataObjectArr.get(0).getRootFolderLocation().length() - 1; i >= 0; i--) {
                Character ch = dataObjectArr.get(0).getRootFolderLocation().charAt(i);

                if (ch.equals('\\')) {
                    RootFolderName = dataObjectArr.get(0).getRootFolderLocation().substring(i + 1);
                    break;
                }
            }

            htmlWriter = new File(System.getProperty("user.home") + "/Desktop" + "/" + RootFolderName + ".html");
            out = new PrintWriter(htmlWriter);
            writingHeaderInformation();
        } else {
            System.out.println("ERROR: NO FOLDER/FILE CONTAINS THE SELECTED SEARCH TERM: " + searchTerm);
        }
    }

    public void writingHeaderInformation() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<h1 align='center'> Welcome to the search results html page </h1>\n" +
                "<h2> Searching: " + dataObjectArr.get(0).getSearchTerm() + "</h2>\n" +
                "<h2> Case Sensitive: " + dataObjectArr.get(0).getCaseSensitive() + "</h2>\n" +
                "<h2> HTML Name: <a href=" + System.getProperty("user.home") + "/Desktop" + "/" + RootFolderName + ".html" + ">" + RootFolderName + ".html</a></h2>" +
                "<h2> Date and Time Generated: " + dtf.format(now) + "</h2>\n"
        );
        out.flush();
        writeHTMLBody();
    }

    public void writeHTMLBody () {
        String oldFileName = "";
        for (int i = 0; i < dataObjectArr.size(); i++) {
            String shortFileName = "";

            for (int j = dataObjectArr.get(i).getFileLocation().length() - 1; j >= 0; j--) {
                Character ch = dataObjectArr.get(i).getFileLocation().charAt(j);
                if (ch.equals('\\')) {
                    shortFileName = dataObjectArr.get(i).getFileLocation().substring(j + 1);
                    break;
                }
            }

            if (!shortFileName.equals(oldFileName)) {
                out.println("</br>");
            }

            if (!shortFileName.equals(oldFileName)) {
                out.println("<h3><li> Searching File: " + shortFileName + "</li></h3>");
                out.println("<h3><li> Location: " + "<a href=\"" + dataObjectArr.get(i).getFileLocation().replace("\\\\", "\\") + "\">" + dataObjectArr.get(i).getFileLocation().replace("\\\\", "\\") + "</a></li></h3>");
                out.println("<h3><li> Type of Search: " + dataObjectArr.get(i).getFolderOrFileType() + "</li></h3>");
            }

            if (dataObjectArr.get(i).getFolderOrFileType().equals("String of a File") && (dataObjectArr.get(i).getSearchLine().contains("<") || dataObjectArr.get(i).getSearchLine().contains(">"))) {
                out.println("<p style=\"margin-left: 40px\"/><p><xmp style=\"margin-left: 40px\"> " + "Line #" + dataObjectArr.get(i).getLineNumber() +  ". " + dataObjectArr.get(i).getSearchLine() + "</xmp></p>");
            } else if (dataObjectArr.get(i).getFolderOrFileType().equals("String of a File")) {
                out.println("<p style=\"margin-left: 40px\">" + "Line #" + dataObjectArr.get(i).getLineNumber() + ". " + dataObjectArr.get(i).getSearchLine() + "</p>");
                out.flush();
            }

            out.flush();
            oldFileName = shortFileName;
        }

        out.println("</html>");
        out.flush();
        out.close();
    }
}
