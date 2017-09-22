package breder.risk.resource;

import java.io.IOException;

import javax.swing.ImageIcon;

import breder.util.util.InputStreamUtil;

public class BrederRiskResource {

	private static final BrederRiskResource instance = new BrederRiskResource();

	private final ImageIcon criticalImage;

	private final ImageIcon moderalImage;

	private final ImageIcon normalImage;

	private final ImageIcon lightImage;

	private final ImageIcon zeroImage;

	private BrederRiskResource() {
		try {
			this.criticalImage = new ImageIcon(InputStreamUtil.getBytes(this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/risk/resource/critical.gif")));
			this.moderalImage = new ImageIcon(InputStreamUtil.getBytes(this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/risk/resource/moderal.gif")));
			this.normalImage = new ImageIcon(InputStreamUtil.getBytes(this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/risk/resource/normal.gif")));
			this.lightImage = new ImageIcon(InputStreamUtil.getBytes(this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/risk/resource/light.png")));
			this.zeroImage = new ImageIcon(InputStreamUtil.getBytes(this
					.getClass().getClassLoader()
					.getResourceAsStream("breder/risk/resource/zero.png")));
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	public static BrederRiskResource getInstance() {
		return instance;
	}

	public ImageIcon getCriticalImage() {
		return criticalImage;
	}

	public ImageIcon getModeralImage() {
		return moderalImage;
	}

	public ImageIcon getNormalImage() {
		return normalImage;
	}

	public ImageIcon getLightImage() {
		return lightImage;
	}

	public ImageIcon getZeroImage() {
		return zeroImage;
	}

}
