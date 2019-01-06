import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.scene.shape.*;

class Disk{
    static final int NUM_SECTORS = 1024;
    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
    public int next_free_sector;

    public Disk(){
        this.next_free_sector = 0;
        for (int i = 0; i < 1024; i++){
            this.sectors[i] = new StringBuffer();
        }
    }

    int get_free_sector(){
        return next_free_sector;
    }

    void write(int sector, StringBuffer data) throws InterruptedException{
        this.sectors[sector] = data;
        this.next_free_sector = sector + 1;
        Thread.sleep((long)((double)200 * Main.newSpeed));
    }
    void read(int sector, StringBuffer data) throws InterruptedException{
        Thread.sleep((long)((double)200 * Main.newSpeed));
        data.setLength(0);
        data.append(this.sectors[sector]);
    }

}