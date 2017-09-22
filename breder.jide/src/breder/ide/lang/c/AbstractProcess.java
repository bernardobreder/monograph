package breder.ide.lang.c;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import breder.ide.lang.InputThread;

public abstract class AbstractProcess {
	
	private Process process;
	
	private File currentDir;
	
	public abstract String[] execute() throws IOException;
	
	public abstract String prompt();
	
	private InputThread inputThread;
	
	private InputThread errorThread;
	
	public void start() throws IOException {
		String[] commands = this.execute();
		ProcessBuilder builder = new ProcessBuilder(commands);
		builder.directory(this.getCurrentDir());
		this.process = builder.start();
		// Integer id = MainFrame.getInstance().buildConsole("Console");
		// this.inputThread = new InputThread(id,
		// this.process.getInputStream());
		// this.errorThread = new InputThread(id, this.process.getErrorStream());
//		this.inputThread.start();
//		this.errorThread.start();
	}
	
	public int waitFor() throws InterruptedException {
		return this.process.waitFor();
	}
	
	public InputStream getInputStream() {
		return process.getInputStream();
	}
	
	public InputStream getErrorStream() {
		return process.getErrorStream();
	}
	
	public OutputStream getOutputStream() {
		return process.getOutputStream();
	}
	
	public String[] println(String... commands) throws IOException {
		for (String command : commands) {
			this.getOutputStream().write((command + "\n").getBytes());
		}
		this.getOutputStream().flush();
		return this.readConsole(this.getInputStream());
	}
	
	public String[] readConsole(InputStream input) throws IOException {
		int index = 0;
		String prompt = this.prompt();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while (true) {
			int n = input.read();
			if (n == -1)
				break;
			output.write(n);
			if (prompt != null) {
				if ((char) n == prompt.charAt(index)) {
					index++;
				} else {
					index = 0;
				}
				if (index == prompt.length()) {
					break;
				}
			}
		}
		String result;
		{
			byte[] bytes = output.toByteArray();
			if (prompt != null && bytes.length >= prompt.length()) {
				result = new String(bytes, 0, bytes.length - prompt.length());
			} else {
				result = new String(bytes);
			}
		}
		List<String> lines = new ArrayList<String>();
		LineNumberReader reader = new LineNumberReader(new StringReader(result));
		String line;
		while (true) {
			line = reader.readLine();
			if (line == null)
				break;
			line = line.trim();
			lines.add(line);
		}
		return lines.toArray(new String[0]);
	}
	
	public File getCurrentDir() {
		return currentDir;
	}
	
	public void setCurrentDir(File currentDir) {
		this.currentDir = currentDir;
	}
	
}
