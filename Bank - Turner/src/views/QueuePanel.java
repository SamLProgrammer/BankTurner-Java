package views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import models.Node;
import models.Turn;

public class QueuePanel extends JPanel{

	private Node<Turn> node;
	private MyOval figure;
	private MainFrame mf;
	private Image clientImg;
	private ArrayList<MyOval> ovalsList;
	private boolean selectedOval, clickedOval, showingOval, addingFlag;
	private int selectedOvalNumber, clickedOvalNumber, y, x, showingOvalNumber;
	private double diameter;
	
	public QueuePanel (MainFrame mainFrame) {
		setBackground(Color.white);
		mf = mainFrame;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 500));
		ovalsList = new ArrayList<>();
		clientImg = new ImageIcon(getClass().getResource("/img/Client.png")).getImage();
		listener();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ovalsList.clear();
		Graphics2D g2 = (Graphics2D)g;
		if(node != null) {
		paintCircles2(g2);
		}
		g2.setColor(Color.decode("#A42626"));
		if(selectedOval) { 
			paintSelectionOval(g2);
		}
		if(clickedOval) {
			paintClickedOval(g2);
		}
		if(showingOval) {
			paintShowedOval(g2);
		}
		if(addingFlag && (y + diameter/2 >= getHeight())) {
			setPreferredSize(new Dimension(getWidth(), (int)(getHeight() + diameter)));
		}
		if(!addingFlag && y + diameter <= getHeight() && x == mf.getWidth()/20) {
			setPreferredSize(new Dimension(getWidth(), (int)(getHeight() - diameter)));
		}
	}
	
	public void paintSelectionOval(Graphics2D g2) {
		g2.setStroke(new BasicStroke(10));
		g2.drawOval(ovalsList.get(selectedOvalNumber).getX(), ovalsList.get(selectedOvalNumber).getY(),
				ovalsList.get(selectedOvalNumber).getDiameter()/2+2, ovalsList.get(selectedOvalNumber).getDiameter()/2+2);
	}
	
	public void paintShowedOval(Graphics2D g2) {
		g2.setStroke(new BasicStroke(10));
		g2.drawOval(ovalsList.get(showingOvalNumber).getX(), ovalsList.get(showingOvalNumber).getY(),
				ovalsList.get(showingOvalNumber).getDiameter()/2+2, ovalsList.get(showingOvalNumber).getDiameter()/2+2);
	}
	
	public void paintClickedOval(Graphics2D g2) {
		g2.setColor(Color.decode("#E94343"));
		g2.drawOval(ovalsList.get(clickedOvalNumber).getX(), ovalsList.get(clickedOvalNumber).getY(),
				ovalsList.get(clickedOvalNumber).getDiameter()/2+2, ovalsList.get(clickedOvalNumber).getDiameter()/2+2);
	}
	
	public void paintCircles2(Graphics2D g2) {
		if(node != null) {
			int stringWidth = 0, counter = 0;
			String turnData = "";
			g2.setStroke(new BasicStroke(3));
			g2.setFont(new Font("Oswald", Font.BOLD, 20));
			diameter = (mf.getWidth()+mf.getHeight())/10;
			x = mf.getWidth()/20;
			y = mf.getHeight()/20;
			Node<Turn> auxNode = node;
			for (int i = 0; i < node.attachedNodesNumber(); i++) {
				turnData = String.valueOf(auxNode.getData().getTurnNumber());
				stringWidth = g2.getFontMetrics().stringWidth(turnData);
				g2.setColor(Color.decode("#262B2C"));
//				g2.drawOval((int)x, (int)y, (int)diameter/2, (int)diameter/2);
				g2.drawImage(clientImg, (int)(x+diameter/20), (int)y,
						(int)(diameter/2), (int)diameter/2, this);
				if(i == 0) {
					String string = "NEXT";
					stringWidth = g2.getFontMetrics().stringWidth(string);
					g2.drawString(string, (int)(x + diameter/4)-stringWidth/2, (int)y- mf.getHeight()/80);
				}
				figure = new MyOval((int)x, (int)y, (int)(21*diameter/20), auxNode.getData().getTurnNumber());
				ovalsList.add(figure);
				if(x != mf.getWidth()/20) {
					int x1 = (int)(x + diameter/80), x2 = (int)(x-9*diameter/20);
					int y1 = (int)(y+21*diameter/80), y2 = (int)(y+21*diameter/80);
					g2.drawLine(x1,y1,x2,y2);
				}
				turnData = String.valueOf(auxNode.getData().getTurnNumber());
				stringWidth = g2.getFontMetrics().stringWidth(turnData);
				g2.setColor(Color.decode("#A42626"));
				g2.drawString(turnData, (int)(x+diameter/4)-stringWidth/2, (int)(y+diameter/4));
				if(counter == 0) {
					if(x - diameter <= 0 && i != 0) {
						g2.setColor(Color.decode("#262B2C"));
						g2.drawLine((int)(x+diameter/4), (int)(y- 9*diameter/20),
								(int)(x+diameter/4), (int)(y - diameter/80));
					}
					x += diameter;
					if(x + diameter >= mf.getWidth()) {
						y += diameter;
						x -= diameter;
						counter =1;
					}
				}
				else {
					if(x + diameter*2 >= mf.getWidth()) {
						g2.setColor(Color.decode("#262B2C"));
						g2.drawLine((int)(x+diameter/4), (int)(y- 9*diameter/20),
								(int)(x+diameter/4), (int)(y - diameter/80));
					}
					x -= diameter;
					if(x <= 0) {
						g2.setColor(Color.decode("#262B2C"));
						y += diameter;
						x += diameter;
						counter = 0;
					}
				}
				auxNode = auxNode.getNext();
			}
		}
	}
	
	//=========================================== LOGIC ======================================
	
	public void setHeadNode(Node<Turn> headNode, boolean flag) {
		node = headNode;
		selectedOval = false;
		clickedOval = false;
		addingFlag = flag;
		showingOval = false;
		if(node != null) {
			mf.updateDataLabel("");
		}
		revalidate();
		repaint();
	}
	
	//=========================================== EVENTS ========================================
	
	public void listener() {
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				clickedOval = false;
//				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < ovalsList.size(); i++) {
					ovalsList.get(i).setxMouse(e.getX());
					ovalsList.get(i).setyMouse(e.getY());
					if(ovalsList.get(i).ovalFx()) {
						setCursor(new Cursor(Cursor.HAND_CURSOR));
						clickedOval = true;
						Node<Turn> auxNode = node;
						String string = auxNode.getData().labelingTurn();
						while(auxNode.getData().getTurnNumber() != ovalsList.get(i).getTurn()) {
							auxNode = auxNode.getNext();
							string = auxNode.getData().labelingTurn();
						}
						mf.updateDataLabel(string);
						showingOvalNumber = i;
						clickedOvalNumber = i;
						i = ovalsList.size();
						repaint();
					}
					else {
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						clickedOval = false;
						repaint();
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {			
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < ovalsList.size(); i++) {
					ovalsList.get(i).setxMouse(e.getX());
					ovalsList.get(i).setyMouse(e.getY());
					if(ovalsList.get(i).ovalFx()) {
						setCursor(new Cursor(Cursor.HAND_CURSOR));
						showingOval = true;
						showingOvalNumber = i;
						i = ovalsList.size();
						repaint();
					}
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				for (int i = 0; i < ovalsList.size(); i++) {
					ovalsList.get(i).setxMouse(e.getX());
					ovalsList.get(i).setyMouse(e.getY());
					if(ovalsList.get(i).ovalFx()) {
						setCursor(new Cursor(Cursor.HAND_CURSOR));
						selectedOval = true;
						selectedOvalNumber = i;
						i = ovalsList.size();
						repaint();
					}
					else {
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						selectedOval = false;
						repaint();
					}
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {		
			}
		});
	}

}
