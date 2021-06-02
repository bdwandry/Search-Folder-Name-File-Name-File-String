import FileSearching.SearchingSpecificFiles;
import StringOfAFileSearching.SearchStringFileObject;
import StringOfAFileSearching.GettingsListOfFilesToSearchASpecificString;
import StringOfAFileSearching.SearchingStringInSpecificFiles;
import FolderSearching.GettingListOfFolders;
import FolderSearching.SearchingSpecificFolder;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SettingsParser {
    void LoadSettings(String RootFolderLocation, String searchTerm, int FolderOrFileSearch, boolean CaseSensitive) throws FileNotFoundException {
        String backupOriginalRootLocation = RootFolderLocation;
        long startOfProgram = System.nanoTime();
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);

        int totalSteps = 0;
        int iterateThroughSteps = 0;

        if (FolderOrFileSearch == 3) {
            totalSteps = 14;
        } else {
            totalSteps = 6;
        }

        ArrayList<DataObject> dataObjectArr = new ArrayList<>();
        HTMLWriter htmlWriter = new HTMLWriter();

        //Gets Operating System Type
        String os = System.getProperty("os.name");

        //Converts the Passed through Folder Location to Work with Windows File System Architecture - i.e. converts '\' to '\\'
        if (os.contains("Windows")) {
            RootFolderLocation = RootFolderLocation.replace("\\", "\\\\");
        }

        //Checks if the User is specifically Searching for Specific Folder[0], File[1], or Both[2]
        ArrayList<String> FolderArr = null;
        ArrayList<String> FileNameArr = null;
        ArrayList<String> FileArr = null;
        System.out.println();

        //------------------------------------------------------------------------------------------------------------------------------------------//
        //If Folder [0] is passed through - i.e searching for specific folder name
        if ((FolderOrFileSearch) == 0 || (FolderOrFileSearch == 3)) {
            long startTime = System.nanoTime();
            System.out.println("[" + (++iterateThroughSteps) + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". Loading Folder Names...");
            GettingListOfFolders gettingListOfFolders = new GettingListOfFolders();
            FolderArr = gettingListOfFolders.ReturnList(RootFolderLocation);
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". " + "Total Time: "+ timeElapsed/1000000 + " ms; Loaded Folder Names...");
        }

        //If file [1] is passed through - i.e. searching for strings in file
        if ((FolderOrFileSearch == 1) || (FolderOrFileSearch == 3)) {
            long startTime = System.nanoTime();
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". Loading File Names...");
            GettingsListOfFilesToSearchASpecificString gettingsListOfFilesToSearchASpecificString = new GettingsListOfFilesToSearchASpecificString();
            FileNameArr = gettingsListOfFilesToSearchASpecificString.ReturnList(RootFolderLocation);
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". " + "Total Time: "+ timeElapsed/1000000 + " ms; Loaded File Names...");
        }

        //If file [2] is passed through - i.e. searching for strings in file
        if ((FolderOrFileSearch == 2) || (FolderOrFileSearch == 3)) {
            long startTime = System.nanoTime();
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". Loading Files to be Searched through...");
            GettingsListOfFilesToSearchASpecificString gettingsListOfFilesToSearchASpecificString = new GettingsListOfFilesToSearchASpecificString();
            FileArr = gettingsListOfFilesToSearchASpecificString.ReturnList(RootFolderLocation);
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". " + "Total Time: "+ timeElapsed/1000000 + " ms; Loaded Files to be Searched Through...");
        }

        //------------------------------------------------------------------------------------------------------------------------------------------//
        //Start Searching for Folder Name
        if (FolderArr != null) {
            long startTime = System.nanoTime();
            SearchingSpecificFolder searchingSpecificFolder = new SearchingSpecificFolder();
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] "  + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". Sorting through Folder Names...");
            ArrayList<String> folderStringArr = searchingSpecificFolder.searchFolderName(FolderArr, searchTerm, CaseSensitive);

            for (int i = 0; i < folderStringArr.size(); i++) {
                DataObject dataObject = new DataObject();
                dataObject.setSearchTerm(searchTerm);
                dataObject.setFolderOrFileType("Folder Name");
                dataObject.setCaseSensitive(CaseSensitive);
                dataObject.setRootFolderLocation(RootFolderLocation);
                dataObject.setFileLocation(folderStringArr.get(i));

                dataObjectArr.add(dataObject);
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". " + "Total Time: "+ timeElapsed/1000000 + " ms; Sorted through Folder Names...");
        }

        //Start Stearching File Name
        if (FileNameArr != null) {
            long startTime = System.nanoTime();
            SearchingSpecificFiles searchingSpecificFiles = new SearchingSpecificFiles();
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". Sorting through File Names...");
            ArrayList<String> fileNameArr = searchingSpecificFiles.searchFileName(FileNameArr, searchTerm, CaseSensitive);

            for (int i = 0; i < fileNameArr.size(); i++) {
                DataObject dataObject = new DataObject();
                dataObject.setSearchTerm(searchTerm);
                dataObject.setFolderOrFileType("File Name");
                dataObject.setCaseSensitive(CaseSensitive);
                dataObject.setRootFolderLocation(RootFolderLocation);
                dataObject.setFileLocation(fileNameArr.get(i));

                dataObjectArr.add(dataObject);
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed"+ ". " + "Total Time: "+ timeElapsed/1000000 + " ms; Sorted through File Names...");
        }

        //Start Searching a String of a File;
        if (FileArr != null) {
            long startTime = System.nanoTime();
            SearchingStringInSpecificFiles searchingStringInSpecificFiles = new SearchingStringInSpecificFiles();
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". Sorting through File to be Searched...");
            ArrayList<SearchStringFileObject> searchStringFileObjectsArr = searchingStringInSpecificFiles.searchSpecificFile(FileArr, searchTerm, CaseSensitive);

            for (int i = 0; i < searchStringFileObjectsArr.size(); i++) {
                DataObject dataObject = new DataObject();
                dataObject.setSearchTerm(searchTerm);
                dataObject.setFolderOrFileType("String of a File");
                dataObject.setCaseSensitive(CaseSensitive);
                dataObject.setRootFolderLocation(RootFolderLocation);
                dataObject.setFileLocation(searchStringFileObjectsArr.get(i).getFileLocation());
                dataObject.setSearchLine(searchStringFileObjectsArr.get(i).getContainedLine());
                dataObject.setLineNumber(searchStringFileObjectsArr.get(i).getLineNumber());
                dataObjectArr.add(dataObject);
            }
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" + ". " + "Total Time: "+ timeElapsed/1000000 + " ms; Sorted through File to be Searched...");
        }

        //Writes out the HTML File
        long startTime = System.nanoTime();
        System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float)iterateThroughSteps/(float) totalSteps) * 100) + "% Completed" +". Writing HTML File to Desktop...");
        boolean ifAnyFoundFlag = htmlWriter.SetupInitialHTMLSettings(dataObjectArr, searchTerm);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        if (ifAnyFoundFlag) {
            System.out.println("[" + ++iterateThroughSteps + "/" + totalSteps + "] " + decimalFormat.format(((float) iterateThroughSteps / (float) totalSteps) * 100) + "% Completed" + ". " + "Total Time: " + timeElapsed / 1000000 + " ms; Wrote HTML File to Desktop... ");

            long endOfProgram = System.nanoTime();
            long totalExecutionOfProgram = endOfProgram - startOfProgram;

            String RootFolderName = null;
            for (int i = RootFolderLocation.length() - 1; i >= 0; i--) {
                Character ch = RootFolderLocation.charAt(i);

                if (ch.equals('\\')) {
                    RootFolderName = RootFolderLocation.substring(i + 1);
                    break;
                }
            }

            System.out.println("Wrote HTML File to: " + System.getProperty("user.home") + "\\Desktop" + "\\" + RootFolderName + ".html");
            System.out.println("Total Program Execution Time: " + totalExecutionOfProgram / 1000000 + "ms");
        }
    }
}
