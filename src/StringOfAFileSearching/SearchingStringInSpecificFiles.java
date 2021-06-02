package StringOfAFileSearching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchingStringInSpecificFiles {

    public ArrayList<SearchStringFileObject> searchSpecificFile(ArrayList<String> FileArr, String SearchTerm, boolean CaseSensitive) throws FileNotFoundException {
        ArrayList<SearchStringFileObject> searchFile = new ArrayList<>();
        for (int i = 0; i < FileArr.size(); i++) {
            String FilePath = FileArr.get(i).replace("\\", "\\\\");
            File file = new File(FilePath);
            Scanner in = new Scanner(file);
            int lineNumber = 1;

            while (in.hasNext()) {
                String searchLine = in.nextLine();

                if (searchLine.contains(SearchTerm) && CaseSensitive) {
                    SearchStringFileObject searchStringFileObject = new SearchStringFileObject();
                    searchStringFileObject.setFileLocation(FileArr.get(i));
                    searchStringFileObject.setContainedLine(searchLine);
                    searchStringFileObject.setLineNumber(lineNumber);
                    searchStringFileObject.setCaseSensitive(true);

                    searchFile.add(searchStringFileObject);
                }

                if (!CaseSensitive) {
                    if (searchLine.toLowerCase().contains(SearchTerm.toLowerCase())) {
                        SearchStringFileObject searchStringFileObject = new SearchStringFileObject();
                        searchStringFileObject.setFileLocation(FileArr.get(i));
                        searchStringFileObject.setContainedLine(searchLine);
                        searchStringFileObject.setLineNumber(lineNumber);
                        searchStringFileObject.setCaseSensitive(false);

                        searchFile.add(searchStringFileObject);
                    }
                }

                lineNumber++;
            }
        }

        return searchFile;
    }
}
