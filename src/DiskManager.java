import java.util.*;
import java.io.*;

class DiskManager {
    DirectoryManager directoryManager;

    public int get_free_sector(int temp){
        return Main.Disks[temp].get_free_sector();
    }
    DiskManager(){
        directoryManager = new DirectoryManager();
    }

}