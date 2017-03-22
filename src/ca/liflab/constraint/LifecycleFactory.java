package ca.liflab.constraint;

import ca.liflab.constraint.inspector.AutomatonInspector;
import ca.liflab.constraint.inspector.PetriNetInspector;
import ca.uqac.lif.ecp.atomic.AtomicEvent;
import ca.uqac.lif.ecp.atomic.Automaton;
import ca.uqac.lif.ecp.petrinet.AtomicPetriNetBuilder;
import ca.uqac.lif.ecp.petrinet.PetriNet;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by quent on 19/03/2017.
 */
public class LifecycleFactory {


    public static Lifecycle fromAutomaton(String dotFilePath) {
        Automaton automaton = null;

        try {
            Scanner s = new Scanner(new File(dotFilePath));
            automaton = Automaton.parseDot(s);

            AutomatonInspector inspector = new AutomatonInspector(automaton);
            return new Lifecycle(inspector);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Lifecycle fromPetriNet(String filePath) {
        PetriNet<AtomicEvent> net = null;

        try {
            Scanner s = new Scanner(new File(filePath));
            net = AtomicPetriNetBuilder.parseFromString(s);

            PetriNetInspector inspector = new PetriNetInspector(net);
            return new Lifecycle(inspector);

        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }



}
