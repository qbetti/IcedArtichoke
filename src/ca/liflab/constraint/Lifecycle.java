package ca.liflab.constraint;

import ca.liflab.Action;

/**
 * Created by quent on 19/03/2017.
 */
public class Lifecycle implements Checkable {

    private LifecycleInspector m_inspector;


    public Lifecycle (LifecycleInspector inspector) {

        m_inspector = inspector;

    }

    public void reset() {
        m_inspector.reset();
    }


    @Override
    public boolean check(Action[] actions) {

        if(m_inspector.check(actions)) {
            System.out.println("=> Lifecycle is verified.");
            return true;
        } else {
            System.out.println("=> Action log does not comply with Lifecycle");
            return false;
        }

    }





}
