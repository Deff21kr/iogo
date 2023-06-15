package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String auth;
	private String dept;
	private Timestamp regdate;
	

}
