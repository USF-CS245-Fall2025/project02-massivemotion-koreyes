package project02;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main driver for the project, a Massive Motion class that
 * simulates comets revolving around a central star at random
 * velocities, holding these as objects in a specified list type:
 * SinglyLinkedList, DoublyLinkedList, and DummyHeadLinkedList. This
 * class then continuously updates and repaints the objects using a 
 * timer. Furthermore, reading configurations such as the window size
 * and velocity from a property file.
 * 
 * Visually, there is one red central star and smaller black "comets"
 * that move across the screen.
 * 
 * @Author: Oliver Reyes
 * @version 2.0
 */
public class MassiveMotion extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1786650193070286570L;
	
    private int windowWidth,windowHeight, bodySize, bodyVelocity, timerDelay;
    private double genX, genY;

    private List<CelestialBody> bodies;
    private Random random = new Random();
    private Timer tm;
    
    /**
     * Constructs a MassiveMotion panel using configurations
     * specified in a property file, or otherwise passes in
     * defaults.
     * 
     * @param propertyFile the name of the property file to read.
     */
    public MassiveMotion(String propertyFile) {
    	PropertyReader reader = new PropertyReader(propertyFile);
    	
        timerDelay = reader.getInt("timer_delay", 75);
        windowWidth = reader.getInt("window_size_x", 1024);
        windowHeight = reader.getInt("window_size_y", 768);
        genX = reader.getDouble("gen_x", 0.06);
        genY = reader.getDouble("gen_y", 0.06);
        bodySize = reader.getInt("body_size", 10);
        bodyVelocity = reader.getInt("body_velocity", 3);

        String listType = reader.getProperties().getProperty("list", "arraylist").toLowerCase();
        if (listType.equals("single")) {
            bodies = new LinkedList<>();
        } else if (listType.equals("double")) {
            bodies = new DoublyLinkedList<>();
        } else if (listType.equals("dummyhead")) {
            bodies = new DummyHeadLinkedList<>();
        } else {
            bodies = new ArrayList<>();
        }
        
        double sx = reader.getDouble("star_position_x", windowWidth / 2.0);
        double sy = reader.getDouble("star_position_y", windowHeight / 2.0);
        double svx = reader.getDouble("star_velocity_x", 0);
        double svy = reader.getDouble("star_velocity_y", 0);
        int sSize = reader.getInt("star_size", 30);
        
        bodies.add(new CelestialBody(sx, sy, sSize, svx, svy, Color.RED));

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        tm = new Timer(timerDelay, this);
        tm.start();
    }
    
    /**
     * Draws all the celestial bodies that are currently displayed in the
     * simulation.
     * 
     * @param g the Graphics object used for drawing.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CelestialBody body : bodies) {
            g.setColor(body.getColor());
            g.fillOval((int) (body.getX() - body.getSize() / 2.0), (int) (body.getY() - body.getSize() / 2.0), body.getSize(), body.getSize());
        }
    }
    
    /**
     * Updates the position of all celestial bodies, removes off-screen
     * bodies, and occasionally spawns new ones at the edges.
     * 
     * @param e the ActionEvent triggered by the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (CelestialBody body : bodies) {
        	body.setX(body.getX() + body.getVx());
            body.setY(body.getY() + body.getVy());
        }
        
        for (int i = bodies.size() - 1; i > 0; i--) {
            CelestialBody b = bodies.get(i);
            if (b.getX() < -b.getSize() || b.getX() > windowWidth + b.getSize()
                || b.getY() < -b.getSize() || b.getY() > windowHeight + b.getSize()) {
                bodies.remove(i);
            }
        }
        
        if (random.nextDouble() < genX || random.nextDouble() < genY) {
            double x, y, vx, vy;

            boolean horizontal = random.nextBoolean();
            if (horizontal) {
                x = random.nextDouble() * windowWidth;
                if (random.nextBoolean()) { // top
                    y = 0;
                    vy = 1 + random.nextInt(bodyVelocity);
                } else { // bottom
                    y = windowHeight;
                    vy = -1 - random.nextInt(bodyVelocity);
                }
                vx = random.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
                if (vx == 0) {
                	vx = 1; // ensure non-zero velocity
                }
            } else {
                y = random.nextDouble() * windowHeight;
                if (random.nextBoolean()) { // left
                    x = 0;
                    vx = 1 + random.nextInt(bodyVelocity);
                } else { // right
                    x = windowWidth;
                    vx = -1 - random.nextInt(bodyVelocity);
                }
                vy = random.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
                if (vy == 0) {
                	vy = 1; // ensure non-zero velocity
                }
            }

            bodies.add(new CelestialBody(x, y, bodySize, vx, vy, Color.BLACK));
        }

        repaint();
    }
    
    /**
     * The main method of the program, initializing and displaying 
     * the MassiveMotion window.
     * 
     * @param args optional command-line argument for the property file.
     */
    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");

        String propertyFile;
        if (args.length > 0) {
            propertyFile = args[0];
        } else {
            propertyFile = "MassiveMotion.txt";
        }

        MassiveMotion mm = new MassiveMotion(propertyFile);

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowWidth, mm.windowHeight);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
