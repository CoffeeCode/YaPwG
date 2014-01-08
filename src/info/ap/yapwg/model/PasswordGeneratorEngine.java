package info.ap.yapwg.model;

import java.util.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

public class PasswordGeneratorEngine {

	//private char{} savedPasswd;
    private String chars = "abcdefghijklmnopqrstuvwxyz";
    private String charsUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String special = "!§$%&/()=?*+#-_.:,;<>€|{}{}~@";
    private String numbers = "01234567890123456789";
    private String leftHandChars = "qwertasdfgxcvbyz";
    private String rightHandSpecial = "?=+-(),.;*";
    private String rightHandChars = "uiophjklbnm";
    private String leftHandSpecial = "!$%";
    private String vowels = "aeiou";
    private String consonant ="bcdfghjklmnpqrstvwxyz";
    

    public String simplePW(int length, Boolean specialChar, Boolean num, Boolean caseSensitive ){
    	
    	String passwd = "";
    	String charsToUse = "";
    	String randomChar;

    	if(specialChar == false && num == false && caseSensitive == false) //000
    		charsToUse = chars;
    	if(specialChar == false && num == false && caseSensitive == true) //001
    		charsToUse = chars.concat(charsUppercase);
    	if(specialChar == false && num == true && caseSensitive == false) //010
    		charsToUse = chars.concat(numbers);
    	if(specialChar == false && num == true && caseSensitive == true){ //011
    		charsToUse = chars.concat(numbers);
    		charsToUse = charsToUse.concat(charsUppercase);}
    	if(specialChar == true && num == false && caseSensitive == false) //100
    		charsToUse = chars.concat(special);
    	if(specialChar == true && num == false && caseSensitive == true){ //101
    		charsToUse = chars.concat(special);
    		charsToUse = charsToUse.concat(charsUppercase);	}
    	if(specialChar == true && num == true && caseSensitive == false){ //110
    		charsToUse = chars.concat(special);
    		charsToUse = charsToUse.concat(numbers);}
    	if(specialChar == true && num == true && caseSensitive == true){ //111
    		charsToUse = chars.concat(numbers);
    		charsToUse = charsToUse.concat(special);
    		charsToUse = charsToUse.concat(charsUppercase);}
    	
        for (int i = 0; i < length; i++) {
        	
            randomChar = (String) Character.toString(charsToUse.charAt((int) (Math.random() * charsToUse.length())));
            passwd += randomChar;
          }
    
        
    	return passwd;
    }

    
    public String legiblePW(int length, Boolean specialChar, Boolean num, Boolean caseSensitive){
    	
    	String passwd ="";
    	String charsToUse = "";
    	String randomVowel;
    	String randomConsonant;    	
    	
    	if(specialChar == false && num == false && caseSensitive == false) //000
    		charsToUse = chars;
    	if(specialChar == false && num == false && caseSensitive == true) //001
    		charsToUse = chars.concat(charsUppercase);
    	if(specialChar == false && num == true && caseSensitive == false) //010
    		charsToUse = chars.concat(numbers);
    	if(specialChar == false && num == true && caseSensitive == true){ //011
    		charsToUse = chars.concat(numbers);
    		charsToUse = charsToUse.concat(charsUppercase);}
    	if(specialChar == true && num == false && caseSensitive == false) //100
    		charsToUse = chars.concat(special);
    	if(specialChar == true && num == false && caseSensitive == true){ //101
    		charsToUse = chars.concat(special);
    		charsToUse = charsToUse.concat(charsUppercase);	}
    	if(specialChar == true && num == true && caseSensitive == false){ //110
    		charsToUse = chars.concat(special);
    		charsToUse = charsToUse.concat(numbers);}
    	if(specialChar == true && num == true && caseSensitive == true){ //111
    		charsToUse = chars.concat(numbers);
    		charsToUse = charsToUse.concat(special);
    		charsToUse = charsToUse.concat(charsUppercase);}    	
    	    	
    	boolean startWithVowel = getRandomBoolean();    	
    	    	
    	for (int i = 0; i < length; i++){    		
    		randomVowel = Character.toString(vowels.charAt((int) (Math.random()* vowels.length())));
    		randomConsonant = Character.toString(charsToUse.charAt((int) (Math.random()* charsToUse.length())));
    		
        	if(startWithVowel){
        		passwd += randomVowel;
        	}
        	else{
        		passwd += randomConsonant;	
        	}
        	startWithVowel = !startWithVowel;
    			    		
    	}
    	
    	return passwd;
    	
    }
    
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
    
    
    public int pwEntropy(String password){
    	int fullCharSetSize = 0;
    	
    	int charSetSize = 26;    	
    	int upperCharSetSize= 26;
    	int integerSetSize = 10;
    	int symbolsSetSize = 30;
    	
    	boolean containsDigit = false, containsLowerCase = false, containsUpperCase = false, containsSymbol = false;
    	
    	char c;
    	
    	for(int i=0;i<password.length(); i++){
    		c = password.charAt(i);
    		
    		if(Character.isDigit(c))
    			containsDigit= true;
    		if(Character.isLowerCase(c))
    			containsLowerCase = true;
    		if(Character.isUpperCase(c))
    			containsUpperCase = true;
    		if(!Character.isDigit(c) && Character.isLowerCase(c) && Character.isUpperCase(c))
    			containsSymbol = true;
    	}
    	
    	
    	if(containsLowerCase)
    		fullCharSetSize += 26;//  fullCharSetSize + charSetSize;
    	
    	if(containsUpperCase)
    		fullCharSetSize += 26; //fullCharSetSize + integerSetSize;
    	
    	if(containsDigit)
    		fullCharSetSize += 10; //fullCharSetSize +  upperCharSetSize;
    	
    	if(containsSymbol)
    		fullCharSetSize +=  30;//fullCharSetSize + symbolsSetSize;  	
    	
        	
    	return (int) (password.length()*(Math.log(fullCharSetSize)/Math.log(2.0)));    	
    }
    
