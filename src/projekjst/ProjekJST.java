//package projekjst;
//import java.lang.Math.*;
//
//public class ProjekJST {
//    public static void main(String[] args) {
//    }
//    
//    public static double[] training(double A[][], double V0[][][], int C, int mi, int mf, int eps, int maxIter){
//        double output[][][][];
//        // [n, M] = A.length;
//        double V[][][] = V0;
//        int v = 1;
//        double E[] = {1};
//        int m, i, j, n = 100, h, M = 100;
//        double d[][] = {{0}};
//        double alfa[][][] = {{{0}}};
//        double sm;
//        
//        // Iterasi
//        while(v<=maxIter && E[v]>eps){
//            // Menaikkan iterasi
//            v++;
//            
//            // Hitung pangkat pembobot
//            m = mi + (v-1)*((mf-mi)/maxIter);
//            
//            // Hitung jarak data input dengan pusat cluster
//            for(i=1; i < n; i++){
//                for(j=1; j<C; j++){
//                    d[i][j] = 0;
//                    for(h=1; h<M; h++){
//                         d[i][j] = d[i][j]+(Math.pow((A[i][h]-V[j][h][v-1]), 2));
//                    }
//                    d[i][j]=Math.sqrt(d[i][j]);
//                }
//            }
//            
//            // hitung alfa
//            for(i=1; i<n; i++){
//                for(j=1; j<C; j++){
//                    alfa[i][j][v]=0;
//                    for(h=1; h<C; h++){
//                        alfa[i][j][v] = alfa[i][j][v] + (Math.pow(Math.pow(d[i][j],2)/Math.pow(d[i][h],2), (1/(m-1))));
//                    }
//                    alfa[i][j][v] = Math.pow(alfa[i][j][v], (-m));
//                }
//            }
//            
//            // hitung laju pembelajaran
//            for(j=1; j<C; j++){
//                for(h=1; h<M; h++){
//                    sm = 0;
//                    for(i=1; i<n; i++){
//                        sm = sm + alfa[i][j][v]*(A[i][h]-V[j][h][v-1]);
//                    }
//                }
//            }
//            
//            // hitung error
//            E[v] = 0;
//            for(j=1; j<C; j++){
//                for(h=1; h<M; h++){
//                    E[v]=E[v]+Math.pow((V[j][h][v]-V[j][h][v-1]),2);
//                }
//            }
//        } 
//    }
//}
