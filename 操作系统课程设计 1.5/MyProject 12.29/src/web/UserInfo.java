package web;

public class UserInfo {
    private String userName;
    private String pwd;
    
    public UserInfo(){
    	
    }
    public UserInfo(String userName, String pwd)
    {
    	this.userName = userName;
    	this.pwd = pwd;
    }
    public void setUserName(String userName)
    {
    	this.userName = userName;
    }
    public void setPwd(String pwd)
    {
    	this.pwd = pwd;
    }
}
