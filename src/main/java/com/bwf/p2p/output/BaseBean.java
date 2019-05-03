package com.bwf.p2p.output;

import java.io.Serializable;

import com.bwf.p2p.constant.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseBean implements Serializable {

	private static final long serialVersionUID = -6442873559675783792L;

	@Builder.Default
	private boolean success = Boolean.TRUE;
	
	@Builder.Default
	private int code = ResponseCode.SUCCESS;
	
	@Builder.Default
	private String msg = "success";
	
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public BaseBean success(boolean success) {
		this.success = success;
		return this;
	}

	public int getCode() {
		return code;
	}

	public BaseBean code(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public BaseBean msg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public BaseBean data(Object data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "BaseBean(success=" + success + ", code=" + code + ", msg=" + msg + ", data=" + data + ")";
	}
	
}
