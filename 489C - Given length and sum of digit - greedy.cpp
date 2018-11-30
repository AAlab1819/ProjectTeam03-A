#include <bits/stdc++.h>
#include <omp.h>
using namespace std;

int main()
{
    /*
       m =  length
       s = sum of the digits of the required numbers
       k = sum
       i = for loop
    */
    int m,s,i,k;
    cin>>m>>s;
    //to check time
    double startTime = omp_get_wtime();
    //ifnya untuk kondisi gagal, kalau misalnya m dan s == 0
    if(s<1 && m>1 || s>m*9){
        cout<<-1 <<" "<<-1<<endl;
    }
    else{
        for(i=m-1,k=s;i>=0;i--){
            int j=max(0,k-9*i);
            if(j==0 && i==m-1 && k)
                j=1;
                cout<<j;
                k = k-j;
        }
        cout<<" ";
        for(i=m-1,k=s;i>=0;i--){
            int j=min(9,k);
            cout<<j;
            k = k-j;
        }
    }
    //to check time
    double endTime = omp_get_wtime();
    cout<<endl;
    cout<< fixed << setprecision(5) << "Start time: " << startTime << endl;
    cout<< fixed << setprecision(5) << "End time: " << endTime << endl;
    cout<< fixed << setprecision(5) << "Process time: " << endTime - startTime << endl;
    return 0;
}
