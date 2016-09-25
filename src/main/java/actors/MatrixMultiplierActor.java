package actors;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import data.Matrix;
import messenges.CalculateMatrixMultiplication;
import messenges.CalculateSection;
import messenges.Calculated;

public class MatrixMultiplierActor extends UntypedActor {

    private int[][] resultMatrix;
    private ActorRef sender;
    private int numberOfUncountedVales;

    @Override
    public void onReceive(Object message) {
        if (message instanceof CalculateMatrixMultiplication) {
            sender = getSender();
            Matrix firstMatrix = ((CalculateMatrixMultiplication) message).getFirstMatrix();
            Matrix secondMatrix = ((CalculateMatrixMultiplication) message).getSecondMatrix();
            if (firstMatrix.getNumberOfColumns() != secondMatrix.getNumberOfRows()) {
                throw new IllegalArgumentException(String.format("NumberOfColumns of the first matrix " +
                        "should be equals to numberOfRows of the second matrix! " +
                        "FirstMatrix: '%s', SecondMatrix: '%s'", firstMatrix, secondMatrix));
            }
            resultMatrix = new int[firstMatrix.getNumberOfRows()][secondMatrix.getNumberOfColumns()];
            numberOfUncountedVales = firstMatrix.getNumberOfRows() * secondMatrix.getNumberOfColumns();
            for (int i = 0; i < firstMatrix.getNumberOfRows(); i++) {
                for (int j = 0; j < secondMatrix.getNumberOfColumns(); j++) {
                    CalculateSection section = new CalculateSection(i, j, firstMatrix.getRow(i), secondMatrix.getColumn(j));
                    context().actorOf(Props.create(WorkerActor.class)).tell(section, self());}
           }
        } else if (message instanceof Calculated) {
            Calculated msg = (Calculated) message;
            resultMatrix[msg.getRowNumber()][msg.getColumnNumber()] = msg.getResult();
            getSender().tell(PoisonPill.getInstance(), getSelf());
            numberOfUncountedVales--;
            if (numberOfUncountedVales == 0) {
                sender.tell(new Matrix(resultMatrix.length, resultMatrix[0].length, resultMatrix), self());
            }
        } else {
            unhandled(message);
        }
    }
}
