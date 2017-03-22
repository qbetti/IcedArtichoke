package ca.liflab;

import ca.uqac.lif.ecp.atomic.AtomicEvent;

import java.text.ParseException;

/**
 * Created by quent on 17/03/2017.
 */
public class Action {

    // Unique identifier for the subject of the action in the document
    private String m_subjectId;

    // Possible type of an action (add, remove, modify, ...)
    private String m_type;

    // New content for the subject if any
    private String m_data;


    public Action(String subjectId, String type, String data) {
        m_subjectId = subjectId;
        m_type = type;
        m_data = data;
    }

    @Override
    public String toString() {
        return "{" + m_subjectId + "|" + m_type + "|" + m_data + "}";
    }

    public static Action parseString (String toParse) throws ParseException {
        try {
            String s = toParse.substring(1, toParse.length() - 1);
            String[] parts = s.split("\\|");

            // TODO : see how to escape the "|" in the data field

            if (parts.length < 3)
                throw new ParseException("String should be of the form {subjectId|type|data}", -1);

            return new Action(parts[0], parts[1], parts[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AtomicEvent toAtomicEvent () {
        return new AtomicEvent(m_type + "|" + m_subjectId);
    }
}
