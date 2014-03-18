
 
package com.kapil;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.log4j.Logger;
 
 
 
 
public class CompanyShareAnalyser {
	Logger log = Logger.getLogger(CompanyShareAnalyser.class);
 
public static void main(String[] args) {
	CompanyShareAnalyser object = new CompanyShareAnalyser();
                     object.run(args[0]);
}

public void run(String csvFile) {    
    BufferedReader br = null;
    //String line = "";
    String cvsSplitBy = ",";
   
    int rowsInFile=0;
    ArrayList<String> lines = new ArrayList<String>();
        String tmp = "";
        int noCols =12;
        int count=0;
    try {
          br = new BufferedReader(new FileReader(csvFile));                
         
          while((tmp=br.readLine())!= null)
        {
                // get the no of columns
                if(count==1){
                      String[] lgth = tmp.split(cvsSplitBy);
                       noCols=lgth.length;
                }
               
            lines.add(tmp);
            if(count==0){
            count++;
            }
        }
          // get the no of rows
        rowsInFile = lines.size();     
        
       
        String[][] numbers = new String[rowsInFile][noCols];

        int row = 0;
        int col=0;
        for(String line : lines)
        {
            StringTokenizer st = new StringTokenizer(line,",");
            col=0;

            while (st.hasMoreTokens()) {
            //get next token and store it in the array
                numbers[row][col] = st.nextToken();
                col++;
            }
            row++;
        }
       
        int begin =1;
        int yr=0;
        int mn=1;
        int prc =2;
        int maxVal=0;
        int seq=0;
        int arrayLngth=((numbers.length-1)*(noCols-2));            
        ArrayList<String> years = new ArrayList<String>();
        HashMap<Integer, StringBuffer> finalMap = new HashMap<Integer, StringBuffer>();
        String year = "";                    
	    String price = "";
	    String cmpny = "";
	    String mnth = "";
	    int priceVal = 0;
	    int val=1;
   
        
       for(int ma=0;ma<arrayLngth;ma++){
               
             
               
                if(((!years.contains(numbers[begin][yr])) && seq!=0)){
                     
                     
                      if(prc==(noCols-1)){
                      if(years.size()!=0)
                            prc=2;
                           
                            begin=begin;
                            val=begin;
                      } else {
                     
                      begin=val;
                      if(years.size()!=0)
                      prc++;
                      
                      }
                     
                     
                      StringBuffer sbuffer = new StringBuffer(cmpny+" ");
                      sbuffer.append(year+" ");
                      sbuffer.append(mnth+" ");
                      sbuffer.append(maxVal);
                      
                      finalMap.put(seq, sbuffer);
                     
                     
                      maxVal=0;
                     
                      year = numbers[begin][yr];                           
                      price = numbers[begin][prc];
                      cmpny = numbers[yr][prc];
                      priceVal = Integer.parseInt(price);
                      if(priceVal>maxVal){
                            maxVal=priceVal;                  /// highest price value and month
                            mnth = numbers[begin][mn];
                      }
                     
                     
                     
                } else {
                      year = numbers[begin][yr];                     
                      price = numbers[begin][prc];
                      cmpny = numbers[yr][prc];
                      priceVal = Integer.parseInt(price);
                      if(priceVal>maxVal){
                            maxVal=priceVal;
                            mnth = numbers[begin][mn];
                      }
                     
                      
                      if(seq==(arrayLngth-1)){
                           
                            StringBuffer sbuffer = new StringBuffer(cmpny+" ");
                            sbuffer.append(year+" ");
                            sbuffer.append(mnth+" ");
                            sbuffer.append(maxVal);
                            
                            finalMap.put(seq, sbuffer);
                      }
                     
                }
         
          begin++;
          seq++;
          years.add(year);
          
          if(begin==rowsInFile){
               
                begin=val;
                prc++;
                years = new ArrayList<String>();
                
          }  
        }
      
       
       
       // result map will list the final expected output each company's highest share value for each year
               
       
    Map<Integer,StringBuffer> resultMap = new TreeMap<Integer,StringBuffer>(finalMap);
       Iterator it = resultMap.entrySet().iterator();             
       while(it.hasNext()){
             Map.Entry pairs= (Map.Entry)it.next();
            
             log.debug(pairs.getValue());
            
       }
    

    } catch (FileNotFoundException e) {
          e.printStackTrace();
    } catch (IOException e) {
          e.printStackTrace();
    } finally {
          if (br != null) {
                try {
                      br.close();
                } catch (IOException e) {
                      e.printStackTrace();
                }
          }
    }

    log.debug("Done");
}

 
                 
                  }