import java.io.*;

class Main {
    private static boolean[] brokenBtnArr;
    private static final int MAX_CHANNEL = 999999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int targetChannel = Integer.parseInt(br.readLine());
        int minPress = Math.abs(targetChannel - 100);
        
        int brokenCount = Integer.parseInt(br.readLine());
        if (brokenCount > 0) {
            brokenBtnArr = new boolean[10];
            for (String btn : br.readLine().split(" ")) {
                brokenBtnArr[Integer.parseInt(btn)] = true;
            }
        }

        if (brokenCount < 10) {
            minPress = Math.min(minPress, findMinPress(targetChannel));
        }

        System.out.println(minPress);
    }

    private static int findMinPress(int targetChannel) {
        int minPress = Integer.MAX_VALUE;
        int press = 0;

        while (true) {
            int upChannel = targetChannel + press;
            int downChannel = targetChannel - press;

            if (downChannel < 0 && upChannel > MAX_CHANNEL) {
                break;
            }

            if (downChannel >= 0) {
                int count = getDigitCount(downChannel);
                if (count != -1) {
                    minPress = Math.min(minPress, targetChannel - downChannel + count);
                    break;
                }
            }

            if (upChannel <= MAX_CHANNEL) {
                int count = getDigitCount(upChannel);
                if (count != -1) {
                    minPress = Math.min(minPress, upChannel - targetChannel + count);
                    break;
                }
            }

            if (minPress != Integer.MAX_VALUE && Math.abs(targetChannel - 100) < minPress) {
                break;
            }

            press++;
        }

        return minPress;
    }

    private static int getDigitCount(int number) {
        if (number == 0) {
            return brokenBtnArr[0] ? -1 : 1;
        }

        int count = 0;
        while (number > 0) {
            if (brokenBtnArr[number % 10]) {
                return -1;
            }
            number /= 10;
            count++;
        }
        return count;
    }
}