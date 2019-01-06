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
    	Thread.sleep(200);
    }
	void read(int sector, StringBuffer data) throws InterruptedException{
		Thread.sleep(200);
		data.setLength(0);
		data.append(this.sectors[sector]);
	}


	public static void main(String[] args){
		Disk disk = new Disk();
		StringBuffer s = new StringBuffer("lololol");
		StringBuffer temp = new StringBuffer("xd");
		StringBuffer random = new StringBuffer();
		try{
			disk.write(0, s);
			disk.write(1, temp);
			disk.read(0, random);

		}
		catch(InterruptedException e){

		}
		System.out.println(random.toString());
		// System.out.println(disk.sectors[0].toString());
		// System.out.println(temp.toString());

		// System.out.println(disk.get_free_sector());
	
	}
}