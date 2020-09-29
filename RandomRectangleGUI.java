import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RandomRectangleGUI {

    JFrame frame;
    RandomRectDrawPanel drawPanel = new RandomRectDrawPanel();
    JButton colorButton;
    JButton sizeButton;

    public static void main(String[] args) {
        RandomRectangleGUI gui = new RandomRectangleGUI();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        colorButton = new JButton("Click me for a random colour");
        sizeButton = new JButton("Click me for a random size");

        colorButton.addActionListener(new RandomColorListener());
        sizeButton.addActionListener(new SizeListener());

        frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
        frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    class RandomRectDrawPanel extends JPanel {

        Color color;
        int height = 50;
        int width = 80;
        int x = 50;
        int y = 50;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

        public void randomColor() {
            int r = (int) (Math.random() * 255);
            int gr = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            color = new Color(r, gr, b);
        }

        public void randomSize() {
            int displace = 5;
            height = (int) (Math.random() * getHeight());
            width = (int) (Math.random() * getWidth());

            int temp;
            if ((y + height) > getHeight()) { 
                temp = getHeight() - (y + height);
                height = height + temp - displace;  
            }
            if (height < 5) {
                height = 5;
            }
            if ((x + width) > getWidth()) {  
                temp = getWidth() - (x + width);
                width = width + temp - displace;  
            }
            if (width < 5) {
                width = 5;
            }
        }
    }

    public class RandomColorListener implements ActionListener {

        public void actionPerformed(ActionEvent rcolor) {
            drawPanel.randomColor();
            drawPanel.repaint(); 
        }
    }

    public class SizeListener implements ActionListener {

        public void actionPerformed(ActionEvent rsize) {
            drawPanel.randomSize();
            drawPanel.repaint(); 
        }
    }

}
