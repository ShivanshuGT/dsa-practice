import java.util.Scanner;



public class Patterns {

    public static void printPattern1(int n){
        for(int i = 0; i< n ; i++){
            for(int j = 0; j < n ; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printPattern2(int n){
        for(int i = 0; i< n ; i++){
            for(int j = 0; j < i + 1; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printPattern3(int n){
        for(int i = 0; i< n ; i++){
            for(int j = 0; j < i + 1; j++){
                System.out.print(j+1+ " ");
            }
            System.out.println();
        }
    }

    public static void printPattern4(int n){
        for(int i = 0; i< n ; i++){
            for(int j = 0; j < i + 1; j++){
                System.out.print(i+1+ " ");
            }
            System.out.println();
        }
    }

    public static void printPattern5(int n){
        for(int i = 0; i< n ; i++){
            for(int j = n; j > i; j--){
                System.out.print( "* ");
            }
            System.out.println();
        }
    }

    public static void printPattern6(int n){
        for(int i = 0; i< n ; i++){
            for(int j = n; j > i; j--){
                System.out.print( n-j+1+ " ");
            }
            System.out.println();
        }
    }

    public static void printPattern7(int n){
        for(int i = 1; i<= n ; i++){
            for(int j = n-i; j > 0; j--){
                System.out.print("  ");
            }
            for(int j = 0; j < ( 2*i -1 ); j++){
                System.out.print("* ");
            }
            for(int j = n-i; j > 0; j--){
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void printPattern8(int n){
        for(int i = 1; i<= n ; i++){
            for(int j = 1; j < i; j++){
                System.out.print("  ");
            }
            for(int j = 2*(n-i) + 1; j > 0; j--){
                System.out.print("* ");
            }
            for(int j = 1; j < i; j++){
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void printPattern9(int n){
        for(int i = 1; i<= n ; i++){
            for(int j = n-i; j > 0; j--){
                System.out.print("  ");
            }
            for(int j = 0; j < ( 2*i -1 ); j++){
                System.out.print("* ");
            }
            for(int j = n-i; j > 0; j--){
                System.out.print("  ");
            }
            System.out.println();
        }
        for(int i = 1; i<= n ; i++){
            for(int j = 1; j < i; j++){
                System.out.print("  ");
            }
            for(int j = 2*(n-i) + 1; j > 0; j--){
                System.out.print("* ");
            }
            for(int j = 1; j < i; j++){
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void printPattern10(int n){
        for(int i = 1; i<n ; i++){
            for(int j = 0; j < i; j++){
                System.out.print("* ");
            }
            System.out.println();
        }
        for(int i = 0; i<n ; i++){
            for(int j = n; j > i; j--){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void printPattern11(int n){
        for(int i = 1; i<=n ; i++){
            for(int j = 1; j <= i; j++){
                if((i + j) % 2 == 0)
                System.out.print("1 ");
                else
                System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public static void printPattern12(int n){
        for(int i = 1; i<=n ; i++){
            int space = 2*(n - i);
            for(int j = 1; j<=i; j++){
                System.out.print(j);
            }
            while(space >0){
                System.out.print(" ");
                space --;
            }
            for(int j = i; j >= 1 ; j --){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void printPattern13(int n){
        int counter = 1;
        for(int i = 1; i<=n ; i++){
            for(int j = 1; j <= i ; j++){
                System.out.print(counter+ " ");
                counter++;
            }
            System.out.println();
        }
    }

    public static void printPattern14(int n){
        for(int i = 1; i<=n ; i++){
            int starter = 64;
            for(int j = 1; j <= i ; j++){
                System.out.print((char)(starter + j)+ " ");
            }
            System.out.println();
        }
    }

    public static void printPattern15(int n){
        for(int i = 1; i <= n ; i++){
            int starter = 64;
            for(int j = 1; j <= (n-i + 1); j++){
                System.out.print((char)(starter + j)+ " ");
            }
            System.out.println();
        }
    }

    public static void printPattern16(int n){
        int starter = 64;
        for(int i = 1; i<=n ; i++){
            for(int j = 1; j <= i ; j++){
                System.out.print((char)(starter + i)+ " ");
            }
            System.out.println();
        }
    }


    public static void printPattern17(int n){
        for(int i = 1; i <= n; i++){
            int starter = 64;
            for(int j = n-i;j >0;j--){
                System.out.print(" ");
            }
            for(int j = 1; j<= i;j++){
                System.out.print((char)(starter + j));
            }
            for(int j = i-1; j > 0; j--){
                System.out.print((char)(starter + j ));
            }
            for(int j =n-i; j > 0; j--){
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void printPattern18(int n){
        for(int i =n; i>0;i--){
            int starter = 64;
            for(int j = i;j <=n;j++){
                System.out.print((char)(starter + j));
            }
            System.out.println();
        }
    }

    public static void printPattern19(int n){
        for(int i =n; i>0;i--){
            for(int j = i;j > 0;j--){
                System.out.print("*");
            }
            for(int j = 2*i;j<2*n;j++){
                System.out.print(" ");
            }
            for(int j = i;j > 0;j--){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i =1; i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            for(int j=2*(n-i);j>0;j--){
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printPattern20(int n){
        
        for(int i =1; i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            for(int j=2*(n-i);j>0;j--){
                System.out.print(" ");
            }
            for(int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i =n-1; i>0;i--){
            for(int j = i;j > 0;j--){
                System.out.print("*");
            }
            for(int j = 2*i;j<2*n;j++){
                System.out.print(" ");
            }
            for(int j = i;j > 0;j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printPattern21(int n){
        for(int i =1; i<=n;i++){
            for(int j = 1;j <=n;j++){
                if(i == 1 || i ==n || j==1 || j==n){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void printPattern22(int n){
        for(int i =0; i<2*n-1;i++){
            for(int j = 0;j < 2*n-1;j++){
                int top = i;
                int bottom = 2*(n-1) - i;
                int left = j;
                int right = 2*(n-1) - j;
                int value = Math.min(Math.min(top, bottom), Math.min(left, right));
                System.out.print(n-value);
            }
            System.out.println();
        }
    }



    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        // printPattern1(x);
        // printPattern2(x);
        // printPattern3(x);
        // printPattern4(x);
        // printPattern5(x);
        // printPattern6(x);
        // printPattern7(x);
        // printPattern8(x);
        // printPattern9(x);
        // printPattern10(x);
        // printPattern11(x);
        // printPattern12(x);
        // printPattern13(x);
        // printPattern14(x);
        // printPattern15(x);
        // printPattern16(x);
        // printPattern17(x);
        // printPattern18(x);
        // printPattern19(x);
        // printPattern20(x);
        // printPattern21(x);
        printPattern22(x);
        sc.close();
    }
}
