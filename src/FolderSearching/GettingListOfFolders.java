package FolderSearching;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GettingListOfFolders {
    ArrayList<String> singFilePathArr = new ArrayList<>();

    //Modified From Stack Overflow
    private List<File> listf(String directoryName) {
        File directory = new File(directoryName);
        List<File> resultList = new ArrayList<File>();
        File[] fList = directory.listFiles();

        assert fList != null;
        for (File file : fList) {
            if (file.isDirectory()) {
                singFilePathArr.add(file.getAbsolutePath());
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        return resultList;
    }

    public ArrayList<String> ReturnList (String location) {
        listf(location);
        return singFilePathArr;
    }
}
