package ca.liflab.constraint.inspector;

import ca.liflab.Action;
import ca.liflab.constraint.LifecycleInspector;

/**
 * Created by quent on 22/03/2017.
 */
public class QEAutomatonInspector implements LifecycleInspector {


    @Override
    public void processAction(Action a) {

    }

    @Override
    public boolean check(Action[] actions) {
        return false;
    }

    @Override
    public void reset() {

    }
}
