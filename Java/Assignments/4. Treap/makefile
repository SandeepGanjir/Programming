JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	rm -f *.class

run :
	rm -f *.class
	javac *.java
	java Assignment4
	dotty out.dot

%.class : %.java
	$(JAVAC) $<
