import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudentGradeTracker extends JFrame implements ActionListener {

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<Double> grades = new ArrayList<Double>();

    JTextField t1, t2;
    JButton b1, b2;
    JTextArea a1;

    public StudentGradeTracker() {
        super("Grade Tracker");
        setSize(400, 450);
        setLayout(new FlowLayout());

        JLabel l1 = new JLabel("Name:");
        t1 = new JTextField(10);

        JLabel l2 = new JLabel("Grade:");
        t2 = new JTextField(5);
        b1 = new JButton("Add");
        b2 = new JButton("Calculate");
        b1.addActionListener(this);
        b2.addActionListener(this);
        a1 = new JTextArea(17, 30);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(b2);
        add(a1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String n = t1.getText();
            String gStr = t2.getText();

            try {
                double g = Double.parseDouble(gStr);
                names.add(n);
                grades.add(g);

                a1.setText(a1.getText() + n + " added with grade " + g + "\n");

                t1.setText("");
                t2.setText("");
            } catch (Exception ex) {
                a1.setText(a1.getText() + "Error! Grade is not a number.\n");
            }

        } else if (e.getSource() == b2) {
            if (grades.size() == 0) {
                a1.setText(a1.getText() + "No grades to calculate!\n");
                return;
            }

            double sum = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);
            int highIdx = 0;
            int lowIdx = 0;

            for (int i = 0; i < grades.size(); i++) {
                double val = grades.get(i);
                sum = sum + val;

                if (val > highest) {
                    highest = val;
                    highIdx = i;
                }

                if (val < lowest) {
                    lowest = val;
                    lowIdx = i;
                }
            }

            double average = sum / grades.size();

            a1.setText(a1.getText() + "\n*** REPORT ***\n");
            for (int i = 0; i < names.size(); i++) {
                a1.setText(a1.getText() + names.get(i) + " : " + grades.get(i) + "\n");
            }

            a1.setText(a1.getText() + "\nTotal students: " + names.size() + "\n");
            a1.setText(a1.getText() + "Average: " + average + "\n");
            a1.setText(a1.getText() + "Highest: " + highest + " (" + names.get(highIdx) + ")\n");
            a1.setText(a1.getText() + "Lowest: " + lowest + " (" + names.get(lowIdx) + ")\n");
        }
    }

    public static void main(String args[]) {
        new StudentGradeTracker();
    }
}
