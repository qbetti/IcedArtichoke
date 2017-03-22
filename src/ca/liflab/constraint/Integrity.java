package ca.liflab.constraint;

import ca.liflab.Action;

/**
 * Created by quent on 19/03/2017.
 */
public class Integrity implements Checkable {


    @Override
    public boolean check(Action[] actions) {
        return false;
    }
}
