package com.boot.dao;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class userScheduleService {
	
	@Scheduled(cron = "${cron.expression}")
	public void demoScheduled() {
		
		System.out.println("Current time is "+ new Date());
	}

}
