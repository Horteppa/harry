package com.pro.cons;

import java.util.List;
import java.util.ArrayList;
	


class Producer extends Thread { 
    static final int MAXQUEUE = 10; 
    private List<String> messages = new ArrayList<String>(); 

    public void run() { 
        try { 
            while ( true ) { 
                putMessage(); 
                //sleep( 1000 ); 
            } 
        }  
        catch( InterruptedException e ) { } 
    } 

    private synchronized void putMessage() 
        throws InterruptedException { 

        while ( messages.size() == MAXQUEUE ) 
            wait(); 
        messages.add( new java.util.Date().toString() ); 
        notify(); 
    } 

    // Called by Consumer 
    public synchronized String getMessage() 
        throws InterruptedException { 
        notify(); 
        while ( messages.size() == 0 ) 
            wait(); 
        String message = (String)messages.get(0); 
        messages.remove( message ); 
        return message; 
    } 
} 

class Consumer extends Thread { 
    Producer producer; 

    Consumer(Producer p) { 
        producer = p; 
    } 

    public void run() { 
        try { 
            while ( true ) { 
                String message = producer.getMessage(); 
                System.out.println("Got message: " + message); 
                sleep( 3000 ); 
            } 
        }  
        catch( InterruptedException e ) { } 
    } 

    
}


public class Prod_Coms_Demo{
	public static void main(String args[]) { 
        Producer producer = new Producer(); 
        producer.start(); 
        new Consumer( producer ).start(); 
    } 
}