import java.util.*;

public class Ex2_8 {

    static int n;
    static boolean[][] s;
    static int[] vis;
    static int[] d;
    static int[] f;
    static int time = 1;
    static int a=0;
    static int b=0;
    static void dfs(int x) {
        vis[x] = 1;
        d[x] = time++;
        for(int i=1; i<=n; i++) {
            if(vis[i] != 1 && s[x][i]) {
                dfs(i);
            }
        }
        f[x] = time++;
    }

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
            s = new boolean[n+1][n+1];
            vis = new int[n+1];
            d = new int[n+1];
            f = new int[n+1];

            for(int i=0;i<=n;i++){
              for(int j=0;j<=0;j++){
                  s[i][j]=false;
              }
            }

            for(int i=0; i<n; i++) {
              int u = sc.nextInt();
              int k = sc.nextInt();
              for(int j=0; j<k; j++) {
                int v = sc.nextInt();
                s[u][v] = true;
                }
              }
              for(a=1; a <= n; a++){
                for(b=a; b <= n ; b++){
                  if(s[a][b] == true){
                    s[a][b]=false;
                    s[b][a]=false;
                    for(int i=1; i<=n; i++) {
                      if(vis[i] == 0) {
                        dfs(i);
                      }
                    }
                    if(f[1] != (2*n)){
                      System.out.println(+a+"->"+b+" is main road.\n");
                      /*for(int i=1; i<=n; i++) {
                          System.out.printf(+i+" : "+d[i]+" "+f[i]+"\n");
                      }
                      System.out.printf("\n");*/
                    }
                    for(int i=0;i<=n; i++){
                      d[i]=0;
                      f[i]=0;
                      vis[i]=0;
                      time=1;
                    }
                    s[a][b]=true;
                    s[b][a]=true;
                  }
                }
              }
            /*for(int i=1; i<=n; i++) {
                System.out.printf("%d %d %d\n", i, d[i], f[i]);
            }*/
        }
    }
}
