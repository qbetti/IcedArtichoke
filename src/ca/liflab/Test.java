package ca.liflab;

import ca.liflab.constraint.Lifecycle;
import ca.liflab.constraint.LifecycleFactory;
import ca.uqac.lif.ecp.Trace;
import ca.uqac.lif.ecp.atomic.AtomicEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by quent on 17/03/2017.
 */
public class Test {


    private static final String AUTOMATON_DOT_FILE_PATH  = "C:/Users/quent/Desktop/extension/IcedArtichoke/test/automaton-test.dot";
    private static final String PETRI_NET_FILE_PATH  = "C:/Users/quent/Desktop/extension/IcedArtichoke/test/petrinet-test.txt";
    private static final String TRACE_FILE_PATH  = "C:/Users/quent/Desktop/extension/IcedArtichoke/test/trace-test.txt";



    public static void main(String[] args) {

        Lifecycle fsm = LifecycleFactory.fromAutomaton(AUTOMATON_DOT_FILE_PATH);
        assert fsm != null;

        Lifecycle petriNet = LifecycleFactory.fromPetriNet(PETRI_NET_FILE_PATH);
        assert petriNet != null;


        List<Action[]> actions = getActions();

        for (Action[] eActions : actions) {
            System.out.println("=== FSM ===");
            fsm.check(eActions);
            fsm.reset();
            System.out.println();

            System.out.println("=== PetriNet ===");
            petriNet.check(eActions);
            petriNet.reset();
            System.out.println();

        }
    }



    public static Set<Trace<AtomicEvent>> getTraces() {
        Set<Trace<AtomicEvent>> traces = null;
        try {
            traces = AtomicEvent.readTestSuite(new Scanner(new File(TRACE_FILE_PATH)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return traces;
    }


    public static List<Action[]> getActions() {
        Set<Trace<AtomicEvent>> traces = getTraces();
        List<Action[]> actions = new ArrayList<>();

        for (Trace<AtomicEvent> trace: traces ) {
            Action[] eActions = new Action[trace.size()];

            for (int i = 0; i < trace.size(); i++) {
                try {
                    eActions[i] = (Action.parseString(trace.get(i).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            actions.add(eActions);
        }

        return actions;
    }

}
