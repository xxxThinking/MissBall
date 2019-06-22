package bollGame;

import java.awt.Color;

public class Ball extends Thread {  

    private Color color;  
    private int x, y, radiu, vx, vy;  
    private BallFrame bf; 
    private boolean gameOver = false;
    double distX, distY, distance;

    public Ball(Color color, int x, int y, int radiu, int vx, int vy,  
            BallFrame bf) {  
        this.color = color;  
        this.x = x;  
        this.y = y;  
        this.radiu = radiu;  
        this.vx = vx;  
        this.vy = vy;  
        this.bf = bf;  
    }  
    public void run() { 
		super.run(); 
		
        while (!isOver()) { 
	            ballMove();
	            try {  
	                Thread.sleep(10);
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
            }  
           
            bf.repaint();  
        }
    }  
    public boolean isOver(){
		distX = Math.abs(BallFrame.mouseX - x);
        distY = Math.abs(BallFrame.mouseY - y);
 		distance = distX*distX+distY*distY;
 		if (distance < 50*50 || BallFrame.mouseX >= 710 - 50 || BallFrame.mouseY >= 650 - 50 ){
 			gameOver = true;
 			bf.setGameOver(gameOver);
 			}
 		return gameOver;
	 }
  
    public void ballMove(){
    	x += vx;  
        y += vy; 
        // x越界  
        if (x <= 0 || x + radiu >= bf.getWidth()) {  
            vx = -vx;
            if (x < 0)  
                x = 0;  
            else if (x > bf.getWidth() - radiu)  
                x = bf.getWidth() - radiu;  
            else {  
            }  
        }  
        // y越界  
        else if (y <= 0 || y + radiu >= bf.getHeight()) {  
            vy = -vy; 
            if (y < 0)  
                y = 0;  
            else if (y > bf.getHeight() - radiu)  
                y = bf.getHeight() - radiu;  
            else {  
            }  
        } else {  
        }
    }
    public Color getcolor() {  
        return color;  
    }  
  
    public void setcolor(Color color) {  
        this.color = color;  
    }  
  
    public int getX() {  
        return x;  
    }  
  
    public void setX(int x) {  
        this.x = x;  
    }  
  
    public int getY() {  
        return y;  
    }  
  
    public void setY(int y) {  
        this.y = y;  
    }  
  
    public int getRadiu() {  
        return radiu;  
    }  
  
    public void setRadiu(int radiu) {  
        this.radiu = radiu;  
    }  
  
    public int getVx() {  
        return vx;  
    }  
  
    public void setVx(int vx) {  
        this.vx = vx;  
    }  
  
    public int getVy() {  
        return vy;  
    }  
  
    public void setVy(int vy) {  
        this.vy = vy;  
    }  
  
}  