import java.io.*;
import java.lang.*;
class SJF
{
String s;
int bt;
int wt;
int tt;

public void printSJF(SJF o[])
{

System.out.println("Process	Burst Time 	Waiting Time	Turnaround Time\n");
for(int i=0; i<o.length; i++)
{
System.out.println(o[i].s+"\t\t"+o[i].bt+"\t\t"+o[i].wt+"\t\t"+o[i].tt);
System.out.println();
}
}
public void sort(SJF o[])
{
SJF temp= new SJF();
for(int i=0; i<(o.length-1);i++)
{
	for(int j=0; j<(o.length-i-1);j++)
	{
		if (o[j].bt>o[j+1].bt)
		{
		temp=o[j];
		o[j]=o[j+1];
		o[j+1]=temp;
		}
	}
}
}
public static void main(String args[])throws IOException
{
	
	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter the number of processes:");
	int n=Integer.parseInt(br.readLine());
	SJF f= new SJF();
	SJF o[]=new SJF[n];
	float wta=0, tta=0;
	System.out.println("Enter the nameof processes:");
	for(int i=0;i<n;i++)
	{
	o[i]=new SJF();
	o[i].s=br.readLine();
	}
	
	System.out.println("Enter the burst time of processes:");
	for(int i=0; i<n; i++)
	{
		o[i].bt=Integer.parseInt(br.readLine());
	}
	f.sort(o);
	for(int i=0; i<n; i++)
	{
	for(int j=0; j<=i; j++)
	{

	 o[i].tt=o[i].tt+o[j].bt;
	 }
	 o[i].wt=o[i].tt-o[i].bt;
	 
	}
	f.printSJF(o);
	for(int i=0;i<n; i++)
	{
	 wta=wta+o[i].wt;
	 tta=tta+o[i].tt;
	 
	 }
	System.out.println(" Avg Waiting Time"+(wta/n)+"\nAvg Turnarund Time:"+(tta/n));
 }
 }
	
	
