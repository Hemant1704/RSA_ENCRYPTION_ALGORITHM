import java.io.*;  
import java.net.*;  
import java.util.*;
import java.math.BigInteger;
import java.lang.*;
public class MyClient {  
public static void main(String[] args) {  
    try{      
        Socket s=new Socket("localhost",6666);  
        Scanner sc = new Scanner(System.in);
       DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
		System.out.println("Enter the message to be encrypted..");
		String msg = sc.nextLine();
        dout.writeUTF(msg);  
        dout.flush();  
        dout.close();  
        s.close();  
    }
    catch(Exception e){
    	System.out.println(e);
    }  
}   
} 

