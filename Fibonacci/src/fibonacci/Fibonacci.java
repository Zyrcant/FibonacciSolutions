package fibonacci;
import java.math.BigInteger;

/**
 *
 * @author tdd160030
 */
public class Fibonacci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //first with n = 100,000
        int n = 100000;
        
        //run O(n) Fibonacci and see runtime
        long startTime = System.nanoTime();
        BigInteger m = dynamicFib(n);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("O(n) fibonacci of " + n + " : " + m + "\nRuntime: " + totalTime + " ns\n");
        
        //run O(logn) Fibonacci and see runtime
        startTime = System.nanoTime();
        BigInteger m2 = matrixFib(n);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("O(logn) fibonacci of " + n + " : " + m2 + "\nRuntime: " + totalTime + " ns\n");
        
        ///////////////////////////////////////////////////////////////////////////////////////////////
        
        //next with n = 1000000
        //run O(logn) Fibonacci and see runtime
        n = 1000000;
        startTime = System.nanoTime();
        BigInteger m4 = matrixFib(n);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("O(logn) fibonacci of " + n + " : " + m4 + "\nRuntime: " + totalTime + " ns\n");
        
        //run O(n) Fibonacci and see runtime
        startTime = System.nanoTime();
        BigInteger m3 = dynamicFib(n);
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("O(n) fibonacci of " + n + " : " + m3 + "\nRuntime: " + totalTime + " ns\n");
    }
    
    //dynamic programming
    public static BigInteger dynamicFib(int n)
    {
        BigInteger fib[] = new BigInteger[n+1]; //dynamic programming
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;
        for(int i = 2; i <= n; i++)
        {
            fib[i] = fib[i-1].add(fib[i-2]);
        }
        return fib[n];
    }
    
    //using formula matrix given in class
    public static BigInteger matrixFib(int n)
    {
        BigInteger fib[][] = new BigInteger[][]{{BigInteger.ONE,BigInteger.ONE}, {BigInteger.ONE,BigInteger.ZERO}}; //creates initial matrix
        if (n == 0)
            return BigInteger.ZERO;
        power(fib, n-1);
        return fib[0][0];
    }
    
    public static void power(BigInteger fib[][], int n)
    {
        if(!((n == 0) || (n == 1)))
        {
            BigInteger matrix[][] = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
            power(fib, n/2);
            m(fib, fib);
            if(n%2 != 0)
                m(fib, matrix);
        }
    }
    
    public static void m(BigInteger fib[][], BigInteger matrix[][])
    {
        BigInteger a = (fib[0][0].multiply(matrix[0][0])).add(fib[0][1].multiply(matrix[1][0]));
        BigInteger b = (fib[0][0].multiply(matrix[0][1])).add(fib[0][1].multiply(matrix[1][1]));
        BigInteger c = (fib[1][0].multiply(matrix[0][0])).add(fib[1][1].multiply(matrix[1][0]));
        BigInteger d = (fib[1][0].multiply(matrix[0][1])).add(fib[1][1].multiply(matrix[1][1]));
        fib[0][0] = a;
        fib[0][1] = b;
        fib[1][0] = c;
        fib[1][1] = d;
    }
}
