import java.util.*;


public class arrays {
    static class MyArrayListComparator implements java.util.Comparator<ArrayList> {
        public int compare(ArrayList al1, ArrayList al2) {
            int d1 = (int) al1.get(1) - (int) al2.get(1);
            int d2 = (int) al2.get(0) - (int) al1.get(0);
            if ((int) al1.get(0) == (int) al2.get(0))
                return d1;
            else
                return d2;

        }
    }

    public static void swapSort(int[] arr, int n) {
        int i = 0;
        while (i < n) {
            if (arr[i] == i + 1 || arr[i] == arr[arr[i] - 1]) {
                i++;
            } else {
                int temp = arr[i];
                int temp2 = arr[arr[i] - 1];
                arr[arr[i] - 1] = temp;
                arr[i] = temp2;
            }
        }

        System.out.println(Arrays.toString(arr));

        for (int j = 0; j < n; j++) {
            if (arr[j] != j + 1) {
                int k = j + 1;
                System.out.println("missing : " + k);
                System.out.println("duplicate : " + arr[j]);
            }
        }
    }

    public static ArrayList<Integer> stockSpan(int[] arr, int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<Integer> s = new Stack<Integer>();

        for (int i = 0; i < n; i++) {
            if (s.empty()) {
                list.add(1);
            } else {
                if (arr[s.peek()] > arr[i]) {
                    list.add(1);
                } else {
                    while (!s.empty() && arr[s.peek()] <= arr[i]) {
                        s.pop();
                    }
                    if (s.empty()) {
                        list.add(i + 1);
                    } else {
                        list.add(i - s.peek());
                    }
                }
            }
            s.push(i);
        }

        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");

        return list;
    }

