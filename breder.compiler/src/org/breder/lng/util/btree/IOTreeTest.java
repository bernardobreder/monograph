package org.breder.lng.util.btree;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class IOTreeTest {

	@Before
	public void before() {
		afterClass();
	}

	@AfterClass
	public static void afterClass() {
		File dir = new File("db");
		if (dir.exists()) {
			for (File file : dir.listFiles()) {
				file.delete();
			}
			dir.delete();
		}
	}

	@Test
	public void test() throws IOException {
		IOTree tree = new IOTree(new FileIOTreeService(new File("db")));
		int max = 8 * 1024;
		for (int n = 0; n < max; n++) {
			tree.add(n, "" + n);
		}
		tree.commit();
		tree = new IOTree(new FileIOTreeService(new File("db")));
		for (int n = 0; n < max; n++) {
			Assert.assertEquals(Integer.valueOf(n).toString(), tree.get(n));
		}
		tree = new IOTree(new FileIOTreeService(new File("db")));
		for (int n = 0; n < max; n++) {
			tree.remove(n);
		}
		tree.commit();
		tree = new IOTree(new FileIOTreeService(new File("db")));
		for (int n = 0; n < max; n++) {
			tree.add(n, "" + n);
		}
		tree.commit();
		tree = new IOTree(new FileIOTreeService(new File("db")));
		for (int n = 0; n < max; n++) {
			Assert.assertEquals(Integer.valueOf(n).toString(), tree.get(n));
		}
		for (int n = 0; n < max; n++) {
			tree.remove(n);
		}
		tree.commit();
	}

}
