import java.io.*;
import java.util.*;

class Printer {			// extends Thread
	PrintWriter pw;
    File file;
    Printer(int id){
        try{

        	String filename = "PRINTER" + Integer.toString(id);
        	file = new File(filename);
    		pw = new PrintWriter(file);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    void print(StringBuffer b) throws InterruptedException{
        try {
            Thread.sleep(275);
            if (file.exists() == false){
                file.createNewFile();
            }
            try{
                pw.append(b.toString());
                pw.println();
                pw.flush();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    void close(){
    	
    }
}