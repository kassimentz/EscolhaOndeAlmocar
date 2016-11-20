/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kassi
 */
public class Utils {
    
    public static String dateToString (Date data) {
    
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        if(data != null){
            return df.format(data.getTime());
        } else {
            return null;
        }
        
    }
    
    public static String timeToString (Date data) {
    
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        
        if(data != null){
            return df.format(data.getTime());
        } else {
            return null;
        }
        
    }
    
    public static String dateToStringOnlyDate (Date data) {
    
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        if(data != null){
            return df.format(data.getTime());
        } else {
            return null;
        }
        
    }
    
    public static Date stringToDate (String data) { 
        
        if (data == null || data.equals("")) {
                return null;
        }
        
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            e.getMessage();
        }
            
        return date;
    }
    
    public static Date stringToTime (String hora) { 
        
        if (hora == null || hora.equals("")) {
                return null;
        }
        
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            date = (java.util.Date)formatter.parse(hora);
        } catch (ParseException e) {            
            e.getMessage();
        }
            
        return date;
    }
    
    public static Boolean compareDates(String date1, String date2) {
        
        Boolean result = false;
        
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date data1 = null;
        Date data2 = null;
        try {
            data1 = new Date(format.parse(date1).getTime());
            data2 = new Date(format.parse(date2).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(data1.equals(data2)){
            System.out.println("Data: " + date1 + " é igual à " + date2);
            result = true;
        
        }else{
            System.out.println("Data: " + date1 + " é diferente à " + date2);
            result = false;
        }
        
        return result;
    
    }
    
   
}
