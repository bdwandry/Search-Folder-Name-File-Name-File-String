package FileSearching;
import java.util.ArrayList;
import java.util.Locale;

public class SearchingSpecificFiles {

    public ArrayList<String> searchFileName (ArrayList<String> folderArr, String searchTerm, boolean CaseSensitive) {
        ArrayList<String> searchFileByName = new ArrayList<>();

        for (int i = 0; i < folderArr.size(); i++) {
            String ShortName = null;
            for (int j = folderArr.get(i).length() - 1; j >= 0; j--) {
                Character ch = folderArr.get(i).charAt(j);

                if (ch.equals('\\')) {
                    ShortName = folderArr.get(i).substring(j + 1);
                    break;
                }
            }

            if (ShortName.contains(searchTerm) && CaseSensitive) {
                searchFileByName.add(folderArr.get(i));
            } else if (ShortName.toLowerCase().contains(searchTerm) && !CaseSensitive) {
                searchFileByName.add(folderArr.get(i));
            }
        }

        return searchFileByName;
    }
}
