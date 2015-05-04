package controllers.board2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

import javax.swing.JPanel;


/**
 * 
 * @author Grupo 1a
 * @see <a href = "https://github.com/Arquisoft/Trivial1a/" /> Git Grupo 1a </a>
 */
public class TrivialBoard  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2501147778986765634L;
	private BufferedImage offscreenImage;  //imagen con fichas
    private Board board;
    protected HashMap<String, Piece> pieces;
    
    public BufferedImage getImgBoard(){
    	return offscreenImage; 
    }
    
    
    public TrivialBoard(){
        pieces = new HashMap<String, Piece>();
    }

    /**
     * Set <em>"the"</em> board of the game.
     * There is only one board per game. If you call this method
     * a second time, board data will be replaced.
     * @param boardFile the image of the board
     * @param cacheFile the image of the board's cache
     * @throws IOException if a file doesn't exist (or is unreadable, ...)
     */
    public void setBoard(File boardFile, File cacheFile) throws IOException {
        board = new Board(boardFile, cacheFile);
        Dimension d = board.getDimension();
        offscreenImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
//        setSize(d);
    }

    /**
     * Add a new piece of the game.
     * @param name the name of the piece. You will retrieve the piece by its name.
     * @param file the image of the piece
     * @throws IOException if the file doesn't exist (or is unreadable, ...)
     */
    public void addPiece(String name, File file) throws IOException {
        pieces.put(name, new Piece(file));
    }
    public void addPiece(String name, BufferedImage buferedImage) throws IOException {
        pieces.put(name, new Piece(buferedImage) );
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(offscreenImage.getWidth(null), offscreenImage.getHeight(null));
//    }
//
//    @Override
//    public int getWidth() {
//        return offscreenImage.getWidth(null);
//    }
//
//    @Override
//    public int getHeight() {
//        return offscreenImage.getHeight(null);
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(offscreenImage, 0, 0, null);
//    }

    /**
     * Display the board, without any pieces.
     * Useful to initialize your screen.
     */
    public void drawBoard() {
        Graphics2D g = offscreenImage.createGraphics();      
        g.drawImage(board.getImage(), 0, 0, null);
//        repaint();
    }
    
    //primer elemento es el k cambia 
    public void repintarTablero(String piezas[], int boxId){
    	
    	drawBoard();						//resetear tablero		
    	
    	pintarPieza(piezas[0], boxId);
    	    
    	if(piezas.length > 1)
			for (int i = 1; i < piezas.length; i++) {
				pintarPieza(piezas[i]);
			}
    	
    }

    
    public void pintarPieza(String pieza){
		
		Graphics2D g = offscreenImage.createGraphics();
	    Piece piece = pieces.get(pieza);
	    Box box = board.getBox(piece.getBoxId());
	    
	    g.drawImage(piece.getImage(), box.getPoint().x, box.getPoint().y, null);
//	    repaint();
	}
      
    private void pintarPieza(String pieza, int boxId){
		
		Graphics2D g = offscreenImage.createGraphics();
	    Box box = board.getBox(boxId);
	    Piece piece = pieces.get(pieza);
	    piece.setBoxId(boxId);
		
	    g.drawImage(piece.getImage(), box.getPoint().x, box.getPoint().y, null);
//	    repaint();
	}
    
    public BufferedImage getIconActualPlayer(String nombreFicha, int tam){
    	
    	return resize( pieces.get(nombreFicha).getImage(), tam, tam);
    
    }
    
    private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;

	}
    

	/**
     * Convenience method.
     * @see Board#addBox
     */
    public void addBox(int id, Color cache, Point reference) {
        board.addBox(id, cache, reference);
    }

    /**
     * Convenience method.
     * @see Board#getBoxId(Point)
     */
    public int getBoxId(Point point) {
        return board.getBoxId(point);
    }

   
}
