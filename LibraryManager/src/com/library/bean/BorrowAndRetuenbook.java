package com.library.bean;
import java.util.Date;

public class BorrowAndRetuenbook {

	  String LIBCARDNO;//����֤��
	  String NAME;
	  String BOOKNAME;//����
	  String BOOKCODE; //ͼ����              
	  Date BORROWDATE; //��������          
	  Date SHOULDRETURNDATE; //Ӧ������    
	  String BORROWMANAGER;  //����������      
	 
	  Date RETURNDATE;//����ʱ��           
	  String RETURNMANAGER;//����������     
	  String ISOVERDUE; //�Ƿ���          
	  double FINE; //����               
	  String NOTE; //��ע
	  
	  public String getLIBCARDNO() {  
	        return LIBCARDNO;  
	    }  
	    public void setLIBCARDNO(String LIBCARDNO) {  
	        this.LIBCARDNO = LIBCARDNO;  
	    }  
	    public String getNAME() {  
	        return NAME;  
	    }  
	    public void setNAME(String NAME) {  
	        this.NAME = NAME;  
	    }
	    public String getBOOKNAME() {  
	        return BOOKNAME;  
	    }  
	    public void setBOOKNAME(String BOOKNAME) {  
	        this.BOOKNAME = BOOKNAME;  
	    }  
	    public String getBOOKCODE() {  
	        return BOOKCODE;  
	    }  
	    public void setBOOKCODE(String BOOKCODE) {  
	        this.BOOKCODE = BOOKCODE;  
	    }  
	    
	    public Date getBORROWDATE() {  
	        return BORROWDATE;  
	    }  
	    public void setBORROWDATE(Date BORROWDATE) {  
	        this.BORROWDATE = BORROWDATE;  
	    } 
	    
	    public Date getSHOULDRETURNDATE() {  
	        return SHOULDRETURNDATE;  
	    }  
	    public void setSHOULDRETURNDATE(Date SHOULDRETURNDATE) {  
	        this.SHOULDRETURNDATE = SHOULDRETURNDATE;  
	    } 
	    
	    public String getBORROWMANAGER() {  
	        return BORROWMANAGER;  
	    }  
	    public void setBORROWMANAGER(String BORROWMANAGER) {  
	        this.BORROWMANAGER= BORROWMANAGER;  
	    } 
	    
	   /* public Date getAcCCEPDATE() {  
	        return AcCCEPDATE;  
	    }  
	    public void setAcCCEPDATE(Date AcCCEPDATE) {  
	        this.AcCCEPDATE = AcCCEPDATE;  
	    } */
	    
	    public Date getRETURNDATE() {  
	        return RETURNDATE;  
	    }  
	    public void setRETURNDATE(Date RETURNDATE) {  
	        this.RETURNDATE = RETURNDATE;  
	    } 
	    
	    public String getRETURNMANAGER() {  
	        return RETURNMANAGER;  
	    }  
	    public void setRETURNMANAGER(String RETURNMANAGER) {  
	        this.RETURNMANAGER = RETURNMANAGER;  
	    } 
	    
	    public String getISOVERDUE() {  
	        return ISOVERDUE;  
	    }  
	    public void setISOVERDUE(String ISOVERDUE) {  
	        this.ISOVERDUE = ISOVERDUE;  
	    } 
	    
	    public double getFINE() {  
	        return FINE;  
	    }  
	    public void setFINE(double FINE) {  
	        this.FINE = FINE;  
	    } 
	
	    public String getNOTE() {  
	        return NOTE;  
	    }  
	    public void setNOTE(String NOTE) {  
	        this.NOTE = NOTE;  
	    } 
}
