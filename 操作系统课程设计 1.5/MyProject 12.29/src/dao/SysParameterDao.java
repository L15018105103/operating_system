package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.SysParameter;
import util.Jdbc;
	
public class SysParameterDao {
	public ArrayList<SysParameter> search(String blockId) throws ClassNotFoundException, SQLException{
		Connection con = Jdbc.connect();
		Statement sta = null;
		ResultSet rs = null;
		sta = (Statement) con.createStatement();
		 
		ArrayList<SysParameter> SysParameters = new ArrayList<SysParameter>();
		rs = (ResultSet) sta.executeQuery("select * from configure where blockId = '"+blockId+"'"+"order by blockId");

		while(rs.next()){
		SysParameter s = new SysParameter();	
		s.setBlockNumber(rs.getString("blockNumber"));
		s.setBlockSize(rs.getString("blockSize"));
		s.setHardDisk(rs.getString("diskSize"));
		//System.out.println(rs.getString("id")+"\t"+rs.getString("password")+"\t"+rs.getString("name")+"\t");
		SysParameters.add(s);
		}
		if(rs != null){
		rs.close();
		}
		Jdbc.close(sta, con);
		return SysParameters;
		}
}
