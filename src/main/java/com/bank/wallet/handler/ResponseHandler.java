package com.bank.wallet.handler;

public class ResponseHandler<T> {
	private int code;
	private String status;
	private String message;
	private T data;
	
	public ResponseHandler(int code, String status, String message, T data){
		setCode(code);
		setStatus(status);
		setMessage(message);
		setData(data);
	}
	
	public ResponseHandler(int code, String status, String message) {
		setCode(code);
		setStatus(status);
		setMessage(message);
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
}
