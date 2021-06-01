package FileSearching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchingSpecificFiles {

    public ArrayList<FileObject> searchSpecificFile(ArrayList<String> FileArr, String SearchTerm, boolean CaseSensitive) throws FileNotFoundException {
        ArrayList<FileObject> searchFile = new ArrayList<>();
        for (int i = 0; i < FileArr.size(); i++) {
            FileObject fileObject = new FileObject();
            String FilePath = FileArr.get(i).replace("\\", "\\\\");
            File file = new File(FilePath);
            Scanner in = new Scanner(file);
            int lineNumber = 1;

            while (in.hasNext()) {
                String searchLine = in.nextLine();

                if (searchLine.contains(SearchTerm) && CaseSensitive) {
                    fileObject.setFileLocation(FileArr.get(i));
                    fileObject.setContainedLine(searchLine);
                    fileObject.setLineNumber(lineNumber);
                    fileObject.setCaseSensitive(true);

                    searchFile.add(fileObject);
                } else {
                    if (searchLine.toLowerCase().contains(SearchTerm.toLowerCase())) {
                        fileObject.setFileLocation(FileArr.get(i));
                        fileObject.setContainedLine(searchLine);
                        fileObject.setLineNumber(lineNumber);
                        fileObject.setCaseSensitive(false);

                        searchFile.add(fileObject);
                    }
                }

                lineNumber++;
            }
        }

        return searchFile;
    }
}
