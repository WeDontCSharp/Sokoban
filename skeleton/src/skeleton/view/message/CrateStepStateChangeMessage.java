package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateStepStateChangeMessage extends StepStateChangeMessage {
    public final Crate crate;
    
    /**
     * Egy l�d�t rajzol ki a fel�leten egy megadott poz�ci�ba, ezzel anim�lva egy l�p�st.
     * 
     * @param crate				A tolt l�da.
     * @param fieldFrom			A mez�, melyikr�l a l�da leker�l.
     * @param fieldTo			A mez�, melyikre a l�da ker�l.
     * @param perc				Az �llapotv�ltoz�s �zenet konkr�t t�pusa
     */
    public CrateStepStateChangeMessage(Crate crate, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.CrateStep, fieldFrom, fieldTo, perc);
        this.crate = crate;
    }
}