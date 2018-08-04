#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <errno.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/time.h>

#define PORT "47531"				//Port must be greater than 1024
#define IP "10.136.0.218"			//IP of the server
#define MAXBUFFER 1492				//Size of each Packet must not Exceed this

int NoP=50;					//Number of Packets

struct DGram
   {	int seqNo;
	unsigned long TStamp;
	int TTL;
   };

int main(int NoArg,char* Args[])		//Takes command-line input
   {	if(NoArg!=4) printf(" Invalid Input!! Provide values of P, T and file_name respectively.");
	int P = atoi(Args[1]), T = atoi(Args[2]);
	if(T%2||T<2||P>MAXBUFFER||P<100) { printf("%s"," Invalid Input!! T not even"); exit(1);}
	
	struct addrinfo adInf, *Res;
	memset(&adInf,0,sizeof(adInf));
	adInf.ai_family=AF_INET;		//Sstting IP type to IPv4
	adInf.ai_socktype=SOCK_DGRAM;		//Setting Socket type as Datagram
	
	//Getting Address information
	int err=getaddrinfo(IP,PORT,&adInf,&Res);
	if (err!=0) { printf("Failed to Resolve remote socket addRess"); return;}

	//Opening a UDP Socket
	int sokt=socket(Res->ai_family,Res->ai_socktype,Res->ai_protocol);
	if (sokt==-1) { printf("Failed to open socket"); return;}

	FILE *file = fopen(Args[3],"w+");	//File to write data
	struct timeval SysTime;			//To store system time
	struct DGram Msg;
	char buffer[MAXBUFFER];			//Buffer to store received data
	long cRTT;
	int i;
	
	fprintf(file,"%s","Packet No.\tCumulative RTT\n");
	for(i=0; i<NoP; i++)
	  { gettimeofday(&SysTime,NULL);	//Recieving System Time
	  
	    //Putting packet information
	    Msg.seqNo=i,	Msg.TStamp=SysTime.tv_usec,	Msg.TTL=T;
	    
	    while(Msg.TTL>0)
	      {	if (sendto(sokt,&Msg,P,0,Res->ai_addr,Res->ai_addrlen)==-1)
		  { printf("%s","Datagram send Failed"); return;}
		
		recvfrom(sokt,&buffer,P,0,Res->ai_addr,&Res->ai_addrlen);
		//Storing the recieved packet in buffer
		memcpy(&Msg,buffer,sizeof(Msg));
		Msg.TTL--;
	      }
	    
	    gettimeofday(&SysTime,NULL);
	    cRTT = SysTime.tv_usec-Msg.TStamp;	//Cumulative Round Trip Time
	    fprintf(file,"%s%d%s%lu","\n   ",Msg.seqNo,"\t\t   ",cRTT);
	    printf(".");
	  }

	printf("\n");
	freeaddrinfo(Res);
	close(sokt); 
	return 0;
   }
