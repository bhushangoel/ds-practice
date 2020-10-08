import java.lang.*;
import java.io.*;
import java.util.*;

public class practice {
    public static void printMat(int t[][]) {
        int n = t.length;
        int m = t[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void printMatBool(boolean t[][]) {
        int n = t.length;
        int m = t[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static int knapsack(int wt[], int val[], int W, int n, int t[][]) {
        if (n == 0 || W <= 0) {
            return 0;
        }

        printMat(t);

        if (t[n][W] != -1) {
            return t[n][W];
        }

        if (wt[n - 1] <= W) {
            System.out.println("n : " + n + " w : " + W);
            t[n][W] = Math.max(val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1, t), knapsack(wt, val, W, n - 1, t));
            return t[n][W];
        } else if (wt[n - 1] > W) {
            t[n][W] = knapsack(wt, val, W - wt[n - 1], n - 1, t);
            return t[n][W];
        }

        return 1;
    }

    public static int knapsack2(int[] wt, int[] val, int W, int n) {
        int t[][] = new int[n + 1][W + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                } else {
                    t[i][j] = -1;
                }
            }
        }
        printMat(t);
        for (int i = 1; i < n + 1; i++) {
            System.out.println("i 1 : " + i);
            for (int j = 1; j < W + 1; j++) {
                System.out.println("i : " + i + " j : " + j);
                if (wt[i - 1] <= j) {
                    t[i][j] = Math.max(val[i - 1] + t[i - 1][j - wt[i - 1]], t[i - 1][j]);
                } else if (wt[i - 1] > j) {
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        printMat(t);
        return t[n][W];
    }

    public static boolean subsetSum(int[] set, int sum, int n) {
        boolean[][] t = new boolean[n + 1][sum + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0) {
                    t[i][j] = false;
                }
                if (j == 0) {
                    t[i][j] = true;
                }
            }
        }
        printMatBool(t);
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (set[i - 1] <= j) {
                    t[i][j] = t[i - 1][j - set[i - 1]] || t[i - 1][j];
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        System.out.println(" ");
        printMatBool(t);
        return t[n][sum];
    }

    public static int countSubset(int[] arr, int sum, int n) {
        int[][] t = new int[n + 1][sum + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0) {
                    t[i][j] = 0;
                }
                if (j == 0) {
                    t[i][j] = 1;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (arr[i - 1] <= j) {
                    t[i][j] = t[i - 1][j] + t[i - 1][j - arr[i - 1]];
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        return t[n][sum];
    }

    public static int subsetWithDiff(int[] arr, int diff, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        int s1 = (sum + diff) / 2;
        int count = countSubset(arr, s1, n);
        System.out.println(count);
        return count;
    }

    public static String revStr(String s) {
        int n = s.length();
        String s2 = "";

        for (int i = n - 1; i >= 0; i--) {
            s2 += s.charAt(i);
        }

        return s2;
    }

    public static void printLPS(int[][] t, String s1, String s2) {

        int p = s1.length();
        int q = s2.length();
        String s = "";
        while (p > 0 && q > 0) {
            if (s1.charAt(p - 1) == s2.charAt(q - 1)) {
                s += s1.charAt(p - 1);
                p--;
                q--;
            } else {
                if (t[p][q - 1] > t[p - 1][q]) {
//                    s += s2.charAt(q-1);
                    q--;
                } else if (t[p][q - 1] < t[p - 1][q]) {
//                    s += s1.charAt(p-1);
                    p--;
                } else {
                    q--;
                }
            }
        }

        System.out.println("s : " + s);
    }

    /*
     * additional details
     * code wala 21635
     * */

    public static int LCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] t = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
//                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
                    t[i][j] = 0;
                }
            }
        }

        System.out.println("s1 : " + s1);
        System.out.println("s2 : " + s2);

        printMat(t);
        printLPS(t, s1, s2);
        return t[m][n];
    }

    public static int lps(String s, int n) {
        String s2 = revStr(s);
        int m = LCS(s, s2);


        return m;
    }

    static void printSubStr(String str, int low, int high) {
//        System.out.println("low : " + low);
//        System.out.println("high : " + high);
        System.out.println(str.substring(low, high + 1));
    }

