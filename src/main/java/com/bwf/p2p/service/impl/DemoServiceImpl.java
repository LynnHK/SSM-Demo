package com.bwf.p2p.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.bwf.p2p.activemq.MessageSender;
import com.bwf.p2p.dao.DemoDao;
import com.bwf.p2p.dto.DemoDto;
import com.bwf.p2p.service.DemoService;
import com.bwf.p2p.utils.ExcelUtil;
import com.bwf.p2p.utils.RedisUtil;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	
//	@Autowired
    private DefaultMQProducer producer;
    
//    @Autowired
    private MessageSender messageSender;
	
	public void demoMethod() {
		log.info("hello servcie!");
		demoDao.demoMethod();
		List<DemoDto> list = Lists.newArrayList();
		list.add(DemoDto.builder().id("1").userName("aaa").password("aaa").build());
		list.add(DemoDto.builder().id("2").userName("bbb").password("bbb").build());
		list.add(DemoDto.builder().id("3").userName("ccc").password("ccc").build());
		redisUtil.set("hello", JSON.toJSONString(DemoDto.builder().id("123").userName("abc").password("def").build()), 60 * 5);
		redisUtil.set("list", JSON.toJSONString(list), 60 * 5);
		log.info("hello: {}", redisUtil.get("hello"));
		log.info("list: {}", redisUtil.get("list"));
	}

	public void saveFile(MultipartFile file, String tempPath) throws RuntimeException {
		// 文件格式校验
		String fileName = file.getOriginalFilename();
		if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
			throw new RuntimeException("不支持的文件格式！");
		}
		
		// 创建缓存目录
		File filePath = new File(tempPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		// 先存到缓存目录
		final String fileFullPath = tempPath + File.separator + fileName;
		try {
			file.transferTo(new File(fileFullPath));
		} catch (IOException e) {
			log.error("文件存储失败", e);
			throw new RuntimeException("文件存储失败！", e);
		}
		
		// 读取文件进行存储
		taskExecutor.execute(new Runnable() {
			public void run() {
				readFileAndSaveData(fileFullPath);
			}
		});
	}

	public void readFileAndSaveData(String filePath) {
		// 加载文件
		List<Map<String,Object>> excel = null;
		List<DemoDto> list = null;
		log.info("read to save file data: {}", filePath);
		
		try {
			excel = ExcelUtil.readExcel(filePath);
		} catch (Exception e) {
			log.error("加载文件{}失败{}", filePath, e);
			return;
		}
		
		// 封装数据到list
		try {
			list = JSON.parseArray(JSON.toJSONString(excel), DemoDto.class);
		} catch (Exception e) {
			log.error("数据封装失败:{}{}", excel, e);
			return;
		}
		
		// 存储list，操作省略，后续定时job清除缓存
//		log.info(JSON.toJSONString(list));
		demoDao.save(list);
	}

	public void mqtest(String topic) {
		log.info("入参.............{}", topic);
		
		for (int i = 0; i < 10; i++) {
			try {
//				sendMessage(topic, i);
				messageSend(topic, i);
			} catch (InterruptedException e) {
				log.error("mqtest error:",e);
			}
		}
	}
	
	private void messageSend(String topic, int i) throws InterruptedException {
		try {
			messageSender.defaultMessage(topic+i);
			messageSender.integraMessage(topic+i);
			Thread.sleep(1000);
		} catch (Exception e) {
			log.error("send error", e);
			Thread.sleep(1000);
		}
		
	}

	public void sendMessage(String topic, int i) throws InterruptedException {
		try {
	        Message msg = new Message(topic, // topic
	            "TagA", // tag
	            ("你好啊 " + i).getBytes(Charset.defaultCharset())// body
	        );

	        // 调用producer的send()方法发送消息
	        // 这里调用的是同步的方式，所以会有返回结果
	        SendResult sendResult = producer.send(msg);

	        // 打印返回结果，可以看到消息发送的状态以及一些相关信息
	        SendStatus status = sendResult.getSendStatus();
	        System.out.println(msg + "返回" + status);
	    } catch (Exception e) {
	        log.error("send error:", e);
	        Thread.sleep(1000);
	    }
	}

}
