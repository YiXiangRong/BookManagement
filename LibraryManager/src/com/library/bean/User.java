package com.library.bean;


public class User {
	int ID;
	String userName;//�û���  
    String userPwd; //����
    String name;//��ʵ����
    String codetype;//֤������
    String codeno;//֤����
    String phone;//�绰
    String emall;//����
    String address;//סַ
    int type;//�û����ͣ�1Ϊ��������Ա��0Ϊ����Ա
    String createdate;//��������
    public User(){}
    
    public int getID() {  
        return ID;  
    }  
    public void setID(int ID) {  
        this.ID = ID;  
    }
    public String getUserName() {  
        return userName;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }
    
    public String getUserPwd() {  
        return userPwd;  
    }  
    public void setUserPwd(String UserPwd) {  
        this.userPwd = UserPwd;  
    }
    
    public String getname() {  
        return name;  
    }  
    public void setname(String name) {  
        this.name = name;  
    }
    
    public String getcodetype() {  
        return codetype;  
    }  
    public void setcodetype(String codetype) {  
        this.codetype = codetype;  
    }
    
    public String getcodeno() {  
        return codeno;  
    }  
    public void setcodeno(String codeno) {  
        this.codeno = codeno;  
    }
    
    public String getphone() {  
        return phone;  
    }  
    public void setphone(String phone) {  
        this.phone = phone;  
    }
    
    public String getemall() {  
        return emall;  
    }  
    public void setemall(String emall) {  
        this.emall = emall;  
    }
    
    public String getaddress() {  
        return address;  
    }  
    public void setaddress(String address) {  
        this.address = address;  
    }
    
    public  int gettype() {  
        return type;  
    }  
    public void settype(int type) {  
        this.type = type;  
    }
    
    public String getcreatedate() {  
        return createdate;  
    }  
    public void setcreatedate(String createdate) {  
        this.createdate = createdate;  
    }
}