JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm -f *.class
	rm -rf *_ii.txt

run :
	rm -f *.class
	rm -rf *_ii.txt
	javac *.java
	java Assignment5 in.txt
	

%.class : %.java
	$(JAVAC) $<
