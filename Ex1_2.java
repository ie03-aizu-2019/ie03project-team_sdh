import java.util.Scanner;

class Location {
    int x = 0;
    int y = 0;
    int num = 0;
}
class Intersection{
    double x = 0;
    double y = 0;
}

public class Ex1_2{
    public static final double EPS = Math.pow(10, -8);
    public static final int max_N = 200;
    public static final int max_M = 100;

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int N = 0,M = 0,P = 0,Q = 0;
        int p1_x = 0,p1_y = 0,q1_x = 0,q1_y = 0;
        int p2_x = 0,p2_y = 0,q2_x = 0,q2_y = 0;
        double matrix_A = 0,s = 0,t = 0,x_result = 0,y_result = 0;
        int n = 1;
        int count = 0;
        double eps = EPS;
        int[] m = new int[max_M];
        int i,j;
        int count_2 = 0;
        double temp_x = 0,temp_y = 0;

        Location[] location = new Location[max_N];
        for(i=0;i<max_N;i++){
            location[i] = new Location();
        }

        Intersection[] intersection = new Intersection[max_M];
        for(i=0;i<max_M;i++){
            intersection[i] = new Intersection();
        }

        // 入力
        N = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
        Q = scanner.nextInt();

        // N分の入力
        for(i=0;i<N;i++){
            location[i].x = scanner.nextInt();
            location[i].y = scanner.nextInt();
            location[i].num = n;
            n++;
        }

        // M用の入力
        for(i=0;i<M*2;i++){
            m[i] = scanner.nextInt();
        }

        i = 0;

        // jのループで一つの線分を決め、iのループでそれぞれの線分と交差しているかを判定する
        for(j=0;j<M*2;){
            for(i=j;i<M*2;){
                if(i == j) i = i+2;
                else{
                    for(count=0;count<4;count++){
                        switch(count){
                            case 0:{
                                p1_x = location[m[j]-1].x;
                                p1_y = location[m[j]-1].y;
                                
                                break;
                            }
                            case 1:{
                                q1_x = location[m[j+1]-1].x;
                                q1_y = location[m[j+1]-1].y;
                                
                                break;
                            }
                            case 2:{
                                p2_x = location[m[i]-1].x;
                                p2_y = location[m[i]-1].y;
                                
                                break;
                            }
                            case 3:{
                                q2_x = location[m[i+1]-1].x;
                                q2_y = location[m[i+1]-1].y;
                                
                                break;
                            }
                        }
                    }
                    i = i + 2;

                    //交差判定
                    matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));

                    if(-eps <= matrix_A && matrix_A <= eps){
                        System.out.println("NA");
                    }else{
                        s = ((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / (double)matrix_A;
                        t = ((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) * (p2_y - p1_y)) / (double)matrix_A;
                    }
                
                    // step 3
                    if((0<s && s<1) && (0<t && t<1)){
                        // 交差あり step 4
                        intersection[count_2].x = p1_x + (q1_x - p1_x) * s;
                        intersection[count_2].y = p2_y + (q2_y - p2_y) * t;
                        count_2++;
                    }
                }
            }
            j = j + 2;
        }

        // バブルソート
        for(i=0;i<count_2 - 1;i++){
            for(j=count_2 - 1;j>i;j--){
                if(intersection[j-1].x > intersection[j].x){
                    temp_x = intersection[j-1].x;
                    temp_y = intersection[j-1].y;
                    intersection[j-1].x = intersection[j].x;
                    intersection[j-1].y = intersection[j].y;
                    intersection[j].x = temp_x;
                    intersection[j].y = temp_y;
                }else if(intersection[j-1].x == intersection[j].x){
                    if(intersection[j-1].y > intersection[j].y){
                        temp_x = intersection[j-1].x;
                        temp_y = intersection[j-1].y;
                        intersection[j-1].x = intersection[j].x;
                        intersection[j-1].y = intersection[j].y;
                        intersection[j].x = temp_x;
                        intersection[j].y = temp_y;
                    }
                }

            }
        }

        // 出力
        for(i=0;i<count_2;i++){
            System.out.printf("%.5f %.5f\n",intersection[i].x,intersection[i].y);
        }

    return;
    }
}