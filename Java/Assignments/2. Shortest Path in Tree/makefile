JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)
	java Assignment2 input.txt

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<
