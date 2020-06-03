package com.example.demo.common;

public enum ErrorCode {
	SUCCESS(1),
	FAILURE(0);


	// 成员变量
	private int code;

	// 构造方法
	private ErrorCode(int code) {
		this.code = code;
	}
	// 返回错误码
	public int getCode() {
		return code;
	}

	// 返回错误名称vv
	public String getName() {
		return this.name();
	}
	public static ErrorCode getECbyCode(int code){
		for(ErrorCode ec: ErrorCode.values()){
			if(code==ec.getCode())
				return ec;
		}
		return null;
		
	}

}
