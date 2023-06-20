package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.AdminMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper mapper;

	@Override
	public List<BoardDTO> getList(String dept) throws ServiceException {
		log.info("\ngetList::::: SERVICE dept:{}",dept);
		try {
			return this.mapper.selectList(dept);
		} catch (Exception e ) {
			throw new ServiceException(e);
		}
	}

}
