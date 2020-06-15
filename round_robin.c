// Round Robin CPU scheduling algorithm
#include<stdio.h>
int main()
{

  int count,i,j,n,time,remain,flag=0,time_quantum;
  int wait_time=0,turnaround_time=0,at[10],bt[10],rt[10];
  printf("\tROUND ROBIN SCHEDULING ALGORITHM\n");
  printf("=============================================");

  printf("\n\nEnter Total Process:\t ");
  scanf("%d",&n);
  printf("\n");
  remain=n;
  for(count=0;count<n;count++)
  {
    printf("Enter Arrival Time for Process %d :\t",count+1);
    scanf("%d",&at[count]);
    printf("Enter Burst Time for Process %d :\t",count+1);
    scanf("%d",&bt[count]);
    rt[count]=bt[count];
    printf("\n");
  }

  printf("\nEnter Time Quantum:\t");
  scanf("%d",&time_quantum);

  printf("\n\nProcess\t|Turnaround Time|Waiting Time\n\n");

  for(time=0,count=0;remain!=0;)
  {
    if(rt[count]<=time_quantum && rt[count]>0)
    {
      time+=rt[count];
      rt[count]=0;
      flag=1;
    }
    else if(rt[count]>0)
    {
      rt[count]-=time_quantum;
      time+=time_quantum;
    }
    if(rt[count]==0 && flag==1)
    {
      remain--;
      printf("P[%d]\t|\t%d\t|\t%d\n",count+1,time-at[count],time-at[count]-bt[count]);
      wait_time+=time-at[count]-bt[count];
      turnaround_time+=time-at[count];
      flag=0;
    }
    if(count==n-1)
      count=0;
    else if(at[count+1]<=time)
      count++;
    else
      count=0;
  }
  j=0;
  printf("GANTT CHART: \n\n\n\t");
  for(i=at[0]; i<time;)
  {
          if(bt[j]>=time_quantum)
          {
              printf("P%d\t",j+1);
              i+=time_quantum;
              bt[j]=bt[j]-time_quantum;
          }
          else if(bt[j]>0)
          {
              printf("P%d\t",j+1);
              i+=bt[j];
              bt[j]=0;
          }
          j++;
          if(j>=n)
          {
              j=0;
          }
  }

  printf("\nAverage Waiting Time= %f\n",wait_time*1.0/n);
  printf("Avg Turnaround Time = %f",turnaround_time*1.0/n);

  return 0;
}
