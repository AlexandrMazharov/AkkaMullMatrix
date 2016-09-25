package actors;

import akka.actor.UntypedActor;
import messenges.CalculateSection;
import messenges.Calculated;

import java.util.stream.IntStream;

public class WorkerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof CalculateSection) {
            CalculateSection section = (CalculateSection) message;
            int res = IntStream.range(0, section.getColumn().length).map(x -> section.getColumn()[x]*section.getRow()[x]).sum();
            Calculated calculated = new Calculated(section.getRowNumber(), section.getColumnNumber(), res);
            getSender().tell(calculated, self());
        } else {
            unhandled(message);
        }
    }
}
