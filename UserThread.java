import java.io.*;
import java.util.*;
/*
if .save is encountered
    we must save the file name by doing substring or split.
    create a new fileinfo:  
        request disknumber from ResourceManager
        Request startingSector from the function we created in disk.
        Filelength will be updated one by one iteratively until we reach .end. Initially set to zero
    read nextline
    
    while the next line does not contain .end:
        Save the line to buffer -> appropriate sector (should be next_free_sector result)
        Print(UserID: saving to disk: buffer);
        Update file length += 1
        read next line
    // We should be here when .end is encountered.
    Send the file to diskmanager (directory manager)
    Release the job from the resource manager
if .print is encountered:
    Save the filename to a string
    PrintJobThread(filename).start();
    
    
    

FileInfo.diskNumber
FileInfo.startingSector
FileInfo.filelength
*/


class UserThread extends Thread {
	BufferedReader br;
	StringBuffer buffer;
    StringBuffer file_name;
	String filename;
    int userID;
    int current_disk = 0;
    public UserThread(int id){
        this.userID = id;
    	filename = "USER" + Integer.toString(id);
        buffer = new StringBuffer();
        try{
            br = new BufferedReader(new FileReader(filename));
        }
        catch(Exception e){
            System.out.println("Error initializing UserThread.");

        }
    }	

    public StringBuffer readLine() throws IOException{
        try {
            String line = br.readLine();
            if (line == null) {
                return null;
            }
            this.buffer = new StringBuffer(line);
            return this.buffer;
        }
        catch(Exception e){

        }
        return this.buffer;
    }
    public void run(){
        try{   
            StringBuffer line = new StringBuffer();
            while ((line = this.readLine()) != null){
                if (line.toString().startsWith(".save")){
                    this.file_name = new StringBuffer(line.substring(6));
                    FileInfo info = new FileInfo();
                    this.current_disk = Main.DiskRM.request();
                    info.diskNumber = this.current_disk;
                    info.startingSector = Main.Disks[info.diskNumber].get_free_sector();
                    info.fileLength = 0;
                    line = this.readLine();
                    while (!line.toString().startsWith(".end")){
                        Main.Disks[info.diskNumber].write(info.startingSector + info.fileLength, line);
                        info.fileLength += 1;
                        System.out.printf("UserID: %d,  %s\n", info.diskNumber, line.toString());
                        line = this.readLine();
                    }
                    Main.DiskM.directoryManager.enter(this.file_name.toString(), info);
                    Main.DiskRM.release(info.diskNumber);

                }
                if (line.toString().startsWith(".print")){
                    StringBuffer temp = new StringBuffer(line.substring(7));
                    new PrintJobThread(temp).start();
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error UserThread::run()");  
        }

        // try{
        //     String line = "";
        //     while((line = this.br.readLine()) != null){
        //         StringBuffer current_line = new StringBuffer();
        //         current_line.setLength(0);
        //         int count = 0;
        //         current_line.append(line);
        //         if (current_line.indexOf(".save") >= 0){
        //             this.file_name = new StringBuffer(current_line.substring(6));
        //             current_disk = Main.DiskRM.request();
        //             System.out.println("Saving file: " + file_name.toString() + " to disk num: " + current_disk);
               
        //             FileInfo info = new FileInfo();
        //             int start = 0;
        //             line = this.readLine().toString();
        //             current_line.setLength(0);
        //             current_line.append(line);
        //             while (line.contains(".end") == false){

        //                 info.diskNumber = current_disk; 
        //                 current_line.setLength(0);
        //                 current_line.append(line);
        //                 //System.out.println("lol: " + Main.Disks[info.diskNumber].next_free_sector);
        //                 start = Main.DiskM.get_free_sector(info.diskNumber);
        //                 info.startingSector = start;

        //                 Main.Disks[info.diskNumber].write(info.startingSector + count, this.buffer);
        //                 System.out.println("User" + userID + ": saving to disk file: " + this.file_name.toString() + "\n");
        //                 count += 1;
        //                 //System.out.println("count = " + count);
        //                 line = this.readLine().toString();
        //                 current_line.setLength(0);
        //                 current_line.append(line);
        //             }
        //             this.filename = this.file_name.toString();
        //             info.enter(current_disk, start, count);
        //             Main.DiskM.directoryManager.enter(this.filename, info);
        //             Main.DiskRM.release(info.diskNumber);
        //             line = this.br.readLine();
        //         }
        //         current_line.setLength(0);
        //         current_line.append(line);
        //         if (current_line.indexOf(".print") >= 0){
        //             this.filename = current_line.substring(7);
        //             this.file_name = new StringBuffer(filename);
        //             (new PrintJobThread(this.file_name)).start();
        //         }
        //     }
        // }
        // catch(Exception e){
        //     e.printStackTrace();
        //     System.out.println("Error UserThread::run()");
        // }


    }


}