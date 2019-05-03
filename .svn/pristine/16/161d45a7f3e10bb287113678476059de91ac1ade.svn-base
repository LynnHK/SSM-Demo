package com.bwf.p2p.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimingTaskJob {
	
	@Scheduled(cron = "${taskJob.timing.cron}")
    public void taskCycle() {
		Date gm_date = new Date();
		
		SimpleDateFormat format_date = new SimpleDateFormat("yyyy/MM/dd");
		String date = format_date.format(gm_date);
		
		SimpleDateFormat format_time = new SimpleDateFormat("HH:mm:ss");
		String time = format_time.format(gm_date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(gm_date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		if (minute == 0) {
			log.info("日期：{} >> 北京时间{}点整", date, hour);
		} else {
			log.info("日期：{} >> 北京时间{}", date, time);
		}
	}

}
