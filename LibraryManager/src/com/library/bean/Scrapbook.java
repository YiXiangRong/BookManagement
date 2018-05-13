package com.library.bean;
import java.util.Date;

public class Scrapbook {

	  String BOOKID;               
	  String BOOKCODE;         
	  String SCRAPREASON;         
	  String DESCRIBES;            
	  Date SCRAPDATE;            
	  String CREATEUSER;           
	  Date CREATEDATE;
	  
	  public String getBOOKCODE() {  
	        return BOOKCODE;  
	    }  
	    public void setBOOKCODE(String BOOKCODE) {  
	        this.BOOKCODE= BOOKCODE;  
	    }  
	    
	    public String getBOOKID() {  
	        return BOOKID;  
	    }  
	    public void setBOOKID(String BOOKID) {  
	        this.BOOKID = BOOKID;  
	    }  
	    
	    public String getSCRAPREASON() {  
	        return SCRAPREASON;  
	    }  
	    public void setSCRAPREASON(String SCRAPREASON) {  
	        this.SCRAPREASON = SCRAPREASON;  
	    } 
	    
	    public String getDESCRIBES() {  
	        return DESCRIBES;  
	    }  
	    public void setDESCRIBES(String DESCRIBES) {  
	        this.DESCRIBES = DESCRIBES;  
	    } 
	    
	    public String getCREATEUSER() {  
	        return CREATEUSER;  
	    }  
	    public void setCREATEUSER(String CREATEUSER) {  
	        this.CREATEUSER= CREATEUSER;  
	    } 
	    
	    public Date getSCRAPDATE() {  
	        return SCRAPDATE;  
	    }  
	    public void setSCRAPDATE(Date SCRAPDATE) {  
	        this.SCRAPDATE = SCRAPDATE;  
	    } 
	    
	    public Date getCREATEDATE() {  
	        return CREATEDATE;  
	    }  
	    public void setCREATEDATE(Date CREATEDATE) {  
	        this.CREATEDATE = CREATEDATE;  
	    } 
}
