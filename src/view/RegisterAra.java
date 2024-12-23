package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterAra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAra frame = new RegisterAra();
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
	public RegisterAra() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbladsoyad = new JLabel("Ad Soyad");
		lbladsoyad.setBounds(10, 10, 65, 21);
		lbladsoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		w_pane.add(lbladsoyad);
		
		fld_name = new JTextField();
		fld_name.setBounds(10, 30, 252, 19);
		fld_name.setColumns(10);
		w_pane.add(fld_name);
		
		JLabel lblTcNumaranz = new JLabel("T.C Numarası");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblTcNumaranz.setBounds(10, 68, 117, 21);
		w_pane.add(lblTcNumaranz);
		
		fld_tc = new JTextField();
		fld_tc.setColumns(10);
		fld_tc.setBounds(10, 90, 252, 19);
		w_pane.add(fld_tc);
		
		JLabel lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblifre.setBounds(10, 131, 117, 21);
		w_pane.add(lblifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 156, 252, 21);
		w_pane.add(fld_pass);
		
		
		
		JButton btnKaytOl = new JButton("Kayıt Ol");
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)   {
				
			if(fld_tc.getText().length()==0 || fld_name.getText().length()==0 || fld_pass.getText().length()==0)
			{
				Helper.showMsg("fill");
			}
			else
			{
				boolean control = hasta.register(fld_tc.getText(), fld_name.getText(), fld_pass.getText());
				if(control)
				{
					Helper.showMsg("success");
					LoginAra login = new LoginAra();
					login.setVisible(true);
					dispose();
				}
				else
				{
					Helper.showMsg("error");
				}
			}
				
				
				
				
				
				
			}
			
		});
		btnKaytOl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnKaytOl.setBounds(10, 203, 252, 31);
		w_pane.add(btnKaytOl);
		
		
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginAra login = new LoginAra();
				login.setVisible(true);
				dispose();
			}
		});
		btnGeriDn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGeriDn.setBounds(10, 244, 252, 31);
		w_pane.add(btnGeriDn);
	}
}
