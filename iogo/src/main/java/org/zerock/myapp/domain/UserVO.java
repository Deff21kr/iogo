package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;

@Value
public class UserVO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String auth;
	private String dept;
	private Timestamp regdate;
	
public UserDTO toDTO() {
		
		UserDTO dto = new UserDTO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setGender(gender);
		dto.setAuth(auth);
		dto.setDept(dept);
		dto.setRegdate(regdate);
		return dto;
	}
	
	

}
