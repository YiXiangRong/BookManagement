package com.library.bean;
import java.util.Date;
public class Book {

	String bookname;//����  
    String bookcode;//���
    String author;//����
    String booktype;//����
    String press;//����
    double price;//�۸�
    
    String printing;//ӡˢ
    String pressdate;//��������
    String content;//��������
    
    String imgadd;//ͼƬ��ַ
    String createuser;//�����
    Date createdate;//�������
    String storetype;//������ͣ����������
    String donationuser;//������
    int number;
    int sum;
    String STATE;//״̬
    public Book(){}
    
    public int getsum() {  
        return sum;  
    }  
    public void setsum(int sum) {  
        this.sum = sum;  
    }  
    public int getnumber() {  
        return number;  
    }  
    public void setnumber(int number) {  
        this.number = number;  
    }  
    
    public String getbookname() {  
        return bookname;  
    }  
    public void setbookname(String bookname) {  
        this.bookname = bookname;  
    }  
    
    public String getbookcode() {  
        return bookcode;  
    }  
    public void setbookcode(String bookcode) {  
        this.bookcode = bookcode;  
    }  
    
    public String getauthor() {  
        return author;  
    }  
    public void setauthor(String author) {  
        this.author = author;  
    } 
    
    public String getbooktype() {  
        return booktype;  
    }  
    public void setbooktype(String booktype) {  
        this.booktype = booktype;  
    } 
    
    public String getpress() {  
        return press;  
    }  
    public void setpress(String press) {  
        this.press = press;  
    } 
    
    public double getprice() {  
        return price;  
    }  
    public void setprice(double price) {  
        this.price = price;  
    } 
    
   /* public int getnumbers() {  
        return numbers;  
    }  
    public void setnumbers(int numbers) {  
        this.numbers = numbers;  
    } */
    
    public String getprinting() {  
        return printing;  
    }  
    public void setprinting(String printing) {  
        this.printing = printing;  
    } 
    
    public String getcontent() {  
        return content;  
    }  
    public void setcontent(String content) {  
        this.content = content;  
    } 
    
    
    
    public String getimgadd() {  
        return imgadd;  
    }  
    public void setimgadd(String imgadd) {  
        this.imgadd = imgadd;  
    } 
    
    public String getcreateuser() {  
        return createuser;  
    }  
    public void setcreateuser(String createuser) {  
        this.createuser = createuser;  
    } 
    
    public Date getcreatedate()
    {
    	return createdate;
    }
    public void setcreatedate(Date creadedate)
    {
    	this.createdate=creadedate;
    }
    
    public String getstoretype()
    {
    	return storetype;
    }
    public void setstoretype(String storetype)
    {
    	this.storetype=storetype;
    }
    
    public String getdonationuser()
    {
    	return donationuser;
    }
    public void setdonationuser(String donationuser){
    	this.donationuser=donationuser;
    }
    
    public String getpressdate() {  
        return pressdate;  
    }  
    public void setpressdate(String pressdate) {  
        this.pressdate = pressdate;  
    }
    public String getSTATE() {  
        return STATE;  
    }  
    public void setSTATE(String STATE) {  
        this.STATE = STATE;  
    }  
}
