import java.util.*;
public class recursion {

    // string subset problem
    public static void strSubset(String str) {
        int n = str.length();
        strSubsetUtil(str, "", 0, n);
    }

    public static void strSubsetUtil(String str, String curr, int idx, int n) {
        if (idx == n) {
            System.out.print(curr + " ");
            return;
        }

        strSubsetUtil(str, curr, idx + 1, n);
        strSubsetUtil(str, curr + str.charAt(idx), idx + 1, n);
    }

    public static void print1ton(int n) {
        if(n==1) {
            System.out.print(n + " ");
            return;
        }
        print1ton(n-1);
        System.out.print(n + " ");
    }

    public static void printnto1(int n) {
        if(n==1) {
            System.out.print(n + " ");
            return;
        }
        System.out.print(n + " ");
        printnto1(n-1);

    }

    public static void sortMe(ArrayList<Integer> arr) {
        if(arr.size() == 1) {
            return;
        }

        int temp = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        sortMe(arr);
        System.out.println("temp : " +temp);
        insert(arr, temp);
    }

    public static void insert(ArrayList<Integer> arr, int temp) {
        if(arr.size() == 0 || arr.get(arr.size() - 1) <= temp) {
            arr.add(temp);
            return;
        }

        int val = arr.get(arr.size() - 1);
        arr.remove(arr.size() - 1);
        insert(arr, temp);
        arr.add(val);
    }

    public static int kthGrammar(int N, int K) {
        if(N == 1 && K == 1) {
            return 0;
        }

        int mid = (int) Math.pow(2, N-1)/2;
        if(K <= mid) {
            return kthGrammar(N-1, K);
        } else {
            return kthGrammar(N-1, K-mid) == 0 ? 1 : 0;
        }
    }

    public static void casePermute(String str, int n, String curr, int idx) {
        if(idx == n) {
            System.out.print(curr+" ");
            return;
        }

        if(Character.toLowerCase(str.charAt(idx)) - 'a' < 0 || Character.toLowerCase(str.charAt(idx)) - 'a' > 25) {
            System.out.print("here");
            casePermute(str, n, curr + str.charAt(idx), idx+1);
            return;
        }

        Character o1 = Character.toLowerCase(str.charAt(idx));
        casePermute(str, n, curr+o1, idx+1);
        Character o2 = Character.toUpperCase(str.charAt(idx));
        casePermute(str, n, curr+o2, idx+1);
    }

    public static List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<String>();

        solve(n, n, "", list);
        return list;
    }

    public static void solve(int open, int close, String curr, ArrayList<String> list) {
        if(open == 0 && close == 0) {
            System.out.println("curr : " + curr);
            list.add(curr);
            return;
        }

        if(open != 0) {
            open -= 1;
            String op1 = curr;
            op1 += "(";
            solve(open, close, op1, list);
        }
        if(close > open) {
            close -= 1;
            String op2 = curr;
            op2 += ")";
            solve(open, close, op2, list);
        }
//        return;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
//        String str = "C";

//        casePermute(str, str.length(), "", 0);

//        System.out.println(kthGrammar(4,3));

//        int[] arr = {0,1,5,2};
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(0);
//        arr.add(1);
//        arr.add(5);
//        arr.add(2);
//        sortMe(arr);
//        System.out.print(arr);

//        print1ton(7);
//        printnto1(7);


//        String str = "ab";
//        strSubset(str);
    }
}
