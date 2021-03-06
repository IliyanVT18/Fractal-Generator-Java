/**
 * @author Iliyan Teofilov
 * @ID 1671952. Group2b
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class PythagorasTreeSidePanel extends SidePanel{
    //Defining variables accessed by the whole class
    PythagorasTree fractalPanel;
    JTextField angleSize;
    JTextField iterations;

    /**
     * Pythagoras Tree side panel constructor. Creates new pythagoras tree side panel object.
     * @param fractalPanel the pythagoras tree fractal panel
     * @param iterationNum number of iterations
     * @param angle angle in degrees
     * @param bgColor background color
     * @param fgColor foreground / square color
     * @param tgColor triangle color
     * @param lnColor line color
     */
    PythagorasTreeSidePanel(PythagorasTree fractalPanel, int iterationNum, double angle, Color bgColor, Color fgColor, Color tgColor, Color lnColor) {
        super();

        this.fractalPanel = fractalPanel;

        JButton pickBg1 = new JButton("Pick");
        JButton pickLn = new JButton("Pick");
        JButton pickFg = new JButton("Pick");
        JButton pickTg = new JButton("Pick");
        JLabel labelAngleSize = new JLabel("Size of Acute Angle: ");
        JLabel labelIterationNum = new JLabel("Number of Iterations");
        labelAngleSize.setForeground(Color.WHITE);
        labelIterationNum.setForeground(Color.WHITE);

        angleSize = new JTextField();
        angleSize.setText(String.valueOf((int) angle));
        iterations = new JTextField();
        iterations.setText(String.valueOf(iterationNum));

        angleSize.setMaximumSize(new Dimension(50, 20));
        iterations.setMaximumSize(new Dimension(50, 20));

        pickBg1.setAlignmentX(Component.CENTER_ALIGNMENT);
        pickLn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pickFg.setAlignmentX(Component.CENTER_ALIGNMENT);
        pickTg.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAngleSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelIterationNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAngleSize.setBorder(BorderFactory.createEmptyBorder(50, 20, 10, 20));
        labelIterationNum.setBorder(BorderFactory.createEmptyBorder(50, 20, 10, 20));

        ColorPicker bgColor1Picker = new ColorPicker(0, bgColor, pickBg1, fractalPanel); //0 is for background 1 picker
        ColorPicker lnColorPicker = new ColorPicker(3, lnColor, pickLn, fractalPanel); //3 is for line picker
        ColorPicker fgColorPicker = new ColorPicker(2, fgColor, pickFg, fractalPanel); //2 is for foreground picker
        ColorPicker tgColorPicker = new ColorPicker(1, tgColor, pickTg, fractalPanel); //1 is for triangle picker
        tgColorPicker.setLabelText("Triangle Color");

        //update angle in real time
        angleSize.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeAngleSize();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changeAngleSize();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                changeAngleSize();
            }

            public void changeAngleSize() {
                if (angleSize.getText().length() > 0) {
                    try {
                        Integer newAngle = Integer.parseInt(angleSize.getText());
                        if (newAngle >= 0 && newAngle <= 90) {
                            fractalPanel.changeAngle(newAngle);
                        } else {
                            JOptionPane.showMessageDialog(null,
                            "Please input an angle between 0 and 90 degrees.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    angleSize.setText(null);
                                }
                            });
                        } 
                    } catch (Exception e) {
                        if (angleSize.getText().length() > 0) {
                            JOptionPane.showMessageDialog(null,
                            "Please input a numeric value.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    angleSize.setText(null);
                                }
                            });
                        }
                    }
                }
            }
        });

        //update iterations in real time
        iterations.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeIterationNum();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeIterationNum();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeIterationNum();
            }
            
            public void changeIterationNum() {
                if (iterations.getText().length() > 0) {
                    try {
                        Integer iterationsNumber = Integer.parseInt(iterations.getText());
                        if (iterationsNumber < 0) {
                            JOptionPane.showMessageDialog(null,
                            "Please enter a number of iterations larger than or equal to 0.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                        } else {
                            fractalPanel.changeIterationNum(iterationsNumber);
                        }
                    } catch (Exception e) {
                        if (iterations.getText().length() > 0) {
                            JOptionPane.showMessageDialog(null,
                            "Please input a numerical value.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    iterations.setText(null);
                                }
                            });
                        }
                    }
                }
            }
        });

        this.add(bgColor1Picker);
        this.add(pickBg1);
        this.add(lnColorPicker);
        this.add(pickLn);
        this.add(fgColorPicker);
        this.add(pickFg);
        this.add(tgColorPicker);
        this.add(pickTg);
        this.add(labelAngleSize);
        this.add(angleSize);
        this.add(labelIterationNum);
        this.add(iterations);
    }
}