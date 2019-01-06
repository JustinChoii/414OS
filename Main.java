class Main{
	private static final int NUMBER_OF_USERS = 4;
	private static final int NUMBER_OF_DISKS = 2;
	private static final int NUMBER_OF_PRINTERS = 3;

	public static Disk disk1 = new Disk();
	public static Disk disk2 = new Disk();
	public static Disk[] Disks = {disk1, disk2};
	public static Printer printer1 = new Printer(1);
	public static Printer printer2 = new Printer(2);
	public static Printer printer3 = new Printer(3);
	public static Printer[] Printers = {printer1, printer2, printer3};
	public static UserThread User1 = new UserThread(1);
	public static UserThread User2 = new UserThread(2);
	public static UserThread User3 = new UserThread(3);
	public static UserThread User4 = new UserThread(4);
	public static UserThread[] Users = {User1, User2, User3, User4};


	public static final DiskManager DiskM = new DiskManager();
	public static final ResourceManager DiskRM = new ResourceManager(NUMBER_OF_DISKS);
	public static final ResourceManager PrinterRM = new ResourceManager(NUMBER_OF_PRINTERS);

    public static void main(String[] args){

    	User1.start();
    	User2.start();
    	User3.start();
    	User4.start();
  //   	Disk disk = new Disk();
		// StringBuffer s = new StringBuffer("lololol");
		// StringBuffer temp = new StringBuffer("xd");
  //   	try{
	 //    	Printer print = new Printer(1);
		// 	disk.write(0, s);
		// 	disk.write(1, temp);
		// 	disk.read(0, temp);
		// 	print.print(s);
		// 	print.print(temp);
	 //    }
	 //    catch(Exception e){

	 //    }
	 //    System.out.println(disk.sectors[0].toString());
		// System.out.println(temp.toString());
		// System.out.println(disk.get_free_sector());
    }
}