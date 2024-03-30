package first;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Balles extends JFrame{
	JPanel panel ;
	ArrayList<Cercle> listofcercle ;
	Timer horloge;
	public Balles() {
		this.setTitle("Balles");
		this.setSize(800,500);
		 this.setLocationRelativeTo(null);
		 listofcercle= new ArrayList<Cercle>();
		 // panel
		 panel = new JPanel() {
			 
			 @Override
			public void paint(Graphics g) {
				super.paint(g);
				
				
				
				for( Cercle c : listofcercle) {
					g.setColor(c.color);
					g.fillOval(c.x,c.y,c.diametre, c.diametre );
				}
				
			}
		 };
		 
		 this.setContentPane(panel);

		 // horloge
		 
//		 horloge = new Timer(20, new ActionListener() {
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	            	for( Cercle c : listofcercle) {
//		            	if (c.x + c.diametre >= panel.getWidth() || c.x <= 0) {
//		                    // Reverse the direction if the circle reaches the horizontal boundary
//		                    c.dx *= -1;
//		                }
//		                if (c.y + c.diametre >= panel.getHeight() || c.y-10 <= 0) {
//		                    // Reverse the direction if the circle reaches the vertical boundary
//		                    c.dy *= -1;
//		                }
//		                c.x += c.dx;
//		                c.y += c.dy;
//		                // Repaint the panel to reflect the changes
//		                repaint();
//					}
//	            	
//	            }
//	        });
		 
		 horloge = new Timer(200, new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        for (Cercle c1 : listofcercle) {
			            // Update the position of the circle
			            c1.x += c1.dx;
			            c1.y += c1.dy;

			            // Check for collisions with the panel boundaries
			            if (c1.x + c1.diametre >= panel.getWidth() || c1.x <= 0) {
			                // Reverse the direction if the circle reaches the horizontal boundary
			                c1.dx *= -1;
			            }
			            if (c1.y + c1.diametre >= panel.getHeight() || c1.y <= 0) {
			                // Reverse the direction if the circle reaches the vertical boundary
			                c1.dy *= -1;
			            }

			            // Check for collisions between pairs of circles
			            for (Cercle c2 : listofcercle) {
			                if (c1 != c2 && collides(c1, c2)) {
			                    // If circles collide, update their velocities to move in the opposite direction
			                    c1.dx *= -1;
			                    c1.dy *= -1;
			                    c2.dx *= -1;
			                    c2.dy *= -1;
			                }
			            }
			        }
			        // Repaint the panel to reflect the changes
			        panel.repaint();
			    }

				private boolean collides(Cercle c1, Cercle c2) {
					int dx = c1.x - c2.x;
				    int dy = c1.y - c2.y;
				    int distanceSquared = dx * dx + dy * dy;
				    int radiusSquared = (c1.diametre / 2 + c2.diametre / 2) * (c1.diametre / 2 + c2.diametre / 2);
				    return distanceSquared <= radiusSquared;
				}
			});

			
		 
		 horloge.start();
		 
		 // add randrom cercles 
		 
		 
		 this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					Random random = new Random();
					 
					Color color = new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255));
					int d =random.nextInt(10 , 80);
					int x = e.getX()-(d/2);
					int y = e.getY()-(d/2);
					int dx =random.nextInt(-5 , 10);
					int dy =random.nextInt(-10 , 15);
					listofcercle.add(new Cercle(x, y, d, dx, dy, color));
					repaint();
				}
			});
		 
		 
		 		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Balles balle = new Balles();
	}
}

class Cercle {
	int x , y ,diametre,  dx,dy ;
	Color color;
	public Cercle(int x, int y,int diametre ,int dx, int dy, Color color) {
		this.x = x;
		this.y = y;
		this.diametre = diametre;
		this.dx = dx;
		this.dy = dy;
		this.color = color;
	}
	
	
}