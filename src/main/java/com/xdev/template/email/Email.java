package com.xdev.template.email;

import java.util.HashSet;
import java.util.Set;

public class Email {

	private String templateUid;

	private Type type;

	private String subject;

	private String from;

	private Set<String> to = new HashSet<>();

	private Set<String> cc = new HashSet<>();

	private Set<String> bcc = new HashSet<>();

	private Object context;

	public String getTemplateUid() {
		return templateUid;
	}

	public void setTemplateUid(String templateUid) {
		this.templateUid = templateUid;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Set<String> getTo() {
		return to;
	}

	public void setTo(Set<String> to) {
		this.to = to;
	}

	public Set<String> getCc() {
		return cc;
	}

	public void setCc(Set<String> cc) {
		this.cc = cc;
	}

	public Set<String> getBcc() {
		return bcc;
	}

	public void setBcc(Set<String> bcc) {
		this.bcc = bcc;
	}

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

	public static enum Type {
		TEXT("text/plain"), HTML("text/html");
		private final String value;

		private Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bcc == null) ? 0 : bcc.hashCode());
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((templateUid == null) ? 0 : templateUid.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (bcc == null) {
			if (other.bcc != null)
				return false;
		} else if (!bcc.equals(other.bcc))
			return false;
		if (cc == null) {
			if (other.cc != null)
				return false;
		} else if (!cc.equals(other.cc))
			return false;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (templateUid == null) {
			if (other.templateUid != null)
				return false;
		} else if (!templateUid.equals(other.templateUid))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Email [templateUid=" + templateUid + ", type=" + type + ", subject=" + subject + ", from=" + from + ", to=" + to + ", cc=" + cc + ", bcc="
				+ bcc + ", context=" + context + "]";
	}
}
