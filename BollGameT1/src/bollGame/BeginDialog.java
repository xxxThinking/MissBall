package bollGame;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BeginDialog extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static int SumNumber = 0;
	private JTextField jtf ;//文本标签
	private JButton btn;
	private JLabel lable ;//标签
	
	public BeginDialog (){
		this.setSize(350, 180);  
        this.setDefaultCloseOperation(3); //点击X关闭程序  3为结束程序
        this.setLocationRelativeTo(null);  //居中
        this.setAlwaysOnTop(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25)); //布局格式 居中
             
		lable = new JLabel("输入小球数量："); 
		lable.setForeground(Color.BLACK);  
		lable.setFont(new Font("黑体", Font.PLAIN, 14));
		this.add(lable); //加入窗口
		
		
		jtf= new JTextField("5",10);
		this.add(jtf);
		
		btn = new JButton("开始游戏");
		btn.setFont(new Font("黑体", Font.PLAIN, 18));
		btn.setBackground(Color.WHITE);	
		this.add(btn);
		
		
		
		btn.addActionListener(this);//添加myActionListener监听事件
		this.setVisible(true);//显示窗口
		
        
	}
	
	public void actionPerformed(ActionEvent e){//这是接口ActionListener里面定义的一个抽象方法，所有实现这个接口的类都要重写这个方法。比如按钮被按下，文本框内输入回车时都会触发这个事件，然后调用你编写的事件处理程序
		if (e.getSource() == btn){//判断这个事件是由哪个组件发出的
			String str = jtf.getText();
			if (!Pattern.compile("-?\\d+").matcher(str).matches() || Integer.valueOf(str) <= 0){//intValue()是把Integer对象类型变成int的基础数据类型；int<integer
				JOptionPane.showMessageDialog(this, "输入不合法！");
				return;
			}
			SumNumber = Integer.parseInt(jtf.getText()); //parseInt(String s): 返回用十进制参数表示的整数值。5
			try {
				new BallFrame();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			this.dispose();
		}
	}

	public static void main(String args[]) throws IOException {
		new BeginDialog();
	}
	
}
