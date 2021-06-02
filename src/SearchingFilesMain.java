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


        String directoryPath = RootFileLocation;
        File file = new File(directoryPath);
        while (!file.isDirectory()) {
            System.out.println("ERROR NOT A VALID DIRECTORY");
            System.out.print("Enter a Valid Directory: ");
            RootFileLocation = in.nextLine();
            file = new File(RootFileLocation);
        }
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
        while (stringContainsFolderOrFile < 0 || stringContainsFolderOrFile > 3) {
            System.out.print("ERROR, ENTER A VALID RESPONSE NUMBER OF EITHER: 0, 1, 2, 3: ");
            stringContainsFolderOrFile = in.nextInt();
        }
    }

    private void setCaseSensitive () {
        Scanner in = new Scanner(System.in);
        System.out.print("Will the Search be case sensitive? <Yes> or <No>: ");
        String FlagString = in.nextLine();

        do {
            if (FlagString.equalsIgnoreCase("Yes")) {
                CaseSensitive = true;
            } else if (FlagString.equalsIgnoreCase("No")) {
                CaseSensitive = false;
            } else {
                System.out.println("ERROR, THE RESPONSE NEEDS TO BE 'YES' OR 'NO'");
                System.out.print("Enter a new Response: ");
                FlagString = in.nextLine();
            }
        } while (!((FlagString.equalsIgnoreCase("Yes")) || (FlagString.equalsIgnoreCase("No"))));
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
            Scanner in = new Scanner(System.in);
            RootFileLocation = args[0];
            String directoryPath = RootFileLocation;
            File file = new File(directoryPath);
            while (!file.isDirectory()) {
                System.out.println("ERROR NOT A VALID DIRECTORY");
                System.out.print("Enter a Valid Directory: ");
                RootFileLocation = in.nextLine();
                file = new File(RootFileLocation);
            }

            SearchString = args[1];
            String FlagString = args[2];
            do {
                if (FlagString.equalsIgnoreCase("Yes")) {
                    CaseSensitive = true;
                } else if (FlagString.equalsIgnoreCase("No")) {
                    CaseSensitive = false;
                } else {
                    System.out.println("ERROR, THE RESPONSE NEEDS TO BE 'YES' OR 'NO'");
                    System.out.print("Enter a new Response: ");
                    FlagString = in.nextLine();
                }
            } while (!((FlagString.equalsIgnoreCase("Yes")) || (FlagString.equalsIgnoreCase("No"))));

            stringContainsFolderOrFile = Integer.parseInt(args[3]);
            while (stringContainsFolderOrFile < 0 || stringContainsFolderOrFile > 3) {
                System.out.print("ERROR, ENTER A VALID RESPONSE NUMBER OF EITHER: 0, 1, 2, 3: ");
                stringContainsFolderOrFile = in.nextInt();
            }
        }
        setup.sendDataSettingsParser();
    }
}
