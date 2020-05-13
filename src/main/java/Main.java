import actors.MainActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import data.Matrix;
import messenges.CalculateMatrixMultiplication;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static Matrix getStandartMatrixA() {
        int rows = 3;
        int column = 3;

        int a[][] = {{1,2,3},{-3,4,10},{2,1,5}};
                //{{1,2},{-3,4}};

        Matrix matrix = new Matrix(rows, column, a);
        return matrix;

    }

    public static Matrix getRandomMatrix(int rows, int column) {


        int a [][] = new int[rows][column] ;

        int min = 100;
        int max = 200;
        int diff = max - min;

        for (int k =0; k<rows;k++)
            for (int p =0; p<column;p++){
                Random random = new Random();
                int i = random.nextInt(diff + 1);
                i += min;
                a [k][p] = i;
            }
        Matrix matrix = new Matrix(rows, column, a);
        return matrix;
    }

    public static Matrix getStandartMatrixB() {
        int rows = 3;
        int column = 3;

        int a [][] = {{-2, 4,5},{3, 1,5},{2,4,5}};
        //{{-2, 4},{3, 1}};

        Matrix matrix = new Matrix(rows, column, a);
        return matrix;

    }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("MultiplierSystem");
        ActorRef mainActor = system.actorOf(Props.create(MainActor.class), "myactor");
        /*
        Matrix firstMatrix = getMatrix();
        Matrix secondMatrix = getMatrix();

        Matrix matrix3 = getMatrix();
        Matrix matrix4 = getMatrix();
*/
        int r =5;
        int c = 5;
        //Matrix firstMatrix = getRandomMatrix(r,c);
         Matrix firstMatrix= getStandartMatrixA();
        firstMatrix.toString();
        //Matrix secondMatrix = getRandomMatrix(r,c);
        Matrix secondMatrix = getStandartMatrixB();
        secondMatrix.toString();

        Timeout timeout = new Timeout(Duration.create(1, TimeUnit.MINUTES));
        long startTime = System.currentTimeMillis();
        Future<Object> future = Patterns.ask(mainActor, new CalculateMatrixMultiplication(firstMatrix, secondMatrix),
                timeout);

        Matrix res = (Matrix) Await.result(future, timeout.duration());

        System.out.println(res.toString());
        System.out.println((double) (System.currentTimeMillis() - startTime));
    }

    public static Matrix getMatrix() {
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = scan.nextInt();
            }
        }

        return new Matrix(n, m, a);
    }
}
