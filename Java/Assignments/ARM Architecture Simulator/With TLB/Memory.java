class Memory
   {	L1 l1=new L1();	L2 l2=new L2(); TLB tlb=new TLB(32);

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
		    if(tlb.check(Addr))
		      if(flag) return 4; else return 3;
		    else return 5;
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

 class TLB
   {	int[][] tlb;	int Sz;
	TLB(int size) {tlb = new int[32][2]; Sz=size;}
	
	public boolean check(int Addr)
	   {	int tag = Addr >>> 12;
		for(int i=0; i<Sz; i++)
		 if(tlb[i][0]==tag) {prioritize(tag); return true;}
		insert(tag);
		return false;
	   }
	   
	void prioritize(int tag)
	   {	int i, idx=0, max=tlb[0][1];
		for(i=0; i<Sz; i++)
		  { if(tlb[i][0]==tag) idx=i;
		    if(tlb[i][1]>max) max=tlb[i][1];
		  }
		tlb[idx][1]=max+1; idx=max-Sz;
		if(max>Sz*8) for(i=0; i<Sz; i++) tlb[i][1] -= idx;
	   }
	
	void insert(int tag)
	   {	int i, min=0, max=tlb[0][1];
		for(i=1; i<Sz; i++)
		  { if(tlb[i][1]<tlb[min][1]) min=i;
		    if(tlb[i][1]>max) max=tlb[i][1];
		  }
		tlb[min][0]=tag; tag=tlb[min][1]; tlb[min][1]=max+1;
		if(max>Sz*8) for(i=0; i<Sz; i++) tlb[i][1] -= tag;
	   }
   }
