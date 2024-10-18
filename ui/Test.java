package mapgame.ui;

import java.util.Random;

public class Test {
    static int[][] win = {{1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}};
    static int[][] data = {{1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,16}};

    public static boolean judge() {
        System.out.println("判断结束");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(data[i][j]!=win[i][j]){
                    System.out.println("lose");
                    return false;
                }
            }
        }
        System.out.println("win");
        return true;
    }

    public static void initData() {
        int[] temparr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random = new Random();
        for (int i = 0; i < temparr.length; i++) {
            int num = random.nextInt(temparr.length);
            int tmp = temparr[i];
            temparr[i] = temparr[num];
            temparr[num] = tmp;

        }

        for (int i = 0; i < temparr.length; i++) {
//            if (temparr[i]==0){
//                x = i/4;
//                y = i%4;
//            }
            data[i/4][i%4] = temparr[i];
        }

    }
}
