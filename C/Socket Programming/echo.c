#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT "47531"				//Port must be greater than 1024
#define MAXBUFFER 1492				//Size of each Packet must not Exceed this

struct DGram
   {	int seqNo;
	long TStamp;
	int TTL;
   };

void Echo(int sokt,struct addrinfo *Res)	//Function which receives & sends Datagram
   {	struct DGram *RecievedMsg=malloc(MAXBUFFER);
	memset(RecievedMsg,0,MAXBUFFER);
	
	int Size = recvfrom(sokt,RecievedMsg,MAXBUFFER,0,Res->ai_addr,&Res->ai_addrlen);
	if (Size==-1) { printf("%s","Datagram recieve Failed"); return;}
	else if (Size==MAXBUFFER) warn("datagram too large for buffer: truncated");
	
	int tmp=RecievedMsg->TTL--;		//Decrementing TTL
	printf(".\b");	if(tmp==2) {printf(".");}
		
	if (sendto(sokt,RecievedMsg,Size,0,Res->ai_addr,Res->ai_addrlen)==-1)
	   { printf("%s","Datagram send Failed"); return;}
	free(RecievedMsg);
   }

 int main(void)
   {	struct addrinfo adInf, *Res;
	memset(&adInf,0,sizeof(adInf));
	adInf.ai_family=AF_INET;	//left unspecified so that both IPv4 and IPv6 addResses can be returned
	adInf.ai_socktype=SOCK_DGRAM;	//Setting Socket type as Datagram
	adInf.ai_protocol=0;
	adInf.ai_flags=AI_PASSIVE;
	
	int err=getaddrinfo(NULL,PORT,&adInf,&Res);
	if (err!=0) { printf("%s","Failed to Resolve remote socket addRess"); return;}

	//Opening a UDP Socket
	int sokt=socket(Res->ai_family,Res->ai_socktype,Res->ai_protocol);
	if (sokt==-1) { printf("%s","Failed to initialize a valid socket"); return;}
	if (bind(sokt,Res->ai_addr,Res->ai_addrlen)==-1) { printf("%s","Binding Failed"); return;}

	while(1) Echo(sokt,Res);	//Server must respond to any/all Received packets
	
	freeaddrinfo(Res);
	close(sokt); 
	return 0;
   }
