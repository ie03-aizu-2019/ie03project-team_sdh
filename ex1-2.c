#include<stdio.h>

#define EPS 1E-08
#define max_N 200
#define max_M 100

typedef struct{
    double x;
    double y;
    int num;
}Location;

int main(){
    int N,M,P,Q;
    Location location[max_N] = {0}; //x,y座標
    Location intersection[max_M] = {0}; //交点座標
    int m[max_M];
    int p1_x,p1_y,q1_x,q1_y;
    int p2_x,p2_y,q2_x,q2_y;
    double matrix_A,s,t,x_result,y_result;
    int n = 1;
    int count = 0;
    int count_2 = 0;
    int i,j;
    double temp_x,temp_y;

    //入力
    scanf("%d%d%d%d",&N,&M,&P,&Q);

    //N分の入力
    for(i=0;i<N;i++){
        scanf("%lf%lf",&location[i].x,&location[i].y);
        location[i].num = n;
        n++;
    }

    //M用の入力
    for(i=0;i<M*2;i++){
        scanf("%d",&m[i]);
    }

    i = 0;

    //jのループで一つの線分を決め、iのループでそれぞれの線分と交差しているかを判定する
    for(j = 0; j < M*2;){
        for(i = j; i < M*2;){
            if(i == j) i = i + 2;
            else {
                for(count = 0; count < 4; count++){
                    switch (count){
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
               
        

                //ここから交差判定
                matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));
            
                if(-EPS <= matrix_A && matrix_A <= EPS){
                    printf("NA_1\n");

                    
                }else{
                    s = (double)((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / matrix_A;
                    t = (double)((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) *  (p2_y - p1_y)) / matrix_A;
                }
            
                //step 3
                if((0<s && s<1) == 1 && (0<t && t<1) == 1){
                    //交差あり step4
                    intersection[count_2].x = p1_x + (q1_x - p1_x) * s;
                    intersection[count_2].y = p2_y + (q2_y - p2_y) * t;
                    count_2++;
                }
            }
        }
        j = j + 2;
    }

    //バブルソート
    for(i = 0; i < count_2-1; i++){
        for(j = count_2-1; j > i; j--){
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

    //出力
    for(i = 0; i<count_2; i++) printf("%.5f %.5f\n",intersection[i].x,intersection[i].y);

    return 0;
}
