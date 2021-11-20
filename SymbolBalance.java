/*Tiffany Neumann 
 * tjn2124
 * 
*/
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileNotFoundException;


public class SymbolBalance implements SymbolBalanceInterface{
   
    private String inString;
    private Scanner input;

    public void SymbolBalance(){
    }
    
    public void setFile(String filename) {
        try {
            File inFile = new File(filename);
            input = new Scanner(inFile);
        }
        catch(FileNotFoundException e){
            System.out.println("Please enter the correct argument type");
            System.out.println(e);
        }        
    }
    public BalanceError checkFile(){
        MyStack<Character> myStack = new MyStack<Character>();

        boolean skip = false;
        int lineNumber = 0;
        boolean prevQuote = false;
        int quoteLine = 0;
        

      
        while (input.hasNextLine()){
            lineNumber++;
            int index = 0; 
            int index1 = 0;
            
            inString = input.nextLine();
            for (int i = 0; i < inString.length(); i++){
                char currentSymbol = inString.charAt(i);
                char currentSymbol2 = 'j';//I set this to a random char to initialize it
                                          //before checking the string length condition below
                System.out.println(currentSymbol);

                
                if(i+1<inString.length()){
                    currentSymbol2 = inString.charAt(i+1);
                }
                if (currentSymbol=='/' && currentSymbol2=='*' && skip==false){
                    skip = true;
                }
                if(skip==true && currentSymbol=='*' && currentSymbol2 =='/'){
                    index1 = i;
                    skip = false;
                    }
                if(skip==false && currentSymbol=='*' && currentSymbol2 =='/' && myStack.isEmpty()==true && i!=index1){
                       return new EmptyStackError(lineNumber);
                }
//             
                if(skip==false && currentSymbol=='*' && currentSymbol2 =='/' && myStack.isEmpty()==false && i!=index1 ){
                    char popped = myStack.pop();
                    if(popped!='*'){
                        return new MismatchError(lineNumber, currentSymbol, popped);
                    }  
                }
//                
                if (currentSymbol=='{' && skip==false){
                    myStack.push(currentSymbol);
                    }
                
                if(currentSymbol=='}' && myStack.isEmpty() && skip==false){
                    return new EmptyStackError(lineNumber);
                    }
                
                if((currentSymbol=='}')&& skip==false){
                    char popped = myStack.pop();
                    if (popped!='{'){
                        return new MismatchError(lineNumber, currentSymbol, popped);
                    }
                }
                if (currentSymbol=='(' && skip==false){
                    myStack.push(currentSymbol);
                    }
                if(currentSymbol==')' && myStack.isEmpty() && skip==false){
                    return new EmptyStackError(lineNumber);
                    }
                if (currentSymbol==')' && skip==false){
                    char popped = myStack.pop();
                    if(popped!='('){
                        return new MismatchError(lineNumber, currentSymbol, popped);
                    }
                }
                if (currentSymbol=='[' && skip==false){
                    myStack.push(currentSymbol);
                }
                if(currentSymbol==']'&& skip==false && myStack.isEmpty()){
                    return new EmptyStackError(lineNumber);
                    }
                
                if((currentSymbol==']')&& skip==false ){
                    char popped = myStack.pop();
                    if(popped!='['){
                        return new MismatchError(lineNumber, currentSymbol, popped);
                    }
                }
                if (currentSymbol=='"' && skip==false && prevQuote==false){
                    index = i;
                    skip = true;
                    prevQuote = true;
                    myStack.push(currentSymbol);
                    
                }
                if(currentSymbol=='"' && myStack.isEmpty() && skip==false && prevQuote==false){
                    return new EmptyStackError(lineNumber);
                    }
                if(currentSymbol=='"' && i!=index && skip==true && prevQuote==true){
                     char popped = myStack.pop();
                     prevQuote = false;
                     if (popped=='"'){
                         skip=false;
                     }
                     else if(popped != '"'){
                            return new MismatchError(lineNumber, currentSymbol, popped);
                    }
                    }
                

        if((input.hasNext()==false) && (myStack.isEmpty()==false)){
            return new NonEmptyStackError(myStack.peek(), myStack.size());
        }
            
        }
        
        }
        System.out.println(myStack.peek());
        input.close();
        return null;
//     }
}
}














