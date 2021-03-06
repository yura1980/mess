package com.beingjavaguys.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.beingjavaguys.dao.DataDao;
import com.beingjavaguys.model.SprAvtors;
import com.beingjavaguys.model.Table1;

public class DataServicesImpl implements DataServices {

	@Autowired
	DataDao dataDao;
	
	@Override
	public boolean addEntity(Table1 ms) throws Exception {
		return dataDao.addEntity(ms);
	}
        
        @Override
	public SprAvtors addEntityAut(SprAvtors au) throws Exception {
		return dataDao.addEntityAut(au);
	}

	@Override
	public Table1 getEntityById(long id) throws Exception {
		return dataDao.getEntityById(id);
	}

	@Override
	public List<Table1> getEntityList() throws Exception {
		return dataDao.getEntityList();
	}
        
        @Override
	public List<SprAvtors> getEntityListAu() throws Exception {
		return dataDao.getEntityListAu();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return dataDao.deleteEntity(id);
	}

}
