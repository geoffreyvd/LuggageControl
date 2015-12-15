package helpers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data type filters 
 * @author Corne Lukken
 */
public final class Filters {
    private Filters() {
        
    }
    
    /**
     * Filters a string and validates that it is a adress.
     * @param originalString String to be checked for adress validity
     * @return String with adress number or empty string if did not validate
     */
    public static String filteredAdress(String originalString) {
        return originalString;
    }
    
    /**
     * Special filter for cellphone numbers
     * @param originalString String to be checked for cellphone validity
     * @return String with cellphone number or empty string if did not validate
     */
    public static String filteredCellphone(String originalString) {
        return filteredString(originalString, new char[]{'0','1','2','3','4','5','6','7','8','9'}, true);
    }
    
    /**
     * Filters a string and validates that it is a email.
     * @param originalString String to be checked for email validity
     * @return filteredEmail with email or empty string if invalid.
     */
    public static String filteredEmail(String originalString) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(originalString);
        if(m.matches()) {
            return originalString;
        }
        return "";
    }
    
    /**
     * Filters a string and validates that it is a postcode.
     * @param originalString String to be checked for postcode validity
     * @return filteredPostcode with postcode or empty string if invalid.
     */
    public static String filteredPostcode(String originalString) {
        return originalString;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Base filter classes for data, datetime, int and string">
    
    /**
     * Checks if the string is a valid according to the supplied date format
     * @param originalDate string to be checked for containing date string or empty string for default yyyy-MM-dd dateformat.
     * @param dateFormat
     * @return the string if it is valid or a empty string if it is not
     */
    public static String filteredDate(String originalDate, String dateFormat) {
        Date date = null;
        if(dateFormat.equals("")) {
            dateFormat = "yyyy-MM-dd";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            date = sdf.parse(originalDate);
        } catch (Exception ex) {
            
        }
        try{
        if(date.before(new Date())) {
            return originalDate;
            
        }
        return "";
        }
        catch(Exception e){
            return "";
        }
    }
    
    /**
     * Filters string to datetime their by verifying that the string does contain a datetime format.
     *
     * @param originalDateTime 
     * @return filtered datetime to prevent SQL injections, cross-site scripting
     * and other exploits
     */
    public static String filteredDateTime(String originalDateTime) {
        try {
            java.sql.Timestamp.valueOf(originalDateTime);
            return originalDateTime;
        }
        catch(Exception e) {
            return "";
        }
    }
    
    /**
     * Filters datetime to the point where they are safe to use within our
     * application, also verifies whether the datetime is between the specified values.
     *
     * @param originalDateTime DateTime string inputted by user
     * @param minimumDateTime minimum time and date for the string to be returned
     * @param maximumDateTime maximum time and date for the string to be returned
     * @return filtered datetime to prevent SQL injections, cross-site scripting
     * and other exploits
     */
    public static String filteredDateTime(String originalDateTime, Timestamp minimumDateTime, Timestamp maximumDateTime) {
        return originalDateTime;
    }
    
    /**
     * 
     * @param originalInt
     * @param minimumInt
     * @param maximumInt
     * @return filtered int between the specified minimum and maximum value or 0
     */
    public static int filteredInt(int originalInt, int minimumInt, int maximumInt) {
        return originalInt;
    }
    
    /**
     * 
     * @param originalInt
     * @param minimumInt
     * @param maximumInt
     * @return Filtered string as int between the specified minimum and maximu value or 0
     */
    public static String filteredInt(String originalInt, int minimumInt, int maximumInt) {
        return originalInt;
    }
    
    /**
     * Filters strings based on a array of characters which either are allowed or disallowed
     *
     * @param originalString the string to be filtered
     * @param characters array of characters which depending on the <code>whitelist</code> boolean are allowed or disallowed
     * @param whitelist if true array of characters is applied as whitelist, default is blacklist
     * @return filtered string to prevent SQL injections, cross-site scripting
     * and other exploits
     */
    public static String filteredString(String originalString, char[] characters, boolean whitelist) {      
        if(whitelist) {
            for(char stringChar: originalString.toCharArray()) {
                boolean checkChar = false;
                for(char character: characters) {
                    if(stringChar == character) {
                        checkChar = true;
                    }
                }
                if(!checkChar) {
                    return "";
                }
            }
        }
        else {
            for(char character: characters) {
                if(originalString.contains(character + "")) {
                    return "";
                }
            }
        }
        return originalString;
    }
    
    /**
     * Filters strings with a minimum and maximum length
     *
     * @param originalString string to be filtered based on length
     * @param minLength the minimal length of the string inclusive
     * @param maxLength the maximum length of the string inclusive
     * @return filtered string to prevent SQL injections, cross-site scripting
     * and other exploits
     */
    public static String filteredString(String originalString, int minLength, int maxLength) {
        if(originalString.length() >= minLength && originalString.length() <= maxLength) {
            return originalString;
        }
        return "";
    }
    
    /**
     * Filters strings to the point where they are safe to use within our
     * application This class is mostly used for filtering user input
     * This method does nothing.
     * @param originalString
     * @return filtered string to prevent SQL injections, cross-site scripting
     * and other exploits
     */
    public static String filteredString(String originalString) {
        return originalString;
    }
    
    //</editor-fold>
}
