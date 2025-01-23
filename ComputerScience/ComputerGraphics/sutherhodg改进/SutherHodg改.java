package ComputerScience.ComputerGraphics.sutherhodg改进;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SutherHodg改 extends JComponent {

    public ArrayList<Figure> figures = new ArrayList<>();

    public void clearFigures() {
        figures.clear();
        repaint();
    }
    protected void paintComponent(Graphics g) {
        Graphics2D drp = (Graphics2D) g;
        boolean isFstDote = true;
       // Color color = Color.black;
        for (Figure figure : figures) {
            if (figure instanceof Point && isFstDote) {
                figure.paint(drp, Color.red);
                isFstDote = false;
            } else
                figure.paint(drp, Color.black);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Sutherland-Hodgman改");
        final SutherHodg改 comp = new SutherHodg改();
        comp.setPreferredSize(new Dimension(1000, 600));
        frame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        buttonsPanel.add(clearButton);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setBackground(Color.blue);
        Window window = new Window();
        Polygon polygon = new Polygon();
        comp.figures.add(window);
        comp.figures.add(polygon);
        comp.repaint();

        comp.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){
                Point dote = new Point(e.getX(),e.getY(),false);
                polygon.update(dote);
                comp.figures.add(dote);
                comp.repaint();
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        });

        clearButton.addActionListener(e->{
            polygon.clear();
            comp.clearFigures();
            comp.figures.add(window);
            comp.figures.add(polygon);
        });
        frame.pack();
        frame.setVisible(true);
    }
}
