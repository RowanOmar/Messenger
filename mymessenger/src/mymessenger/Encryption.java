/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymessenger;

/**
 *
 * @author Rawan
 */
public class Encryption {
    public static String Encrypt(String word)
{
    String  result=" ";
    for(int i=0;i<word.length();i++)
    {
    result+=(char)(word.charAt(i)+20);
    
    }
return result;

}
    
    public static String decrypt( String word)
{
    
   String result=" ";
   
   
for(int i=0;i<word.length();i++)
{
result+=(char)(word.charAt(i)-5-15);
}
  return result;  
    }
    
}
