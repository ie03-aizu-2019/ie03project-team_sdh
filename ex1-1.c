#include<stdio.h>

#define EPS 1E-08

typedef struct{
    int x;
    int y;
    int num;
}Location;

int main(){
    int N,M,P,Q;
    Location location[4] = {0}; //x,y座標
    int m[4];
    int p1_x,p1_y,q1_x,q1_y;
    int p2_x,p2_y,q2_x,q2_y;
    double matrix_A,s,t,x_result,y_result;
    int n = 1;
    int count = 0;


    //入力
    scanf("%d%d%d%d",&N,&M,&P,&Q);

    //N分のinput loop
    for(int i=0;i<N;i++){
        scanf("%d%d",&location[i].x,&location[i].y);
        location[i].num = n;
        n++;
    }

    //M用のinput loop
    for(int i=0;i<M+2;i++){
        scanf("%d",&m[i]);
    }

    for(int i=0;i<N;i++){
            switch (count){
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

    //ここから交差判定
    matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));
    printf("%f\n",matrix_A);
    if(-EPS <= matrix_A && matrix_A <= EPS){
        printf("NA\n");

        return 0;
    }else{
        s = (double)((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / matrix_A;
        t = (double)((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) *  (p2_y - p1_y)) / matrix_A;
    }
    printf("%f,%f\n",s,t);
    //step 3
    if((0<=s && s<=1) == 1 && (0<=t && t<=1) == 1){
        //交差あり step4
        x_result = p1_x + (q1_y - p1_y) * s;
        y_result = p2_y + (q2_y - p2_y) * t;

        printf("%.5f %.5f\n",x_result,y_result);
    }else{
        printf("NA\n");
    }



    return 0;
}