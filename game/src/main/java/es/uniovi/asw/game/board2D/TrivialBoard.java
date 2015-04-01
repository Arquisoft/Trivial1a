package es.uniovi.asw.game.board2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import javax.swing.JPanel;


public class TrivialBoard extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2501147778986765634L;
	private BufferedImage offscreenImage;
    private Board board;
    protected HashMap<String, Piece> pieces;

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
        setSize(d);
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(offscreenImage.getWidth(null), offscreenImage.getHeight(null));
    }

    @Override
    public int getWidth() {
        return offscreenImage.getWidth(null);
    }

    @Override
    public int getHeight() {
        return offscreenImage.getHeight(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(offscreenImage, 0, 0, null);
    }

    /**
     * Display the board, without any pieces.
     * Useful to initialize your screen.
     */
    public void drawBoard() {
        Graphics2D g = offscreenImage.createGraphics();      
        g.drawImage(board.getImage(), 0, 0, null);
        repaint();
    }

//    /**
//     * Draw a piece on the board, deleting the background first.
//     * @param name the name of the piece to display
//     * @param boxId the box id in wich to display the piece
//     */
//    public void drawPiece(String name, int boxId) {
//        drawPiece(name, boxId, true);
//    }
//
//    /**
//     * Draw a piece on the board, without deleting the background first.
//     * @param name the name of the piece to display
//     * @param boxId the box id in wich to display the piece
//     */
//    public void drawPieceOnTop(String name, int boxId) {
//        drawPiece(name, boxId, false);
//    }
//
//    
//    private void drawPiece(String name, int boxId, boolean deleteBackgroundFirst) {
//        Graphics2D g = offscreenImage.createGraphics();
//    	
//        Box box = board.getBox(boxId);
//        Piece piece = pieces.get(name);
//
////        try {
////			ImageIO.write(offscreenImage , "png", new File("prueba.png"));
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 
//        
//        if(deleteBackgroundFirst) {
//            clearBackground(g, box.getPoint(), piece.getDimension());
//        }
//        
////        try {
////			ImageIO.write(offscreenImage , "png", new File("prueba1.png"));  //es el anterior
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 
//        
//        
//        g.drawImage(piece.getImage(), box.getPoint().x, box.getPoint().y, null);
//        repaint();
//        
//    }
//
//    private void clearBackground(Graphics2D g, Point p, Dimension d) {//FIXME
//        BufferedImage background = board.getImage() ;
//        int x2 = p.x + d.width;
//        int y2 = p.y + d.height;
//
//        g.drawImage(background, p.x , p.y , x2, y2, p.x , p.y , x2, y2, null) ;
//        
//
//    }
    
//    Graphics2D g = offscreenImage.createGraphics();
//    g.drawImage(board.getImage(), 0, 0, null);
//    repaint();

    
    
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
	    repaint();
	}
      
    private void pintarPieza(String pieza, int boxId){
		
		Graphics2D g = offscreenImage.createGraphics();
	    Box box = board.getBox(boxId);
	    Piece piece = pieces.get(pieza);
	    piece.setBoxId(boxId);
		
	    g.drawImage(piece.getImage(), box.getPoint().x, box.getPoint().y, null);
	    repaint();
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
