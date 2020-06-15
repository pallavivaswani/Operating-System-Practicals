import java.io.*;
public class Optimal
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int f, pointer = 0, hit = 0, fault = 0,p;
        boolean flag = false;
        int frame[];
        int page[];
        int mem_layout[][];
        
        System.out.println("Please enter the number of f: ");
        f = Integer.parseInt(br.readLine());
        
        System.out.println("Please enter the length of the page string: ");
        p = Integer.parseInt(br.readLine());
        
        page = new int[p];
        mem_layout = new int[p][f];
        frame = new int[f];
		
        for(int i = 0; i < f; i++)
                frame[i] = -1;
        
        System.out.println("Please enter the page string: ");
        for(int i = 0; i < p; i++)
        {
            page[i] = Integer.parseInt(br.readLine());
        }
        System.out.println();
        for(int i = 0; i < p; i++)
        {
         int search = -1;
         for(int j = 0; j < f; j++)
         {
          if(frame[j] == page[i])
          {
           search = j;
           hit++;
           break;
          } 
         }
         if(search == -1)
         {
          if(flag)
          {
           int index[] = new int[f];
           boolean index_flag[] = new boolean[f];
           for(int j = i + 1; j < p; j++)
           {
            for(int k = 0; k < f; k++)
            {
             if((page[j] == frame[k]) && (index_flag[k] == false))
             {
              index[k] = j;
              index_flag[k] = true;
              break;
             }
            }
           }
           int max = index[0];
           pointer = 0;
           if(max == 0)
            max = 200;
           for(int j = 0; j < f; j++)
           {
            if(index[j] == 0)
             index[j] = 200;
            if(index[j] > max)
            {
             max = index[j];
             pointer = j;
            }
           }
          }
          frame[pointer] = page[i];
          fault++;
          if(!flag)
          {
           pointer++;
              if(pointer == f)
              {
               pointer = 0;
               flag = true;
              }
          }
         }
            for(int j = 0; j < f; j++)
                mem_layout[i][j] = frame[j];
        }   
			/*System.out.println("Frame: ");
            for(int k = 0; k < f; k++)
				System.out.print(frame[k]+" ");
            System.out.println(); */
        for(int i = 0; i < f; i++)
        {
            for(int j = 0; j < p; j++)
                System.out.printf("%3d ",mem_layout[j][i]);
            System.out.println();
        }
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/p));
        System.out.println("The number of Faults: " + fault);
		System.out.println("Hit Ratio: " + (float)((float)fault/p));
    }
}