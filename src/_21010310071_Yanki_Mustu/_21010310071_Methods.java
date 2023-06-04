package _21010310071_Yanki_Mustu;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class _21010310071_Methods {
	
	static String[] degiskenler;
	 int dogrulukSayisi;
	int satirSayisi;
	int degiskenSayisi;
	 int[][] diyagram;
	 int[] karnaugh;
	 int[][] dogrulukTablosu;
	 
	 
	 public void dosyaOku() {
	String dosyaAdi = "karnaugh.txt";
     File dosya = new File(dosyaAdi);
     
     try {
    	 
         Scanner scanner = new Scanner(dosya);
         
         
         String degiskenlerSatiri = scanner.nextLine();
         degiskenler = degiskenlerSatiri.split(": ")[1].split(", ");
         
        degiskenSayisi = degiskenler.length;
         
         
         
         
         
         if(degiskenSayisi<4) {
         	satirSayisi=2;
         }else {
         	satirSayisi=4;
         }
        
         
         if(degiskenSayisi<4) {
         	dogrulukSayisi=8;
         }else {
         	dogrulukSayisi=16;
         }
         
         diyagram = new int [satirSayisi][4];
         
         Scanner scanner2 = new Scanner(dosya);
         int sayac = 0;
        
         while (scanner2.hasNextLine()) {   
         	sayac++;
         	String[] row = scanner2.nextLine().split(" ");
         	if(sayac>2) {
         	
         
                                
                 for (int j = 0; j < diyagram[0].length; j++) {
                     diyagram[sayac-3][j] = Integer.parseInt(row[j]);
                 }
                 }
              
            }
         System.out.println(dosya+" dosyası okundu.");
         scanner2.close();
         scanner.close();
        
     } catch (FileNotFoundException e) {
         System.out.println("Dosya bulunamadı ya da yanlış yazıldı, dosya adı: " + dosyaAdi);
     }

}
	 

	 public void dogrulukTablosu(int diyagram[][],String degiskenler[]) {
		 
        if(satirSayisi<3) {
         int temp=0;
         for (int i = 0; i < satirSayisi; i++) {
         	temp=diyagram[i][2];
         	diyagram[i][2] = diyagram[i][3];
         	diyagram[i][3] = temp;
         }
          }else {
         	
         	 int temp=0;
         	 int temp2=0;
              for (int i = 0; i < satirSayisi; i++) {
              	temp=diyagram[i][2];
              	diyagram[i][2] = diyagram[i][3];
              	diyagram[i][3] = temp;
              	
           }
              for(int x = 0; x<4; x++) {
           		temp2=diyagram[2][x];
               	diyagram[2][x] = diyagram[3][x];
               	diyagram[3][x] = temp2;
           	}
          }
             
         
         karnaugh = new int[dogrulukSayisi];
        
         int index = 0;
         for (int i = 0; i < diyagram.length; i++) {
             for (int j = 0; j < diyagram[i].length; j++) {
                 karnaugh[index++] = diyagram[i][j];
             }
         }
         
         System.out.println("doğruluk Tablosu: ");
         for(int i=0; i<degiskenSayisi; i++) {
         	System.out.print(degiskenler[i]+" ");
         }
         System.out.print("F");
         System.out.println();
         
         dogrulukTablosu = new int [dogrulukSayisi][degiskenSayisi];

         
         for (int i=0; i<dogrulukSayisi; i++) {
         	int y=0;
             for (int j=degiskenSayisi-1; j>=0; j--) {
                 System.out.print((i/(int) Math.pow(2, j))%2 + " ");
                 dogrulukTablosu[i][y] = i/(int) Math.pow(2, j)%2;
                 y++;
             }
             System.out.print(karnaugh[i]);
             System.out.println();
         }
     
    
	 }
	 public void fonksiyonlar(int[] karnaugh) {
		 System.out.println("fonksiyon ifadeleri:");
         
         String minterimler = "";
         for(int i=0; i<karnaugh.length; i++) {
      	   
      	   if(karnaugh[i]==1) {
      		  
      		   for(int k = 0; k<degiskenler.length;k++) {
          		   if(dogrulukTablosu[i][k]==0) {
          			   minterimler+= degiskenler[k]+"'";
          			}
          		   else {
          			   minterimler+= degiskenler[k];
          		   }
          		   
          	   }
      		   minterimler += "+";
      		}
      	 }
         
         
         minterimler= minterimler.substring(0, minterimler.length()-1);
          System.out.println("F = "+minterimler);
        
          
          
          
          String maxterimler = "";
          for(int i=0; i<karnaugh.length; i++) {
       	   
       	   if(karnaugh[i]==0) {
       		   maxterimler += "(";
       		   for(int k = 0; k<degiskenler.length;k++) {
           		   if(dogrulukTablosu[i][k]==0) {
           			   maxterimler+= degiskenler[k]+"+";
           			}
           		   else {
           			   maxterimler+= degiskenler[k]+"'+";
           		   }
           		   
           	   }
       		  maxterimler= maxterimler.substring(0, maxterimler.length()-1);
       		   maxterimler += ").";
       		}
       	 }
          maxterimler= maxterimler.substring(0, maxterimler.length()-1);
          System.out.println("F = "+maxterimler);
       
          
          String minterimlert = "";
          for(int i=0; i<karnaugh.length; i++) {
       	   
       	   if(karnaugh[i]==0) {
       		  
       		   for(int k = 0; k<degiskenler.length;k++) {
           		   if(dogrulukTablosu[i][k]==0) {
           			   minterimlert+= degiskenler[k]+"'";
           			}
           		   else {
           			   minterimlert+= degiskenler[k];
           		   }
           		   
           	   }
       		   minterimlert += "+";
       		}
       	 }
          
          
          minterimlert= minterimlert.substring(0, minterimlert.length()-1);
           System.out.println("F' = "+minterimlert);

           
           String maxterimlert = "";
           for(int i=0; i<karnaugh.length; i++) {
        	   
        	   if(karnaugh[i]==1) {
        		   maxterimlert += "(";
        		   for(int k = 0; k<degiskenler.length;k++) {
            		   if(dogrulukTablosu[i][k]==0) {
            			   maxterimlert+= degiskenler[k]+"+";
            			}
            		   else {
            			   maxterimlert+= degiskenler[k]+"'+";
            		   }
            		   
            	   }
        		  maxterimlert= maxterimlert.substring(0, maxterimlert.length()-1);
        		   maxterimlert += ").";
        		}
        	 }
           maxterimlert= maxterimlert.substring(0, maxterimlert.length()-1);
           System.out.println("F' = "+maxterimlert);
		 
	 }
	
 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public int[][] getDiyagram(){
		 return diyagram;
	 }
	 public String[] getDegiskenler(){
		 return degiskenler;
	 }
	 public int[] getKarnaugh(){
		 return karnaugh;
	 }




















}
