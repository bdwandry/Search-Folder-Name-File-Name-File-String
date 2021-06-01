public class DataObject {
    String SearchTerm;
    String FolderOrFileType;
    Boolean CaseSensitive;
    String RootFolderLocation;
    String FileLocation;
    String SearchLine;
    int LineNumber;

    public String getSearchTerm() {
        return SearchTerm;
    }

    public void setSearchTerm(String searchString) {
        SearchTerm = searchString;
    }

    public String getFolderOrFileType() {
        return FolderOrFileType;
    }

    public void setFolderOrFileType(String folderOrFileType) {
        FolderOrFileType = folderOrFileType;
    }

    public Boolean getCaseSensitive() {
        return CaseSensitive;
    }

    public void setCaseSensitive(Boolean caseSensitive) {
        CaseSensitive = caseSensitive;
    }

    public String getRootFolderLocation() {
        return RootFolderLocation;
    }

    public void setRootFolderLocation(String rootFolderLocation) {
        RootFolderLocation = rootFolderLocation;
    }

    public String getFileLocation() {
        return FileLocation;
    }

    public void setFileLocation(String fileLocation) {
        FileLocation = fileLocation;
    }

    public String getSearchLine() {
        return SearchLine;
    }

    public void setSearchLine(String searchLine) {
        SearchLine = searchLine;
    }

    public int getLineNumber() {
        return LineNumber;
    }

    public void setLineNumber(int lineNumber) {
        LineNumber = lineNumber;
    }

}
