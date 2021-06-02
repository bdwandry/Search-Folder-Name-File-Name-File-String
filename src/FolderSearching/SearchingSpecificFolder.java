package FolderSearching;

import java.util.ArrayList;

public class SearchingSpecificFolder {
    public ArrayList<String> searchFolderName (ArrayList<String> folderArr, String searchTerm, boolean CaseSensitive) {
        ArrayList<String> searchFolderNames = new ArrayList<>();

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
                searchFolderNames.add(folderArr.get(i));
            } else if (ShortName.toLowerCase().contains(searchTerm.toLowerCase()) && !CaseSensitive) {
                searchFolderNames.add(folderArr.get(i));
            }
        }

        return searchFolderNames;
    }
}
