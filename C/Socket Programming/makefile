all : 
	rm -f *.out
	rm -f *.txt
	gcc client.c -o client.out

ttl2:client.out 
	./client.out 100 2 dat2_100.txt
	./client.out 200 2 dat2_200.txt
	./client.out 300 2 dat2_300.txt
	./client.out 400 2 dat2_400.txt
	./client.out 500 2 dat2_500.txt
	./client.out 600 2 dat2_600.txt
	./client.out 700 2 dat2_700.txt
	./client.out 800 2 dat2_800.txt
	./client.out 900 2 dat2_900.txt
	./client.out 1000 2 dat2_1000.txt

ttl8:client.out 
	./client.out 100 8 dat8_100.txt
	./client.out 200 8 dat8_200.txt
	./client.out 300 8 dat8_300.txt
	./client.out 400 8 dat8_400.txt
	./client.out 500 8 dat8_500.txt
	./client.out 600 8 dat8_600.txt
	./client.out 700 8 dat8_700.txt
	./client.out 800 8 dat8_800.txt
	./client.out 900 8 dat8_900.txt
	./client.out 1000 8 dat8_1000.txt

ttl16:client.out 
	./client.out 100 16 dat16_100.txt
	./client.out 200 16 dat16_200.txt
	./client.out 300 16 dat16_300.txt
	./client.out 400 16 dat16_400.txt
	./client.out 500 16 dat16_500.txt
	./client.out 600 16 dat16_600.txt
	./client.out 700 16 dat16_700.txt
	./client.out 800 16 dat16_800.txt
	./client.out 900 16 dat16_900.txt
	./client.out 1000 16 dat16_1000.txt
	
clean:
	rm -f *.out
	rm -f *.txt
	rm -f *~
