package breder.info.resource;

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import breder.util.util.InputStreamUtil;

public class BResource {

	public static Icon API_ICON;

	public static Icon BNI_ICON;

	public static Icon DOC_ICON;

	public static Icon DOWN_ICON;

	public static Icon NEWS_ICON;

	static {
		try {
			BResource.API_ICON = new ImageIcon(getImage("api.gif"));
			BResource.BNI_ICON = new ImageIcon(getImage("bni.ico"));
			BResource.DOC_ICON = new ImageIcon(getImage("doc.ico"));
			BResource.DOWN_ICON = new ImageIcon(getImage("down.ico"));
			BResource.NEWS_ICON = new ImageIcon(getImage("news.ico"));
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	private static byte[] getImage(String string) throws IOException {
		return InputStreamUtil.getBytes(BResource.class.getClassLoader().getResourceAsStream("breder/info/resource/" + string));
	}

}
