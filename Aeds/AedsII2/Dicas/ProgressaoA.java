class ProgressaoA{

    static int progArit(Double a, Double b, int n){
        int resp = 0;

        for(int i=0; i<n; i++){
            resp += a + (b*i);
        }
        return resp;
    }

    static Double progAritt(Double a, Double b, int n){
        return n*(a+(a+(b*(n-1))))/2;                           //n*(a0+an)/2
    }

    public static void main(String[] args){
        System.out.println(progArit(10.0,3.0,4));
        System.out.println(progAritt(10.0,3.0,4));
    }
}