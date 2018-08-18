JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)
	java Assignment1

clean :
	rm -f *.class

%.class : %.java
	$(JAVAC) $<
