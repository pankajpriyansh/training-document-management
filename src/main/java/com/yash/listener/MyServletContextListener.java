package com.yash.listener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.yash.tdms.model.Batch;
import com.yash.tdms.service.BatchService;
import com.yash.tdms.service.DocumentService;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	@Autowired
	private BatchService batchService;

	@Autowired
	private DocumentService documentService;

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		List<Batch> batchs = batchService.getAllBatches();
		System.out.println(batchs);
		event.getServletContext().setAttribute("batches",
				batchService.getAllBatches());
		hideDocumentsForSpecificMember();
	}

	private void hideDocumentsForSpecificMember() {
		int MINUTES = 30; // The delay in minutes
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() { // Function runs every MINUTES minutes.
				documentService.hideDocumentForSpecificMember();
			}
		}, 0, 1000 * 60 * MINUTES);
	}
}
