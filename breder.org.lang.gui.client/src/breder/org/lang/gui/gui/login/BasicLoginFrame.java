package breder.org.lang.gui.gui.login;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import breder.org.lang.gui.task.BasicLoginTask;
import breder.org.lang.gui.task.FinishProgramTask;
import breder.util.swing.BFrame;
import breder.util.swing.GBC;
import breder.util.task.Task;
import breder.webservice.ILoginFrame;

public class BasicLoginFrame extends BFrame implements ILoginFrame {
	
	private JTextField username;
	
	private JPasswordField password;
	
	public BasicLoginFrame() {
		super(null);
		this.setTitle("Breder Language - Manager");
		this.setResizable(false);
		this.build();
		this.pack();
		this.setSize(320, this.getHeight());
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void reset() {
		this.username.setText("");
		this.password.setText("");
	}
	
	public void build() {
		this.setLayout(new GridBagLayout());
		this.buildImage();
		this.buildUsername();
		this.buildPassword();
		this.buildButton();
	}
	
	private void buildImage() {
		JPanel panel = new JPanel();
		this.add(panel, new GBC(0, 0).gridwh(2, 1).both());
	}
	
	private void buildUsername() {
		this.add(new JLabel("Usuário : "), new GBC(0, 1));
		this.add(username = new JTextField(""), new GBC(1, 1).horizontal());
	}
	
	private void buildPassword() {
		this.add(new JLabel("Senha : "), new GBC(0, 2));
		this.add(password = new JPasswordField(), new GBC(1, 2).horizontal());
	}
	
	private void buildButton() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		{
			JButton c = new JButton("Ok");
			c.addActionListener(this.getLoginTask(this, c));
			this.getRootPane().setDefaultButton(c);
			panel.add(c);
		}
		{
			JButton c = new JButton("Cancel");
			c.addActionListener(new FinishProgramTask(false));
			panel.add(c);
		}
		this.add(panel, new GBC(0, 3).gridwh(2, 1).horizontal());
	}
	
	@Override
	public Task getLoginTask(ILoginFrame frame, Component... components) {
		return new BasicLoginTask(this, components);
	}
	
	@Override
	public void open() {
		this.setVisible(true);
	}
	
	@Override
	public void close() {
		super.close();
	}
	
	public String getUsername() {
		return username.getText().trim();
	}
	
	public char[] getPassword() {
		return password.getPassword();
	}
	
}
