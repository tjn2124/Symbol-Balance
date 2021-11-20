/*Tiffany Neumann 
 * tjn2124
 * 
 * 
 * use a regular Java array to store data and resizes to a
 * larger array once it is full. 
 * Implement a stack in a class called MyStack that uses an array 
 * to store data and resizes the array when necessary.
 *  All methods should run in constant time, 
 * except when the array must be resized (this is still amortized constant time).

NOTE: Your class must implement MyStackInterface 
 */
import java.util.*;
import java.util.Arrays;

public class MyStack<T> implements MyStackInterface<T>{
    private T[] myArray;
    private int top;
    private int size;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public MyStack(){
        size = 0;
        capacity = 1;
        myArray =(T[]) (new Object[capacity]);
        top = -1;
    }
    
    public void push(T x){
        if (top+1==size()) {    //this would normally be an over flow but  
            adjustSize(2*(myArray.length));
        }
        myArray[++top] = x;
        size++;
//         System.out.println("item was pushed on stack @ index "+ top);
//         System.out.println("  size :" +size());
//         System.out.println("top of stack"+ peek());
    }
    
    public boolean isEmpty(){
        if(top==-1){
            return true;
        }
        else {
            return false;
        }
     }
    
    public T peek(){
        if (!isEmpty()){
            return myArray[top];}
        else {
            return null;
        }
     }

    public T pop(){
        if(isEmpty()){
            System.out.println("underflow error, no data" );
            return null;
        } 
        T prevTop = myArray[top];
        top--;
        size--;
        return prevTop;
    }
    
    public int size(){
        return size;
    }
    
    @SuppressWarnings("unchecked")
    public void adjustSize( int capacity2 )
    {
        capacity = capacity2;
        T[] newArray = (T[]) (new Object[capacity2]); 
        for( int i = 0; i < myArray.length; i++ ){
            newArray[ i ] = myArray[ i ];}
        myArray = newArray;
    }
    

}

    