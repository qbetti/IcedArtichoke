package ca.liflab.constraint.inspector;

import ca.liflab.Action;
import ca.liflab.constraint.LifecycleInspector;
import ca.uqac.lif.ecp.atomic.AtomicEvent;
import ca.uqac.lif.ecp.petrinet.PetriNet;
import ca.uqac.lif.ecp.petrinet.Transition;

/**
 * Created by quent on 19/03/2017.
 */
public class PetriNetInspector implements LifecycleInspector {

    private PetriNet<AtomicEvent> m_net;
    private boolean m_isActionFired;


    public PetriNetInspector(PetriNet<AtomicEvent> net) {
        m_net = net;
        m_isActionFired = false;
    }


    @Override
    public void processAction(Action a) {
        m_isActionFired = m_net.fire(new Transition<>(a.toAtomicEvent()));

        if(m_isActionFired)
            System.out.println("[" + a.toAtomicEvent() + "] fired");
        else
            System.out.println("[" + a.toAtomicEvent() + "] is not fireable");


    }

    @Override
    public boolean check(Action[] actions) {

        for (Action a : actions) {

            processAction(a);
            if(!m_isActionFired){
                return false;
            }
        }

        return true;
    }

    @Override
    public void reset() {
        m_net.reset();

    }
}
