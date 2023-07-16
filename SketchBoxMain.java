package project20;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

class SketchArea extends JPanel {
	 java.util.List<Point> points = new ArrayList<Point>();
	 class Point {
	 int x, y;
	 public Point(int x, int y) {
	 this.x = x;
	 this.y = y;
	 }
	 }
	 public void addPoint(int x, int y) {
	 points.add(new Point(x,y));
	 repaint();
	 }
	 public void clear() {
	 points.clear();
	 repaint();
	 }
	 Point previousPoint;
	 void drawPoint(Graphics g, Point p) {
	 if(previousPoint == null) {
	 previousPoint = p;
	 return;
	 }
	 g.drawLine(previousPoint.x, previousPoint.y,
	 p.x, p.y);
	 previousPoint = p;
	 }
	 public void paintComponent(Graphics g) {
	 super.paintComponent(g);
	 g.setColor(Color.red);
	 previousPoint = null;
	 for(Point p : points)
	 drawPoint(g, p);
	 }
	}
	class SketchBox extends JFrame {
	 SketchArea sketch = new SketchArea();
	 JSlider
	 hAxis = new JSlider(),
	 vAxis = new JSlider(JSlider.VERTICAL);
	 JButton erase = new JButton("Erase");
	 ChangeListener cl = new ChangeListener() {
	 public void stateChanged(ChangeEvent e) {
	 sketch.addPoint(hAxis.getValue(), vAxis.getValue());
	 erase.setText(
	 "[Erase] points.size() = " +
	 sketch.points.size());
	 }
	 };
	 public SketchBox() {
	 add(sketch);
	 hAxis.setValue(0);
	 vAxis.setValue(0);
	 vAxis.setInverted(true);
	 hAxis.addChangeListener(cl);
	 vAxis.addChangeListener(cl);
	 add(BorderLayout.SOUTH, hAxis);
	 add(BorderLayout.WEST, vAxis);
	 add(BorderLayout.NORTH, erase);
	 erase.addActionListener(new ActionListener() {
	 public void actionPerformed(ActionEvent e) {
	 sketch.clear();
	 erase.setText(
	 "[Erase] points.size() = " +
	 sketch.points.size());
	 }
	 });
	 
	 addComponentListener(new ComponentAdapter() {
	 public void componentResized(ComponentEvent e) {
	 super.componentResized(e);
	 hAxis.setMaximum(sketch.getWidth());
	 vAxis.setMaximum(sketch.getHeight());
	 }
	 });
	 }
	}
	public class SketchBoxMain {
	 public static void main(String args[]) {
	 run(new SketchBox(), 700, 400);
	 }public static void
	 run(final SketchBox sketchBox, final int width, final int height) {
		   SwingUtilities.invokeLater(new Runnable() {
		     public void run() {
		    	 sketchBox.setTitle(sketchBox.getClass().getSimpleName());
		    	 sketchBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	 sketchBox.setSize(width, height);
		    	 sketchBox.setVisible(true);
		     }
		   });
		 }
		}  
