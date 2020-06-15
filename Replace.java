import java.io.*;
import java.util.*;
class Replace
{
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	int f, p, pos=0, num=0, hit = 0, fault = 0;
    int pages[], frame[], temp[];
    boolean flag=true;
	public void fifo() throws IOException
	{
		System.out.println("Please enter the number of frame: ");
        f = Integer.parseInt(br.readLine());
        System.out.println("Please enter the number of pages: ");
        p = Integer.parseInt(br.readLine());
        pages = new int[p];
        frame = new int[f];
        for(int i = 0; i < f; i++)
                frame[i] = -1;
        System.out.println("Please enter the reference string: ");
        for(int i = 0; i < p; i++)
            pages[i] = Integer.parseInt(br.readLine());
		for(int i = 0; i < p; i++)
        {
			flag=true;
			int page=pages[i];
			 for(int j = 0; j < f; j++)
			 {
				  if(frame[j] ==page)
				  {
				   flag=false;
				   hit++;
				   break;
				  } 
			}
			 if(num==f)
			 {
			   num= 0;
			 }
			 if(flag)
			 {
				frame[num]=page;
				num++;
			 }
				System.out.println("Frame: ");
				for(int k = 0; k < f; k++)
					System.out.print(frame[k]+" ");
				System.out.println();
				fault=p-hit;
				System.out.println("The number of hit: " + hit);
				System.out.println("The number of fault: " + fault);
		}		
        System.out.println("\n Hit Ratio: " + (float)((float)hit/p));
		System.out.println("Fault Ratio: " + (float)((float)fault/p));
	}
	public void lru() throws IOException
	{
		boolean isFull=false;
		int t;
		 ArrayList<Integer> stack = new ArrayList<Integer>();
		System.out.println("Enter number of frame: ");
		f=Integer.parseInt(br.readLine());
		frame=new int[f];

		System.out.println("Enter number of pages: ");
		p=Integer.parseInt(br.readLine());
		pages=new int[p];
		temp=new int[p];
		System.out.println("Enter page reference string: ");
		for(int i = 0; i < p; ++i)
			pages[i]=Integer.parseInt(br.readLine());
		for(int i = 0; i < f; ++i)
			frame[i] = -1;
		for(int i=0;i<p;i++)
		{
			if(stack.contains(pages[i]))
			{
				stack.remove(stack.indexOf(pages[i]));
			}
			stack.add(pages[i]);
			int search=-1;
			for(int j=0; j<f ;j++)
			{
				if(frame[j]==pages[i])
				{
					search=j;
					hit++;
					break;
				}
			}
			if (search==-1)
			{	
				if(isFull)
				{
					int min=p;
					for(int j=0; j<f ;j++)
					{
						if(stack.contains(frame[j]))
						{	
							t = stack.indexOf(frame[j]);
							if(t<min)
							{
								min=t;
								pos=j;
							}
						}
					}
				}
				frame[pos]=pages[i];
				fault++;
				pos++;
				if(pos==f)
				{
					pos=0;
					isFull=true;
				}
			}
			System.out.print("\n");
			System.out.println("Frame: ");
			for(int k=0;k<f;k++)
				System.out.print(frame[k]+" ");
			System.out.println();
			System.out.println("The number of hit: " + hit);
			System.out.println("The number of fault: " + fault);	
		}
		System.out.println("\n Hit Ratio: " + (float)((float)hit/p));
		System.out.println("Fault Ratio: " + (float)((float)fault/p));
	}
	public void optimal() throws IOException
	{
		int flag1, flag2, flag3, max;
		System.out.println("Enter number of frame: ");
		f=Integer.parseInt(br.readLine());
		frame=new int[f];
		System.out.println("Enter number of pages: ");
		p=Integer.parseInt(br.readLine());
		pages=new int[p];
		temp=new int[p];
	    System.out.println("Enter page reference string: ");
		for(int i = 0; i < p; ++i)
			pages[i]=Integer.parseInt(br.readLine());
		for(int i = 0; i < f; ++i)
			frame[i] = -1;
		for(int i = 0; i < p; ++i)
		{
			flag1 = flag2 = 0;
			for(int j = 0; j < f; ++j)
			{
				if(frame[j] == pages[i])
				{
					   flag1 = flag2 = 1;
					   break;
				}
			}
			if(flag1 == 0)
			{
				for(int j = 0; j < f; ++j)
				{
					if(frame[j] == -1)
					{
						fault++;
						//System.out.print("\t\t FAULT");
						frame[j] = pages[i];
						flag2 = 1;
						break;
					}
				}    
			}
			if(flag2 == 0)
			{
				flag3 =0;
				
				for(int j = 0; j < f; ++j){
					temp[j] = -1;
					
					for(int k = i + 1; k < p; ++k)
					{
						if(frame[j] == pages[k])
						{
							temp[j] = k;
							break;
						}
					}
				}
				for(int j = 0; j < f; ++j)
				{
					if(temp[j] == -1)
					{
						pos = j;
						flag3 = 1;
						break;
					}
				}
				
				if(flag3 ==0)
				{
					max = temp[0];
					pos = 0;
					for(int j = 1; j < f; ++j)
					{
						if(temp[j] > max)
						{
							max = temp[j];
							pos = j;
						}
					}            	
				}
				frame[pos] = pages[i];
				fault++;
			}
				System.out.print("\n");
				System.out.println("Frame: ");
				for(int k = 0; k < f; k++)
					System.out.print(frame[k]+" ");
				System.out.println();
				System.out.println("The number of hit: " + (p-fault));
				System.out.println("The number of fault: " + fault);	
		}
		hit=p-fault;
		System.out.println("\n Hit Ratio: " + (float)((float)hit/p));
		System.out.println("Fault Ratio: " + (float)((float)fault/p));
	}
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ch;
		Replace obj=new Replace();
		do
		{
		System.out.println("1. FIFO page replacement\n2. LRU page replacement\n3. Optimal Page replacement\n4. Exit\nEnter your choice: ");
		ch=Integer.parseInt(br.readLine());
		switch(ch)
		{
			case 1: obj.fifo();
					break;
			case 2: obj.lru();
					break;
			case 3: obj.optimal();
					break;
			case 4: break;
		}
		}while(ch!=4);
	}
}
