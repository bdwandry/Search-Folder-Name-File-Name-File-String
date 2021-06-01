import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchingFilesMain extends SettingsParser {
    static private String FileLocation;
    static private String SearchString;
    static private int stringContainsFolderOrFile;
    static private boolean CaseSensitive;

    private void setFileLocation () {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter File Path to Root Directory of a Folder: ");
        FileLocation = in.nextLine();
    }

    private void setSearchString () {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter text to Compare: ");
        SearchString = in.nextLine();
    }

    private void setStringContainsFolderOrFile () {
        Scanner in = new Scanner(System.in);
        System.out.print("Search just Folder Names [0], Files [1], or Both [2]? [0], [1], [2]: ");
        stringContainsFolderOrFile = in.nextInt();
    }

    private void setCaseSensitive () {
        Scanner in = new Scanner(System.in);
        System.out.print("Will the Search be case sensitive? <Yes> or <No>: ");
        String FlagString = in.nextLine();

        if (FlagString.equalsIgnoreCase("Yes")) {
            CaseSensitive = true;
        }

        if (FlagString.equalsIgnoreCase("No")) {
            CaseSensitive = false;
        }
    }

    private void sendDataSettingsParser() throws FileNotFoundException {
        SettingsParser settingsParser = new SettingsParser();
        settingsParser.LoadSettings(FileLocation, SearchString, stringContainsFolderOrFile, CaseSensitive);
    }

    public static void main(String[] args) throws FileNotFoundException {
        SearchingFilesMain setup = new SearchingFilesMain();
        if (args.length != 4) {
            System.out.println("java SearchString [FILEPATH] [TERM TO SEARCH FOR] [SEARCH FOLDER, FILE, OR BOTH: <0>, <1>, <2>] [CaseSensitive: <Yes> or <No>]");
        }

        setup.setFileLocation();
        setup.setSearchString();
        setup.setStringContainsFolderOrFile();
        setup.setCaseSensitive();
        setup.sendDataSettingsParser();
    }
}
