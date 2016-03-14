import java.io.File;
import java.util.Stack;

public class TxtFiles {
	private File rootDir;
	private Stack<File> stackOfFiles = new Stack<>();

	public TxtFiles(File _rootDir) throws Exception {
		if (_rootDir.isDirectory()) {
			rootDir = _rootDir;
		}
		else throw new Exception("The parametar is not a directory.");
	}

	private Stack<File> toStack(File dir) {
		TxtFilesAndFoldersFilter onlyTxtAndDir = new TxtFilesAndFoldersFilter();
		for (String name : dir.list(onlyTxtAndDir)) {
			File tempFile = new File(dir.getPath() + "\\" + name);
			if (tempFile.isDirectory()) {
				toStack(tempFile);
			} else {
				stackOfFiles.push(tempFile);
			}
		}
		return stackOfFiles;
	}

	public Stack<File> toStack() {
		return toStack(rootDir);

	}

}
