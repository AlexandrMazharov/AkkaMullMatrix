package actors;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;
import data.Matrix;
import messenges.CalculateMatrixMultiplication;
import java.util.HashMap;
import java.util.Map;

public class MainActor extends UntypedActor {

    private Map<ActorRef, ActorRef> senders = new HashMap<>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof CalculateMatrixMultiplication) {
            ActorRef calculateActor = context().actorOf(Props.create(MatrixMultiplierActor.class));
            senders.put(calculateActor, getSender());
            calculateActor.tell(message, getSelf());
        } else if (message instanceof Matrix) {
            ActorRef sender = getSender();
            senders.get(sender).tell(message, self());
            senders.remove(sender);
            sender.tell(PoisonPill.getInstance(), self());
        } else {
            unhandled(message);
        }
    }
}
