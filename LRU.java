import java.io.*;
class LRU
{
	public static void main(String args[]) throws IOException
	{
	flag=new int[20];
	c= new int[20];
	int n,m, z, i, j, hit, fault;
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	System.out.println("\nPlease enter the size of reference string\n");
	m=Integer.parseInt(br.readLine());
	ref_str= new int[m];
	System.out.println("Please enter the reference string\n");
	for(i=0; i<m; i++)
	{
		ref_str[i]=Integer.parseInt(br.readLine());
	}
	System.out.println("\nPlease enter the size of page frame\n");
	n=Integer.parseInt(br.readLine());
	page_frame=new int[n];		
	for(i=0; i<m; i++)
		flag[i] = 0;
	for(i=0; i<n; i++)
	{
		page_frame[i] = -1;
		c[i] = 0;
	}
		
	for(i=0; i<m; i++)
	{		
		for(j=0; j<n; j++)
		{
			if(ref_str[i] == page_frame[j])
			{
				flag[i] = 1;
				hit++;
				break;
			}
		}
		
		if(flag[i] == 0 && page_frame[n-1] == -1)
		{
			for(j=0; j<n; j++)
			{
				if(page_frame[j] == -1)
				{
					page_frame[j] = ref_str[i];
					fault++;
					flag[i] = 1;
					break;
				}
			}
		}
			
		if(flag[i] == 0)
		{
			z = find_max(c);
			page_frame[z] = ref_str[i];
			fault++;
			for(int j=0; j<n; j++)
				c[z] += 1;
			c[z] = 0;
		}
			
		//System.out.println("\nIteration %d : ", (i+1));
		for(j=0; j<n; j++)
			System.out.println(page_frame[j]);
		System.out.println("Hit :"+ hit);
		System.out.println("\nFault :"+ fault);
	}
	System.out.println("\nHit Ratio :"+ ((float)hit/(float)n));
	System.out.println("\nFault Ratio :"+((float)fault/(float)n));	
	}
}
	