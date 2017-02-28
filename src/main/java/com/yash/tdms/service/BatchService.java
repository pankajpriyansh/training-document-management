package com.yash.tdms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.tdms.model.Batch;

@Service
public interface BatchService {

	List<Batch> getAllBatches();

	boolean checkIfBatchExists(String batchName);

	int addBatch(Batch batch);

	int getTotalBatches();

}
