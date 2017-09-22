package breder.ide.lang;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.swing.text.JTextComponent;

import breder.ide.MainFrame;

public class InputThread extends Thread {
	
	private final Integer id;
	
	private final InputStream input;
	
	private final ByteArrayOutputStream output;
	
	public InputThread(Integer id, InputStream input) {
		super("InputThread");
		this.id = id;
		this.input = input;
		this.output = new ByteArrayOutputStream();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				int c = input.read();
				if (c == -1)
					break;
				this.output.write(c);
				JTextComponent text = MainFrame.getInstance().getConsole(id);
				synchronized (text) {
					text.setText(text.getText() + (char) c);
				}
			} catch (Exception e) {
			}
		}
	}
	
	public String getBuffer() {
		return null;
	}
	
}
