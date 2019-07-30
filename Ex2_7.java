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

public class Ex2_7{
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

        N = scanner.nextInt();
        M = scanner.nextInt();
        P = scanner.nextInt();
        Q = scanner.nextInt();

        for(i=0;i<N;i++){
            location[i].x = scanner.nextInt();
            location[i].y = scanner.nextInt();
            location[i].num = n;
            n++;
        }

        for(i=0;i<M*2;i++){
            m[i] = scanner.nextInt();
        }

        i = 0;

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

                    matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));

                    if(-eps <= matrix_A && matrix_A <= eps){
                        System.out.println("NA");
                    }else{
                        s = ((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / (double)matrix_A;
                        t = ((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) * (p2_y - p1_y)) / (double)matrix_A;
                    }

                    if((0<s && s<1) && (0<t && t<1)){
                        intersection[count_2].x = p1_x + (q1_x - p1_x) * s;
                        intersection[count_2].y = p2_y + (q2_y - p2_y) * t;
                        count_2++;
                    }
                }
            }
            j = j + 2;
        }

        for(i=0;i<count_2 - 1;i++){
            for(j=count_2 - 1;j>i;j--){
                if(intersection[j-1].x > intersection[j].x){
                    temp_x = intersection[j-1].x;
                    temp_y = intersection[j-1].y;
                    intersection[j-1].x = intersection[j].x;
                    intersection[j-1].y = intersection[j].y;
                    intersection[j].x = temp_x;
                    intersection[j].y = temp_y;
                }
            }
        }

        for(i=0;i<count_2;i++){
            //System.out.printf("%.5f %.5f\n",intersection[i].x,intersection[i].y);
        }
        int []temp = new int[P*2];
        for(i=0;i<(P*2);){
          temp[i] = scanner.nextInt();
          temp[i+1] = scanner.nextInt();
          i=i+2;
        }
        double a, b, temp_a, temp_b, px, py, cx, cy, at, bt, temp_d,x1,x2,y1,y2,d1,d2;
        double d;
        d=100000;
        cy =0;
        temp_a=0;
        temp_b=0;
        for(i=0;i<(P*2);){
          px=temp[i];
          py=temp[i+1];
          for(j=0;j<M*2;){

            a=((double)location[m[j+1]-1].y-(double)location[m[j]-1].y)/((double)location[m[j+1]-1].x-(double)location[m[j]-1].x);
            b=(double)location[m[j+1]-1].y-a*(double)location[m[j+1]-1].x;
            //System.out.printf("(%d,%d) : a=( %d - %d)/( %d - %d )= %f\n",m[j],m[j+1],location[m[j+1]-1].y,location[m[j]-1].y,location[m[j+1]-1].x,location[m[j]-1].x,a);
            //System.out.printf("(%d,%d) : b = %d - (%.5f x %d) = %f\n",m[j],m[j+1],location[m[j+1]-1].y,a,location[m[j+1]-1].x,b);
            if(a==0 || b==0){
              j=j+2;
              continue;
            }
            at=(-1)*(1/a);
            bt=py-(at*px);
            cx=(b-bt)/(at-a);
            //System.out.printf("at : %f\n",at);
            cy=at*cx+bt;
            temp_d=(cx-px)*(cx-px)+(cy-py)*(cy-py);
            temp_d=Math.sqrt(temp_d);
            //System.out.printf("%.0f %.0f:%.5f %.5f\n",px,py,a,b);
            //System.out.printf("%.5f ",temp_d);

            /*if(location[m[j+1]-1].x >= location[m[j]-1].x){
              x1 = location[m[j+1]-1].x;
              x2 = location[m[j]-1].x;
            }
            else{
              x2 = location[m[j+1]-1].x;
              x1 = location[m[j]-1].x;
            }
            if(location[m[j+1]-1].y >= location[m[j]-1].y){
              y1 = location[m[j+1]-1].y;
              y2 = location[m[j]-1].y;
            }
            else{
              y2 = location[m[j+1]-1].y;
              y1 = location[m[j]-1].y;
            }
            System.out.printf("P(%.0f,%.0f) : x1=%.1f , x2=%.1f , y1=%.1f , y2=%.1f\n" ,px,py,x1,x2,y1,y2);
            if(x1 <= px || x2 >= px || y1 <= py || y2 >= py && x1 != 100000){
              d1 = (location[m[j+1]-1].x-px)*(location[m[j+1]-1].x-px)+(location[m[j+1]-1].y-py)*(location[m[j+1]-1].y-py);
              d1 = Math.sqrt(d1);
              d2 = (location[m[j]-1].x-px)*(location[m[j]-1].x-px)+(location[m[j]-1].y-py)*(location[m[j]-1].y-py);
              d2 = Math.sqrt(d2);
              if(d1 < d2){
                temp_d = d1;
                temp_a = location[m[j]-1].x;
                temp_b = location[m[j]-1].y;
              }
              else{
                temp_d = d2;
                temp_a = location[m[j+1]-1].x;
                temp_b = location[m[j+1]-1].y;
              }
              d1 = 100000;
              d2 = 100000;
              x1 = 100000;
              x2 = 100000;
              y1 = 100000;
              y2 = 100000;


              j=j+2;

            }
            */
            if(d>temp_d && temp_a <100000 && temp_b < 100000  ){
              d=temp_d;
              temp_a=cx;
              temp_b=cy;
            }
            j=j+2;
          }

          System.out.printf("%f %f\n",temp_a,temp_b);
          d=100000;
          i=i+2;
        }
    return;
    }

}
