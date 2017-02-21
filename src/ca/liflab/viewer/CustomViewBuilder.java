package ca.liflab.viewer;

import org.icepdf.ri.common.SwingViewBuilder;
import javax.swing.*;


/**
 * Created by quent on 21/02/2017.
 */
public class CustomViewBuilder extends SwingViewBuilder {

    private CustomController customViewerController;

    public CustomViewBuilder(CustomController c) {
        super(c);
        this.customViewerController = c;
    }


    // Override method to add a JMenu to MenuBar
    @Override
    public JMenuBar buildCompleteMenuBar() {
        JMenuBar menuBar = super.buildCompleteMenuBar();

        // Add any menu item by calling this.addToMenuBar(menuBar, ...);
        this.addToMenuBar(menuBar, this.buildSecurityMenu());

        return menuBar;
    }


    public JMenu buildSecurityMenu() {
        JMenu menu = new JMenu("Securité");
        this.addToMenu(menu, this.buildIntegrityCheckItem());

        return menu;
    }

    public JMenuItem buildIntegrityCheckItem() {
        JMenuItem mi = this.makeMenuItem("Vérifier intégrité", null);
        if(this.customViewerController != null && mi != null) {
            this.customViewerController.setIntegrityCheckMenuItem(mi);
        }

        return mi;
    }
}
