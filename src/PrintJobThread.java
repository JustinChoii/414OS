import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.shape.*;

class PrintJobThread extends Thread{
    String filename;
    StringBuffer file_name;
    StringBuffer buffer = new StringBuffer();
    FileInfo info = new FileInfo();

    public PrintJobThread(StringBuffer name){
        this.file_name = new StringBuffer(name);
        this.filename = file_name.toString();
        this.info = Main.DiskM.directoryManager.lookup(filename);
    }
    public void run(){
        try{
            int printer_num = Main.PrinterRM.request();
            Main.p[printer_num].setFill(Color.GREEN);


            for (int i = 0; i < info.fileLength; i++){
                Platform.runLater(() ->{
                    Main.d[info.diskNumber].setFill(Color.GREEN);
                    Main.td[info.diskNumber].setText("Writing to Printer: " + String.valueOf(printer_num + 1));
                });
                Main.Disks[info.diskNumber].read(info.startingSector + i, buffer);
                Platform.runLater(() ->{
                    Main.d[info.diskNumber].setFill(Color.RED);
                    Main.td[info.diskNumber].setText("Wrote Printer: " + String.valueOf(printer_num + 1));
                });

                Main.Printers[printer_num].print(buffer);
                Platform.runLater(() ->{
                    Main.tp[printer_num].setText("Printed: " + buffer.toString());
                });
            }
            Main.p[printer_num].setFill(Color.RED);
            Platform.runLater(() ->{
                Main.tp[printer_num].setText("Finished Printing!");
            });
            Main.PrinterRM.release(printer_num);
        }

        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error running PrintJobThread");
        }
    }
}