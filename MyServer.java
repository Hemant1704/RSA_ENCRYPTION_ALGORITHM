import java.io.*;  
import java.net.*;  
import java.util.*;
import java.math.BigInteger;
import java.lang.*;
public class MyServer {  
    public static void main(String[] args){  
        try{  
           //establishes connection   
           
           
           Random rand1 = new Random(System.currentTimeMillis());
           Random rand2 = new Random(System.currentTimeMillis()*10);
           int pubkey = 2;
           BigInteger p = BigInteger.probablePrime(32,rand1);
           BigInteger q = BigInteger.probablePrime(32,rand2);

    // multiply p and q 
          BigInteger n = p.multiply(q);
          BigInteger p_1 = p.subtract(new BigInteger("1"));
          BigInteger q_1 = q.subtract(new BigInteger("1"));

          BigInteger z = p_1.multiply(q_1);
          while(true){
               BigInteger gcd = z.gcd(new BigInteger(""+pubkey));
               if(gcd.equals(BigInteger.ONE)){
                    break;
               } 
          pubkey++;
          }
          BigInteger big_pubkey = new BigInteger(""+pubkey);
          BigInteger prvkey = big_pubkey.modInverse(z);
          System.out.println("public key "+pubkey+","+n);
          System.out.println("private key "+prvkey+","+n);
          
           ServerSocket ss=new ServerSocket(6666);  
           Socket s=ss.accept();

           DataInputStream din=new DataInputStream(s.getInputStream());
           //DataInputStream din=new DataInputStream(s.getInputStream());
           String  msg=(String)din.readUTF();

           

           byte[] bytes = msg.getBytes();

    for(int i=0;i<msg.length();i++){
      int asciiVal = bytes[i];
      BigInteger val = new BigInteger(""+asciiVal);
      
      BigInteger cipherVal = val.modPow(big_pubkey,n);
      System.out.println("cipher text:"+cipherVal);

      BigInteger plainVal = cipherVal.modPow(prvkey,n);
      int i_plainVal = plainVal.intValue();

      System.out.println("plain text: "+Character.toString((char)i_plainVal));
    }
           ss.close();  
         }
         catch(Exception e){System.out.println(e);}  
     }

}