package entity;

public class Folder {
	private int folder_id;
	private String folder_name;
	private String folder_path;
	public Folder(){}
	public Folder(int folder_id, String folder_name, String folder_path) {
		super();
		this.folder_id = folder_id;
		this.folder_name = folder_name;
		this.folder_path = folder_path;
	}
	public int getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}
	public String getFolder_name() {
		return folder_name;
	}
	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}
	public String getFolder_path() {
		return folder_path;
	}
	public void setFolder_path(String folder_path) {
		this.folder_path = folder_path;
	}
	
	
	
	
	
	

}
