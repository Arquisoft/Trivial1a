package controllers.board2D;

import java.awt.*;

/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class Box {

    private Point point;
    private Color cacheColor;

    /**
     * Sole constructor.
     * @param point
     * @param color the color of the cached area.
     */
    public Box(Point point, Color color) {
        this.point = point;
        this.cacheColor = color;
    }

    public Color getCacheColor() {
        return cacheColor;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        if (this.point != other.point && (this.point == null || !this.point.equals(other.point))) {
            return false;
        }
        if (this.cacheColor != other.cacheColor && (this.cacheColor == null || !this.cacheColor.equals(other.cacheColor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.point != null ? this.point.hashCode() : 0);
        hash = 31 * hash + (this.cacheColor != null ? this.cacheColor.hashCode() : 0);
        return hash;
    }

    public String toString(){
    	
    	return"Color:"+cacheColor+"  Punto:"+point;
    }
    
    
}
