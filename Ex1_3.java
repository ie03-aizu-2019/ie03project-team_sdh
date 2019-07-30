import java.math.BigDecimal;
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
class Intersection_Coo{
    int num = 0; //ダイクストラ配列のインデックスに使う
    double x = 0;
    double y = 0; 
}

public class Ex1_3{
    public static final double EPS = Math.pow(10, -8);
    public static final int max_N = 1000;
    public static final int max_M = 500;
    public static final int max_Q = 100;
    public static final int INFTY = 1000000;
    public static final int max = 10000;
    public static final int WHITE = 0;
    public static final int GRAY = 1;
    public static final int BLACK = 2;

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int N = 0,M = 0,P = 0,Q = 0;
        int p1_x = 0,p1_y = 0,q1_x = 0,q1_y = 0;
        int p2_x = 0,p2_y = 0,q2_x = 0,q2_y = 0;
        double matrix_A = 0,s = 0,t = 0,x_result = 0,y_result = 0;
        int n = 1;
        int count = 0;
        double eps = EPS;
        int i,j;
        int count_intersection = 0; //交差の数(C1,C2,...のカウントに必要)
        double temp_x = 0,temp_y = 0;
        String[][] q = new String[max_Q][3];
        int count_tmp = 0;
        
        Intersection[] intersection_loc = new Intersection[499]; //交差判定の重複を防ぐための配列
        for(i=0;i<499;i++){
            intersection_loc[i] = new Intersection();
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

        Location[] location = new Location[N];
        for(i=0;i<N;i++){
            location[i] = new Location();
        }

        int[] m = new int[M*2];

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

        //Q用の入力
        for(i=0;i<Q;i++){
            for(j=0;j<3;j++){
                q[i][j] = scanner.next();
            }
        }

        // iのループで一つの線分を決め、jのループでそれぞれの線分と交差しているかを判定する
        //　交差の数だけ数えることができる
        for(i=0;i<M*2;){
            for(j=i;j<M*2;){
                if(i == j) j = j+2;
                else{
                    for(count=0;count<4;count++){
                        switch(count){
                            case 0:{
                                p1_x = location[m[i]-1].x;
                                p1_y = location[m[i]-1].y;
                                
                                break;
                            }
                            case 1:{
                                q1_x = location[m[i+1]-1].x;
                                q1_y = location[m[i+1]-1].y;
                                
                                break;
                            }
                            case 2:{
                                p2_x = location[m[j]-1].x;
                                p2_y = location[m[j]-1].y;
                                
                                break;
                            }
                            case 3:{
                                q2_x = location[m[j+1]-1].x;
                                q2_y = location[m[j+1]-1].y;
                                
                                break;
                            }
                        }
                    }
                    j = j + 2;
                    
                    

                    //交差判定
                    matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));

                    if(-eps <= matrix_A && matrix_A <= eps){
                        
                        
                    }else{
                        s = ((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / (double)matrix_A;
                        t = ((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) * (p2_y - p1_y)) / (double)matrix_A;
                    }

                    
                
                    // step 3
                    if((0<s && s<1) && (0<t && t<1)){
                        // 交差あり step 4
                        
                        intersection[count_intersection].x = p1_x + (q1_x - p1_x) * s;
                        intersection[count_intersection].y = p2_y + (q2_y - p2_y) * t;
                        count_intersection++;
                    }
                }
            }
            i = i+2;
        }
        //ここで交差の数分かる
        
        
        double[][] array_dijkstra = new double[N+count_intersection][N+count_intersection]; //ダイクストラのための配列。元の座標の数+交差の数での配列
        for(i=0;i<N+count_intersection;i++){
            for(j=0;j<N+count_intersection;j++){
                array_dijkstra[i][j] = INFTY;
            }
        }


        Intersection_Coo[] arr_intersection = new Intersection_Coo[count_intersection]; //交差座標のための配列
        for(i=0;i<count_intersection;i++){
            arr_intersection[i] = new Intersection_Coo();
        }

        count_intersection = 0;

        
        for(i=0;i<M*2;i=i+2){
            for(j=0;j<M*2;j=j+2){
                if(i == j) continue;
                else{
                    for(count=0;count<4;count++){
                        switch(count){
                            case 0:{
                                p1_x = location[m[i]-1].x;
                                p1_y = location[m[i]-1].y;
                                
                                break;
                            }
                            case 1:{
                                q1_x = location[m[i+1]-1].x;
                                q1_y = location[m[i+1]-1].y;
                                
                                break;
                            }
                            case 2:{
                                p2_x = location[m[j]-1].x;
                                p2_y = location[m[j]-1].y;
                                
                                break;
                            }
                            case 3:{
                                q2_x = location[m[j+1]-1].x;
                                q2_y = location[m[j+1]-1].y;
                                
                                break;
                            }
                        }
                    }

                    
                    //交差判定
                    matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));

                    if(-eps <= matrix_A && matrix_A <= eps){
                        
                        
                    }else{
                        s = ((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / (double)matrix_A;
                        t = ((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) * (p2_y - p1_y)) / (double)matrix_A;
                    }

                    
                
                    // step 3
                    if((0<s && s<1) && (0<t && t<1)){
                        // 交差あり step 4

                        
                        
                        int frag = 0;
                        double d_x = p1_x + (q1_x - p1_x) * s;
                        String tmp_x = String.valueOf(d_x);
                        double d_y = p2_y + (q2_y - p2_y) * t;
                        String tmp_y = String.valueOf(d_y);
                        BigDecimal bd_x = new BigDecimal(tmp_x);
                        BigDecimal bd_y = new BigDecimal(tmp_y);
                        bd_x = bd_x.setScale(10, BigDecimal.ROUND_HALF_UP);
                        bd_y = bd_y.setScale(10, BigDecimal.ROUND_HALF_UP);
                        
                        



                        for(int k=0;k<count_intersection;k++){
                            if((bd_x.doubleValue() == arr_intersection[k].x) && (bd_y.doubleValue() == arr_intersection[k].y)){
                                frag = 1;
                            }
                        }

                        if(frag == 0){
                            
                            arr_intersection[count_intersection].x = bd_x.doubleValue();
                            arr_intersection[count_intersection].y = bd_y.doubleValue();
                            count_intersection++;
                        }

                        
                    }
                }
                
            }
        }
        
        

        // arr_intersection配列をxが小さい順に、そしてyが小さい順にバブルソートする
        for(i=0;i<count_intersection-1;i++){
            for(j=count_intersection-1;j>i;j--){
                if(arr_intersection[j-1].x > arr_intersection[j].x){
                    temp_x = arr_intersection[j-1].x;
                    temp_y = arr_intersection[j-1].y;
                    arr_intersection[j-1].x = arr_intersection[j].x;
                    arr_intersection[j-1].y = arr_intersection[j].y;
                    arr_intersection[j].x = temp_x;
                    arr_intersection[j].y = temp_y;
                }
                else if(arr_intersection[j-1].x == arr_intersection[j].x){
                    if(arr_intersection[j-1].y < arr_intersection[j].y){
                        temp_x = arr_intersection[j-1].x;
                        temp_y = arr_intersection[j-1].y;
                        arr_intersection[j-1].x = arr_intersection[j].x;
                        arr_intersection[j-1].y = arr_intersection[j].y;
                        arr_intersection[j].x = temp_x;
                        arr_intersection[j].y = temp_y;
                    }
                }
            }
        }
        //arr_intersection配列のnumに値を入れていく
        for(i=0;i<count_intersection;i++){
            arr_intersection[i].num = N+i; // C1,C2,...,Cn
        }
        
        
        


        //再び交差判定、ここでダイクストラのための配列に入れる値を計算する
        for(i=0;i<M*2;i = i+2){
            
            Intersection_Coo[] line_intersec = new Intersection_Coo[499]; //線分ごとに交差地点を保管する一次元配列
            for(int k=0;k<499;k++){
                line_intersec[k] = new Intersection_Coo();
            }
            int count_line_intersec = 0; //ある線分の交差座標の数をカウントする
            int start,end; //距離計算の始点と終点に使う
            double line_length;//距離
            

            for(j=0;j<M*2;j = j+2){
                if(i == j) continue;
                else{
                    for(count=0;count<4;count++){
                        switch(count){
                            case 0:{
                                p1_x = location[m[i]-1].x;
                                p1_y = location[m[i]-1].y;
                                
                                break;
                            }
                            case 1:{
                                q1_x = location[m[i+1]-1].x;
                                q1_y = location[m[i+1]-1].y;
                                
                                break;
                            }
                            case 2:{
                                p2_x = location[m[j]-1].x;
                                p2_y = location[m[j]-1].y;
                                
                                break;
                            }
                            case 3:{
                                q2_x = location[m[j+1]-1].x;
                                q2_y = location[m[j+1]-1].y;
                                
                                break;
                            }
                        }
                    }
                    //交差判定
                    matrix_A = ((q1_x - p1_x) * (p2_y - q2_y)) + ((q2_x - p2_x) * (q1_y - p1_y));
                    //交差がないときにs,tの値どうする?
                    if(-eps <= matrix_A && matrix_A <= eps){
                        //System.out.println("NA");
                        
                    }else{
                        s = ((p2_y - q2_y) * (p2_x - p1_x) + (q2_x - p2_x) * (p2_y - p1_y)) / (double)matrix_A;
                        t = ((p1_y - q1_y) * (p2_x - p1_x) + (q1_x - p1_x) * (p2_y - p1_y)) / (double)matrix_A;
                    }
                
                    // step 3
                    if((0<s && s<1) && (0<t && t<1)){
                        // 交差あり step 4

                        double d_x = p1_x + (q1_x - p1_x) * s;
                        String tmp_x = String.valueOf(d_x);
                        double d_y = p2_y + (q2_y - p2_y) * t;
                        String tmp_y = String.valueOf(d_y);
                        BigDecimal bd_x = new BigDecimal(tmp_x);
                        BigDecimal bd_y = new BigDecimal(tmp_y);
                        bd_x = bd_x.setScale(10, BigDecimal.ROUND_HALF_UP);
                        bd_y = bd_y.setScale(10, BigDecimal.ROUND_HALF_UP);


                        line_intersec[count_line_intersec].x = bd_x.doubleValue();
                        line_intersec[count_line_intersec].y = bd_y.doubleValue();

                        for(int l=0;l<count_intersection;l++){
                            if(arr_intersection[l].x==line_intersec[count_line_intersec].x && arr_intersection[l].y==line_intersec[count_line_intersec].y){
                                line_intersec[count_line_intersec].num = arr_intersection[l].num;
                                
                            }
                            
                        }
                        count_line_intersec++;
                    }
                }
            }
            

            
            
            //ここで一つの線分を視点とした交差判定が終了
            //line_intersec配列をnumでソートする
            for(int k=0;k<count_line_intersec-1;k++){
                for(j=count_line_intersec-1;j>k;j--){
                    if(line_intersec[j-1].x > line_intersec[j].x){
                        temp_x = line_intersec[j-1].x;
                        temp_y = line_intersec[j-1].y;
                        line_intersec[j-1].x = line_intersec[j].x;
                        line_intersec[j-1].y = line_intersec[j].y;
                        line_intersec[j].x = temp_x;
                        line_intersec[j].y = temp_y;
                    }
                    else if(line_intersec[j-1].x == line_intersec[j].x){
                        if(line_intersec[j-1].y > line_intersec[j].y){
                            temp_x = line_intersec[j-1].x;
                            temp_y = line_intersec[j-1].y;
                            line_intersec[j-1].x = line_intersec[j].x;
                            line_intersec[j-1].y = line_intersec[j].y;
                            line_intersec[j].x = temp_x;
                            line_intersec[j].y = temp_y;
                        }
                    }
                }
            }
            
            //ここから距離計算して、ダイクストラ配列に入れる
            if(count_line_intersec != 0){
                for(int l=0;l<=count_line_intersec;l++){
                    if(m[i]>m[i+1]){
                        //line_intersecを逆ソートする
                        count_tmp++;
                        for(int k=0;k<count_line_intersec-1;k++){
                            for(j=count_line_intersec-1;j>k;j--){
                                if(line_intersec[j-1].x < line_intersec[j].x){
                                    temp_x = line_intersec[j-1].x;
                                    temp_y = line_intersec[j-1].y;
                                    line_intersec[j-1].x = line_intersec[j].x;
                                    line_intersec[j-1].y = line_intersec[j].y;
                                    line_intersec[j].x = temp_x;
                                    line_intersec[j].y = temp_y;
                                }
                                else if(line_intersec[j-1].x == line_intersec[j].x){
                                    if(line_intersec[j-1].y < line_intersec[j].y){
                                        temp_x = line_intersec[j-1].x;
                                        temp_y = line_intersec[j-1].y;
                                        line_intersec[j-1].x = line_intersec[j].x;
                                        line_intersec[j-1].y = line_intersec[j].y;
                                        line_intersec[j].x = temp_x;
                                        line_intersec[j].y = temp_y;
                                    }
                                }
                            }
                        }
                    }

                    if(l==0){
                        start = m[i]-1;
                        //end = line_intersec[l];
                        line_length = Math.sqrt((location[start].x - line_intersec[l].x) * (location[start].x - line_intersec[l].x) + (location[start].y - line_intersec[l].y) * (location[start].y - line_intersec[l].y));
                        array_dijkstra[start][line_intersec[l].num] = line_length;
                        array_dijkstra[line_intersec[l].num][start] = line_length;
                        
                    }
                    else if(l == count_line_intersec){
                        end = m[i+1]-1;
                        line_length = Math.sqrt((line_intersec[l-1].x - location[end].x) * (line_intersec[l-1].x - location[end].x) + (line_intersec[l-1].y - location[end].y) * (line_intersec[l-1].y - location[end].y));
                        array_dijkstra[line_intersec[l-1].num][end] = line_length;
                        array_dijkstra[end][line_intersec[l-1].num] = line_length;
                        
                    }
                    else{
                        line_length = Math.sqrt((line_intersec[l-1].x - line_intersec[l].x) * (line_intersec[l-1].x - line_intersec[l].x) + (line_intersec[l-1].y - line_intersec[l].y) * (line_intersec[l-1].y - line_intersec[l].y));
                        array_dijkstra[line_intersec[l-1].num][line_intersec[l].num] = line_length;
                        array_dijkstra[line_intersec[l].num][line_intersec[l-1].num] = line_length;
                        
                    }
                


                }//ここでダイクストラには値が入っている
            }
        
            //交差がない時
            if(count_line_intersec==0){
                start = m[i]-1;
                end = m[i+1]-1;
                line_length = Math.sqrt((location[start].x - location[end].x) * (location[start].x - location[end].x) + (location[start].y - location[end].y) * (location[start].y - location[end].y));
                array_dijkstra[start][end] = line_length;
                array_dijkstra[end][start] = line_length;
                
            }

            
        }

        
        //Qの処理
        for(i=0;i<Q;i++){
            int start=0,end=0;
            int judge = 0; //有効な値かどうか判定
            for(j=0;j<2;j++){
                //startがCで始まるかどうか
                if(j==0){
                    char tmp = q[i][j].charAt(0);
                    if(tmp == 'C'){
                        int str_len;
                        str_len = q[i][j].length();
                        String str_num = q[i][j].substring(1);
                        start = N - 1 + Integer.parseInt(str_num);
                        if(start > N+count_intersection){
                            System.out.println("NA");
                            judge = 1;
                        }
                    }
                    else{
                        start = Integer.parseInt(q[i][j]) - 1;
                    }
                }
                if(j==1){
                    char tmp = q[i][j].charAt(0);
                    if(tmp == 'C'){
                        int str_len;
                        str_len = q[i][j].length();
                        String str_num = q[i][j].substring(1);
                        end = N - 1 + Integer.parseInt(str_num);
                        if(end > N+count_intersection && judge != 1){
                            System.out.println("NA");
                            judge = 1;
                        }
                    }
                    else{
                        end = Integer.parseInt(q[i][j]) - 1;
                    }
                }
            }
            if(judge == 0){
            // ダイクストラ
                int num_vertex = N + count_intersection;
                double min = 0;
                double[] dist = new double[N + count_intersection];
                int[] color = new int[N + count_intersection];

                for(j=0;j<num_vertex;j++){
                    dist[j] = INFTY;
                    color[j] = WHITE;
                }

                dist[start] = 0;
                color[start] = GRAY;
                while(true){
                    min = INFTY;
                    int u = -1;
                    for(int k=0;k<num_vertex;k++){
                        if(min > dist[k] && color[k] != BLACK){
                            u = k;
                            min = dist[k];
                        }
                    }
                    if(u==-1) break;
                    color[u] = BLACK;
                    for(int v=0;v<num_vertex;v++){
                        if(color[v] != BLACK && array_dijkstra[u][v] != INFTY){
                            if(dist[v] > dist[u] + array_dijkstra[u][v]){
                                dist[v] = dist[u] + array_dijkstra[u][v];
                                color[v] = GRAY;
                            }
                        }
                    }
                }
                System.out.printf("%.5f\n",dist[end]);
            }
        }

    }
}