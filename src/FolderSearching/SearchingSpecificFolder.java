package FolderSearching;

import java.util.ArrayList;

public class SearchingSpecificFolder {
    public ArrayList<String> searchFolderName (ArrayList<String> folderArr, String searchTerm, boolean CaseSensitive) {
        ArrayList<String> searchFolderNames = new ArrayList<>();

        for (int i = 0; i < folderArr.size(); i++) {
            if (folderArr.get(i).contains(searchTerm) && CaseSensitive) {
                searchFolderNames.add(folderArr.get(i));
            } else if (folderArr.get(i).toLowerCase().contains(searchTerm.toLowerCase()) && !CaseSensitive) {
                searchFolderNames.add(folderArr.get(i));
            }
        }

        return searchFolderNames;
    }
}
