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

			for (int i = 0; i < info.fileLength; i++){
				Main.Disks[info.diskNumber].read(info.startingSector + i, buffer);
				Main.Printers[printer_num].print(buffer);
				System.out.printf("PrinterID: %d, 	%s\n", printer_num, buffer.toString());
			}
			Main.PrinterRM.release(printer_num);
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error running PrintJobThread");
		}
	}
}