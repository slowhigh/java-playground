import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String chen = "80000";
        String[] chenArr = chen.split("");
        int minPress = Math.abs(Integer.parseInt(chen) - 100);
        int chenLen = chenArr.length;
        boolean[] brokenBtnArr = new boolean[10];
        int brokenCount = Integer.parseInt("2");
        if (brokenCount >= 10) {
            System.out.println(minPress);
            return;
        } else if (brokenCount == 0) {
            System.out.println(chenLen);
            return;
        }
        for (String btn : "8 9".split(" ")) {
            brokenBtnArr[Integer.parseInt(btn)] = true;
        }

        int btn;
        int totalPress = chenLen;
        for (int i = 0; i < chenLen; i++) {
            btn = Integer.parseInt(chenArr[chenLen - i - 1]);
            int press = 0;

            while ((btn - press) >= 0 || (btn + press) <= 9) {
                if ((btn + press) <= 9 && !brokenBtnArr[btn + press])
                    break;
                
                if ((btn - press) >= 0 && !brokenBtnArr[btn - press])
                    break;

                press++;
            }

            totalPress += Math.pow(10, i) * press;
            if (minPress < totalPress) {
                System.out.println(minPress);
                return;
            }
        }

        System.out.println(totalPress != 0 ? Math.min(minPress, totalPress) : minPress);
    }
}