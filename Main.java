import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 0 0 0 0 X 2
        // 1 0 0 X 2 2
        // 1 1 1 2 2 2
        // 0 0 0 X 2 2

        // - 방문 비트 마스킹
        // - if 방문 == 0
        // - if X <= 3
        //     - 0 -> X
        // - else
        //     - 0 -> 2
        // - if 방문 == X
        // - if X > 3
        //     - X -> 2
        // - else 
        //     - no action

        // for 방문 기준으로 상하좌우
        // - 신규 in rect
        // - 2(바이러스), 1(벽)은 안됨
        // - 방문 유무 확인(이거 조건 생각해봐야함)
        // - 재귀호출

        // - 재귀호출할 때가 없다?
        // max = max vs (all - 1 - X(무조건 놔야 하니까 고정 3) - 2)



        
    }
}