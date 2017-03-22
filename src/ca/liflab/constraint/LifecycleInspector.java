package ca.liflab.constraint;


import ca.liflab.Action;

/**
 * Created by quent on 19/03/2017.
 */
public interface LifecycleInspector {

    void processAction (Action a);

    boolean check (Action[] actions);

    void reset ();

}
