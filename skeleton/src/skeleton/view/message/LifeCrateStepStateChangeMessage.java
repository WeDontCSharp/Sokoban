package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateStepStateChangeMessage extends StepStateChangeMessage {
    public final LifeCrate lifeCrate;

    /**
     * Egy szívecskés ládát rajzol ki a felületen egy megadott pozícióba,
     *  ezzel animálva egy lépést.
     * 
     * @param lifeCrate			A tolt szívecskés láda.
     * @param fieldFrom			A mezõ, melyikrõl a szívecskés láda lekerül.
     * @param fieldTo			A mezõ, melyikre a szívecskés láda kerül.
     * @param perc				Az állapotváltozás üzenet konkrét típusa
     */
    public LifeCrateStepStateChangeMessage(LifeCrate lifeCrate, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.LifeCrateStep, fieldFrom, fieldTo, perc);
        this.lifeCrate = lifeCrate;
    }
}