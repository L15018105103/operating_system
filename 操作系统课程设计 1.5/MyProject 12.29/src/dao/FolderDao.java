package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Folder;
import util.Jdbc;

public class FolderDao {
	
	public static  ArrayList<Folder> getcildrenFolder(String folder_path) throws ClassNotFoundException, SQLException{
		Connection con = Jdbc.connect();
		Statement sta = null;
		ResultSet rs = null;
		sta = (Statement) con.createStatement();
		 
		ArrayList<Folder> folderlist= new ArrayList<Folder>();
		rs = (ResultSet) sta.executeQuery("select * from folderpath where folder_path like '"+folder_path+"/_'");

		while(rs.next()){
		Folder folder=new Folder();
		folder.setFolder_id(rs.getInt("folder_id"));
		folder.setFolder_name(rs.getString("folder_name"));
		folder.setFolder_path(rs.getString("folder_path"));
 		
		folderlist.add(folder);
		}
		if(rs != null){
		rs.close();
		}
		Jdbc.close(sta, con);
		return folderlist;
		}

	public static  ArrayList<Folder> getfatherFolder(String folder_path) throws ClassNotFoundException, SQLException{
		Connection con = Jdbc.connect();
		Statement sta = null;
		ResultSet rs = null;
		sta = (Statement) con.createStatement();
		
		folder_path=folder_path.substring(0,folder_path.length()-4);// 1/1/2/3  ->1/1
		 
		ArrayList<Folder> folderlist= new ArrayList<Folder>();
		rs = (ResultSet) sta.executeQuery("select * from folderpath where folder_path like '"+folder_path+"/_'");

		while(rs.next()){
		Folder folder=new Folder();
		folder.setFolder_id(rs.getInt("folder_id"));
		folder.setFolder_name(rs.getString("folder_name"));
		folder.setFolder_path(rs.getString("folder_path"));
 		
		folderlist.add(folder);
		}
		if(rs != null){
		rs.close();
		}
		Jdbc.close(sta, con);
		return folderlist;
		}
	
	
	public static void newFolder(Folder folder) throws ClassNotFoundException, SQLException{
		Connection con = Jdbc.connect();
		
		
		 int folder_id=1;
		 String folder_path=folder.getFolder_path().concat("/"+String.valueOf(folder_id));
		 ArrayList<Folder> snbfolderlist=getcildrenFolder(folder.getFolder_path());
		 for(int i=1;i<10;i++){
			 boolean isnotsame=true;
		 for(Folder f:snbfolderlist){
			 folder_path=folder.getFolder_path().concat("/"+String.valueOf(i));
			 if(folder_path.equals(f.getFolder_path())){
				 isnotsame=false;
			 }
			}
		 if(isnotsame){
			 folder_id=i;
			 break;
		 }
		 }
		
		 
		String  query="insert into folderpath(folder_id, folder_name,folder_path) VALUES "
				+ "('"+folder_id+"','"+folder.getFolder_name()+"', '"+folder_path+"')";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.execute();
		con.close();
		return ;
		}
	
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		for(Folder f:getfatherFolder("1/1/2/3")){
			System.out.println(f.getFolder_path()+"   "+f.getFolder_name());
		}
	}*/
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Folder folder=new Folder(1, "ÀîÀÏ°å", "1/1");
		newFolder(folder);
	}
		 */
		
	
}
