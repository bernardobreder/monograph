package logic;

import logic.listener.BinaryListener;

public class BinaryManager {
	
	private static final BinaryManager instance = new BinaryManager();
	
	private MainMemoryTableRefreshTask task;
	
	private Binary binary;
	
	private BinaryManager() {
		task = new MainMemoryTableRefreshTask();
		task.start();
	}
	
	public Binary getBinary() {
		return binary;
	}
	
	public void setBinary(Binary binary) {
		this.binary = binary;
	}
	
	public boolean addBinaryListener(BinaryListener e) {
		return task.addListener(e);
	}
	
	public static BinaryManager getInstance() {
		return instance;
	}
	
}
