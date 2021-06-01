import FileSearching.FileObject;
import FileSearching.GettingsListOfFiles;
import FileSearching.SearchingSpecificFiles;
import FolderSearching.GettingListOfFolders;

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
        ArrayList<String> FolderArr;
        ArrayList<String> FileArr = null;

        if ((FolderOrFileSearch) == 0 || (FolderOrFileSearch == 2)) {
            GettingListOfFolders gettingListOfFolders = new GettingListOfFolders();
        }

        //If file [1] is passed through - i.e. searching for strings in file
        if ((FolderOrFileSearch == 1) || (FolderOrFileSearch == 2)) {
            GettingsListOfFiles gettingsListOfFiles = new GettingsListOfFiles();
            FileArr = gettingsListOfFiles.ReturnList(RootFolderLocation);
        }

        //Start Searching Files;
        if (FileArr != null) {
            SearchingSpecificFiles searchingSpecificFiles = new SearchingSpecificFiles();
            ArrayList<FileObject> fileObjectsArr = searchingSpecificFiles.searchSpecificFile(FileArr, searchTerm, CaseSensitive);

            for (int i = 0; i < fileObjectsArr.size(); i++) {
                DataObject dataObject = new DataObject();
                dataObject.setSearchTerm(searchTerm);
                dataObject.setFolderOrFileType("String of a File");
                dataObject.setCaseSensitive(CaseSensitive);
                dataObject.setRootFolderLocation(RootFolderLocation);
                dataObject.setFileLocation(fileObjectsArr.get(i).getFileLocation());
                dataObject.setSearchLine(fileObjectsArr.get(i).getContainedLine());
                dataObject.setLineNumber(fileObjectsArr.get(i).getLineNumber());
                dataObjectArr.add(dataObject);
            }
        }

        //Writes out the HTML File
        htmlWriter.SetupInitialHTMLSettings(dataObjectArr);
    }
}