    public static void nsl(int[] arr, int n) {
        Stack<Integer> s = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s.empty()) {
                list.add(-1);
            } else {
                if (s.peek() < arr[i]) {
                    list.add(s.peek());
                } else {
                    while (!s.empty() && s.peek() >= arr[i]) {
                        s.pop();
                    }
                    if (s.empty()) {
                        list.add(-1);
                    } else {
                        list.add(s.peek());
                    }
                }
            }
            s.push(arr[i]);
        }

        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
    }

    public static void nsr(int[] arr, int n) {
        Stack<Integer> s = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            if (s.empty()) {
                list.add(-1);
            } else {
                if (s.peek() < arr[i]) {
                    list.add(s.peek());
                } else {
                    while (!s.empty() && s.peek() >= arr[i]) {
                        s.pop();
                    }
                    if (s.empty()) {
                        list.add(-1);
                    } else {
                        list.add(s.peek());
                    }
                }
            }
            s.push(arr[i]);
        }

        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
    }

    public static int bSearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    // find number of rotations in a sorted rotated array (binary-search)
    // search an element in a sorted rotated array (binary-search)
    public static int search(int[] arr, int target) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        int pivot = -1;

        if (arr[0] <= arr[n - 1]) {
            pivot = 0;
        } else {
            if (n == 1) {
                pivot = 0;
            } else if (n == 2) {
                pivot = arr[0] < arr[1] ? 0 : 1;
            } else {
                while (start <= end) {
                    int mid = start + (end - start) / 2;
                    int p = (mid + n - 1) % n;
                    int nx = (mid + 1) % n;
                    if (arr[mid] <= arr[p] && arr[mid] <= arr[nx]) {
                        pivot = mid;
                        break;
                    } else if (arr[0] <= arr[mid]) {
                        start = mid + 1;
                    } else if (arr[mid] < arr[n - 1]) {
                        end = mid - 1;
                    }
                }
            }
        }

        if (arr[pivot] == target) {
            return pivot;
        }

        int rightSearch = -1;
        int leftSearch = -1;

        if (target <= arr[n - 1]) {
            rightSearch = bSearch(arr, target, pivot, n - 1);
        } else {
            leftSearch = bSearch(arr, target, 0, pivot - 1);
        }

        if (leftSearch == -1 && rightSearch > -1) {
            return rightSearch;
        } else if (leftSearch > -1 && rightSearch == -1) {
            return leftSearch;
        }
        return -1;
    }

   /* public static ArrayList<Integer> arrange(ArrayList<Integer> A, ArrayList<Integer> B, int n) {
        // code here
        Collections.sort(A);
        int m = A.size();

        ArrayList<Integer> list = new ArrayList<Integer>(n);
//        System.out.println();

        for (int i = n - 1; i >= 0; i--) {
//            System.out.println("i : " + i);
            int v = B.get(i);
//            System.out.println("v : " + v);
            int item = A.get(m - v - 1);
//            System.out.println("item : " + item);
            list.add(item);
            A.remove(m - v - 1);
            m = A.size();
        }

        return list;
    }*/


    public static ArrayList<Integer> arrange(ArrayList<Integer> A, ArrayList<Integer> B, int n) {
        ArrayList<ArrayList<Integer>> people = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            people.add(temp);
        }

        for (int i = 0; i < n; i++) {
            people.get(i).add(A.get(i));
            people.get(i).add(B.get(i));
        }

        Collections.sort(people, new MyArrayListComparator());

        ArrayList<Integer> ans = new ArrayList<Integer>();

        for (int i = 0; i < n; i++)
            ans.add(people.get(i).get(1), people.get(i).get(0));

        return ans;
    }


    public static boolean compareMapP(HashMap<Character, Integer> map, String p) {
        System.out.println(p);
        int n = p.length();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(p.charAt(i)) && map.get(p.charAt(i)) > 0) {
                map.put(p.charAt(i), map.get(p.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean valid(String p, String s) {
        //code here
        int n = p.length();
        int m = s.length();

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i <= m - n; i++) {
            for (int j = i; j < i + n; j++) {
                char k = s.charAt(j);
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + 1);
                } else {
                    map.put(k, 1);
                }
            }
            if (compareMapP(map, p)) {
                return true;
            }
            map.clear();
        }

        return false;
    }

    public static void rotate(int[][] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            int m = a[i].length;
            for (int j = i+1; j < m; j++) {
                if (i != j) {
                    int f = a[i][j];
                    int s = a[j][i];
                    a[i][j] = s;
                    a[j][i] = f;
                }
            }
        }

        /*for (int i = 0; i < n; i++) {
            int m = a[i].length;
            for (int j = 0; j < m / 2; j++) {
                int f = a[i][j];
                int s = a[i][m - j - 1];
                a[i][j] = s;
                a[i][m - j - 1] = f;
            }
        }*/

        for (int i = 0; i < n; i++) {
            int m = a[i].length;
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(a);
//        valid("zyxabc", "fghcxabyzbvf");

        ArrayList<Integer> A = new ArrayList<>();

        A.add(5);
        A.add(3);
        A.add(2);
        A.add(6);
        A.add(1);
        A.add(4);

        ArrayList<Integer> B = new ArrayList<>();

        B.add(0);
        B.add(1);
        B.add(2);
        B.add(0);
        B.add(3);
        B.add(2);

//        System.out.println(arrange(A, B, A.size()));

//        int[] arr = {4,5,6,7,0,1,2,3};
//        int[] arr = {11,12,15,18,2,5,6,8};

//        System.out.println(search(arr, 0));

//        int[] arr = {4, 5, 2, 10, 8};
//        nsr(arr, arr.length);
//        nsl(arr, arr.length);
//        int[] arr = {100, 80, 60, 70, 60, 75, 85};
//        int[] arr = {10, 4, 5, 90, 120, 80};
//        stockSpan(arr, arr.length);

//        int[] arr = {2,3,1,8,2,3,5,1};
//        int[] arr = {2,3,1,5,1};
//        swapSort(arr, arr.length);
    }
}
