package com.yash.tdms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.yash.tdms.model.Batch;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.BatchService;

@Controller
public class BatchController {
	@Autowired
	private BatchService batchService;

	@RequestMapping("/saveBatch")
	public void saveBatch(@RequestParam("batchName") String batchName,
			HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (batchService.checkIfBatchExists(batchName)) {
			response.getWriter().append("batchExists");
			return;
		}
		Batch batch = makeBatchObjectWithGivenDetails(batchName, session);
		int batchId = batchService.addBatch(batch);
		batch.setId(batchId);
		List listOfBatchObjectAndTotalBatches = getListOfBatchObjectAndTotalBatches(batch);
		String jsonOfBatch = new Gson()
				.toJson(listOfBatchObjectAndTotalBatches);
		response.setContentType("application/json");
		response.getWriter().append(jsonOfBatch);
	}

	private Batch makeBatchObjectWithGivenDetails(String batchName,
			HttpSession session) {
		Batch batch = new Batch();
		batch.setName(batchName);
		batch.setCreatedBy(((Member) session.getAttribute("loggedInUser"))
				.getId());
		batch.setModifiedBy(((Member) session.getAttribute("loggedInUser"))
				.getId());
		return batch;
	}

	private List getListOfBatchObjectAndTotalBatches(Batch batch) {
		List listOfBatchObjectAndTotalBatches = new ArrayList();
		listOfBatchObjectAndTotalBatches.add(batch);
		listOfBatchObjectAndTotalBatches.add(batchService.getTotalBatches());
		return listOfBatchObjectAndTotalBatches;
	}

}
