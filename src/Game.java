import java.awt.*;
import java.util.ArrayList;

public class Game {
	private World world;
	private ArrayList<Ball> balls, targetsList, chiefs,pop;
	private int width = 2;
	private int height = 2;
	private int quantTargets = 50;
	private Color background = StdDraw.BLUE;
	
	public Game () {
		world = new World (width, height, background);
		targetsList = new ArrayList<Ball>();
		chiefs = new ArrayList<Ball>();
		pop = new ArrayList<Ball>();
		balls = new ArrayList<Ball>();
		double raio = 0.05;
		Ball b = null;
		
		for (int i = 0; i < quantTargets; i ++) {
			b = new Ball (geraPonto(raio), 
		              new Velocity (-0.01 * Math.random(), 0.01 * Math.random()),
		              raio, 
		              StdDraw.GREEN);
			
			targetsList.add(b);
			balls.add(b);
		}
		
		b = new Ball (geraPonto(raio), 
	              new Velocity (-0.01 * Math.random(), 0.01 * Math.random()),
	              raio, 
	              StdDraw.MAGENTA);
		
		pop.add(b);
		balls.add(b);
		
		b = new Ball (geraPonto(raio), 
	              new Velocity (-0.01 * Math.random(), 0.01 * Math.random()),
	              raio, 
	              StdDraw.RED);
		
		chiefs.add(b);
		balls.add(b);
	}
	
	public void go() {
		StdDraw.setScale(-world.getWidth()/2, world.getWidth()/2);
		
		boolean explode = false;
		boolean roda = true;
		while (roda == true) {
			StdDraw.clear(world.getBackground());
			
			for (Ball ball : balls) {
				ball.move(world);
				ball.draw();
			}
		
			boolean colidiu = false;
			for (Ball chief : chiefs) {
				if (!targetsList.isEmpty()) {
					for (Ball target : targetsList) {
						if (chief.hasCollided(target)) 
						{
							//target.setColor(StdDraw.YELLOW); Uncomment it if wish to switch color when crush
							
							targetsList.remove(target);
							
							target.getV().setVx(chief.getV().getVx());
							target.getV().setVy(chief.getV().getVy());
							
							chiefs.add(target);
							
							colidiu = true;
							break;
						}
					}
					if(!pop.isEmpty())
					{
						for( Ball burst : pop)
						{
							//System.out.println("teste ");
							if(chief.hasCollided(burst)) // PLX OTIMIZAR ISSO
							{
								//System.out.println("Teste2 ");
								
								/*
								Ball aux = chiefs.get(0);
								chiefs.clear();
								chiefs.add(aux);
								*/
								pop.remove(burst);
								balls.remove(burst);
								
								explode = true;
								colidiu = true;
								break;
							}
						}
					}
					
					if (colidiu) { break; }
				}
				else {
					roda = false;
					break;
				}
				
				
			}
			
			if(explode)
			{
			
				for(Ball remove : chiefs)
				{
					if(chiefs.indexOf(remove) != 0)
					{
						//chiefs.remove(remove);
						balls.remove(remove);
					}
				}
				
				Ball aux = chiefs.get(0);
				chiefs.clear();
				chiefs.add(aux);
				
				explode = false;
			}
		
			StdDraw.show(10);
		}
	}
	
	private static Point geraPonto(double raio) {
		double x = 2 * Math.random() - 1;
		double y = 2 * Math.random() - 1;
		
		x = (x + raio >= 1) ? x - (raio+0.01) : x;
		x = (x - raio <= -1) ? x + (raio+0.01) : x;
		
		y = (y + raio >= 1) ? y - (raio+0.01) : y;
		y = (y - raio <= -1) ? y + (raio+0.01) : y;
		
		return new Point(x,y);
	}
}