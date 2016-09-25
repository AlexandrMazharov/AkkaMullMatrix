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

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("MultiplierSystem");
        ActorRef mainActor = system.actorOf(Props.create(MainActor.class), "myactor");
        Matrix firstMatrix = getMatrix();
        Matrix secondMatrix = getMatrix();

        Matrix matrix3 = getMatrix();
        Matrix matrix4 = getMatrix();


        Timeout timeout = new Timeout(Duration.create(1, TimeUnit.MINUTES));
        Future<Object> future = Patterns.ask(mainActor, new CalculateMatrixMultiplication(firstMatrix, secondMatrix),
                timeout);
        Future<Object> future1 = Patterns.ask(mainActor, new CalculateMatrixMultiplication(matrix3, matrix4),
                timeout);


        Matrix res = (Matrix) Await.result(future, timeout.duration());

        Matrix res1 = (Matrix) Await.result(future1, timeout.duration());
        System.out.println(res.toString());

        System.out.println(res1.toString());
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

        return new Matrix(n,m,a);
    }
}