    public static void lpss(String str, int n) {
        // table[i][j] will be false if
        // substring str[i..j] is not palindrome.
        // Else table[i][j] will be true
        boolean table[][] = new boolean[n][n];

        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; ++i)
            table[i][i] = true;

        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2.
        // k is length of substring
        for (int k = 3; k <= n; k++) {
//            System.out.println("k ::: " + k);
            for (int i = 0; i < n - k + 1; i++) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;
//                System.out.println("i : " + i);
//                System.out.println("j : " + j);
                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    table[i][j] = true;
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

//        System.out.println("start : " + start);
//        System.out.println("maxLength : " + maxLength);
        printSubStr(str, start, start + maxLength - 1);
//        System.out.println(str.substring(start, start + maxLength+1));

//        printMatBool(table);
    }

    public static void scsPrint(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] t = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }

        int p = m;
        int q = n;
        String s = "";
        while (p > 0 && q > 0) {
            if (s1.charAt(p - 1) == s2.charAt(q - 1)) {
                s += s1.charAt(p - 1);
                p--;
                q--;
            } else {
                if (t[p][q - 1] > t[p - 1][q]) {
                    s += s2.charAt(q - 1);
                    q--;
                } else {
                    s += s1.charAt(p - 1);
                    p--;
                }
            }
        }

        while (p > 0) {
            s += s1.charAt(p - 1);
            p--;
        }

        while (q > 0) {
            s += s2.charAt(q - 1);
            q--;
        }

        String s3 = revStr(s);

        System.out.println(s3);
    }

    public static boolean isPalindrome(String s, int i, int j) {
        System.out.println("is palindrome called : " + s);
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static int solve(String s, int n, int i, int j) {
        if (s.length() <= 1) {
            return 0;
        }

        if (isPalindrome(s, i, j)) {
            return 0;
        }

        System.out.println("here..." + i + " <> " + j);

        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = 1 + solve(s, n, i, k) + solve(s, n, k + 1, j);

            ans = Math.min(ans, temp);
        }

        return ans;
    }

    public static int scrambleString(String A, String B, HashMap<String, Integer> map) {
        int n = A.length();
        int m = B.length();

        System.out.println("n : " + n);
        System.out.println("m : " + m);
        System.out.println("A : " + A);
        System.out.println("B : " + B);

        if (n != m) {
            return 0;
        }

        if (A.equals(B)) {
            return 1;
        }

        if (A.length() <= 1) {
            return 0;
        }

        String s1 = "";
        s1 += A;
        s1 += B;

        if (map.containsKey(s1)) {
            return map.get(s1);
        }

        int flag = 0;

        for (int i = 1; i < n - 1; i++) {
            int swap1 = scrambleString(A.substring(0, i), B.substring(n - i, n), map);
            int swap2 = scrambleString(A.substring(i, n), B.substring(0, n - i), map);
            int noswap1 = scrambleString(A.substring(0, i), B.substring(0, i), map);
            int noswap2 = scrambleString(A.substring(i, n), B.substring(i, n), map);

            if (((swap1 & swap2) == 1) || ((noswap1 & noswap2) == 1)) {
                flag = 1;
                break;
            }
        }

        String s = "";
        s += A;
        s += B;
        map.put(s, flag);
        return flag;
    }

    public static void main(String[] args) {
//        String A = "abbbcbaaccacaacc";
//        String B = "acaaaccabcabcbcb";

//        HashMap<String, Integer> map = new HashMap<>();

//        System.out.println(scrambleString(A, B, map));

//        String s11 = "ueokcxsxomszsnsnhmlqlggkrnarwtkszaebxw";
//        int n11 = s11.length();
//        System.out.println(solve(s11, s11.length(), 0, n11-1));
//
//        String s1 = "hello";
//        String s2 = "geek";
////        scsPrint(s1, s2);
//
        String s = "aaaabbaa";
        lpss(s, s.length());
//        System.out.println(lpss(s, s.length()));
//
//        int[] arr1 = {1,1,2,3};
//        int diff = 1;
//
////        subsetWithDiff(arr1, diff, arr1.length);
//
////        int[] arr = {2, 3, 5, 6, 8, 10};
//        int[] arr =  {1, 1, 1, 1};
//        int sum = 1;
////        System.out.println(countSubset(arr, sum, arr.length));
//
//        int[] set = {1, 2, 3};
////        int sum = 3;
////        System.out.println(subsetSum(set, sum, set.length));
//
//        int wt[] = {1, 3, 4, 5};
//        int val[] = {1, 4, 5, 7};
//        int W = 7;
//        int n = 4;
//
//        int t[][] = new int[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                t[i][j] = -1;
//            }
//        }

//        System.out.println(knapsack(wt, val, W, n, t));
//        System.out.println(knapsack2(wt, val, W, n));
    }
}
