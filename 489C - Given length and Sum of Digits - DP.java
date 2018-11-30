import java.math.BigInteger;
import java.util.Scanner;

public class d {
    public static void main(String[] args) {
        BigInteger[][] dp, dp1;
        dp = new BigInteger[101][901];
        dp1 = new BigInteger[101][901];
        int digit, sum;
        BigInteger number1 = new BigInteger(
                "91111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        Scanner scanner = new Scanner(System.in);
        digit = scanner.nextInt();
        sum = scanner.nextInt();
        double startTime = System.nanoTime();
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