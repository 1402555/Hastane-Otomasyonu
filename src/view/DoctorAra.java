package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Helper.Helper;
import Model.Doctor;
import Model.Randevu;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class DoctorAra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable tablewhour;
	private DefaultTableModel  whourModel;
	private Object[] whourData = null;
	
	
	
	// ben ekliyorum
	
	 private JTable tableRandevu;
	 private JTable tableRandevu_1;
	    private DefaultTableModel randevuModel;
	    private Object[] randevuData = null;
	    private JTable tableIptal;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorAra frame = new DoctorAra(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DoctorAra(Doctor doctor) throws SQLException {
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for(int i=0; i<doctor.getWhourList(doctor.getId()).size(); i++)
		{
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		
		
	
		
		
		setBackground(Color.WHITE);
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 294, 27);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginAra login = new LoginAra();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(637, 10, 89, 27);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(Color.WHITE);
		w_tab.setBounds(0, 73, 716, 380);
		w_pane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Çalışma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(21, 10, 114, 19);
		w_whour.add(dateChooser);
		
		JComboBox selecttime = new JComboBox();
		selecttime.setModel(new DefaultComboBoxModel(new String[] {"9.00", "9.30", "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00"}));
		selecttime.setBounds(160, 10, 50, 19);
		w_whour.add(selecttime);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date ="";
				try {
				 date = sdf.format(dateChooser.getDate());
				}catch(Exception e2) {
					
				}
				if(date.length()==0)
				{
					Helper.showMsg("Lütfen Geçerli Bir Tarih Girin");
				}
				else
				{
					String time = " " + selecttime.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
				
				boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
				if(control)
				{
					Helper.showMsg("success");
					try {
						updateWhourModel(doctor);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					Helper.showMsg("error");
				}
				}
				
				
			}
		});
		btn_addWhour.setBounds(220, 10, 89, 21);
		w_whour.add(btn_addWhour);
		
		JScrollPane scrollwhour = new JScrollPane();
		scrollwhour.setBounds(10, 58, 691, 285);
		w_whour.add(scrollwhour);
		
		tablewhour = new JTable(whourModel);
		tablewhour.setBackground(new Color(240, 248, 255));
		scrollwhour.setViewportView(tablewhour);
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tablewhour.getSelectedRow();
				if(selRow >= 0)
				{
					String selectRow = tablewhour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					try {
						boolean control = doctor.deleteWhour(selID);
						if(control)
						{
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}
						else
						{
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					Helper.showMsg("Lütfen Bir Tarih Seçiniz!");
				}
				
				
			}
		});
		btn_deleteWhour.setBounds(612, 8, 89, 21);
		w_whour.add(btn_deleteWhour);
		
		
		
		
		// ben ekledim 
		
		
		JPanel w_randevu = new JPanel();
		w_randevu.setBackground(Color.WHITE);
		w_tab.addTab("Randevularım", null, w_randevu, null);
		w_randevu.setLayout(null);
		
		JScrollPane scrollRandevu = new JScrollPane();
		scrollRandevu.setBounds(10, 10, 691, 333);
		w_randevu.add(scrollRandevu);
		
		tableRandevu = new JTable();
		tableRandevu.setBackground(new Color(240, 248, 255));
		scrollRandevu.setViewportView(tableRandevu);
		
	

        randevuModel = new DefaultTableModel();
        Object[] colRandevu = new Object[3];
        colRandevu[0] = "ID";
        colRandevu[1] = "Tarih";
        colRandevu[2] = "Hasta Adı";
        randevuModel.setColumnIdentifiers(colRandevu);
        randevuData = new Object[3];


	     
	        updateRandevuModel(doctor);
	        tableRandevu_1 = new JTable(randevuModel);
	        tableRandevu_1.setBackground(new Color(240, 248, 255));
	        scrollRandevu.setViewportView(tableRandevu_1);
	        
	        
	        // buraya kadar doktorun kendi randevularını görebilmesi
	        
	        
	        
	        
	     
		
		
	}
		
		
	
	
	
	
	
	
	
	public void updateWhourModel (Doctor doctor) throws SQLException
	{
		
		DefaultTableModel clearModel =  (DefaultTableModel) tablewhour.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<doctor.getWhourList(doctor.getId()).size(); i++)
		{
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
	
	
	
	
	// ben ekliyorum
	
	
	
	 public void updateRandevuModel(Doctor doctor) throws SQLException {
	        DefaultTableModel clearModel = (DefaultTableModel) tableRandevu.getModel();
	        clearModel.setRowCount(0);
	        ArrayList<Randevu> randevuList = doctor.getRandevuList(doctor.getId()); // Randevuları al

	        for (Randevu randevu : randevuList) {
	            randevuData[0] = randevu.getHastaID();
	            randevuData[1] = randevu.getAppDate();
	            randevuData[2] = randevu.getHastaName();
	            randevuModel.addRow(randevuData);
	        }
	 }
	 
	 
	 // buraya kadar başarılı
	 
	 
	 

	 
	 
	 
	
	 
	 
	 
	 
	 
	 
}

