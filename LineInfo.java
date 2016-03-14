import java.io.File;

public class LineInfo {
	private File file;
	private long lineNumber;
	private String line;
	
	public LineInfo(File _file, long _lineNumber, String _line){
		file = _file;
		lineNumber = _lineNumber;
		line = _line;
	}
	
	public File getFile(){
		return file;
	}
	
	public long getLineNumber(){
		return lineNumber;
	}
	
	public String getLine(){
		return line;
	}

}
