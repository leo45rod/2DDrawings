package java2ddrawings;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public class DrawingApplicationFrame extends JPanel {

    private DrawPanel myPanel;
    public DrawingApplicationFrame() {
        JLabel mouseCords = new JLabel();
        mouseCords.setText("test");
        DrawPanel panel = new DrawPanel(mouseCords);
        myPanel = panel;
        DrawFrame application = new DrawFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(mouseCords, BorderLayout.SOUTH);
        application.add(myPanel, BorderLayout.CENTER);
        application.setSize(900, 400);
        application.setVisible(true);

    }

    public class DrawFrame extends JFrame {

        DrawFrame() {
            super("Java 2D Drawings");
            setLayout(new BorderLayout());
            upperButtons upperbuttons = new upperButtons();
            add(upperbuttons, BorderLayout.NORTH);

        }
        public class upperButtons extends JPanel {
            upperButtons() {
                setLayout(new FlowLayout());
                JButton Undo = new JButton();
                JButton Clear = new JButton();
                Undo.setText("Undo");
                Clear.setText("Clear");
                String ShapeChoices[] = {"Line", "Oval", "Rectangle"};
                JComboBox ShapeSelection = new JComboBox(ShapeChoices);
                JCheckBox filledFlag = new JCheckBox();
                filledFlag.setText("Fill");
                UndoHandler undohandler = new UndoHandler();
                Undo.addActionListener(undohandler);
                ClearHandler clearhandler = new ClearHandler();
                Clear.addActionListener(clearhandler);
                ShapeSelectionHandler shapeselectionhandler = new ShapeSelectionHandler(ShapeSelection);
                ShapeSelection.addActionListener(shapeselectionhandler);
                FillCheckBoxHandler fillcheckboxhandler = new FillCheckBoxHandler(filledFlag);
                filledFlag.addActionListener(fillcheckboxhandler);
                add(Clear);
                add(Undo);
                add(ShapeSelection);
                add(filledFlag);
                lowerButtons lowerbuttons = new lowerButtons();
                add(lowerbuttons);
            }
        }
        public class lowerButtons extends JPanel {
            lowerButtons() {
                JCheckBox gradientCheck = new JCheckBox();
                gradientCheck.setText("Use gradient");
                GradientCheckBoxHandler gradientcheckboxhandler = new GradientCheckBoxHandler(gradientCheck);
                gradientCheck.addActionListener(gradientcheckboxhandler);
                add(gradientCheck);
                JButton firstButton = new JButton();
                firstButton.setText("1st Color...");
                FirstColorButtonHandler firstcolorbuttonhandler = new FirstColorButtonHandler(gradientCheck);
                firstButton.addActionListener(firstcolorbuttonhandler);
                add(firstButton);
                JButton secondButton = new JButton();
                secondButton.setText("2nd Color...");
                SecondColorButtonHandler secondcolorbuttonhandler = new SecondColorButtonHandler(gradientCheck);
                secondButton.addActionListener(secondcolorbuttonhandler);
                add(secondButton);
                JLabel widthLabel = new JLabel();
                widthLabel.setText("Width:");
                add(widthLabel);
                JTextField widthField = new JTextField();
                widthField.setColumns(2);
                WidthHandler widthhandler = new WidthHandler(widthField);
                widthField.addActionListener(widthhandler);
                add(widthField);
                JLabel dashLabel = new JLabel();
                dashLabel.setText("Dash Length:");
                add(dashLabel);
                JTextField dashField = new JTextField();
                dashField.setColumns(2);
                DashHandler dashhandler = new DashHandler(dashField);
                dashField.addActionListener(dashhandler);
                add(dashField);
                JCheckBox dashCheck = new JCheckBox();
                dashCheck.setText("Dashed");
                DashCheckBoxHandler dashcheckboxhandler = new DashCheckBoxHandler(dashCheck);
                dashCheck.addActionListener(dashcheckboxhandler);
                add(dashCheck);
              
            }
        }
        public class UndoHandler implements ActionListener {           
            public void actionPerformed(ActionEvent event)
            {
                myPanel.clearLastShape();
            }
        }
        public class ClearHandler implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                myPanel.clearDrawing();
            }
        }

        public class ShapeSelectionHandler implements ActionListener {
            private JComboBox ShapeBox;
            public ShapeSelectionHandler(JComboBox myBox) {
                ShapeBox = myBox;
            }
            public void actionPerformed(ActionEvent event) {
                String shapeString = (String) ShapeBox.getSelectedItem(); //downcast
                if (shapeString == "Line") {
                    myPanel.setShapetype(0);
                }
                if (shapeString == "Oval") {
                    myPanel.setShapetype(1);
                }
                if (shapeString == "Rectangle") {
                    myPanel.setShapetype(2);
                }
            }
        }
        public class ColorSelectionHandler implements ActionListener {
            private JComboBox ColorBox;
            public ColorSelectionHandler(JComboBox myBox) {
                ColorBox = myBox;
            }
            public void actionPerformed(ActionEvent event) {
                String colorString = (String) ColorBox.getSelectedItem(); //downcast
                switch (colorString) {
                    case "Black":
                        myPanel.setCurrentColor(Color.BLACK);
                        break;
                    case "Blue":
                        myPanel.setCurrentColor(Color.BLUE);
                        break;
                    case "Cyan":
                        myPanel.setCurrentColor(Color.CYAN);
                        break;
                    case "Dark Gray":
                        myPanel.setCurrentColor(Color.DARK_GRAY);
                        break;
                    case "Gray":
                        myPanel.setCurrentColor(Color.GRAY);
                        break;
                    case "Green":
                        myPanel.setCurrentColor(Color.GREEN);
                        break;
                    case "Light Gray":
                        myPanel.setCurrentColor(Color.LIGHT_GRAY);
                        break;
                    case "Magenta":
                        myPanel.setCurrentColor(Color.MAGENTA);
                        break;
                    case "Orange":
                        myPanel.setCurrentColor(Color.ORANGE);
                        break;
                    case "Pink":
                        myPanel.setCurrentColor(Color.PINK);
                        break;
                    case "Red":
                        myPanel.setCurrentColor(Color.RED);
                        break;
                    case "White":
                        myPanel.setCurrentColor(Color.WHITE);
                        break;
                    case "Yellow":
                        myPanel.setCurrentColor(Color.YELLOW);
                        break;

                }
            }

        }
        public class FillCheckBoxHandler implements ActionListener {
            private JCheckBox CheckBox;
            public FillCheckBoxHandler(JCheckBox myCheckBox) {
                CheckBox = myCheckBox;
            }
            public void actionPerformed(ActionEvent event) {
                if (CheckBox.isSelected()) {
                    myPanel.setFilledShape(true);
                } else if (!CheckBox.isSelected()) {
                    myPanel.setFilledShape(false);
                }
            }
        }
           public class GradientCheckBoxHandler implements ActionListener {
         private JCheckBox CheckBox;
         public GradientCheckBoxHandler(JCheckBox myCheckBox) {
         CheckBox = myCheckBox;
         }
         public void actionPerformed(ActionEvent event) {
         if (CheckBox.isSelected()) {
             myPanel.setGradientStatus(true);
             myPanel.setCurrentColor(new GradientPaint( 0, 0, myPanel.getGradient1(), 50, 50, myPanel.getGradient2(), true));
         } else if (!CheckBox.isSelected()) {
            myPanel.setGradientStatus(false);
            myPanel.setCurrentColor(myPanel.getGradient1());
         }
         }
         }
     public class DashCheckBoxHandler implements ActionListener {
            private JCheckBox CheckBox;
            public DashCheckBoxHandler(JCheckBox myCheckBox) {
                CheckBox = myCheckBox;
            }
            public void actionPerformed(ActionEvent event) {
                if (CheckBox.isSelected()) {
                    myPanel.setDash(true);
                } else if (!CheckBox.isSelected()) {
                    myPanel.setDash(false);
                }
            }
        }
        public class FirstColorButtonHandler implements ActionListener {
             private Color gradientcolor1;
             private JCheckBox checkStatus;
             FirstColorButtonHandler(JCheckBox myCheckBox)
             {
             gradientcolor1 = Color.BLACK;
             myPanel.setGradient1(gradientcolor1);
             checkStatus = myCheckBox;
             }
            Color getGradientColor1()
            {return gradientcolor1;}
            public void actionPerformed(ActionEvent e) {
                gradientcolor1 = JColorChooser.showDialog(myPanel, "Select a Background Color", gradientcolor1);
                myPanel.setCurrentColor(gradientcolor1);
                myPanel.setGradient1(gradientcolor1);
                if (myPanel.getGradientStatus())
                    myPanel.setCurrentColor(new GradientPaint( 0, 0, myPanel.getGradient1(), 50, 50, myPanel.getGradient2(), true));
            }
        }
        public class SecondColorButtonHandler implements ActionListener {
            private Color gradientcolor2; 
            private JCheckBox checkStatus;
            SecondColorButtonHandler(JCheckBox myCheckBox)//NO LONGER NEED THIS ARGUMENT
            {
                checkStatus = myCheckBox;
                gradientcolor2 = Color.WHITE;
                myPanel.setGradient2(gradientcolor2);
            }
            public void actionPerformed(ActionEvent e) {
                gradientcolor2 = JColorChooser.showDialog(myPanel, "Select a Background Color", gradientcolor2);
                myPanel.setGradient2(gradientcolor2);
                if (myPanel.getGradientStatus())
                    myPanel.setCurrentColor(new GradientPaint( 0, 0, myPanel.getGradient1(), 50, 50, myPanel.getGradient2(), true));
            }
        }
        public class WidthHandler implements ActionListener {
           private JTextField textGrabber;
           private int myIntParse;
           WidthHandler(JTextField myTextField){
                textGrabber = myTextField;
           }
           public void actionPerformed(ActionEvent e){
                myIntParse = Integer.parseInt(textGrabber.getText());
                myPanel.setStrokeWidth(myIntParse);
           }
       }
        public class DashHandler implements ActionListener {
           private JTextField textGrabber;
           private int myIntParse;
           DashHandler (JTextField myTextField){
               textGrabber = myTextField;
           }
           public void actionPerformed(ActionEvent e){
               myIntParse = Integer.parseInt(textGrabber.getText());
               myPanel.setDashLength(myIntParse);
           }
       }
    }
    public class DrawPanel extends JPanel {
        private MyShapes[] shapes;
        private int shapeCount; 
        private int shapeType; 
        private MyShapes currentShape; 
        private Paint currentColor; 
        private Stroke currentStroke;
        private Color gradient1, gradient2;
        private int shapeWidth;
        private float[] dashes; 
        boolean filledShape;
        boolean gradientStatus;
        boolean dashStatus;
        JLabel statusLabel;
        DrawPanel(JLabel myLabel) {
            setBackground(Color.WHITE);
            statusLabel = myLabel;
            currentShape = null;
            shapes = new MyShapes[100];
            shapeCount = 0;
            shapeType = 0;
            currentColor = Color.BLACK;
            gradient1 = Color.BLACK;
            gradient2 = Color.WHITE;
            shapeWidth = 1; 
            dashes = new float[1];
            dashes[0] = 1;
            dashStatus = false; 
            currentStroke = new BasicStroke( shapeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );//default solid stroke with shapeWidth of 1
            addMouseMotionListener(new mouseHandler());
            addMouseListener(new mouseHandler());
        }
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D gDD = (Graphics2D) g;
            super.paintComponent(g);
            for (int i = 0; i < shapeCount; i++) {
                shapes[i].draw(gDD);
            }
            if (currentShape == null) {
                int q;
            } else {
                currentShape.draw(gDD);
                  g.drawArc( 200, 100, 100, 50, 90, 90 );
            }
        }//end paintComp
        void setGradientStatus(boolean myGradient){
            gradientStatus = myGradient;
        }
        boolean getGradientStatus()
        {
            return gradientStatus;
        }
        void setGradient1(Color myColor)
        {
            gradient1 = myColor;
        }
        void setGradient2(Color myColor)
        {
            gradient2 = myColor;
        }
        void setStrokeWidth(int myWidth)
        {
            shapeWidth = myWidth;
            currentStroke = new BasicStroke( shapeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
        }
        void setDashLength (int myDashLength)
        {
            dashes[0] = myDashLength;
            if (dashStatus)
                currentStroke = new BasicStroke( shapeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0 );
            else
                currentStroke = new BasicStroke( shapeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
        }
        float[] getDashLength ()
        {
            return dashes;
        }
        void setDash (boolean myDash)
        {
            dashStatus = myDash;
            if (dashStatus)
                currentStroke = new BasicStroke( shapeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0 );
            else
                currentStroke = new BasicStroke( shapeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
        }
        int getStrokeWidth ()
        {
            return shapeWidth;
        }
        Color getGradient1()
        {return gradient1;}
        Color getGradient2()
        {return gradient2;}
        void setShapetype(int myShapeType) {
            shapeType = myShapeType;
        }
        void setCurrentColor(Paint myCurrentColor) {
            currentColor = myCurrentColor;
        }
        Paint getCurrentColor()
        {return currentColor;}
        void setFilledShape(boolean fillFlag) {
            filledShape = fillFlag;
        }
        void setStroke(Stroke myCurrentStroke) {
            currentStroke = myCurrentStroke;
        }
        void clearLastShape() {
            if (shapeCount > 0) {
                shapeCount = shapeCount - 1;
            }
            repaint();
        }
        void clearDrawing() {
            shapeCount = 0;
            repaint();
        }
        public class mouseHandler extends MouseAdapter implements MouseMotionListener {
            @Override
            public void mousePressed(MouseEvent event) {
                if (shapeType == 0) {
                    currentShape = new MyLine();
                }
                if (shapeType == 1) {
                    currentShape = new MyOval();
                }
                if (shapeType == 2) {
                    currentShape = new MyRectangle();
                }
                currentShape.setX1(event.getX());
                currentShape.setY1(event.getY());
                currentShape.setColor(currentColor);
                currentShape.setFill(filledShape);
                currentShape.setStroke(currentStroke);
            }
            @Override
            public void mouseReleased(MouseEvent event) {
                currentShape.setX2(event.getX());
                currentShape.setY2(event.getY());
                shapes[ shapeCount] = currentShape;
                shapeCount++;
                currentShape = null;
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent event) {
                statusLabel.setText(event.getX() + ", " + event.getY());//if mouse moves, update coordinates
                repaint();
            }
            @Override
            public void mouseDragged(MouseEvent event) {
                currentShape.setX2(event.getX());
                currentShape.setY2(event.getY());
                statusLabel.setText(event.getX() + ", " + event.getY()); //if mouse moves while pressed, update coordinates
                repaint();
            }
        }
    }
    public abstract class MyShapes {
        private Point startPoint = new Point();
        private Point endPoint = new Point();
        private int X1, X2, Y1, Y2;
        private Paint ShapeColor;
        private Stroke ShapeStroke;
        private boolean ShapeFill;
        MyShapes() {
            X1 = 0;
            X2 = 0;
            Y1 = 0;
            Y2 = 0;
            ShapeColor = Color.BLACK;
            ShapeFill = false;
            ShapeStroke = new BasicStroke();
        }//end mySHape constructor
        MyShapes(Point pntA, Point pntB, Paint myColor, Stroke myStroke) {
            X1 = (int) pntA.getX();
            X2 = (int) pntB.getX();
            Y1 = (int) pntA.getY();
            Y2 = (int) pntA.getY();
            ShapeColor = myColor;
            ShapeStroke = myStroke;
        }
        void setX1(int myX1) {
            X1 = myX1;
        }
        void setX2(int myX2) {
            X2 = myX2;
        }
        void setY1(int myY1) {
            Y1 = myY1;
        }
        void setY2(int myY2) {
            Y2 = myY2;
        }
        void setColor(Paint myColor) {
            ShapeColor = myColor;
        }
        void setFill(boolean myFill) {
            ShapeFill = myFill;
        }
        void setStroke (Stroke myStroke){
            ShapeStroke = myStroke;
        }
        boolean getFill() {
            return ShapeFill;
        }
        int getUpperX() {
            if (X1 > X2) {
                return X1;
            } else {
                return X2;
            }
        }
        int getUpperY() {
            if (Y1 > Y2) {
                return Y2;
            } else {
                return Y1;
            }
        }
        int getWidth() {
            return Math.abs(X1 - X2);
        }
        int getHeight() {
            return Math.abs(Y1 - Y2);
        }
        int getX1() {
            return X1;
        }
        int getX2() {
            return X2;
        }
        int getY1() {
            return Y1;
        }
        int getY2() {
            return Y2;
        }
        Paint getColor() {
            return ShapeColor;
        }
        Stroke getStroke() {
            return ShapeStroke;
        }
        public abstract void draw(Graphics2D g);

    } 
    class MyLine extends MyShapes {
        MyLine() {
            super();
        }
        MyLine(Point pntA, Point pntB, Paint myColor, Stroke myStroke) {
            super(pntA, pntB, myColor, myStroke);
        }

        @Override
        public void draw(Graphics2D g) {
            g.setPaint(MyLine.super.getColor());
            g.setStroke(MyLine.super.getStroke());
            g.drawLine(MyLine.super.getX1(), MyLine.super.getY1(), MyLine.super.getX2(), MyLine.super.getY2());
        }
    }

    class MyOval extends MyShapes {

        MyOval()
        {
            super();
        }

        MyOval(Point pntA, Point pntB, Paint myColor, boolean myFill, Stroke myStroke) {
            super(pntA, pntB, myColor, myStroke);
            super.setFill(myFill);
        }

        @Override
        public void draw(Graphics2D g) {
            g.setPaint(MyOval.super.getColor());
            g.setStroke(MyOval.super.getStroke());
            g.drawOval(MyOval.super.getUpperX(), MyOval.super.getUpperY(), MyOval.super.getWidth(), MyOval.super.getHeight());
            if (super.getFill()) {
                g.fillOval(MyOval.super.getUpperX(), MyOval.super.getUpperY(), MyOval.super.getWidth(), MyOval.super.getHeight());
            }

        }

    }

    class MyRectangle extends MyShapes {

        MyRectangle() {
            super();
        }

        MyRectangle(Point pntA, Point pntB, Paint myColor, boolean myFill, Stroke myStroke) {
            super(pntA, pntB, myColor, myStroke);
            super.setFill(myFill);
        }

        @Override
        public void draw(Graphics2D g) {
            g.setPaint(MyRectangle.super.getColor());
            g.setStroke(MyRectangle.super.getStroke());
            g.drawRect(MyRectangle.super.getUpperX(), MyRectangle.super.getUpperY(), MyRectangle.super.getWidth(), MyRectangle.super.getHeight());
            if (super.getFill()) {
                g.fillRect(MyRectangle.super.getUpperX(), MyRectangle.super.getUpperY(), MyRectangle.super.getWidth(), MyRectangle.super.getHeight());
            }
        }

    }

    public static void main(String[] args) {
        DrawingApplicationFrame frame = new DrawingApplicationFrame();
    }
}