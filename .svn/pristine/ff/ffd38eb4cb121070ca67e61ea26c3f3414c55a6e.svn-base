package com.bwf.p2p.job;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存每日一清
 * @author LynnLee
 */
@Slf4j
@Component
public class CacheTempCleanJob {
	
	@Scheduled(cron = "${taskJob.cacheclean.cron}")
    public void execute() {
		String filePath = System.getProperty("catalina.base") + File.separator + "temp";
		log.info("cache clean job is running. destDir: {}", filePath);
		File dirFile = new File(filePath);
		if (!dirFile.exists()) {
			return;
		}
		File[] files = dirFile.listFiles();
		deleteFileBeforeDay(files);
	}
	
	private void deleteFileBeforeDay(File[] files) {
		if (files == null || files.length == 0) {
			return;
		}
		for (File file : files) {
			long lastModified = file.lastModified();
			long deadTime = beforeDay(new Date()).getTime();
			if (lastModified < deadTime) {
				log.debug("cache clean job delete file: {}", file.getAbsolutePath());
				FileUtils.deleteQuietly(file);
			} else if (file.isDirectory()) {
				deleteFileBeforeDay(file.listFiles());
			}
		}
	}
	
	private Date beforeDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String str = sdf.format(date);
        try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			log.error("time parse error", e);
			date = null;
		}
		return date;
	}

}
