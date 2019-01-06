JFLAGS = -g
JC = javac
JVM= java
dir = src/

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(dir)*.java

CLASSES = \
	src/FileInfo.java \
	src/DirectoryManager.java \
	src/Disk.java \
	src/DiskManager.java \
	src/Printer.java \
	src/PrintJobThread.java \
	src/ResourceManager.java \
	src/UserThread.java \
	src/Main.java

MAIN = Main

default: classes
	java -cp src Main

classes: $(CLASSES:.java=.class)

run: $(JVM) src/Main