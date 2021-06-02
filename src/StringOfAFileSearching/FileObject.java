package StringOfAFileSearching;

public class FileObject {
    String FileLocation;
    String ContainedLine;
    int LineNumber;
    Boolean CaseSensitive;

    public String getFileLocation() {
        return FileLocation;
    }

    public void setFileLocation(String fileLocation) {
        FileLocation = fileLocation;
    }

    public String getContainedLine() {
        return ContainedLine;
    }

    public void setContainedLine(String containedLine) {
        ContainedLine = containedLine;
    }

    public int getLineNumber() {
        return LineNumber;
    }

    public void setLineNumber(int lineNumber) {
        LineNumber = lineNumber;
    }

    public Boolean getCaseSensitive() { return CaseSensitive; }

    public void setCaseSensitive(Boolean caseSensitive) { CaseSensitive = caseSensitive; }
}
