class Memory
   {	L1 l1=new L1();	L2 l2=new L2();

	int MAccess(int Addr,boolean WR)
	   {	
		if(l1.check(Addr)==0)
		  { if(WR) {l2.modify(Addr); return 1;}
		    else return 0;
		  }
		else if(l2.check(Addr)==2)
		  { l1.insert(Addr);
		    if(WR) l2.modify(Addr);
		    return 2;
		  }
		else
		  { boolean flag=WR && l2.modbit(Addr);
		    l1.insert(Addr); l2.insert(Addr,WR);
		    if(flag) return 4; else return 3;
		  }
	   }
   }

 class L1
   {	int [][][]cac = new int[2][512][2];
	int tag, ind;
	
	public int check(int mem)
	   {	int temp=mem;
		tag=mem>>>14; temp=mem<<18; ind=temp >>> 23;
		if(cac[1][ind][0]==tag) return 0;
		else if(cac[0][ind][0]==tag) return 0;
		else return 7;
	   }
	   
	public void insert(int mem)
	   {	int temp=mem;
		tag=mem>>> 14; temp=mem<< 18; ind=temp>>> 23;
		cac[0][ind][0]=cac[1][ind][0];
		cac[0][ind][1]=cac[1][ind][1];
		cac[1][ind][0]=tag;
		cac[1][ind][1]=mem;
	   }
   }

 class L2
   {	int [][][]cac=new int[8][2048][2];
	int [][]mod=new int[8][2048];
	int tag, ind, prio=0;

	public int check(int mem)
	   {	int temp=mem;
		tag=mem>>>18; temp=mem<<14; ind=temp>>>21;
		for (int i=0;i<8;i++) if(cac[i][ind][0]==tag) return 2;
		return 5;
	   }

	public void insert(int mem,Boolean rw)
	   {	int temp=mem;
		tag=mem>>>18; temp=mem<<14;
		ind=temp>>>21; prio=prio%8;
		if(rw) mod[prio][ind]=1;
		cac[prio][ind][0]=tag;
		cac[prio++][ind][1]=mem;
	   }

	public void modify(int mem)
	   {	int temp=mem;
		tag=mem>>>18; temp=mem<<14; ind=temp>>>21;
		for (int i=0;i<8;i++) 
		  if(cac[i][ind][0]==tag) {mod[i][ind]=1; break;}
	   }
	   
	public boolean modbit(int mem)
	   {	int temp=mem;
		tag=mem>>>18; temp=mem<<14; ind=temp>>>21;
		for (int i=0;i<8;i++) 
		  if(cac[i][ind][0]==tag)
		     if(mod[i][ind]==1) return true;
		     else return false;
		return false;
	   }
   }
