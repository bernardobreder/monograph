package breder.org.lang.gui.resource;

import java.io.IOException;

import javax.swing.ImageIcon;

import breder.util.util.InputStreamUtil;

public class LangResource {

	private static final LangResource instance = new LangResource();

	private ImageIcon validatorTrue;

	private LangResource() {
	}

	public ImageIcon getLogo() {
		if (this.validatorTrue == null) {
			this.validatorTrue = this.getImage("quebracabeca2.png");
		}
		return this.validatorTrue;
	}

	private ImageIcon getImage(String name) {
		try {
			return new ImageIcon(
					InputStreamUtil.getBytes(this
							.getClass()
							.getClassLoader()
							.getResourceAsStream(
									"breder/org/lang/gui/resource/" + name)));
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	public static LangResource getInstance() {
		return instance;
	}

}
