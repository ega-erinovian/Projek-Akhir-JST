package projekjst;

public class Training {
    // Input
    private double A[][], V0[][][]; 
    private int C, mi, mf,maxIter;
    double eps;
    
    // Etc
    private int i, j, h;
    private final int n = A.length, M = A.length;
    double[][] lr;

    // Contructor
    public Training(double[][] A, double[][][] V0, int C, int mi, int mf, int eps, int maxIter) {
        this.A = A;
        this.V0 = V0;
        this.C = C;
        this.mi = mi;
        this.mf = mf;
        this.eps = eps;
        this.maxIter = maxIter;
    }
    
    // Hitung jarak data input dengan pusat cluster
    public double[][] hitungJarak(int v, int m){
        double d[][] = null;
        double V[][][] = hitungPusatClusterBaru(v, m);
        for(i=1; i <=n; i++){
            for(j=1; j<=C; j++){
                d[i][j] = 0;
                for(h=1; h<=M; h++){
                    d[i][j] = d[i][j]+(Math.pow((A[i][h]-V[j][h][v-1]), 2));
                }
                d[i][j]=Math.sqrt(d[i][j]);
            }
        }
        return d;
    }
    
    // hitung alfa
    public double[][][] hitungAlfa(int v, int m){
        double alfa[][][] = null;
        double d[][] = hitungJarak(v, m);
        for(i=1; i<=n; i++){
            for(j=1; j<=C; j++){
                alfa[i][j][v]=0;
                for(h=1; h<=C; h++){
                    alfa[i][j][v] = alfa[i][j][v] + (Math.pow(Math.pow(d[i][j],2)/Math.pow(d[i][h],2), (1/(m-1))));
                }
                alfa[i][j][v] = Math.pow(alfa[i][j][v], (-m));
            }
        }
        return alfa;
    }
    
    // hitung laju pembelajaran
    public double[][] hitungLajuPembelajaran(int v, int m){
        double[][][] alfa = hitungAlfa(v, m);
        // sum matrix
        int alfaSum = 0;
        for(i=0; i<alfa.length;i++){
            for(j=0; j<C; j++){
                alfaSum += alfa[i][j][v];
            }
        }
        
        for(j=1; j<=C; j++){
            lr[j][v] = 1/alfaSum;
        }
        
        return lr;
    }
    
    // hitung pusat cluster baru
    public double[][][] hitungPusatClusterBaru(int v, int m){
        double V[][][] = V0;
        double[][][] alfa = hitungAlfa(v, m);
        double[][] lr = hitungLajuPembelajaran(v, m);
        double sm;
        for(j=1; j<=C; j++){
            for(h=1; h<=M; h++){
                sm = 0;
                for(i=1; i<n; i++){
                    sm = sm + (alfa[i][j][v]*(A[i][h]-V[j][h][v-1]));
                }
                V[j][h][v] = V[j][h][v-1] + lr[j][v]*sm;
            }
        }
        return V;
    }
    
    // hitung error
    public double[] hitungError(int v, int m, double[] E){
        double V[][][] = hitungPusatClusterBaru(v, m);
        for(j=1; j<=C; j++){
            for(h=1; h<=M; h++){
                E[v]=E[v]+Math.pow((V[j][h][v]-V[j][h][v-1]),2);
            }
        }
        return E;
    }
    
    // Iterasi
    public void iterasi(){
        double V[][][] = V0;
        double alfa[][][] = null;
        double E[] = {1};
        int v = 1;
        int m;
        while(v<=maxIter && E[v]>eps){
            // Menaikkan iterasi
            v++;
            
            // hitung pangkat pembobot
            m = mi + (v-1)*((mf-mi)/maxIter);
            
            V = hitungPusatClusterBaru(v, m);
            alfa = hitungAlfa(v, m);
            E = hitungError(v, m, E);
        }
    }
}
