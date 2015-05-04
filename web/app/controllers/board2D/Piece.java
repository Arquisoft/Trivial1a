package controllers.board2D;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Represents a piece of the game.
 * @see TrivialBoard#addPiece
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class Piece {

    private BufferedImage image;
    private int boxId = 72; //CASILLA_CENTRAL

    /**
     * Sole constructor.
     * @param filename the name of the image of the piece
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public Piece(File file) throws IOException {
        image = ImageIO.read(file);
    }
    
    public Piece(BufferedImage bufferedImage){
    	image = bufferedImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Dimension getDimension() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

	public int getBoxId() {
		return boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}
    
  
}
