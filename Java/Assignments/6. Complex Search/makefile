JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm -f *.class
	rm -f *~

run :
	rm -f *.class
	javac *.java
	java Assignment6 in.txt
	

%.class : %.java
	$(JAVAC) $<
