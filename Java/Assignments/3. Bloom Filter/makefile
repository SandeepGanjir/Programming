JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all :
	rm -f *.class
	javac *.java
	java Assignment3
	chmod +x test1.pg
	./test1.pg > test1.png

clean :
	rm -f *.class
	rm -f *.dat
	rm -f *.png

run :
	rm -f *.class
	javac *.java
	java Assignment3

%.class : %.java
	$(JAVAC) $<
