package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;

@Service
@Transactional()
public class TxServiceImpl implements TxService {
	
	@Setter(onMethod_={@Autowired})
	private TimeMapper mapper;
	
	@Override
	public void insertAll(String str) {
		
		mapper.insertT1(str);
		mapper.insertT2(str);
	}

}
