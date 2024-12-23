package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateclinicAra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clinic clinic = new Clinic();   // yeni ekledim
					UpdateclinicAra frame = new UpdateclinicAra(clinic);
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
	public UpdateclinicAra(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı:");
		lblPoliklinikAd.setBounds(10, 10, 132, 21);
		lblPoliklinikAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		contentPane.add(lblPoliklinikAd);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setBounds(10, 41, 156, 21);
		fld_clinicName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fld_clinicName.setColumns(10);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);
		
		JButton btnUpdateClinic = new JButton("Düzenle");
		btnUpdateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure"))
				{
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdateClinic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdateClinic.setBounds(10, 72, 156, 31);
		contentPane.add(btnUpdateClinic);
	}
}
