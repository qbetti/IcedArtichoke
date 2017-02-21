package ca.liflab; /**
 * Created by quent on 21/02/2017.
 */

import org.icepdf.ri.common.ComponentKeyBinding;
import ca.liflab.viewer.CustomController;
import ca.liflab.viewer.CustomViewBuilder;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        CustomController controller = new CustomController();
        CustomViewBuilder factory = new CustomViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        // add copy keyboard command
        ComponentKeyBinding.install(controller, viewerComponentPanel);

        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));


        // Create a JFrame to display the panel in
        JFrame window = factory.buildViewerFrame();

        window.setTitle("Iced Artichoke");

        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
