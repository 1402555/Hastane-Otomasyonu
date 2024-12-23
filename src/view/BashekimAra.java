package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import java.awt.BorderLayout;  // bu hemşire yaparken eklendi
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Helper.*;
import javax.swing.JComboBox;




public class BashekimAra extends JFrame {
	
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_dname;
	private JTextField fld_dtc;
	private JTextField fld_dpass;
	private JTextField fld_did;
	private JTable tableDoctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData= null;
	private JTable tableclinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	// ben deniyorum
	private JTable tableHemsire;
	private JTextField fld_nurseName;
	private JTextField fld_nurseTc;
	private JTextField fld_nursePass;
	private JTextField fld_nurseId;
	private DefaultTableModel hemsireModel = null;
	private Object[] hemsireData= null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimAra frame = new BashekimAra(bashekim);
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
	public BashekimAra(Bashekim bashekim) throws SQLException {
		
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for(int i=0; i<bashekim.getDoctorList().size(); i++)
		{
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
		
		
		
		
		
		// deneme
		
		hemsireModel = new DefaultTableModel();
		Object[] colhemsireName = new Object[4];
		colhemsireName[0] = "ID";
		colhemsireName[1] = "Ad Soyad";
		colhemsireName[2] = "TC NO";
		colhemsireName[3] = "Şifre";
		hemsireModel.setColumnIdentifiers(colhemsireName);
		hemsireData = new Object[4];
		for(int i=0; i<bashekim.getHemsireList().size(); i++)
		{
			hemsireData[0] = bashekim.getHemsireList().get(i).getId();
			hemsireData[1] = bashekim.getHemsireList().get(i).getName();
			hemsireData[2] = bashekim.getHemsireList().get(i).getTcno();
			hemsireData[3] = bashekim.getHemsireList().get(i).getPassword();
			hemsireModel.addRow(hemsireData);
			
		}
		
		
		
		// buraya kadarrr
		
		
		
		
		
		
		
		
		
		
		
		

		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adı";
		
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for(int i=0; i<clinic.getList().size(); i++)
		{
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			
			clinicModel.addRow(clinicData);
			
		}
		
		
		
		
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setBackground(new Color(180, 192, 205));
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setForeground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın Başhekim " + bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 22, 294, 27);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginAra login = new LoginAra();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(621, 22, 89, 27);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 73, 716, 380);
		w_pane.add(w_tabpane);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setToolTipText("");
		w_doctor.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JLabel lbladsoyad = new JLabel("Ad Soyad");
		lbladsoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbladsoyad.setBounds(517, 0, 91, 27);
		w_doctor.add(lbladsoyad);
		
		fld_dname = new JTextField();
		fld_dname.setBounds(517, 27, 184, 27);
		w_doctor.add(fld_dname);
		fld_dname.setColumns(10);
		
		JLabel lbltcno = new JLabel("TC No");
		lbltcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lbltcno.setBounds(517, 64, 91, 27);
		w_doctor.add(lbltcno);
		
		fld_dtc = new JTextField();
		fld_dtc.setBounds(517, 87, 184, 27);
		w_doctor.add(fld_dtc);
		fld_dtc.setColumns(10);
		
		JLabel lblpass = new JLabel("Şifre");
		lblpass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblpass.setBounds(517, 124, 91, 27);
		w_doctor.add(lblpass);
		
		fld_dpass = new JTextField();
		fld_dpass.setBounds(517, 150, 184, 27);
		w_doctor.add(fld_dpass);
		fld_dpass.setColumns(10);
		
		JButton btnaddDoctor = new JButton("Ekle");
		btnaddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_dname.getText().length()==0 || fld_dpass.getText().length() == 0 || fld_dtc.getText().length()==0)
				{
					Helper.showMsg("fill");
				}
				else
				{
					try {
						boolean control = bashekim.addDoctor(fld_dtc.getText(), fld_dpass.getText(), fld_dname.getText());
						if(control)
						{
							Helper.showMsg("success");
							fld_dname.setText(null);
							fld_dtc.setText(null);
							fld_dpass.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnaddDoctor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnaddDoctor.setBounds(517, 187, 184, 31);
		w_doctor.add(btnaddDoctor);
		
		
		
		JLabel lblid = new JLabel("Kullanıcı Id");
		lblid.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblid.setBounds(517, 217, 91, 27);
		w_doctor.add(lblid);
		
		fld_did = new JTextField();
		fld_did.setBackground(SystemColor.controlHighlight);
		fld_did.setBounds(517, 241, 184, 27);
		w_doctor.add(fld_did);
		fld_did.setColumns(10);
		
		
		
		JButton btndelDoctor = new JButton("Sil");
		btndelDoctor.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(fld_did.getText().length() == 0)
			{
				Helper.showMsg("Lütfen Geçerli Bir Doktor Seçiniz!");
			}
			else
			{
				if(Helper.confirm("sure")) {
				int selectID = Integer.parseInt(fld_did.getText());
				try {
					boolean control = bashekim.deleteDoctor(selectID);
					if(control)
					{
						Helper.showMsg("success");
						fld_did.setText(null);
						updateDoctorModel();
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			  }
			}
			
		  }
		});
		btndelDoctor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btndelDoctor.setBounds(517, 288, 184, 27);
		w_doctor.add(btndelDoctor);
		
		
		
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 0, 493, 338);
		w_doctor.add(w_scrollDoctor);
		
		
		
		
		tableDoctor = new JTable(doctorModel);
		tableDoctor.setBackground(new Color(240, 248, 255));
		w_scrollDoctor.setViewportView(tableDoctor);
		
		
		tableDoctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
		
			public void valueChanged(ListSelectionEvent e) {
				try {
				fld_did.setText(tableDoctor.getValueAt(tableDoctor.getSelectedRow(),0).toString());
				}
				catch(Exception ex)
				{
					
				}
			}
		});
		
		tableDoctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()== TableModelEvent.UPDATE)
				{
					int selectID = Integer.parseInt(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
					String selectName = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 1).toString();
					String selectTcno = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 2).toString();
					String selectPass = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 3).toString();
					
					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
			
			
		});
		
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tabpane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollclinic = new JScrollPane();
		w_scrollclinic.setBounds(10, 0, 260, 353);
		w_clinic.add(w_scrollclinic);
		
		
		clinicMenu = new JPopupMenu();                        
		JMenuItem updateMenu = new JMenuItem("Güncelle");     // saga tıklayınca maeddelerde tablodaki guncelle sil çıkıyo
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		
		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(tableclinic.getValueAt(tableclinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateclinicAra updateAra = new UpdateclinicAra(selectClinic);
				updateAra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateAra.setVisible(true);
				updateAra.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		
		});
		
		
		
		
	
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		

		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
		      if(Helper.confirm("sure"))
		      {
		    	  int selID = Integer.parseInt(tableclinic.getValueAt(tableclinic.getSelectedRow(), 0).toString());
		    	  try {
					if(clinic.deleteClinic(selID))
					  {
						  Helper.showMsg("success");
						  updateClinicModel();
					  }
					  else
					  {
						  Helper.showMsg("error");
					  }
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		      }
		
			}
		});
		
		
		
		
		
		
		
		
		tableclinic = new JTable(clinicModel);
		tableclinic.setBackground(new Color(240, 248, 255));
		tableclinic.setComponentPopupMenu(clinicMenu);
		tableclinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tableclinic.rowAtPoint(point);
				tableclinic.setRowSelectionInterval(selectedRow, selectedRow);
				
			}
			
		});
		w_scrollclinic.setViewportView(tableclinic);
		
		
		
		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı");
		lblPoliklinikAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblPoliklinikAd.setBounds(280, 10, 91, 27);
		w_clinic.add(lblPoliklinikAd);
		
		
		
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(280, 37, 165, 27);
		w_clinic.add(fld_clinicName);
		
		
		
		
		
		JButton btnaddclinic = new JButton("Ekle");
		btnaddclinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicName.getText().length()==0)
				{
					Helper.showMsg("fill");
				}
				else {
					try {
						if(clinic.addClinic(fld_clinicName.getText()));
						{
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			}
			
		});
		btnaddclinic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnaddclinic.setBounds(280, 69, 165, 31);
		w_clinic.add(btnaddclinic);
		
		
		
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(455, 0, 256, 353);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		table_worker.setBackground(new Color(240, 248, 255));
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(280, 276, 165, 31);
		for(int i=0; i<bashekim.getDoctorList().size(); i++ )
		{
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
			
			
		});
		w_clinic.add(select_doctor);
		
		
		
		
		
	
	JButton btn_addworker = new JButton("Ekle");
		btn_addworker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow = tableclinic.getSelectedRow();
				if(selRow >= 0)
				{
					String selClinic = tableclinic.getModel().getValueAt(selRow,0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if(control)
						{
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0; i<bashekim.getClinicDoctorList(selClinicID).size(); i++)
							{
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						}
						else
						{
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				else
				{
					Helper.showMsg("choice");
				}
				
			}
			
		});
		  btn_addworker.setFont(new Font("Tahoma", Font.PLAIN, 15));
		  btn_addworker.setBounds(280, 317, 165, 31);
			w_clinic.add(btn_addworker);
			
			
			
			
			JButton btn_workerSelect = new JButton("Seç");
			btn_workerSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int selRow = tableclinic.getSelectedRow();
					if(selRow>=0)
					{
						String selClinic = tableclinic.getModel().getValueAt(selRow, 0).toString();
						int selClinicID = Integer.parseInt(selClinic);
						DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
						clearModel.setRowCount(0);
						
						try {
							for(int i=0; i<bashekim.getClinicDoctorList(selClinicID).size(); i++)
							{
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						table_worker.setModel(workerModel);
					}
					else
					{
						Helper.showMsg("choice");
					}
					
					
					
				}
			});
			btn_workerSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btn_workerSelect.setBounds(280, 161, 165, 31);
			w_clinic.add(btn_workerSelect);
			
			JLabel lblPoliklinikAd_1 = new JLabel("Poliklinik Adı");
			lblPoliklinikAd_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
			lblPoliklinikAd_1.setBounds(280, 133, 91, 27);
			w_clinic.add(lblPoliklinikAd_1);
			
			
			
			
			
			
			
			
			// ben deniyorum
			JPanel w_hemsire = new JPanel();
			w_tabpane.addTab("Hemşire Yönetimi"+ "", null, w_hemsire, null);
			w_hemsire.setLayout(null);
			
		
			
			
			
			JLabel lblad = new JLabel("Ad Soyad");
			lblad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
			lblad.setBounds(427, 2, 91, 27);
			w_hemsire.add(lblad);
			
			fld_nurseName = new JTextField();
			fld_nurseName.setColumns(10);
			fld_nurseName.setBounds(422, 30, 184, 27);
			w_hemsire.add(fld_nurseName);
			
			JLabel lbltc = new JLabel("TC No");
			lbltc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
			lbltc.setBounds(422, 58, 91, 27);
			w_hemsire.add(lbltc);
			
			fld_nurseTc = new JTextField();
			fld_nurseTc.setColumns(10);
			fld_nurseTc.setBounds(422, 84, 184, 27);
			w_hemsire.add(fld_nurseTc);
			
			JLabel lblpass_1 = new JLabel("Şifre");
			lblpass_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
			lblpass_1.setBounds(427, 110, 91, 27);
			w_hemsire.add(lblpass_1);
			
			fld_nursePass = new JTextField();
			fld_nursePass.setColumns(10);
			fld_nursePass.setBounds(427, 140, 184, 27);
			w_hemsire.add(fld_nursePass);
			
			JButton btnaddHemsire = new JButton("Ekle");
			btnaddHemsire.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(fld_nurseName.getText().length()==0 || fld_nursePass.getText().length() == 0 || fld_nurseTc.getText().length()==0)
					{
						Helper.showMsg("fill");
					}
					else
					{
						try {
							boolean control = bashekim.addHemsire(fld_nurseTc.getText(), fld_nursePass.getText(), fld_nurseName.getText());
							if(control)
							{
								Helper.showMsg("success");
								fld_nurseName.setText(null);
								fld_nurseTc.setText(null);
								fld_nursePass.setText(null);
								updateHemsireModel();
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}
					
					
				}
			});
			btnaddHemsire.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnaddHemsire.setBounds(427, 177, 184, 31);
			w_hemsire.add(btnaddHemsire);
			
			JLabel lblid_1 = new JLabel("Kullanıcı Id");
			lblid_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
			lblid_1.setBounds(427, 207, 91, 27);
			w_hemsire.add(lblid_1);
			
			fld_nurseId = new JTextField();
			fld_nurseId.setColumns(10);
			fld_nurseId.setBackground(SystemColor.controlHighlight);
			fld_nurseId.setBounds(427, 244, 184, 27);
			w_hemsire.add(fld_nurseId);
			
			JButton btndelHemsire = new JButton("Sil");
			btndelHemsire.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(fld_nurseId.getText().length() == 0)
					{
						Helper.showMsg("Lütfen Geçerli Bir Hemşire Seçiniz!");
					}
					else
					{
						if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_nurseId.getText());
						try {
							boolean control = bashekim.deleteHemsire(selectID);
							if(control)
							{
								Helper.showMsg("success");
								fld_nurseId.setText(null);
								updateHemsireModel();
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					  }
					}
					
				}
			});
			btndelHemsire.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btndelHemsire.setBounds(427, 281, 184, 27);
			w_hemsire.add(btndelHemsire);
			
			
			
			
			JScrollPane scrollHemsire = new JScrollPane();
			scrollHemsire.setBounds(20, 10, 379, 333);
			w_hemsire.add(scrollHemsire);
			
			tableHemsire = new JTable();
			tableHemsire.setBackground(new Color(240, 248, 255));
			scrollHemsire.setViewportView(tableHemsire);
			tableHemsire.setModel(hemsireModel);
			tableHemsire.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
			
				public void valueChanged(ListSelectionEvent e) {
					try {
					fld_nurseId.setText(tableHemsire.getValueAt(tableHemsire.getSelectedRow(),0).toString());
					}
					catch(Exception ex)
					{
						
					}
				}
			});
			
			tableHemsire.getModel().addTableModelListener(new TableModelListener() {

				@Override
				public void tableChanged(TableModelEvent e) {
					if(e.getType()== TableModelEvent.UPDATE)
					{
						int selectID = Integer.parseInt(tableHemsire.getValueAt(tableHemsire.getSelectedRow(), 0).toString());
						String selectName = tableHemsire.getValueAt(tableHemsire.getSelectedRow(), 1).toString();
						String selectTcno = tableHemsire.getValueAt(tableHemsire.getSelectedRow(), 2).toString();
						String selectPass = tableHemsire.getValueAt(tableHemsire.getSelectedRow(), 3).toString();
					
						try {
							boolean control = bashekim.updateHemsire(selectID, selectTcno, selectPass, selectName);
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}
				}
				
				
			});
			
		
			
			
			
			
			
			
			
			
			
			// buraya kadarr
			
		
			
			
			
			
			
			
			
			
		 
}
		  
		  
	
	
	public void updateDoctorModel () throws SQLException
	{
		
		DefaultTableModel clearModel =  (DefaultTableModel) tableDoctor.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<bashekim.getDoctorList().size(); i++)
		{
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
	}
	
	
	
	public void updateClinicModel() throws SQLException
	{
		DefaultTableModel clearModel =  (DefaultTableModel) tableclinic.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<clinic.getList().size(); i++)
		{
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			
			clinicModel.addRow(clinicData);
			
		}
	}
	
	
	
	
	
	
	//  deneme
	public void updateHemsireModel () throws SQLException
	{
		
		DefaultTableModel clearModel =  (DefaultTableModel) tableHemsire.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<bashekim.getHemsireList().size(); i++)
		{
			hemsireData[0] = bashekim.getHemsireList().get(i).getId();
			hemsireData[1] = bashekim.getHemsireList().get(i).getName();
			hemsireData[2] = bashekim.getHemsireList().get(i).getTcno();
			hemsireData[3] = bashekim.getHemsireList().get(i).getPassword();
			clearModel.addRow(hemsireData);
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
