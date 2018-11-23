# Solving Codeforces 489 C using Greedy & DP
This repo will solve Codeforces problem [489 C](https://codeforces.com/problemset/problem/489/C) using Greedy approach and Dynamic Programming. We will also compare which method is better.

## Team Member
- [Alessandro Luiz Kartika](http://codeforces.com/profile/Elderhawk)
- [Eugene Sebastian](https://codeforces.com/profile/nachos)
- [Denny Raymond](http://codeforces.com/profile/dendenray)
- [Steven](http://codeforces.com/profile/Steve2015) 

## Requirement
- [C++ 11](https://osdn.net/projects/sfnet_tdm-gcc/)

## Installation/running instruction
**Using online compiler**
- Open your web browser
- Go to [C++ Online Compiler](https://www.onlinegdb.com/online_c++_compiler)
- Copy paste the source code from the rpository into the website's code field
- Run the program

**Using DevC++ program**
- Open DevC++
- Copy paste the source code from the rpository into the DevC++'s code field
- Run the program

## Sample I/O
We are going to use an example from the [problem](https://codeforces.com/contest/489/problem/C).

**Example #1**

If the input is:

```
2 15
```

The output will be:

```
69 96
```

**Example #2**

If the input is:

```
3 0
```

The output will be:

```
-1 -1
```

## Explanation

### Problem statement
<p align="justify">
Our Codeforce problem is called "Given Length and Sum of Digits...". The problem want us to find the smallest and the largest numbers from the two input numbers. The number we enter cannot be negative and cannot be decimal. If there are none, print out -1 and -1. For example if s = 0 will produce the output: -1 -1.

**To find minimum number:**

<p align="justify">
The number is traversing from i=m-1 to i=0 and filling every digit. If i=m-1 then he is filling 1st digit and (m-1) digits are remaining (excluding present digit). If i=m-2 then he is filling 2nd digit and (m-2) digits are remaining(excluding present digit), so if the number is at some i then i digits are remaining.

**To find maximum number:**

<p align="justify">
First we take as many nines as possible and decrease the sum by 9 respectively and when sum becomes less than 9 we print that digit and remaining digits should be zero.

### Greedy Approach
<p align="justify">
Greedy is an algorithmic paradigm that builds up a solution piece by piece, always choosing the next piece that offers the most obvious and immediate benefit. Greedy algorithms are used for optimization problems; an optimization problem can be solved using greedy if the problem has the following property: At every step, we can make a choice that looks best at the moment, and we get the optimal solution of the complete problem.

<p align="justify">
We can make whatever choice seems best at the moment and then solve the problems that arise later. The choice made by a greedy algorithm may depend on choices made so far but not on future choices or all the solutions to the subproblem. It iteratively makes one greedy choice after another, reducing each given problem into a smaller one.

For greedy method, the code can be seen below:

```
#include <iostream>
using namespace std;

int main()
{
    int m,s,i,k;
    cin>>m>>s;//our first and second input
    
    //if it is more than 9*m, zero, or minus, then the output is -1 -1
    if(s<1 && m>1 || s>m*9)
    {
        cout<<-1 <<" "<<-1<<endl;
    }
    else
    {
        //Finding max and min
        for(i=m-1,k=s;i>=0;i--)
        {
            int j=max(0,k-9*i);
            if(j==0 && i==m-1 && k)
                j=1;
                cout<<j;//first input, the minimum
                k = k-j;
        }
        cout<<' ';
        for(i=m-1,k=s;i>=0;i--)
        {
            int j=min(9,k);
            cout<<j;//second output, the maximum
            k = k-j;
        }
    }
    return 0;
}
```

The solution link can be seen [here](https://codeforces.com/contest/489/submission/45753386)

The complexity

### DP approach
<p align="justify">
Dynamic Programming (DP) is a method for problem solving used in math and computer science in which large problems are broken down into smaller problems. Through solving the individual smaller problems, the solution to the larger problem is discovered.

<p align="justify">
The idea behind dynamic programming is quite simple. In general, to solve a given problem, we need to solve different parts of the problem, then combine the solutions of the subproblems to reach an overall solution. Often when using other method, many of the subproblems are generated and solved many times.

For dynamic programming method, the code can be seen below:
    
```
#include <iostream>
#include <cmath>
 
using namespace std;
void highest(int counter, int remainder);
void lowest(int counter, int remainder, int m);
int main ()
{
    int m,s,i,k;
    cin>>m>>s;
    if((s<1 && m>1)||s>m*9)
    cout<<-1<<' '<<-1<<endl;
 
    else
    {
        lowest(m-1,s,m);
        cout << " ";
        highest(m-1,s);
    }
}
 
void lowest(int counter, int remainder, int m)
{
    int subtractor;
    if (counter >= 0)
    {
        subtractor = max(0, remainder - 9 * counter);
        if (subtractor == 0 && counter == m - 1 && remainder )
        {
            subtractor = 1;
        }
        remainder -= subtractor;
        cout << subtractor;
        counter--;
        lowest(counter, remainder, m);
    }
}
 
void highest(int counter, int remainder)
{
    int subtractor;
    if (counter >= 0){
        subtractor = min(9, remainder );
        remainder -= subtractor;
        cout << subtractor;
        counter--;
        highest(counter, remainder);
    }
}
```

The solution link can be seen [here](https://codeforces.com/contest/489/submission/46112688)

### Comparison
<p align="justify">
In conclusion, Dynamic Programming can first examine the path which takes the shortest time and then start journey, on the other hand Greedy algorithm take the optimal decision on the spot without thinking for the next step and on the next step change its decision again and so on...

If we try to input the example above, we can get the time as follow:

**With Greedy**

Example 1:
```
~1.3sec
```
Example 2:
```
~1sec
```

**With Dynamic Programming**

Example 1:
```
~1.2sec
```

Example 2:
```
~0.9sec
```

From the experiment above, using dynamic programming is faster... a little bit...👌

*Note:The time for the code to process also depends on the input time, so we try to quickly input the number repeatedly and count the average*
