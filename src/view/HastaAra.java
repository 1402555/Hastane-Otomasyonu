package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Clinic;
import Model.Hasta;
import Model.Randevu;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaAra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private static Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_rand;
	private DefaultTableModel randModel;
	private Object[] randData = null;
	private Randevu rand = new Randevu();
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaAra frame = new HastaAra(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * 
	 */

	public HastaAra(Hasta hasta) throws SQLException {

		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";

		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		
		
		
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";

		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		
		
		
		
		randModel = new DefaultTableModel();
		Object[] colRand = new Object[3];
		colRand[0] = "ID";
		colRand[1] = "Doktor";
		colRand[2] = "Tarih";

		randModel.setColumnIdentifiers(colRand);
		randData = new Object[3];
		for(int i =0; i<rand.getHastaList(hasta.getId()).size(); i++)
        {
	      randData[0]= rand.getHastaList(hasta.getId()).get(i).getId();
	      randData[1]= rand.getHastaList(hasta.getId()).get(i).getDoctorName();
	      randData[2]= rand.getHastaList(hasta.getId()).get(i).getAppDate();
	      randModel.addRow(randData);
        }


		
		
		
		
		

		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + hasta.getName());
		lblNewLabel.setBounds(10, 10, 219, 22);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginAra login = new LoginAra();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(637, 14, 89, 27);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 64, 716, 389);
		w_pane.add(w_tab);

		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_randevu, null);
		w_randevu.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 42, 280, 303);
		w_randevu.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		table_doctor.setBackground(new Color(240, 248, 255));
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel label_1 = new JLabel("Doktor Listesi");
		label_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		label_1.setBounds(10, 10, 99, 22);
		w_randevu.add(label_1);

		JLabel label_2 = new JLabel("Poliklinik Adı");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		label_2.setBounds(313, 16, 99, 22);
		w_randevu.add(label_2);

		JComboBox select_clinic = new JComboBox();
		select_clinic.addItem("--Poliklinik Seç");
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}

			}
		});

		select_clinic.setBounds(307, 42, 148, 32);
		w_randevu.add(select_clinic);

		JLabel lblPoliklinikAd_1 = new JLabel("Doktor Seç");
		lblPoliklinikAd_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblPoliklinikAd_1.setBounds(300, 100, 91, 27);
		w_randevu.add(lblPoliklinikAd_1);

		JButton btn_selDoctor = new JButton("Seç");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
				
				} else {
					Helper.showMsg("Lütfen Bir Doktor Seçiniz");
				}

			}
		});
		btn_selDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_selDoctor.setBounds(297, 131, 165, 31);
		w_randevu.add(btn_selDoctor);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(472, 42, 229, 303);
		w_randevu.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		table_whour.setBackground(new Color(240, 248, 255));
		w_scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5); // burda ID yazısı kısaldı bu kodla jtable a
																		// whourModel ide o yuzden yazdık

		JLabel lblUygunSaatler = new JLabel("Uygun Saatler");
		lblUygunSaatler.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblUygunSaatler.setBounds(472, 10, 99, 22);
		w_randevu.add(lblUygunSaatler);

		JLabel lblPoliklinikAd_1_1 = new JLabel("Randevu Al");
		lblPoliklinikAd_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblPoliklinikAd_1_1.setBounds(300, 187, 91, 27);
		w_randevu.add(lblPoliklinikAd_1_1);

		JButton btn_addRandevu = new JButton("Randevu Al");
		btn_addRandevu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addRandevu(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);

							updateWhourModel(selectDoctorID);
							updateRandModel(hasta.getId());

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen Geçerli Bir Tarih Giriniz");
				}

			}
		});

		btn_addRandevu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_addRandevu.setBounds(297, 213, 165, 31);
		w_randevu.add(btn_addRandevu);
		
		JPanel w_rand = new JPanel();
		w_tab.addTab("Randevularım", null, w_rand, null);
		w_rand.setLayout(null);
		
		JScrollPane w_scrollRand = new JScrollPane();
		w_scrollRand.setBounds(10, 38, 691, 314);
		w_rand.add(w_scrollRand);
		
		table_rand = new JTable(randModel);
		table_rand.setBackground(new Color(240, 248, 255));
		w_scrollRand.setViewportView(table_rand);
		
	}
		
		
	
	
	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
	
	
	
	
	public void updateRandModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_rand.getModel();
		clearModel.setRowCount(0);
		for(int i =0; i<rand.getHastaList(hasta_id).size(); i++)
        {
	      randData[0]= rand.getHastaList(hasta_id).get(i).getId();
	      randData[1]= rand.getHastaList(hasta_id).get(i).getDoctorName();
	      randData[2]= rand.getHastaList(hasta_id).get(i).getAppDate();
	      randModel.addRow(randData);
        }
	}
	
	
	
	
	
	
	
	
	
}

