package skeleton.view;

public class WorkerStepStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;

    public WorkerStepStateChangeMessage(int playerIndex){
        super(StateChangeMessageType.WorkerStep);
        this.playerIndex = playerIndex;
    }
}