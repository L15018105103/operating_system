package entity;

public class UserFile {
	private String fileStatus;
	private String fileName;
	private String fileSize;
	private String fileDate;
	private String fileContent;
	private String folder_path;
    
	public UserFile()
	{
		
	}
	
	public UserFile(String fileStatus, String fileName, String fileSize, String fileDate,
			String fileContent, String folder_path)
	{
		this.fileStatus = fileStatus;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileDate = fileDate;
		this.fileContent = fileContent;
		this.folder_path = folder_path;
	}
	public String getFileStatus()
	{
		return fileStatus;
	}
	public String getFileName()
	{
		return fileName;
	}
	public String getFileSize()
	{
		return fileSize;
	}
	public String getFileDate()
	{
		return fileDate;
	}
	public String getFileContent()
	{
		return fileContent;
	}
	public String getFolder_path()
	{
		return folder_path;
	}
	public void setFileStatus(String fileStatus)
	{
		this.fileStatus = fileStatus;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	public void setFileSize(String fileSize)
	{
		this.fileSize = fileSize;
	}
	public void setFileDate(String fileDate)
	{
		this.fileDate = fileDate;
	}
	public void setFileContent(String fileContent)
	{
		this.fileContent = fileContent;
	}
	public void setFolder_path(String folder_path)
	{
		this.folder_path = folder_path;
	}
}
