/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algebra.project.utils;

/**
 *
 * @author Kiki
 */
public class StringUtils {
   
    private StringUtils() {}
    
    public static String charToString(char[] charArray) {
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < charArray.length; i++) {
            sb.append(charArray[i]);
        }
 
        return sb.toString();
    }
}
