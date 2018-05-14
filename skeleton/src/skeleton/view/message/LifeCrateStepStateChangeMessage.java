package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateStepStateChangeMessage extends StepStateChangeMessage {
    public final LifeCrate lifeCrate;

    /**
     * Egy sz�vecsk�s l�d�t rajzol ki a fel�leten egy megadott poz�ci�ba,
     *  ezzel anim�lva egy l�p�st.
     * 
     * @param lifeCrate			A tolt sz�vecsk�s l�da.
     * @param fieldFrom			A mez�, melyikr�l a sz�vecsk�s l�da leker�l.
     * @param fieldTo			A mez�, melyikre a sz�vecsk�s l�da ker�l.
     * @param perc				Az �llapotv�ltoz�s �zenet konkr�t t�pusa
     */
    public LifeCrateStepStateChangeMessage(LifeCrate lifeCrate, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.LifeCrateStep, fieldFrom, fieldTo, perc);
        this.lifeCrate = lifeCrate;
    }
}