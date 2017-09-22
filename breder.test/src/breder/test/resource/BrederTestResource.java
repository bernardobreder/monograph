package breder.test.resource;

import java.io.IOException;

import javax.swing.ImageIcon;

import breder.util.util.InputStreamUtil;

public class BrederTestResource {

	private static final BrederTestResource instance = new BrederTestResource();

	private ImageIcon trueImage;

	private ImageIcon falseImage;

	private ImageIcon shieldImage;

	private BrederTestResource() {
		try {
			this.trueImage = new ImageIcon(InputStreamUtil.getBytes((this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/test/resource/true.png"))));
			this.falseImage = new ImageIcon(InputStreamUtil.getBytes((this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/test/resource/false.gif"))));
			this.shieldImage = new ImageIcon(InputStreamUtil.getBytes((this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/test/resource/shield.png"))));
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	public static BrederTestResource getInstance() {
		return instance;
	}

	public ImageIcon getTrueImage() {
		return trueImage;
	}

	public ImageIcon getFalseImage() {
		return falseImage;
	}

	public ImageIcon getShieldImage() {
		return shieldImage;
	}

}
