package com.example.berp_and.work;

import java.io.Serializable;
import java.sql.Date;

public class CommonCodeVO implements Serializable {

	private String code_title;
	private String code_value;
	private String code_used;
	private String code_name;
	private String code_maker;
	private String code_maker_name;
	private Date code_birth;

	public String getCode_maker_name() {
		return code_maker_name;
	}

	public void setCode_maker_name(String code_maker_name) {
		this.code_maker_name = code_maker_name;
	}


	public String getCode_title() {
		return code_title;
	}
	public void setCode_title(String code_title) {
		this.code_title = code_title;
	}
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	public String getCode_used() {
		return code_used;
	}
	public void setCode_used(String code_used) {
		this.code_used = code_used;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_maker() {
		return code_maker;
	}
	public void setCode_maker(String code_maker) {
		this.code_maker = code_maker;
	}
	public Date getCode_birth() {
		return code_birth;
	}
	public void setCode_birth(Date code_birth) {
		this.code_birth = code_birth;
	}
	
	
	
	
	
}
