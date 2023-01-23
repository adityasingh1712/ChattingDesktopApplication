package chatting.Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Client implements ActionListener{
	
	static JPanel text ;
	JTextField field ;
	static Box vertical = Box.createVerticalBox() ;
	static DataOutputStream Dout ;
	static JFrame j = new JFrame() ;
	
	
	Client(){
		
		j.setLayout(null);
		
		JPanel header = new JPanel() ;
		header.setBackground(new Color(18,140,126));
		header.setBounds(0, 0, 450, 60);
		header.setLayout(null);
		j.add(header) ;
		
		JPanel footer = new JPanel() ;
		footer.setBackground(new Color(18,140,126));
		footer.setBounds(0,620,450,30);
		j.add(footer) ;
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png")) ;  
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT) ;
		i1 = new ImageIcon(i2) ;
		JLabel back = new JLabel(i1) ;
		back.setBounds(10,20,25,25) ;
		header.add(back) ;
		
		
		back.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		}) ;
		
		ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png")) ;  
		Image i4 = i3.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT) ;
		i3 = new ImageIcon(i4) ;
		JLabel profile = new JLabel(i3) ;
		profile.setBounds(40,8,50,50) ;
		header.add(profile) ;
		
		
		ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png")) ;  
		Image i6 = i5.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT) ;
		i5 = new ImageIcon(i6) ;
		JLabel VC = new JLabel(i5) ;
		VC.setBounds(320,15,30,30) ;
		header.add(VC) ;
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png")) ;  
		Image i8 = i7.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT) ;
		i7 = new ImageIcon(i8) ;
		JLabel call = new JLabel(i7) ;
		call.setBounds(370,15,35,30) ;
		header.add(call) ;
		
		ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")) ;  
		Image i0 = i9.getImage().getScaledInstance(10, 30, Image.SCALE_DEFAULT) ;
		i9 = new ImageIcon(i0) ;
		JLabel threeIcons = new JLabel(i9) ;
		threeIcons.setBounds(420,15,10,30) ;
		header.add(threeIcons) ;
		
		
		JLabel name = new JLabel("User 2") ;
		name.setBounds(110,12,100,30) ;
		name.setForeground(new Color(255, 255, 255)) ;
		name.setFont(new Font("Calligrapher",Font.ITALIC,18)) ;
		header.add(name) ;
		
		JLabel status = new JLabel("Active now") ;
		status.setBounds(110,35,100,20) ;
		status.setForeground(new Color(255, 255, 255)) ;
		header.add(status) ;
		
		text = new JPanel() ;
//		text.setBackground(Color.black) ;
		text.setBounds(0,65,450,480) ;
		text.setBorder(null) ;
		j.add(text) ;
		
		field = new JTextField() ;
		field.setBounds(0,550,366,65) ;
		field.setForeground(Color.white) ;
		field.setFont(new Font("Calligrapher",Font.PLAIN,25)) ;
		field.setBackground(Color.DARK_GRAY) ;
		j.add(field) ;
		
		JButton send = new JButton("Send") ;
		send.setBounds(365,550,83,65) ;
		send.setForeground(new Color(37,211,102)) ;
		send.setFont(new Font("arial",Font.PLAIN,20)) ;
		send.addActionListener(this) ;
//		send.setBackground(Color.blue) ;
//		send.setOpaque(true) ;
		j.add(send) ;
		
		
		j.setSize(450,650);
//		setBackground(Color.black);
		j.setLocation(850, 100);
		j.setUndecorated(true);
		j.setVisible(true);
		

	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		try {
		String out = field.getText() ;
		if(out.isEmpty())
		{
			return ;
		}
//		System.out.println(out) ;
		Dout.writeUTF(out);
		
		
		JPanel txtPanel = formatLabel(out) ;
		field.setText("");
//		txtPanel.setForeground(Color.white);
	
		
		text.setLayout(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout()) ;
//		right.setBackground(Color.black);
		right.add(txtPanel,BorderLayout.LINE_END) ;
		vertical.add(right) ;
		vertical.add(Box.createVerticalStrut(15)) ;
		
		text.add(vertical,BorderLayout.PAGE_START) ;
		
		j.repaint(); 
		j.validate();
		j.invalidate();
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public static JPanel formatLabel(String out)
	{
		JLabel output = new JLabel(out) ;
		output.setFont(new Font("arial",Font.PLAIN,30)) ;
		output.setBackground(new Color(37,211,102));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50)) ;
		
		
		JPanel txtpanel = new JPanel() ;
		
		txtpanel.setLayout(new BoxLayout(txtpanel,BoxLayout.Y_AXIS)) ;
		txtpanel.add(output) ;
		
		Calendar cal = Calendar.getInstance() ;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm") ;
		
		JLabel time = new JLabel() ;
		time.setText(sdf.format(cal.getTime())) ;
		
		txtpanel.add(time) ;
		
		return txtpanel ;
	}
	
	public static void main(String[] args) {
		new Client() ;
		
		try {
			
			Socket s = new Socket("127.0.0.1",190) ;
			
			DataInputStream Din = new DataInputStream(s.getInputStream()) ;
			Dout = new DataOutputStream(s.getOutputStream()) ;
			
			while(true)
			{
				text .setLayout(new BorderLayout());
				
				String msg = Din.readUTF() ;
				JPanel temp = formatLabel(msg) ;
				
				JPanel left = new JPanel(new BorderLayout()) ;
				left.add(temp,BorderLayout.LINE_START) ;
				vertical.add(left) ;
				vertical.createVerticalStrut(15) ;
				text.add(vertical,BorderLayout.PAGE_START);
				
				j.validate() ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
