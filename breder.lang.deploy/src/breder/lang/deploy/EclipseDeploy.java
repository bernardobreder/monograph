package breder.lang.deploy;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;

import breder.util.exception.SOSupportedException;
import breder.util.net.BrederFtp;
import breder.util.so.SoUtil;

public class EclipseDeploy {

	public static void main(String[] args) throws Exception {
		String os;
		if (SoUtil.isWindow()) {
			os = "windows";
		} else if (SoUtil.isLinux()) {
			os = "linux";
		} else if (SoUtil.isMacOs()) {
			os = "mac";
		} else {
			throw new SOSupportedException();
		}
		JFileChooser c = new JFileChooser();
		c.setMultiSelectionEnabled(false);
		c.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (c.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			BrederFtp ftp = new BrederFtp("webapps/pub/beclipse_" + os + ".zip");
			OutputStream output = ftp.getOutputStream();
			InputStream input = new FileInputStream(c.getSelectedFile());
			double readed = 0;
			byte[] bytes = new byte[1024];
			while (true) {
				int n = input.read(bytes);
				if (n == -1)
					break;
				readed += n;
				output.write(bytes, 0, n);
				System.out.println(String.format("Sended %f MBytes...",
						readed / 1024 / 1024));
			}
			input.close();
			output.close();
		}
	}
}
