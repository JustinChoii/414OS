import java.util.*;
import java.io.*;
class DirectoryManager {
	Hashtable<String, FileInfo> T;
	DirectoryManager(){
		T = new Hashtable<String, FileInfo>();
	}
	void enter(String key, FileInfo file){
		T.put(key, file);
	}
	FileInfo lookup(String key){
		return T.get(key);

	}
}
