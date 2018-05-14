package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateStepStateChangeMessage extends StepStateChangeMessage {
    public final Crate crate;
    
    /**
     * Egy ládát rajzol ki a felületen egy megadott pozícióba, ezzel animálva egy lépést.
     * 
     * @param crate				A tolt láda.
     * @param fieldFrom			A mezõ, melyikrõl a láda lekerül.
     * @param fieldTo			A mezõ, melyikre a láda kerül.
     * @param perc				Az állapotváltozás üzenet konkrét típusa
     */
    public CrateStepStateChangeMessage(Crate crate, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.CrateStep, fieldFrom, fieldTo, perc);
        this.crate = crate;
    }
}