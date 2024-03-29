package bollGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BallFrame extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Ball> list = new ArrayList<Ball>();
    private Random rand = new Random(); //随机
    private JPanel centerPanel;//面板
    private Timer timer;
    private TimeCount timecount;
    public static int mouseX,mouseY; 
    private boolean gameOver = false;
    private boolean lastOver = false;
    private int response; //响应
    double distX, distY, distance;
    private int count;
    private int frameWidth,frameHeight;

    public void setGameOver(boolean go) {
    	this.gameOver = go;
    }
	 
	public BallFrame() throws IOException
	{
		super();
		init();
	}
	
	private void init() {
		this.frameWidth = 720;
		this.frameHeight = 650;
		this.setTitle("KEEP LIFE");  
        this.setSize(frameWidth, frameHeight);  
        this.setDefaultCloseOperation(3);  
        this.setLocationRelativeTo(null);  //居中
        this.getContentPane().setBackground(Color.WHITE); //内容面板 
        this.count = 0;
        
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        this.add(centerPanel,BorderLayout.CENTER);//边界布局中心
        this.setVisible(true);//可视
       
        Image cur = createImage(90,90);
        cur.getGraphics().fillRect(0,0,90,90);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cur, new Point(0,0), "A");	//new point 空间坐标
        centerPanel.setCursor(cursor);
        
        createBall(BeginDialog.SumNumber);
        start();
       
        addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent event){
				mouseX = event.getX();
				mouseY = event.getY();
			}
			
			public void mouseDragged(MouseEvent event){
				mouseX = event.getX();
				mouseY = event.getY();
			}
		});
        
         
	}
	  public void createBall(int sum){
	        for(int i=0;i<sum;i++){
	            addBall();
	        }
	  }
	  
	  public void addBall(){
		  Ball ball = new Ball(new Color(rand.nextInt(255), rand.nextInt(255),  //0-255
                  rand.nextInt(255)), rand.nextInt(650), rand.nextInt(600), 50,  
                  rand.nextInt(4) + 1, rand.nextInt(4) + 1, this);
          ball.start();
          list.add(ball);
	  }
	 
	  //重写paint
	  public void paint(Graphics g) {  
		   if(!gameOver){
			   drawAll();
		       collision();  
		       // 调用碰撞函数  
	        }
	    }
	  public void drawAll(){
		  Image image = createImage(frameWidth,frameHeight);
		  Graphics g = image.getGraphics();
		  g.setColor(Color.white);
		  g.fillRect(0, 0, frameWidth, frameHeight);
		  for (int i = 0; i < list.size(); i++) {  
	            // 从ball中获取颜色并设置  
	            g.setColor(list.get(i).getcolor());  
	            // 画出小球  
	            g.fillOval(list.get(i).getX(),list.get(i).getY(), list.get(i).getRadiu(),list.get(i).getRadiu()); 
	      }
		  
		  g.setColor(new Color(123,123,123));
		  g.setFont(new Font("Serif", Font.PLAIN, 30));
		  g.drawString(timecount.showTime(), 355, 70);
		  //lable.setText("你已坚持："+timecount.showTime());
		  this.getGraphics().drawImage(image, 0, 0, null);
	  }
	  
	  //计时线程启动
	 public void start(){
		 timecount = new TimeCount();
		 timer = new Timer();
		 timer.schedule(new TimerTask() {
			//private boolean lastGameover;
			public void run() {	//任务
				if (!gameOver){
					count++;
					timecount.update();
					repaint();
					if(count%200==0){//10000ms每50ms执行一次增加一个小球
						addBall();
					}
				}
				if (gameOver != lastOver){
					isAgain();
				}
				lastOver = gameOver;
			}
		}, 0 , 50);//50ms执行一次
	 }
	 
	 public void isAgain(){
		response=JOptionPane.showConfirmDialog(this, "您存活了"+timecount.showTime()+"秒,是否重新开始游戏？","GAMEOVER",JOptionPane.OK_CANCEL_OPTION);
		//new BeginDialog();
   	    if (response == 0) {   //确定
            if (list.size() != 0) {
                // 现将原来的对象从队列中移除  
                list.removeAll(list); 
                gameOver = true;
                new BeginDialog();
            } 
   	    } else if (response == -1 || response == 1) { //取消
       	// 如果点击关闭，则将线程对象从队列中移除  
            list.removeAll(list);  
            System.exit(0);
        }else {  //关闭窗口
        	System.exit(0);
        } 
	 }
 
	// 碰撞函数  
    private void collision() {  
        // 距离数组，存储两小球间的距离  
        double[][] dis = new double[list.size()][list.size()];  
        for (int i = 0; i < list.size(); i++) {  
            for (int j = 0; j < list.size(); j++) {  
                // 计算两个小球间的距离  
                dis[i][j] = Math.sqrt(Math.pow(list.get(i).getX() - list.get(j).getX(),  //x 2 y 2 = z 2
                        2) + Math.pow(list.get(i).getY() - list.get(j).getY(), 2));  
            }  
        }  
        for (int i = 0; i < list.size(); i++) {  
            for (int j = i + 1; j < list.size(); j++) {  //同一个小球不做计算
                if (dis[i][j] < (list.get(i).getRadiu() + list.get(j).getRadiu()) / 2) { //两球距离小于两球直径
                    int t;  
                    // 交换x方向的速度
                    t = list.get(i).getVx();  
                    list.get(i).setVx(list.get(j).getVx());  
                    list.get(j).setVx(t);  
                    // 交换y方向的速度
                    t = list.get(i).getVy();  
                    list.get(i).setVy(list.get(j).getVy());  
                    list.get(j).setVy(t);  
                    // 确定碰撞后第二个小球的位置  
                    int x2 = list.get(j).getX() - list.get(i).getX(),//半径得位置
                        	y2 = list.get(j).getY() - list.get(i).getY();  
                        list.get(j).setX(list.get(i).getX() + x2);  //将小球得位置控制在相接触时的位置  i小球直径与半径的和与J小球位置正好是刚接触时
                        list.get(j).setY(list.get(j).getY() + y2);  
                    } else {  
                    	
                }  
            }  
        }  
    }  
}