package com.example.demo.common;

import java.util.Map;

public class Result {
	private Integer code;
	private String message;
	private Object data;
	private Map attribute;

	public static Result init(){
		return init(null);
	}

	public static Result init(Object data){
		Result result = new Result();
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMessage("SUCCESS");
		result.setData(data);
		return result;
	}


	public static Result error(String msg) {
		Result result = new Result();
		result.setCode(ErrorCode.FAILURE.getCode());
		result.setMessage(msg);
		return result;
	}


	public Result(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result() {
	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Map getAttribute() {
		return attribute;
	}

	public void setAttribute(Map attribute) {
		this.attribute = attribute;
	}
}
