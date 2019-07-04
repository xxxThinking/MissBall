package bollGame;

import java.awt.*;
import java.applet.*;
public class Test extends Applet implements Runnable {
 private static final long serialVersionUID = 1L;
 int X, Y, moveX, moveY, width, height;
 Thread newThread;
 Image OffScreen;
 Graphics drawOffscreen;
 public void init() {
  X = 0;
  Y = 0;
  moveX = 10;      //水平移动的速度
  moveY = 15;       //垂直移动速度
  width = getSize().width ;    //窗口的宽度
   
  System.out.println("width = " + width);
  height = getSize().height ;
  System.out.println("height = " + height);
   
  OffScreen = createImage(width, height);        //创建背景
  drawOffscreen = OffScreen.getGraphics();
 }
 public void start()
 {
  newThread = new Thread(this);
  newThread.start();
 }
 public void stop()
 {
  newThread = null;
 }
 public void paint(Graphics g)
 {
  drawOffscreen.setColor(Color.black); // 设置背景色
  drawOffscreen.fillRect(0, 0, width, height); // 填充所在区域
  drawOffscreen.setColor(Color.white); // 设置球的颜色
  drawOffscreen.fillOval(X, Y, 15, 15);   //画球
  g.drawImage(OffScreen, 0, 0, this); // 画背景
 }
 public void update(Graphics g)
 {
  paint(g);
 }
 public void run()
 {
  while (newThread != null)
  {
   repaint();
   try
   {
    Thread.sleep(50);
   }
   catch (InterruptedException E) {
   }
    
    
    
   X = X + moveX;
   Y = Y + moveY;
   if (X >= (width - 15))
   {
    X = width - 15;
    moveX = -moveX;
   }
   if (X <= 0)
   {
    X = 0;
    moveX = -moveX;
   }
   if (Y >= (height - 15))
   {
    Y = height - 15;
    moveY = -moveY;
   }
   if (Y <= 0)
   {
    Y = 0;
    moveY = -moveY;
   }
  }
 }
}
