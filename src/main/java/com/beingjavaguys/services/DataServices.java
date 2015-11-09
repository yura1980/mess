package com.beingjavaguys.services;

import java.util.List;

import com.beingjavaguys.model.Table1;
import com.beingjavaguys.model.SprAvtors;

public interface DataServices {
	public boolean addEntity(Table1 ms) throws Exception;
        public SprAvtors addEntityAut(SprAvtors au) throws Exception;
	public Table1 getEntityById(long id) throws Exception;
	public List<Table1> getEntityList() throws Exception;
        public List<SprAvtors> getEntityListAu() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
