package project02;

import java.awt.Color;

/**
 * Object class that represents a celestial body with a position,
 * velocity, size, and color.
 * 
 * @Author: Oliver Reyes
 * @version: 1.0
 */
public class CelestialBody {
    private double x, y, vx, vy;
    private int size;
    private Color color;
    
    /**
     * Constructs a new CelestialBody a specified position, size, velocity, and color.
     *
     * @param x the x-coordinate of the celestial body.
     * @param y the y-coordinate of the celestial body.
     * @param size the size of the celestial body.
     * @param vx the velocity in the x-direction.
     * @param vy the velocity in the y-direction.
     * @param color the color of the celestial body.
     */
    CelestialBody (double x, double y, int size, double vx, double vy, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
    }
    
    /**
     * Sets the x-coordinate of this celestial body.
     *
     * @param x the new x-coordinate
     */
    public void setX (double x) {
    	this.x = x;
    }
    
    /**
     * Returns the x-coordinate of this celestial body.
     *
     * @return the x-coordinate.
     */
    public double getX () { 
    	return x; 
    }
    
    /**
     * Sets the y-coordinate of this celestial body.
     *
     * @param y the new y-coordinate.
     */
    public void setY (double y) {
    	this.y = y;
    }
    
    /**
     * Returns the y-coordinate of this celestial body.
     *
     * @return the y-coordinate.
     */
    public double getY () { 
    	return y; 
    }
    

    /**
     * Returns the velocity of this celestial body in the x-direction.
     *
     * @return the x-direction velocity.
     */
    public double getVx() {
    	return vx;
    }
    
    /**
     * Returns the velocity of this celestial body in the y-direction.
     *
     * @return the y-direction velocity.
     */
    public double getVy() {
    	return vy;
    }
    
    /**
     * Returns the size of this celestial body.
     *
     * @return the size, with diameter in pixels.
     */
    public int getSize () { 
    	return size; 
    }
    
    /**
     * Returns the color of this celestial body.
     *
     * @return the color.
     */
    public Color getColor() { 
    	return color; 
    }
}