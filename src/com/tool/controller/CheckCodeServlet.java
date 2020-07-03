package com.tool.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckCodeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int width = 100;
		int height = 50;

		// 建立物件在記憶體中
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 2.美化圖片
		// 2.1 fill color
		Graphics g = image.getGraphics();
		g.setColor(Color.PINK);
		g.fillRect(0, 0, width, height);

		// 2.2border
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

//		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		//簡單就好
		String str = "1234567890";
		// 隨機座標
		Random ran = new Random();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 3; i++) {
			int index = ran.nextInt(str.length());
			char ch = str.charAt(index);
			sb.append(ch);
			g.drawString(ch + "", width / 5 * i, height / 2);
		}
		
		String checkCodeImg = sb.toString();
//		System.out.println("CheckCodeServlet: " + checkCodeImg);
		
		// 畫線干擾
		g.setColor(Color.CYAN);
		for (int i = 0; i < 10; i++) {
			int x1 = ran.nextInt(width);
			int x2 = ran.nextInt(width);
			int y1 = ran.nextInt(height);
			int y2 = ran.nextInt(height);
			g.drawLine(x1,x2,y1,y2);
		}

		// 3.輸出
		ImageIO.write(image, "jpg", res.getOutputStream());

		// 4.驗證碼存session
		req.getSession().setAttribute("checkCodeImg", checkCodeImg);
	}

}
