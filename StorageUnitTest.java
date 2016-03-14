import java.io.File;
import java.util.Stack;
import java.util.Vector;

import org.junit.Test;

import static org.junit.Assert.*;

public class StorageUnitTest {

	@Test
	public void testPut() throws InterruptedException {
		Stack<File> fileStack = new Stack<>();
		fileStack.push(new File(Constants.MAIN_DIR + "nomatter.txt"));
		Storage storage = new Storage(fileStack);
		storage.put(new File(Constants.MAIN_DIR), (long) 5, "test line for unit test");
		Vector<LineInfo> expected = new Vector<>();
		expected.add(new LineInfo(new File(Constants.MAIN_DIR), (long) 5, "test line for unit test"));
		Vector<LineInfo> real = storage.getVector();

		assertEquals(expected.get(0).getFile(), real.get(0).getFile());
		assertEquals(expected.get(0).getLine(), real.get(0).getLine());
		assertEquals(expected.get(0).getLineNumber(), real.get(0).getLineNumber());
	}

	@Test
	public void testGet() throws InterruptedException {
		Stack<File> fileStack = new Stack<>();
		fileStack.push(new File(Constants.MAIN_DIR + "nomatter.txt"));
		Storage storage = new Storage(fileStack);
		storage.put(new File(Constants.MAIN_DIR), (long) 5, "test line for unit test1");
		storage.put(new File(Constants.MAIN_DIR), (long) 6, "test line for unit test2");
		storage.put(new File(Constants.MAIN_DIR), (long) 7, "test line for unit test3");
		storage.put(new File(Constants.MAIN_DIR), (long) 8, "test line for unit test4");

		String expected = Constants.MAIN_DIR + " 7 " + "test line for unit test3";
		String real = storage.get("test3");

		assertEquals(expected, real);
	}

	@Test
	public void testIsEmpty() {
		Stack<File> fileStack = new Stack<>();
		fileStack.push(new File(Constants.MAIN_DIR + "nomatter.txt"));
		Storage storage = new Storage(fileStack);
		assertTrue(storage.isEmpty());
	}
}
