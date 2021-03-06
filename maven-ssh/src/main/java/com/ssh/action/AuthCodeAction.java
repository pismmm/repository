package com.ssh.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by shizhenchao on 2014-9-13.
 */
public class AuthCodeAction extends ActionSupport {
    private HttpServletResponse response = ServletActionContext.getResponse();
    private HttpServletRequest request = ServletActionContext.getRequest();

    @Action(value = "authCode")
    public String authCode() {
        try {
            int width = 50;
            int height = 18;
            // 取得一个4位随机字母数字字符串
            String s = RandomStringUtils.random(4, true, true);

            // 保存入session,用于与用户的输入进行比较.
            // 注意比较完之后清除session.
            HttpSession session = request.getSession(true);
            session.setAttribute("authCode", s);

            response.setContentType("images/jpeg");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            ServletOutputStream out = response.getOutputStream();
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            // 设定背景色
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, width, height);

            // 设定字体
            Font mFont = new Font("Times New Roman", Font.BOLD, 18);// 设置字体
            g.setFont(mFont);

            // 画边框
            // g.setColor(Color.BLACK);
            // g.drawRect(0, 0, width - 1, height - 1);

            // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
            g.setColor(getRandColor(160, 200));
            // 生成随机类
            Random random = new Random();
            for (int i = 0; i < 155; i++) {
                int x2 = random.nextInt(width);
                int y2 = random.nextInt(height);
                int x3 = random.nextInt(12);
                int y3 = random.nextInt(12);
                g.drawLine(x2, y2, x2 + x3, y2 + y3);
            }

            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));

            g.drawString(s, 2, 16);

            // 图象生效
            g.dispose();
            // 输出图象到页面
            ImageIO.write((BufferedImage) image, "JPEG", out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
