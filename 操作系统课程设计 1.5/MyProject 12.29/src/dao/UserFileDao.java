package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.UserFile;
import util.Jdbc;

public class UserFileDao {
	public ArrayList<UserFile> search(String fileDate) throws ClassNotFoundException, SQLException{
		Connection con = Jdbc.connect();
		Statement sta = null;
		ResultSet rs = null;
		sta = (Statement) con.createStatement();
		 
		ArrayList<UserFile> UserFiles = new ArrayList<UserFile>();
		rs = (ResultSet) sta.executeQuery("select * from filetable where fileDate = '"+fileDate+"'"+"order by fileDate");

		while(rs.next()){
		UserFile u = new UserFile();			
		u.setFileStatus(rs.getString("fileStatus"));
		u.setFileName(rs.getString("fileName"));
		u.setFileSize(rs.getString("fileSize"));
		u.setFileDate(rs.getString("fileDate"));
		u.setFileContent(rs.getString("fileContent"));
		u.setFolder_path(rs.getString("folder_path"));
		//System.out.println(rs.getString("id")+"\t"+rs.getString("password")+"\t"+rs.getString("name")+"\t");
		UserFiles.add(u);
		}
		if(rs != null){
		rs.close();
		}
		Jdbc.close(sta, con);
		return UserFiles;
		}
}
