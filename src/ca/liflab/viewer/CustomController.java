package ca.liflab.viewer;

import ca.liflab.TriggeredBySecurityButton;
import org.icepdf.ri.common.SwingController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by quent on 21/02/2017.
 */
public class CustomController extends SwingController {

    private JMenuItem integrityCheckMenuItem;


    public CustomController() {
        super();
    }


    public void setIntegrityCheckMenuItem(JMenuItem mi) {
        this.integrityCheckMenuItem = mi;
        mi.addActionListener(this);
    }

    public void checkIntegrity() {
        new TriggeredBySecurityButton().run();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        Object source = event.getSource();
        if(source != null) {
            try {
                Runnable e;
                if(source == this.integrityCheckMenuItem) {
                    e = new Runnable() {
                        @Override
                        public void run() {
                            checkIntegrity();
                        }
                    };
                    SwingUtilities.invokeLater(e);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
