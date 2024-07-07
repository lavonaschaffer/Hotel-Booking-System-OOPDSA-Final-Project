package user_interface.BookingInterface.ui_wireframe;

import javax.swing.*;
import java.awt.*;

public class BookingFrame extends javax.swing.JFrame {

    public static JFrame frame = new JFrame();

    public static void setNewView(String title) {
        //SwingUtilities.invokeLater(() -> {
        frame.setTitle(title);
        frame.getContentPane().removeAll();

        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());
            frame.setResizable(false);
            //frame.setVisible(true);
            frame.revalidate();
            frame.repaint();
        });
        //});
    }
}
