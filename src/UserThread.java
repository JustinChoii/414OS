import java.io.*;
import javafx.application.Platform;
import java.util.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.text.Text;
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
        filename = "./inputs/USER" + Integer.toString(id);
        buffer = new StringBuffer();
        try{
            br = new BufferedReader(new FileReader(filename));
        }
        catch(Exception e){
            System.out.println("Error initializing UserThread.");

        }
    }

    public StringBuffer readLine() throws IOException{
        String line = br.readLine();
        if (line == null){
            return null;
        }
        this.buffer = new StringBuffer(line);
        return this.buffer;
    }
    public void run(){
        try{
            StringBuffer line = new StringBuffer();
            Main.u[this.userID-1].setFill(Color.GREEN);
            while ((line = this.readLine()) != null){
                if (line.toString().startsWith(".save")){
                    this.file_name = new StringBuffer(line.substring(6));
                    FileInfo info = new FileInfo();
                    this.current_disk = Main.DiskRM.request();
                    Main.d[this.current_disk].setFill(Color.GREEN);
                    info.diskNumber = this.current_disk;
                    Platform.runLater(() -> {
                        String temp = "Writing to DiskNumber: " + String.valueOf(info.diskNumber);
                        Main.tu[this.userID-1].setText(temp);
                            });
                    info.startingSector = Main.Disks[info.diskNumber].get_free_sector();
                    info.fileLength = 0;
                    line = this.readLine();
                    while (!line.toString().startsWith(".end")){
                        Main.Disks[info.diskNumber].write(info.startingSector + info.fileLength, line);
                        info.fileLength += 1;
                        line = this.readLine();
                    }
                    Main.DiskM.directoryManager.enter(this.file_name.toString(), info);
                    Main.DiskRM.release(info.diskNumber);
                    Main.d[this.current_disk].setFill(Color.RED);
                    Platform.runLater(() -> {
                        String temp = "Wrote to DiskNumber: " + String.valueOf(info.diskNumber) + "!";
                        Main.tu[this.userID-1].setText(temp);
                    });

                }
                if (line.toString().startsWith(".print")){
                    StringBuffer temp = new StringBuffer(line.substring(7));
                    Platform.runLater(() -> {
                        String temps = "Printing: " + temp.toString();
                        Main.tu[this.userID-1].setText(temps);
                    });
                    new PrintJobThread(temp).start();
                }
            }
            Main.u[this.userID-1].setFill(Color.RED);
            Platform.runLater(() -> {
            String temp = "Finished saving the file :)";
            Main.tu[this.userID-1].setText(temp);
            });


        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error UserThread::run()");
        }



    }


}