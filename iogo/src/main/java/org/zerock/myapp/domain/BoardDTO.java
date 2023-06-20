package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class BoardDTO {
	private Integer bno;
	private String id;
	private String name;
	private String dept;
	private String title;
	private String content;
	private String status;
	
}