	public int checkPasswordStrength(String password) {
		
		
		char tempChar;
		String tempString;
		char[] charArray = password.toCharArray();		
		
		int strengthPercentage=0;
		
		 
	    //Number of Characters
		int n = password.length();
	    int numberOfSymbols = doesContain(password, special);
        int numberOfChars = doesContain(password, chars);
        int numberOfCharsUppercase = doesContain(password, charsUppercase);
        int numberOfNumbers = doesContain(password, numbers);
        int numberOfSymbolsAndNumbersInMiddle = doesContain(password.substring(1, password.length()-1), numbers) + doesContain(password.substring(1, password.length()-1), special);
        
        
        boolean lettersOnlyBool = Pattern.matches("[a-zA-Z]+", password);
        int lettersOnly=0;
		if(lettersOnlyBool)
        		lettersOnly = n;
        boolean numbersOnlyBool = Pattern.matches("[0-9]+", password);
        int numbersOnly=0;
		if(numbersOnlyBool)
        		numbersOnly = n;     
		
        int numberRepeatChars = countRepeating(password) - password.length();
        
        
        int consecutiveNumbers = 0;
        Boolean firstNumber = false;            
        int consecutiveChars = 0;
        Boolean firstChar= false;
        int consecutiveCharsUppercase = 0;
        Boolean firstUpperChar = false;
        
       
        for(int i = 0 ; i< charArray.length; i++){
        	if(Pattern.matches("[a-z]+", String.valueOf(charArray[i]))){
        		if(firstChar == true)
        			consecutiveChars++;
        		firstChar = true;
        	}else{
        		firstChar = false;
        	}        	
        	if( Pattern.matches("[0-9]+", String.valueOf(charArray[i]))){
        		if(firstNumber == true)
        			consecutiveNumbers++;
        		firstNumber = true;
        	}else{
        		firstNumber = false;
        	}
        	if( Pattern.matches("[A-Z]+", String.valueOf(charArray[i]))){
        		if(firstUpperChar == true)
        			consecutiveCharsUppercase++;
        		firstUpperChar = true;
        	}else{
        		firstUpperChar = false;
        	}
        }
         
            
        
        strengthPercentage = 	(n * 4)+        					 	
        					 	((n-numberOfChars)*2)+
        					 	((n-numberOfCharsUppercase)*2)+
        					 	((n- numberOfSymbols)*2)+
        					 	(numberOfNumbers*4)+
        					 	(numberOfSymbols*6)+
        					 	(numberOfSymbolsAndNumbersInMiddle*2)
        					 	-
        					 	numbersOnly-
        					 	lettersOnly-
        					 	numberRepeatChars * 2-
        					 	(consecutiveNumbers*2)-
        					 	(consecutiveChars*2)-
        					 	(consecutiveCharsUppercase*2)
        					 	;             
        
	    return strengthPercentage;


	}
	
	
	public int countRepeating(String string){
		
		
		int count=0, length=0, score=0;
        do
        {  
          try
          {
          char name[]=string.toCharArray();
              length=name.length;
              count=0;
              for(int j=0;j<length;j++)
               {
                  if((name[0]==name[j])&&((name[0]>=33&&name[0]<=127))) 
                      count++;
               }
              if(count!=0)
                System.out.println(name[0]+" "+count+" Times");
              string=string.replace(""+name[0],"");  
              score+=count;
          }
          catch(Exception ex){}
        }
        while(length!=1);			
        
		return score;
	}
	
	
	
	
	// Checks a string for a list of characters
	public int doesContain(String strPassword,String strCheck)
	 {
	        int nCount = 0;	 
	        for (int i = 0; i < strPassword.length(); i++)
	        {
	                if (strCheck.indexOf(strPassword.charAt(i)) > -1)
	                        nCount++;
	        }	 
	        return nCount;
	}    
   
}
