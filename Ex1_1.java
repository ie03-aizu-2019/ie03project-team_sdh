import java.util.Scanner;

class Location{
    int x = 0;
    int y = 0;
    int num = 0;
}

public class Ex1_1{
    public static final double EPS = Math.pow(10,-8);
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int N = 0,M = 0,P = 0,Q = 0;
        int p1_x = 0,p1_y = 0,q1_x = 0,q1_y = 0;
        int p2_x = 0,p2_y = 0,q2_x = 0,q2_y = 0;
        double matrix_A = 0,s = 0,t = 0,x_result = 0,y_result = 0;
        int n = 1;
        int count = 0;
        double eps = EPS;
        int[] m = new int[4];

        Location[] location = new Location[4];
        for(int i=0;i<4;i++){
            location[i] = new Location();
        }



        // 入力
        N = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
        Q = scanner.nextInt();

        // N分の入力
        for(int i=0;i<N;i++){
            location[i].x = scanner.nextInt();
            location[i].y = scanner.nextInt();
            location[i].num = n;
            n++;
        }

        // M用の入力
        for(int i=0;i<M+2;i++){
            m[i] = scanner.nextInt();
        }

        for(int i=0;i<N;i++){
            switch(count){
                case 0:{
                    p1_x = location[m[i]-1].x;
                    p1_y = location[m[i]-1].y;
                    count++;
                    break;
                }
                case 1:{
                    q1_x = location[m[i]-1].x;
                    q1_y = location[m[i]-1].y;
                    count++;
                    break;
                }
                case 2:{
                    p2_x = location[m[i]-1].x;
                    p2_y = location[m[i]-1].y;
                    count++;
                    break;
                }
                case 3:{
                    q2_x = location[m[i]-1].x;
                    q2_y = location[m[i]-1].y;
                    count++;
                    break;
                }
            }
        }

        //交差判定
        matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));

        if(-eps <= matrix_A && matrix_A <= eps){
            System.out.println("NA");
            return;
        }else{
            s = (double)((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / matrix_A;
            t = (double)((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) * (p2_y - p1_y)) / matrix_A;
        }

        // step 3
        if((0<s && s<1) && (0<t && t<1)){
            // 交差あり step 4
            x_result = p1_x + (q1_x - p1_x) * s;
            y_result = p2_y + (q2_y - p2_y) * t;
            System.out.printf("%.5f %.5f\n",x_result,y_result);
        }else{
            System.out.println("NA");
        }
        return;
    }
}