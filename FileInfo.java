class FileInfo {
	int diskNumber;
	int startingSector;
	int fileLength;

	public FileInfo(){
		this.diskNumber = 0;
		this.startingSector = 0;
		this.fileLength = 0;
	}

	public void enter(int disknum, int sector, int filelen){
		this.diskNumber = diskNumber;
		this.startingSector = sector;
		this.fileLength = filelen;
	}
}
