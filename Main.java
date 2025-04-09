import java.io.*;

class Main {
    private static boolean[] brokenBtnArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String chenStr = br.readLine();
        int chenNum = Integer.parseInt(chenStr);
        int minPress = Math.abs(chenNum - 100);
        brokenBtnArr = new boolean[10];
        int brokenCount = Integer.parseInt(br.readLine());
        if (brokenCount == 0) {
            minPress = Math.min(minPress, chenStr.length());
        } else {
            for (String btn : br.readLine().split(" ")) {
                brokenBtnArr[Integer.parseInt(btn)] = true;
            }
        }

        int totalPress = Integer.MAX_VALUE;

        if (brokenCount < 10) {
            int press = 0;
            int upChen, downChen, count;
            while (true) {
                upChen = chenNum + press;
                downChen = chenNum - press;

                if (downChen < 0 && upChen > 999999)
                    break;

                if (downChen >= 0) {
                    count = check(downChen);
                    if (count != -1) {
                        totalPress = Math.min(totalPress, chenNum - downChen + count);
                        break;
                    }
                }

                if (upChen <= 999999) {
                    count = check(upChen);
                    if (count != -1) {
                        totalPress = Math.min(totalPress, upChen - chenNum + count);
                        break;
                    }
                }

                if (totalPress != Integer.MAX_VALUE && minPress < totalPress) {
                    break;
                }

                press++;
            }
        }

        System.out.println(totalPress != Integer.MAX_VALUE ? Math.min(minPress, totalPress) : minPress);
    }

    private static int check(int chenNum) {
        if (chenNum == 0) {
            return brokenBtnArr[0] ? -1 : 1;
        }

        int count = 0;

        while (chenNum != 0) {
            if (brokenBtnArr[chenNum % 10])
                return -1;

            chenNum /= 10;
            count++;
        }

        return count;
    }
}