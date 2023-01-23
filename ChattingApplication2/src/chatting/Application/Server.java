package chatting.Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.* ;
import java.awt.geom.RoundRectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.validation.Validator;

public class Server  implements ActionListener{

	static JFrame j = new JFrame() ;
	
	JTextField field ;
	static JPanel text ;
	static Box vertical = Box.createVerticalBox() ;
	
	static DataOutputStream Dout ;
	
	Server(){
		j.setLayout(null);
		
		JPanel p1 = new JPanel() ;
		p1.setBackground(new Color(18,140,126));
		p1.setBounds(0,0,450,60);
		p1.setLayout(null);
		j.add(p1) ;
		
		JPanel p2 = new JPanel() ;
		p2.setBackground(new Color(18,140,126));
		p2.setBounds(0,620,450,30);
		j.add(p2) ;
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png")) ;  
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT) ;
		i1 = new ImageIcon(i2) ;
		JLabel back = new JLabel(i1) ;
		back.setBounds(10,20,25,25) ;
		p1.add(back) ;
		
		
		back.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		}) ;
		
//		back.addMouseListener(new MouseAdapter() {
//		public void mouseClicked(MouseEvent e) {
//			System.exit(0);
//		}
//		}) ;
		
		
		ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png")) ;  
		Image i4 = i3.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT) ;
		i3 = new ImageIcon(i4) ;
		JLabel profile = new JLabel(i3) ;
		profile.setBounds(40,8,50,50) ;
		p1.add(profile) ;
		
		
		ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png")) ;  
		Image i6 = i5.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT) ;
		i5 = new ImageIcon(i6) ;
		JLabel VC = new JLabel(i5) ;
		VC.setBounds(320,15,30,30) ;
		p1.add(VC) ;
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png")) ;  
		Image i8 = i7.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT) ;
		i7 = new ImageIcon(i8) ;
		JLabel call = new JLabel(i7) ;
		call.setBounds(370,15,35,30) ;
		p1.add(call) ;
		
		ImageIcon i9 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")) ;  
		Image i0 = i9.getImage().getScaledInstance(10, 30, Image.SCALE_DEFAULT) ;
		i9 = new ImageIcon(i0) ;
		JLabel threeIcons = new JLabel(i9) ;
		threeIcons.setBounds(420,15,10,30) ;
		p1.add(threeIcons) ;
		
		
		JLabel name = new JLabel("User 1") ;
		name.setBounds(110,12,100,30) ;
		name.setForeground(new Color(255, 255, 255)) ;
		name.setFont(new Font("Calligrapher",Font.ITALIC,18)) ;
		p1.add(name) ;
		
		JLabel status = new JLabel("Active now") ;
		status.setBounds(110,35,100,20) ;
		status.setForeground(new Color(255, 255, 255)) ;
		p1.add(status) ;
		
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
//		getContentPane().setBackground(Color.black);
		j.setLocation(200,100);
		j.setUndecorated(true) ;
		j.setShape(new RoundRectangle2D.Double(0,0, 450, 650, 15, 15));
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
		
		Dout.writeUTF(out);
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		j.repaint(); 
		j.validate();
		j.invalidate();
		
		
  		
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
		// TODO Auto-generated method stub

		new Server() ;
		
		try {
			
			ServerSocket skt = new ServerSocket(190) ;
			while(true)
			{
				Socket s = skt.accept() ;
				DataInputStream Din = new DataInputStream(s.getInputStream()) ;
				Dout = new DataOutputStream(s.getOutputStream()) ;
				
				while(true)
				{
					text.setLayout(new BorderLayout());
					
					String msg = Din.readUTF() ;
					JPanel temp = formatLabel(msg) ;
					
					JPanel left = new JPanel(new BorderLayout()) ;
					left.add(temp,BorderLayout.LINE_START) ;
					vertical.add(left) ;
					vertical.createVerticalStrut(15) ;
					text.add(vertical,BorderLayout.PAGE_START) ;
					
					j.validate() ;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
