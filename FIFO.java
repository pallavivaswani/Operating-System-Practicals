import java.io.*;
public class FIFO {

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int f, p, num=0, hit = 0, fault = 0;
        int pages[];
        int frame[];
        boolean flag=true;
        
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
        {
            pages[i] = Integer.parseInt(br.readLine());
        }
        
        for(int i = 0; i < p; i++)
        {
         flag=true;
	int page=pages[i];
         for(int j = 0; j < f; j++)
         {
          if(frame[j] ==page)
          {
           flag=false;
		   //System.out.println("HIT");
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
        }
        fault=p-hit;
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/p));
        System.out.println("The number of Faults: " + fault);
		System.out.println("Fault Ratio: " + (float)((float)fault/p));
    }
    
}