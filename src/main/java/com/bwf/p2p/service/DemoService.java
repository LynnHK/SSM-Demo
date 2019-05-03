package com.bwf.p2p.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface DemoService {

	public void demoMethod();

	public void saveFile(MultipartFile file, String tempPath) throws IOException;

	public void mqtest(String topic);

}
