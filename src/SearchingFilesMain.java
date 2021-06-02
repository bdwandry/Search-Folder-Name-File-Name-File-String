import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchingFilesMain extends SettingsParser {
    static private String RootFileLocation;
    static private String SearchString;
    static private int stringContainsFolderOrFile;
    static private boolean CaseSensitive;

    private void setFileLocation () {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter File Path to Root Directory of a Folder: ");
        RootFileLocation = in.nextLine();
    }

    private void setSearchString () {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter text to Compare: ");
        SearchString = in.nextLine();
    }

    private void setStringContainsFolderOrFile () {
        Scanner in = new Scanner(System.in);
        System.out.print("Search just Folder Names [0], File Names [1], String in a File [2],or All [3]? <0>, <1>, <2>, or <3>: ");
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
        settingsParser.LoadSettings(RootFileLocation, SearchString, stringContainsFolderOrFile, CaseSensitive);
    }

    public static void main(String[] args) throws FileNotFoundException {
        SearchingFilesMain setup = new SearchingFilesMain();
        if (args.length != 4) {
            System.out.println("java SearchString [FILEPATH] [TERM TO SEARCH FOR] [CaseSensitive: <Yes> or <No>] [SEARCH FOLDER, FILE, String of File, or All: <0>, <1>, <2>, <3>]");
            setup.setFileLocation();
            setup.setSearchString();
            setup.setCaseSensitive();
            setup.setStringContainsFolderOrFile();
        } else {
            RootFileLocation = args[0];
            SearchString = args[1];

            if (args[2].equalsIgnoreCase("Yes")) {
                CaseSensitive = true;
            } else if (args[2].equalsIgnoreCase("No")) {
                CaseSensitive = false;
            }

            stringContainsFolderOrFile = Integer.parseInt(args[2]);
        }
        setup.sendDataSettingsParser();
    }
}
