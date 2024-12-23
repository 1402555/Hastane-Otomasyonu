package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginAra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JTextField fld_doctortc;
	private JPasswordField fld_hastapass;
	private JPasswordField fld_doctorpass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAra frame = new LoginAra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginAra() {
		setTitle("Hastane Otomasyonu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		
		JLabel logo = new JLabel(new ImageIcon("C:\\Users\\Sümeyye\\Downloads\\medicine.png"));
		logo.setBounds(189, 10, 190, 152);
		w_pane.add(logo);
		
		
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 23));
		lblNewLabel.setBounds(85, 161, 419, 55);
		w_pane.add(lblNewLabel);
		
		
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBackground(Color.WHITE);
		w_tabpane.setBounds(10, 212, 566, 241);
		w_pane.add(w_tabpane);
		
		
		
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel hastapass = new JLabel("Şifre:");
		hastapass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 23));
		hastapass.setBounds(20, 69, 419, 55);
		w_hastaLogin.add(hastapass);
		
		JLabel hastatc = new JLabel("T.C. Numaranız:");
		hastatc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 23));
		hastatc.setBounds(10, 7, 419, 55);
		w_hastaLogin.add(hastatc);
		
		fld_hastatc = new JTextField();
		fld_hastatc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_hastatc.setBounds(209, 21, 303, 33);
		w_hastaLogin.add(fld_hastatc);
		fld_hastatc.setColumns(10);
		
		JButton btn_hastakayit = new JButton("Kayıt Ol");
		btn_hastakayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegisterAra rAra = new RegisterAra();
				rAra.setVisible(true);
				dispose();
				
				
			}
		});
		btn_hastakayit.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_hastakayit.setBounds(80, 145, 167, 42);
		w_hastaLogin.add(btn_hastakayit);
		
		JButton btnhastalogin = new JButton("Giriş Yap");
		btnhastalogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_hastatc.getText().length() == 0 || fld_hastapass.getText().length() == 0 )
				{
					Helper.showMsg("fill");
				}
				else
				{
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
					
						while(rs.next()) {      // tum cagırdıgımız user ları tek tek dolascaz
							if(fld_hastatc.getText().equals(rs.getString("tcno")) && fld_hastapass.getText().equals(rs.getString("password")))
							{
								if(rs.getString("type").equals("hasta")) {
								Hasta hasta = new Hasta();
								hasta.setId(rs.getInt("id"));
								hasta.setPassword("password");
								hasta.setTcno(rs.getString("tcno"));
								hasta.setName(rs.getString("name"));
								hasta.setType(rs.getString("type"));
								HastaAra hAra = new HastaAra(hasta);
								hAra.setVisible(true);
								dispose();
								key = false;
								}
								
							}
						}
					} catch (SQLException e1) {
							e1.printStackTrace();
					}
					if(key)
					{
						Helper.showMsg("Böyle Bir Hasta Bulunamadı Lütfen Kayıt Olunuz !");
					}
				}
				
				
				                                                                                                                                                                                                                                                                             
				
			}
		});
		btnhastalogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnhastalogin.setBounds(328, 145, 162, 42);
		w_hastaLogin.add(btnhastalogin);
		
		fld_hastapass = new JPasswordField();
		fld_hastapass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_hastapass.setBounds(131, 86, 347, 33);
		w_hastaLogin.add(fld_hastapass);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel doctortc = new JLabel("T.C. Numaranız:");
		doctortc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 23));
		doctortc.setBounds(10, 10, 419, 55);
		w_doctorLogin.add(doctortc);
		
		JLabel doctorsifre = new JLabel("Şifre:");
		doctorsifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 23));
		doctorsifre.setBounds(20, 75, 330, 49);
		w_doctorLogin.add(doctorsifre);
		
		fld_doctortc = new JTextField();
		fld_doctortc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_doctortc.setBounds(205, 26, 264, 29);
		w_doctorLogin.add(fld_doctortc);
		fld_doctortc.setColumns(10);
		
		JButton btndoctorlogin = new JButton("Giriş Yap");
		btndoctorlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctortc.getText().length()==0 || fld_doctorpass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT *FROM user");
						while(rs.next()) {      // tum cagırdıgımız user ları tek tek dolascaz
							if(fld_doctortc.getText().equals(rs.getString("tcno")) && fld_doctorpass.getText().equalsIgnoreCase(rs.getString("password")))
							{
								if(rs.getString("type").equals("bashekim")) {
								Bashekim bhekim = new Bashekim();
								bhekim.setId(rs.getInt("id"));
								bhekim.setPassword("password");
								bhekim.setTcno(rs.getString("tcno"));
								bhekim.setName(rs.getString("name"));
								bhekim.setType(rs.getString("type"));
								BashekimAra bAra = new BashekimAra(bhekim);
								bAra.setVisible(true);
								dispose();
								}
								if(rs.getString("type").equals("doktor"))
								{
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorAra dAra = new DoctorAra(doctor);
									dAra.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
							e1.printStackTrace();
					}
				}
			}
		});
		
		btndoctorlogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btndoctorlogin.setBounds(72, 143, 419, 50);
		w_doctorLogin.add(btndoctorlogin);
		
		
		
		fld_doctorpass = new JPasswordField();
		fld_doctorpass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_doctorpass.setBounds(122, 91, 369, 29);
		w_doctorLogin.add(fld_doctorpass);
	}
}
