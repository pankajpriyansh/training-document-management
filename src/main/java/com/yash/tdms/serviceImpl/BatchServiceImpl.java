package com.yash.tdms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.tdms.dao.BatchDao;
import com.yash.tdms.model.Batch;
import com.yash.tdms.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchDao batchDao;

	public List<Batch> getAllBatches() {
		System.out.println("Inside Service");
		return batchDao.getAllBatches();
	}

	public boolean checkIfBatchExists(String batchName) {
		return batchDao.checkIfBatchExists(batchName);
	}

	public int addBatch(Batch batch) {
		return batchDao.addBatch(batch);
	}

	public int getTotalBatches() {
		return batchDao.getTotalBatches();
	}

}
