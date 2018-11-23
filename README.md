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
For the greedy approach, we first check if the sum is lying between 1 and 9*(length of the numbers), if not than print -1 -1 ( i mean Maximum sum we can form with a number of length m is 9*m(by placing all nines) so if s is greater than 9*m there is no answer. 

to find the maximum number, first we take as many 9 as possible and decrease the sum by 9 and when we sum become less than 9, we just print that digit and remaining digits should be zero.

The hardest part its actually finding the minimum as there are restriction that there should be no leading zeroes thus we can't just start assigning 9 to low priority digits ( digits on the right), to escape that. 
we transvers from i=m-1 to i=0 and filling every digit. check 
if i=m-1 then he is filling 1st digit and (m-1) digits are remaining (excluding present digit). if i=m-2 then he is filling 2nd digit and (m-2) digits are remaining(excluding present digit).
thus if its at some i the i digits are remaining. 
maximum possible sum we can form with those remaining i digits is 9*i and k is the remaining sum, since __j=max(0,k-9*i), the we assign 1 to j (if i==m and j==9), its because we should not have a leading zeroes, so its taking j at that place and subtracting j from remain of sum k.


snipplet code:

```c++

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

The complexity of greedy algorithm is O(n).

### DP approach
<p align="justify">
Dynamic Programming (DP) is a method for problem solving used in math and computer science in which large problems are broken down into smaller problems. Through solving the individual smaller problems, the solution to the larger problem is discovered.

<p align="justify">
The idea behind dynamic programming is quite simple. In general, to solve a given problem, we need to solve different parts of the problem, then combine the solutions of the subproblems to reach an overall solution. Often when using other method, many of the subproblems are generated and solved many times.

For dynamic programming method, the code can be seen below:
    
```c++

void highest(int counter, int remainder);
void lowest(int counter, int remainder, int m);

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

The complexity for dynamic programming is O(n).

### Comparison
<p align="justify">
In conclusion, Dynamic Programming can first examine the path which takes the shortest time and then start journey, on the other hand Greedy algorithm take the optimal decision on the spot without thinking for the next step and on the next step change its decision again and so on...

If we try to input the example above, we can get the time as follow:

**With Greedy**

Example #1:
```
~1.3sec
```
Example #2:
```
~1sec
```

**With Dynamic Programming**

Example #1:
```
~1.2sec
```

Example #2:
```
~0.9sec
```

From the experiment above, using dynamic programming is faster... a little bit...👌 but with greedy approach its more easier to think.

*Note: The time for the code to process also depends on the input time, so we try to quickly input the number repeatedly and count the average number.*
