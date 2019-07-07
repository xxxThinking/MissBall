package bollGame;

import java.awt.*;
import java.applet.*;
public class Test extends Applet implements Runnable {
	//测试
 private static final long serialVersionUID = 1L;
 int X, Y, moveX, moveY, width, height;
 Thread newThread;
 Image OffScreen;
 Graphics drawOffscreen;
 

 private Label l2,l3;
 private Button b2;
 private Panel p1;
 private int i=0;
 

 public void run1(){

 b2.setEnabled(true);
 l2.setText(" "+i);
 l3.setText(" "+i);
 Button bb=new Button();
 bb.setBounds(250,380,10,10);
 p1.add(bb);

 int r=(int)(Math.random()*254+1);
 int g=(int)(Math.random()*254+1);
 int b=(int)(Math.random()*254+1);

 bb.setBackground(new Color(r,g,b));

 int rx=(int)(Math.random()*-10)+5;
 int ry=(int)(Math.random()*10);
 while(true){
 int x=bb.getX();
 int y=bb.getY();
 if(x<=0){
 rx=-rx;
 }
 else if(x>=520){
 rx=-rx;
 }
 else if(y<=0){
 ry=-ry;
 }
 else if(y>=385){
 ry=-ry;
 }
 else if(rx==0){
 rx=-3;
 }
 else if(ry==0){
 ry=3;
 }
 try{
 Thread.sleep(2);
 }
 catch(InterruptedException e){
 e.printStackTrace();
 }
 bb.setLocation(x-=rx,y-=ry);
 }
 }

 
 
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
