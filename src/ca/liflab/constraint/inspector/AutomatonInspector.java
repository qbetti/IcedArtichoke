package ca.liflab.constraint.inspector;


import ca.liflab.Action;
import ca.liflab.constraint.LifecycleInspector;
import ca.uqac.lif.ecp.atomic.Automaton;
import ca.uqac.lif.ecp.atomic.StateShallowHistory;
import ca.uqac.lif.structures.MathList;
import ca.uqac.lif.structures.MathSet;

/**
 * Created by quent on 19/03/2017.
 */
public class AutomatonInspector implements LifecycleInspector {

    private static final String INVALID_LABEL = "invalid";
    private static final int SSHISTORY_SIZE = 2;

    private Automaton m_automaton;
    private StateShallowHistory m_sshistory;
    private MathSet<MathList<Integer>> m_category;

    private int m_invalidState;
    private int m_previousState;
    private int m_currentState;


    public AutomatonInspector(Automaton automaton) {
        m_automaton = automaton;
        m_sshistory = new StateShallowHistory(automaton, SSHISTORY_SIZE);
        m_category = m_sshistory.getStartClass();

        m_previousState = -1;
        m_currentState = m_automaton.getInitialVertex().getId();
        m_invalidState = searchInvalidState();
    }


    @Override
    public void processAction(Action a) {
        m_category = m_sshistory.read(a.toAtomicEvent());
        m_currentState = getCurrentState();
        m_previousState = getPreviousState();

        System.out.println(m_previousState + " -> [" + a.toAtomicEvent() + "] -> " + m_currentState);
    }

    @Override
    public boolean check(Action[] actions) {

        for (Action a : actions) {
            processAction(a);

            if(isCurrentStateInvalid())
                return false;
        }

        return isCurrentStateInvalid();
    }

    @Override
    public void reset() {
        m_sshistory.reset();
        m_currentState = m_automaton.getInitialVertex().getId();
    }

    private int getCurrentState() {
        int currentVertexId = -1;

        for (MathList<Integer> states : m_category) {
            currentVertexId = states.get(SSHISTORY_SIZE - 1);
        }

        return currentVertexId;
    }

    private int getPreviousState() {
        int previousState = -1;

        for (MathList<Integer> states : m_category) {
            previousState = states.get(0);
        }

        return previousState;
    }


    private boolean isCurrentStateInvalid() {
        return m_currentState == m_invalidState;
    }

    private int searchInvalidState() {
        return m_automaton.getLabelling().getWithValue(new MathSet<>(INVALID_LABEL));
    }



}
