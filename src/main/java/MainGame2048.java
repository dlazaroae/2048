import java.util.Random;
import java.util.Scanner;

public class MainGame2048 {
    public static void main(String[] args) {
        int n = 0;
        int table[][] = new int[4][4];
        while (n < 25) {


//            table[1][1]=2;
//            table[2][1]=2;




            if (gameOver(table)) {
                System.out.println("Game Over! D:");
                break;}


            if (n == 0) {
                n = init(n, table);
                print(table);
            }
            System.out.println("n=" + n);
            shift(table);


            addNewValue(table);
            print(table);
        }
    }

    private static int[] addRD(int x[]) {
        int a=3;
        if(x[a]==x[a-1]){
            x[a]=x[a]*2;
            x[a-1]=0;
        }
        a=2;
        if(x[a]==x[a-1]){
            x[a]=x[a]*2;
            x[a-1]=0;
        }
        a=1;
        if(x[a]==x[a-1]){
            x[a]=x[a]*2;
            x[a-1]=0;
        }
        return x;
    }

    private static int[] addLU(int x[]) {
        int a=0;
        if(x[a]==x[a+1]){
            x[a]=x[a]*2;
            x[a+1]=0;
        }
        a=1;
        if(x[a]==x[a+1]){
            x[a]=x[a]*2;
            x[a+1]=0;
        }
        a=2;
        if(x[a]==x[a+1]){
            x[a]=x[a]*2;
            x[a+1]=0;
        }
        return x;
    }

    private static boolean gameOver(int[][] table) {
        int GO = 0;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (table[i][j] == 0)
                    GO++;
            }
        }
        int possibleMoves = 0;
        if (GO == 0) {
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (table[i][j] == table[i][j + 1]) {
                        possibleMoves++;
                    }
                }
            }
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (table[j][i] == table[i][j + 1]) {
                        possibleMoves++;
                    }
                }
            }
        }
        if (possibleMoves == 0) {
            return false;
//            System.out.println("Game over!");
        }
        return true;
    }

    private static void addNewValue(int[][] table) {
        int g, h;
        Random random = new Random();
        do {
            g = showRandomInteger(0, 3, random);
            h = showRandomInteger(0, 3, random);

        } while (table[g][h] != 0);
        table[g][h] = 2;
    }

    private static void shift(int[][] table) {


        Scanner in = new Scanner(System.in);
        int dir = in.nextInt();
        Direction direction = Direction.fromVal(dir);
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            shiftLR(table, direction);
        } else {
            shiftUD(table, direction);
        }


    }

    private static void shiftUD(int[][] table, Direction direction) {
        int x[] = new int[4];
        for (int j = 0; j <= 3; j++) {
            if (direction == Direction.UP) {
                int a = 0;
                x = new int[4];
                for (int i = 0; i <= 3; i++) {
                    if (table[i][j] != 0) {
                        x[a] = table[i][j];
                        a++;
                    }
                }
               addLU(x);



                for (a = 0; a <= 3; a++)
                    table[a][j] = x[a];
                x = new int[4];
                a = 0;
                for (int i = 0; i <= 3; i++) {
                    if (table[i][j] != 0) {
                        x[a] = table[i][j];
                        a++;
                    }
                }
                for (a = 0; a <= 3; a++)
                    table[a][j] = x[a];
            } else if (direction == Direction.DOWN) {
                int a = 3;
                x = new int[4];
                for (int i = 3; i >= 0; i--) {
                    if (table[i][j] != 0) {
                        x[a] = table[i][j];
                        a--;
                    }
                }
                for (a = 3; a >= 0; a--) {
                    if (x[a] == x[a - 1]) {
                        x[a] = x[a] * 2;
                        x[a - 1] = 0;
                    }
                    if (a == 1)
                        break;
                }
                for (a = 3; a >= 0; a--) {
                    table[a][j] = x[a];
                }
                x = new int[4];
                a = 3;
                for (int i = 3; i >= 0; i--) {
                    if (table[i][j] != 0) {
                        x[a] = table[i][j];
                        a--;
                    }
                }
                for (a = 3; a >= 0; a--) {
                    table[a][j] = x[a];
                }
            }

        }
    }

    private static void shiftLR(int[][] table, Direction direction) {
        int y[] = new int[4];

        for (int i = 0; i <= 3; i++) {
            if (direction == Direction.LEFT) {
                int a = 0;
                y = new int[4];
                for (int j = 0; j <= 3; j++) {
                    if (table[i][j] != 0) {
                        y[a] = table[i][j];
                        a++;
                    }
                }
                for (a = 0; a <= 2; a++) {
                    if (y[a] == y[a + 1]) {
                        y[a] = y[a] * 2;
                        y[a + 1] = 0;
                    }

                }
                for (a = 0; a <= 3; a++) {
                    table[i][a] = y[a];
                }
                y = new int[4];
                a = 0;
                for (int j = 0; j <= 3; j++) {
                    if (table[i][j] != 0) {
                        y[a] = table[i][j];
                        a++;
                    }
                }
                for (a = 0; a <= 3; a++) {
                    table[i][a] = y[a];
                }
            } else if (direction == Direction.RIGHT) {
                int a = 3;
                y = new int[4];
                for (int j = 3; j >= 0; j--) {
                    if (table[i][j] != 0) {
                        y[a] = table[i][j];
                        a--;
                    }
                }
                for (a = 3; a >= 0; a--) {
                    if (y[a] == y[a - 1]) {
                        y[a] = y[a] * 2;
                        y[a - 1] = 0;
                    }
                    if (a == 1)
                        break;
                    for (a = 3; a >= 0; a--) {
                        table[i][a] = y[a];
                    }
                    a = 3;
                    y = new int[4];
                    for (int j = 3; j >= 0; j--) {
                        if (table[i][j] != 0) {
                            y[a] = table[i][j];
                            a--;
                        }
                    }
                    for (a = 3; a >= 0; a--) {
                        table[i][a] = y[a];
                    }
                }

            }
        }
    }

    private static void print(int[][] table) {
     /*   for (int i = 0; i <= 3; i++) {

            System.out.println(table[i][0] + " - " + table[i][1] + " - " + table[i][2] + " - " + table[i][3]);

        }
        System.out.println("\n");*/

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    private static int init(int n, int[][] table) {
        int y = 0;
        int z = 0;
        n++;

        int nr[] = new int[15];
        int START = 0;
        int END = 3;
        Random random = new Random();

        for (int idx = 1; idx <= 2; ++idx) {
            y = showRandomInteger(START, END, random);
            z = showRandomInteger(START, END, random);

            if (idx == 1) {
                table[y][z] = 2;
            } else {
                table[y][z] = 4;
            }

        }
        return n;
    }


    private static int showRandomInteger(int aStart, int aEnd, Random aRandom) {
        int y;
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber = (int) (fraction + aStart);
        y = randomNumber;
        return y;
    }


}
//        for(int i=0;i<=3;i++){
//                for(int j=1;j<=4;j++){
//                    if(table[i][j]==0){
//                        table[i][j]= table[i][j-1];
//                        table[i][j-1]=0;