package com.ego.exception;

public class ParamException extends Exception{
	private static final long serialVersionUID = 3044246689485010811L;

	public ParamException() {
		super("参数为空");
	}
}
