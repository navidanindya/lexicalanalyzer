
package lexicalanalyzer;

/**
 *
 * @author Navid Anindya
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalyzer {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {        
        //Take code input from file
        Scanner reader = new Scanner(new File("input.txt"));        
        
        //ArrayList for lexemes and HashTable for the Symbol Table
        ArrayList<String> lines = new ArrayList<>();
        Map<String, List<String>> symbolTable = new HashMap<String, List<String>>();
        
        //Reads every line and then splits everything into lexemes
        while (reader.hasNextLine()) {
            String str = reader.nextLine(); //Reads the lines
            if (!(str.length() == 0)) {     //If no blank lines detected, then continue
                String [] strSplit = str.trim().split("\\s+|\\s*,\\s*|\\;+|\\\"+|\\:+|\\[+|\\]+");     //Regex for splitting the code into lexemes          
                
                List <String> list = Arrays.asList(strSplit);       //Save the lexeme array into a Container List
                lines.addAll(list);                                 //Add the Container List to the ArrayList or HashTable
            }
        }
        
        //Add values from ArrayList to HashMap Key keywords
        List<String> keywords = new ArrayList<String>();        
        if (lines.contains("int") || lines.contains("float") || lines.contains("if") || lines.contains("else")) {         
            if (lines.contains("int")) {
                int index = 0;
                index = lines.indexOf("int");   
                keywords.add(lines.get(index));
            }  
            if (lines.contains("float")) {
                int index = 0;
                index = lines.indexOf("float");  
                keywords.add(lines.get(index));
            }  
            if (lines.contains("if")) {
                int index = 0;
                index = lines.indexOf("if");  
                keywords.add(lines.get(index));
            }  
            if (lines.contains("else")) {
                int index = 0;
                index = lines.indexOf("else");   
                keywords.add(lines.get(index));
            }            
        }        
        
        //Put the ArrayLists in HashMap for particular Keys
        symbolTable.put("Keywords", keywords);
        
        //Add values from ArrayList to HashMap Key keywords
        List<String> operators = new ArrayList<String>();        
        if (lines.contains("=") || lines.contains("-") || lines.contains("+") || lines.contains("*")) {         
            if (lines.contains("=")) {
                int index = 0;
                index = lines.indexOf("=");   
                operators.add(lines.get(index));
            }  
            if (lines.contains("-")) {
                int index = 0;
                index = lines.indexOf("-");  
                operators.add(lines.get(index));
            }  
            if (lines.contains("+")) {
                int index = 0;
                index = lines.indexOf("+");  
                operators.add(lines.get(index));
            }  
            if (lines.contains("*")) {
                int index = 0;
                index = lines.indexOf("*");   
                operators.add(lines.get(index));
            }            
        }        
        
        //Put the ArrayLists in HashMap for particular Keys
        symbolTable.put("Math Operators", operators); 
        
        //Add values from ArrayList to HashMap Key keywords
        List<String> logical = new ArrayList<String>();        
        if (lines.contains("<") || lines.contains(">") || lines.contains("<=") || lines.contains(">=")) {         
            if (lines.contains("<")) {
                int index = 0;
                index = lines.indexOf("<");   
                logical.add(lines.get(index));
            }  
            if (lines.contains(">")) {
                int index = 0;
                index = lines.indexOf(">");  
                logical.add(lines.get(index));
            }  
            if (lines.contains("<=")) {
                int index = 0;
                index = lines.indexOf("<=");  
                logical.add(lines.get(index));
            }  
            if (lines.contains(">=")) {
                int index = 0;
                index = lines.indexOf(">=");   
                logical.add(lines.get(index));
            }            
        }        
        
        //Put the ArrayLists in HashMap for particular Keys
        symbolTable.put("Logical Operators", logical);          
        
        //Convert the array list into an array
        String [] linesArray = lines.toArray(new String [0]);
        
        //Add values from ArrayList to HashMap Key keywords
        List<String> digits = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\d+|\\d+\\.\\d+")) {  //Use regex here for numbers                 
                digits.add(linesArray[count]);                       
            }        
        }
        
        //Put the ArrayLists in HashMap for particular Keys
        symbolTable.put("Numerical Values", digits);
        
        //Add values from ArrayList to HashMap Key keywords
        List<String> identifiers = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\w+") && !linesArray[count].matches("\\d+") && !linesArray[count].matches("int|float|if|else")) {  //Use regex here for variables  
                if (!identifiers.contains(linesArray[count])) {
                    identifiers.add(linesArray[count]);    
                }
            }        
        }
        
        //Put the ArrayLists in HashMap for particular Keys
        symbolTable.put("Identifiers", identifiers);
        
        //Add values from ArrayList to HashMap Key keywords
        List<String> others = new ArrayList<String>();     
        for (int count = 0;  count < linesArray.length; count++) {
            if (linesArray[count].matches("\\(|\\)|\\{|\\}")) {  //Use regex here for variables  
                if (!identifiers.contains(linesArray[count])) {
                    others.add(linesArray[count]);    
                }
            }        
        }
        
        //Put the ArrayLists in HashMap for particular Keys
        symbolTable.put("Others", others);
        
        //Prints the ArrayList
        System.out.print("The Lexemes: ");
        System.out.println(lines);  
        
        System.out.println();
        
        //Prints the Symbol Table
        for (Map.Entry<String, List<String>> entry : symbolTable.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.print(key + ": ");
            System.out.println(values);
        }
    }
}
