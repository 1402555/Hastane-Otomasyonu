package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Doctor extends User {

	
	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;
	public Doctor() {
		super();
		
	}
	public Doctor(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		
	}
	
	
	public boolean addWhour(int doctor_id, String doctor_name, String wdate)
	{
		int key = 0;
		int count =0;
		String query = "INSERT INTO whour" + "(doctor_id,doctor_name,wdate) VALUES" + "(?,?,?)";
		
		try {
			st=con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND doctor_id = " + doctor_id + " AND wdate ='"+ wdate +"'");
			while(rs.next())
			{
				count++;
				break;
			}
			
			if(count==0) {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setString(3, wdate);
		
			preparedStatement.executeUpdate();
			
			}
			key=1;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if(key==1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	
	
	
	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException{
		ArrayList<Whour> list = new ArrayList<>();
		
		Whour obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status ='a' AND doctor_id = " + doctor_id);
			while(rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
		} }catch (SQLException e) {
			
			e.printStackTrace();
		}
	    return list;
		}
	
	
	
	

	public boolean deleteWhour(int id) throws SQLException
	{
		String query = "DELETE FROM whour WHERE id = ?";
		boolean key = false;
		try {
		st=con.createStatement();
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, id);	
		preparedStatement.executeUpdate();
		key = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
			
		if(key)
			return true;
		else
			return false;
	}
	
	
	
	
	
	// ben ekliyorum
	
	
	public ArrayList<Randevu> getRandevuList(int doctor_id) throws SQLException {
	    ArrayList<Randevu> list = new ArrayList<>();
	    Randevu obj;
	    try {
	        st = con.createStatement();
	        rs = st.executeQuery("SELECT * FROM randevu WHERE doctor_id = " + doctor_id);
	        while (rs.next()) {
	            obj = new Randevu();
	            obj.setId(rs.getInt("id"));
	            obj.setDoctorID(rs.getInt("doctor_id"));
	            obj.setDoctorName(rs.getString("doctor_name"));
	            obj.setHastaID(rs.getInt("hasta_id"));
	            obj.setHastaName(rs.getString("hasta_name"));
	            obj.setAppDate(rs.getString("app_date"));
	            list.add(obj);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
