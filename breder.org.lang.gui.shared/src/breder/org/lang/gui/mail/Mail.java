package breder.org.lang.gui.mail;

import java.io.Serializable;
import java.util.Date;

public class Mail implements Serializable, Comparable<Mail> {

	private final String group;

	private final Date date;

	private final String subject;

	private final String text;

	public Mail(String group, Date date, String subject, String text) {
		super();
		this.group = group;
		this.date = date;
		this.subject = subject;
		this.text = text;
	}

	public String getGroup() {
		return group;
	}

	public Date getDate() {
		return date;
	}

	public String getSubject() {
		return subject;
	}

	public String getText() {
		return text;
	}

	@Override
	public int compareTo(Mail o) {
		return this.subject.compareTo(o.getSubject());
	}

}
