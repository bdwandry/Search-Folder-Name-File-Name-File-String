package StringOfAFileSearching;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GettingsListOfFiles {
    ArrayList<String> singFilePathArr = new ArrayList<>();

    //Modified From Stack Overflow
    private List<File> listf(String directoryName) {
        File directory = new File(directoryName);
        List<File> resultList = new ArrayList<File>();
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));

        for (File file : fList) {
            if (file.isFile()) {
                singFilePathArr.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
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
