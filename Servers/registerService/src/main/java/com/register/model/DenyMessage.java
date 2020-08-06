package com.register.model;

import java.io.Serializable;

public class DenyMessage{
	private String denyMessage;
	
	public DenyMessage() {}
	
	public DenyMessage(String denyMessage) {
		super();
		this.denyMessage = denyMessage;
	}

	public String getDenyMessage() {
		return denyMessage;
	}

	public void setDenyMessage(String denyMessage) {
		this.denyMessage = denyMessage;
	}
	
}
