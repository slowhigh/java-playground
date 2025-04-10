import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] l1 = br.readLine().split(" ");
        int N = Integer.parseInt(l1[0]);
        int S = Integer.parseInt(l1[1]);
        int min = Integer.MAX_VALUE;
        int[] arr = new int[N];
        String[] l2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(l2[i]);
        int left = 0, sum = 0;
        for (int right = 0; right < N; right++) {
            sum += arr[right];

            while (sum >= S) {
                min = Math.min(min, right - left + 1);
                sum -= arr[left];
                left++;
            }
        }

        System.out.println(min != Integer.MAX_VALUE ? min : 0);
    }
}
// 10 15
// 5 1 3 5 10 7 4 9 2 8

// 2