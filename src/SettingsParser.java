import FileSearching.SearchingSpecificFiles;
import StringOfAFileSearching.SearchStringFileObject;
import StringOfAFileSearching.GettingsListOfFilesToSearchASpecificString;
import StringOfAFileSearching.SearchingStringInSpecificFiles;
import FolderSearching.GettingListOfFolders;
import FolderSearching.SearchingSpecificFolder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SettingsParser {
    void LoadSettings(String RootFolderLocation, String searchTerm, int FolderOrFileSearch, boolean CaseSensitive) throws FileNotFoundException {
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

        //------------------------------------------------------------------------------------------------------------------------------------------//
        //If Folder [0] is passed through - i.e searching for specific folder name
        if ((FolderOrFileSearch) == 0 || (FolderOrFileSearch == 3)) {
            GettingListOfFolders gettingListOfFolders = new GettingListOfFolders();
            FolderArr = gettingListOfFolders.ReturnList(RootFolderLocation);
        }

        //If file [1] is passed through - i.e. searching for strings in file
        if ((FolderOrFileSearch == 1) || (FolderOrFileSearch == 3)) {
            GettingsListOfFilesToSearchASpecificString gettingsListOfFilesToSearchASpecificString = new GettingsListOfFilesToSearchASpecificString();
            FileNameArr = gettingsListOfFilesToSearchASpecificString.ReturnList(RootFolderLocation);
        }

        //If file [2] is passed through - i.e. searching for strings in file
        if ((FolderOrFileSearch == 2) || (FolderOrFileSearch == 3)) {
            GettingsListOfFilesToSearchASpecificString gettingsListOfFilesToSearchASpecificString = new GettingsListOfFilesToSearchASpecificString();
            FileArr = gettingsListOfFilesToSearchASpecificString.ReturnList(RootFolderLocation);
        }

        //------------------------------------------------------------------------------------------------------------------------------------------//
        //Start Searching for Folder Name
        if (FolderArr != null) {
            SearchingSpecificFolder searchingSpecificFolder = new SearchingSpecificFolder();
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
        }

        //Start Stearching File Name
        if (FileNameArr != null) {
            SearchingSpecificFiles searchingSpecificFiles = new SearchingSpecificFiles();
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
        }

        //Start Searching a String of a File;
        if (FileArr != null) {
            SearchingStringInSpecificFiles searchingStringInSpecificFiles = new SearchingStringInSpecificFiles();
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
        }

        //Writes out the HTML File
        htmlWriter.SetupInitialHTMLSettings(dataObjectArr, searchTerm);
    }
}
