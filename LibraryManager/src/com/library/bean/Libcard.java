package com.library.bean;

import java.util.Date;

public class Libcard {

	String libcardno;//借书证号  
    String name;//姓名
    String cardtype;//证件类型
    String cardno;//证件号
    String phone;
    String address;
    String emall;
    String createuser;
    String createdate;
    String effectivedate;
    
    
    public String getlibcardno() {  
        return libcardno;  
    }  
    public void setlibcardno(String libcardno) {  
        this.libcardno = libcardno;  
    }  
    
    public String getname() {  
        return name;  
    }  
    public void setname(String name) {  
        this.name = name;  
    }  
    
    public String getcardtype() {  
        return cardtype;  
    }  
    public void setcardtype(String cardtype) {  
        this.cardtype = cardtype;  
    }  
    
    public String getcardno() {  
        return cardno;  
    }  
    public void setcardno(String cardno) {  
        this.cardno = cardno;  
    }  
    
    public String getphone() {  
        return phone;  
    }  
    public void setphone(String phone) {  
        this.phone = phone;  
    }  
    
    public String getaddress() {  
        return address;  
    }  
    public void setaddress(String address) {  
        this.address = address;  
    }  
    
    public String getemall() {  
        return emall;  
    }  
    public void setemall(String emall) {  
        this.emall = emall;  
    }  
    
    public String getcreateuser() {  
        return createuser;  
    }  
    public void setcreateuser(String createuser) {  
        this.createuser = createuser;  
    }  
    
    public String getcreatedate() {  
        return createdate;  
    }  
    public void setcreatedate(String createdate) {  
        this.createdate = createdate;  
    }  
    
    public String geteffectivedate() {  
        return effectivedate;  
    }  
    public void seteffectivedate(String effectivedate) {  
        this.effectivedate = effectivedate;  
    }  
}
