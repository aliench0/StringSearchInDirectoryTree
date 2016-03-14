import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class TxtFilesAndFoldersFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String fileName) {
		return fileName.endsWith(".txt") || new File(dir.getPath() + File.separator + fileName).isDirectory();
	}

}
