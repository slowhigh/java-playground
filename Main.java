import java.io.*;

class Main {
    private static boolean[] brokenBtnArr;
    private static final int MAX_CHANNEL = 999999;
    private static final int DEFAULT_CHANNEL = 100;
    private static int minPress = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int targetChannel = Integer.parseInt(br.readLine());
        minPress = Math.abs(targetChannel - DEFAULT_CHANNEL);

        int brokenCount = Integer.parseInt(br.readLine());
        if (brokenCount == 0) {
            minPress = Math.min(minPress, String.valueOf(targetChannel).length());
        } else {
            brokenBtnArr = new boolean[10];
            for (String btn : br.readLine().split(" ")) {
                brokenBtnArr[Integer.parseInt(btn)] = true;
            }
            if (brokenCount < 10) {
                minPress = Math.min(minPress, findMinPress(targetChannel));
            }
        }

        System.out.println(minPress);
    }

    private static int findMinPress(int targetChannel) {
        int minPress = Integer.MAX_VALUE;
        for (int press = 0;; press++) {
            int upChannel = targetChannel + press;
            int downChannel = targetChannel - press;

            if (downChannel < 0 && upChannel > MAX_CHANNEL)
                break;

            if (downChannel >= 0) {
                int count = getDigitCount(downChannel);
                if (count > 0) {
                    return press + count;
                }
            }

            if (upChannel <= MAX_CHANNEL) {
                int count = getDigitCount(upChannel);
                if (count > 0) {
                    return press + count;
                }
            }

            if (minPress < press)
                break;
        }

        return minPress;
    }

    private static int getDigitCount(int number) {
        if (number == 0)
            return brokenBtnArr[0] ? -1 : 1;

        int count = 0;
        while (number > 0) {
            if (brokenBtnArr[number % 10])
                return -1;
            number /= 10;
            count++;
        }
        return count;
    }
}
