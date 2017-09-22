package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import logic.listener.BinaryListener;
import breder.util.task.RemoteTask;

public class MainMemoryTableRefreshTask extends RemoteTask {
	
	private long lastModified = Long.MAX_VALUE;
	
	private final File file;
	
	private Binary binary;
	
	private List<BinaryListener> listeners = new ArrayList<BinaryListener>();
	
	public MainMemoryTableRefreshTask() {
		File file = new File("../breder.compiler/binary.b");
		if (!file.exists()) {
			JFileChooser chooser = new JFileChooser();
			while (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			}
			file = chooser.getSelectedFile();
		}
		this.file = file;
	}
	
	@Override
	public void perform() throws Throwable {
		binary = null;
		if (file.lastModified() == lastModified) {
			this.start(1000);
			return;
		}
		while (true) {
			InputStream input = null;
			try {
				input = new FileInputStream(file);
				binary = new Binary(input);
				binary.start();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				Thread.sleep(1000);
			} finally {
				if (input != null)
					input.close();
			}
		}
		this.start(1000);
		lastModified = file.lastModified();
	}
	
	private void start(final int i) {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(i);
				} catch (InterruptedException e) {
				}
				MainMemoryTableRefreshTask.this.start();
			}
		}.start();
	}
	
	@Override
	public void handler(Throwable t) {
		t.printStackTrace();
	}
	
	@Override
	public void updateUI() {
		if (binary != null) {
			BinaryManager.getInstance().setBinary(binary);
			this.fireChangeBinary(binary);
		}
	}
	
	public void fireChangeBinary(Binary binary) {
		for (BinaryListener listener : this.listeners) {
			listener.changeBinary(binary);
		}
	}
	
	public boolean addListener(BinaryListener e) {
		return listeners.add(e);
	}
	
}
