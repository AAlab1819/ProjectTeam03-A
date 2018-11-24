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
For the greedy approach, we first check if the sum is lying between 1 and 9*(length of the numbers), if not than print -1 -1 (i mean maximum sum we can form with a number of length m is 9*m (by placing all nines), so if s is greater than 9*m there is no answer. To find the maximum number, first we take as many 9 as possible and decrease the sum by 9 and when we sum become less than 9, we just print that digit and remaining digits should be zero.

<p align="justify">
The hardest part its actually finding the minimum as there are restriction that there should be no leading zeroes, thus we can't just start assigning 9 to low priority digits ( digits on the right), to escape that. We transvers from i=m-1 to i=0 and filling every digit. Check if i=m-1 then he is filling 1st digit and (m-1) digits are remaining (excluding present digit). If i=m-2, then he is filling 2nd digit and (m-2) digits are remaining(excluding present digit). Thus if its at some i the i digits are remaining. Maximum possible sum we can form with those remaining i digits is 9*i and k is the remaining sum, since j=max(0,k-9*i), the we assign 1 to j (if i==m and j==9), its because we should not have a leading zeroes, so its taking j at that place and subtracting j from remain of sum k.

Snipplet code:

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
```

The solution link can be seen [here](https://codeforces.com/contest/489/submission/45753386)

The complexity of greedy algorithm is O(n^2).

### DP approach
<p align="justify">
The way its solve is same with greedy, just we make function to find the highest and the lowest number possible. With this way, we think the way the code run gonna become a little bit faster because it can be run at the same time. 

The complexity of greedy algorithm is O(n).

Snipplet code: 
    
```c++

void highest(int counter, int remainder);
void lowest(int counter, int remainder, int m);
{
    //if the output isn't -1 -1, go to else function
    else
    {
        lowest(m-1,s,m);
        cout << " ";
        highest(m-1,s);
    }
}

//the first output, the minimum
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

//the second output, the maximum
void highest(int counter, int remainder)
{
    int subtractor;
    if (counter >= 0)
    {
        subtractor = min(9, remainder );
        remainder -= subtractor;
        cout << subtractor;
        counter--;
        highest(counter, remainder);
    }
}
```

The solution link can be seen [here](https://codeforces.com/contest/489/submission/46112688)

The complexity for dynamic programming is O(n^2).

### Comparison
<p align="justify">
In conclusion, Dynamic Programming can first examine the path which takes the shortest time and then start journey, on the other hand Greedy algorithm take the optimal decision on the spot without thinking for the next step and on the next step change its decision again and so on...

If we try to input the example above, we can get the time as follow:

**With Greedy**

![greedy](https://user-images.githubusercontent.com/25146223/48964956-f9112600-efe5-11e8-9cee-5b7c1d71182c.jpg)


**With Dynamic Programming**

![dp](https://user-images.githubusercontent.com/25146223/48964960-1645f480-efe6-11e8-8156-881f4e074a1c.jpg)

<p align="justify">
From the experiment above, using dynamic programming is faster for the non -1 -1 output; but with greedy approach it's more faster to print the -1 -1 output. Dynamic programming also has consistent time (being 15ms) for each sample.
