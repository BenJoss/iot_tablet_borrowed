package com.huafen.tablet.msg;

public class DeviceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9086392537894212890L;
    
	private Object msg;
	
	
	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public DeviceException() {
		super();
	}

	public DeviceException(String message) {
		super(message);
	}

	public DeviceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DeviceException(Throwable throwable) {
		super(throwable);
	}
}
