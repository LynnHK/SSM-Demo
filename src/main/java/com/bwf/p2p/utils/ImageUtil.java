package com.bwf.p2p.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.common.collect.Lists;

public class ImageUtil {
	
	private static List<String> strs;
	
	static {
		if (strs == null) {
			strs = Lists.newArrayList();
		}
		
		for (char i = 'a'; i <= 'z'; i++) {
			strs.add(String.valueOf(i));
		}
		
		for (char i = 'A'; i <= 'Z'; i++) {
			strs.add(String.valueOf(i));
		}
		
		for (int i = 0; i <= 9; i++) {
			strs.add(String.valueOf(i));
		}
	}
	
	/**
	 * 生成n个随机数
	 * @param n
	 * @return
	 */
	public static String getRandomStr(int n) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < n; i++) {
			Random r = new Random();
			int a = r.nextInt(strs.size());
			buffer.append(strs.get(a));
		}
		
		return buffer.toString();
	}
	
	public static String getRandomStr() {
		return getRandomStr(4);
	}
	
	public static void getImage(String str, OutputStream out) throws IOException {
		getImage(115, 37, str, out);
	}
	
	public static void getImage(int width, int height, String str, OutputStream out) throws IOException {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		// 1画背景图片
		g.setColor(getRundomRGB(120, 255));
		g.fillRect(0, 0, width, height);

		// 2随机产生多条线
		g.setColor(getRundomRGB(145, 175));
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			if (i % 4 == 0)
				g.setColor(getRundomRGB(120, 220));
			int x = rd.nextInt(width);
			int y = rd.nextInt(height);
			int x1 = rd.nextInt(width / 3);
			int y1 = rd.nextInt(height / 3);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 3画字符串
		char[] c = str.toCharArray();
		g.setFont(new Font("Serif", Font.BOLD, 24));
		for (int i = 0; i < c.length; i++) {
			g.setColor(getRundomRGB(0, 90));
			if (rd.nextInt(1000) % 2 == 0) {
				//旋转
				g.rotate(.8, width / 10  + (width / 4) * i, height
						+ height / 2 + height/12);
				g.drawString(String.valueOf(c[i]), width / 4 - width / 5-width/7
						+ (width / 4) * i, height - height / 8);
				g.rotate(-.8, width / 10  + (width / 4) * i, height
						+ height / 2+height/12);
			} else {
				g.drawString(String.valueOf(c[i]), width / 4 - width / 6
						+ (width / 4) * i, height - height / 4);
			}
		}
		//====
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0)
				g.setColor(getRundomRGB(142, 220));
			int x = rd.nextInt(width);
			int y = rd.nextInt(height);
			int x1 = rd.nextInt(width / 3);
			int y1 = rd.nextInt(height / 3);
			g.drawLine(x, y, x + x1, y + y1);
		}
		
		g.dispose();
		ImageIO.write(image, "png", out);
	}

	private static Color getRundomRGB(int pr, int pg) {
		if (pr > 255)
			pr = 255;
		if (pg > 255)
			pg = 255;
		Random rd = new Random();
		int r = pr + rd.nextInt(pg - pr);
		int g = pr + rd.nextInt(pg - pr);
		int b = pr + rd.nextInt(pg - pr);
		return new Color(r, g, b);
	}

	public static void main(String[] args) throws Exception {
		FileOutputStream out = new FileOutputStream("E:/c.png");
		
		String str = ImageUtil.getRandomStr();
		System.out.println(str);
		ImageUtil.getImage(str, out);
	}

}
