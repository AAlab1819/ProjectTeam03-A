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
        Using the BigInteger class from Java we are able to calculate for large sums of numbers. The first condition is to check for a        particular case i.e if sum = 0 and digit = 1. Next is to check whether the inputs are valid or not, if the sum is less than 1 or if
    the sum is too large for the amount of digits that is set we return -1 -1. Finally if non of these conditions are met the program 
    goes to the algorithm. 
    
    ```
    0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
    0 1 2 3 4 5 6 7 8 9 0 0 0 0 0 0 
    0 10 20 30 40 50 60 70 80 90 91 92 93 94 95 96
    ```
    
        Here things get solved by using a nested loop which intializes dp[i][j] to zero and dp1 to variable number1 which is set to an extremely high value. Next the program iterates from 1 to 9 since the max single digit is 9, which is used to map dp[1][i] and dp1[1][i] The function then loops again with a triple nested for loop that compares between the various digits  
Snipplet code: 
    
```
       if (sum == 0 && digit == 1)
            System.out.println("0 0");
        else if (sum < 1 || sum > 9 * digit)
            System.out.println("-1 -1");
        else {
            for (int i = 0; i <= digit; i++)
                for (int j = 0; j <= sum; j++) {
                    dp[i][j] = BigInteger.ZERO;
                    dp1[i][j] = number1;
                }

            for (int i = 1; i <= 9; i++) {
                dp[1][i] = new BigInteger(Integer.toString(i));
                dp1[1][i] = new BigInteger(Integer.toString(i));
            }
            for (int i = 2; i <= digit; i++)
                for (int j = 1; j <= sum; j++)
                    for (int k = 0; k <= j && k <= 9; k++)
                        dp[i][j] = dp[i][j].max(
                                BigInteger.TEN.multiply(dp[i - 1][j - k]).add(new BigInteger(Integer.toString(k))));
            for (int i = 2; i <= digit; i++)
                for (int j = 1; j <= sum; j++) {
                    BigInteger temp = number1;
                    for (int k = 0; k <= j && k <= 9; k++)
                        if (!dp1[i - 1][j - k].equals(BigInteger.ZERO))
                            temp = temp.min(BigInteger.TEN.multiply(dp1[i - 1][j - k])
                                    .add(new BigInteger(Integer.toString(k))));
                    dp1[i][j] = temp;
                }
            double endtime = System.nanoTime() - startTime;

            System.out.println(dp1[digit][sum].toString() + " " + dp[digit][sum].toString());
            // waktunya dibagi 1 juta biar mili
            System.out.println("start time = " + startTime);
            System.out.println("end time = " + endtime);
        }
    }
}
```

The solution link can be seen [here](https://codeforces.com/contest/489/submission/46389138)

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
From the experiment above, using dynamic programming is faster for the non -1 -1 output; but with greedy approach it's more faster to print the -1 -1 output. Dynamic programming also has consistent time (being 15ms) for each sample. Futhermore, greedy code is much easier to understand, than the dynamic programming one.
