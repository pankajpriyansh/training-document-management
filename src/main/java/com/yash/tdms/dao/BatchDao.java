package com.yash.tdms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yash.tdms.model.Batch;

@Repository
public interface BatchDao {

	List<Batch> getAllBatches();

	boolean checkIfBatchExists(String batchName);

	int addBatch(Batch batch);

	int getTotalBatches();

}
