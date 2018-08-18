JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)
	java Main

clean :
	rm -f *.class
	rm -f Manifest*
	rm -f *~	
jar :
	echo Main-Class: Main > Manifest.mf
	javac *.java
	jar cfm Hw-3.jar Manifest.mf *.class
	rm -f *.class
	rm -f Manifest*
	java -jar Hw-3.jar

%.class : %.java
	$(JAVAC) $<
