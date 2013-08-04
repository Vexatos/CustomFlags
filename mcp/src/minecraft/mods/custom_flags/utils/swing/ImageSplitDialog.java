package mods.custom_flags.utils.swing;

import net.minecraft.util.StatCollector;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Aaron on 4/08/13.
 */
public class ImageSplitDialog extends JDialog {

    BufferedImage image;
    int max_x;
    int max_y;

    int xSections = 4;
    int ySections = 4;

    int selectedX = 0;
    int selectedY = 0;

    JSlider xSectionsSlider;
    JSlider ySectionsSlider;

    JSlider xSelectioSlider;
    JSlider ySelectioSlider;

    JPanel imagePanel;





    public ImageSplitDialog(BufferedImage image, int max_x, int max_y) {
        this.image = image;
        this.max_x = max_x;
        this.max_y = max_y;



        this.setTitle("Image Splitter");
        this.setModal(true);

        this.setLayout(new BorderLayout());

        xSectionsSlider = new JSlider(1, max_x, max_x);
        xSectionsSlider.createStandardLabels(1);
        xSectionsSlider.setPaintTicks(true);
        xSectionsSlider.setPaintLabels(true);
        xSectionsSlider.setMajorTickSpacing(1);


        ySectionsSlider = new JSlider(JSlider.VERTICAL, 1, max_y, max_y);
        ySectionsSlider.createStandardLabels(1);
        ySectionsSlider.setPaintTicks(true);
        ySectionsSlider.setPaintLabels(true);
        ySectionsSlider.setMajorTickSpacing(1);


        imagePanel = new JPanel();
        imagePanel.setMinimumSize(new Dimension(300, 300));
        imagePanel.setPreferredSize(new Dimension(300, 300));


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(new JLabel(StatCollector.translateToLocal("gui.max_x_sections")));
        mainPanel.add(xSectionsSlider);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel2.add(imagePanel);




        panel2.add(ySectionsSlider);

        mainPanel.add(panel2);


        this.setLayout(new BorderLayout());
        this.add(mainPanel);






        /*


        imagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        this.add(imagePanel, BorderLayout.CENTER);





        JPanel controls1 = new JPanel();
        controls1.setLayout(new BorderLayout());

        controls1.add(new JLabel(StatCollector.translateToLocal("gui.max_x_sections")));
        controls1.add(xSectionsSlider);

        this.add(controls1, BorderLayout.NORTH);


        JPanel controls2 = new JPanel();
        controls2.setLayout(new BorderLayout());

        controls2.add(new JLabel(StatCollector.translateToLocal("gui.max_y_sections")));
        controls2.add(ySectionsSlider);

        this.add(controls2, BorderLayout.EAST);
        */


        this.pack();

    }






}
