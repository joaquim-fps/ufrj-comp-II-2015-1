import java.awt.Color;


public class CherishBall extends PokeBall
{
	public CherishBall(World world, double radius, Color color) {
		super(radius, color);
		
		double x = world.getWidth() * Math.random();
		
		x = (x + radius >= world.getWidth()) ? x - (radius+1) : x;
		x = (x - radius <= 0) ? x + (radius+1) : x;
		
		super.setCenter(new Point(x, radius+1));
		super.setV(new Velocity(3 * Math.random(), -3 * Math.random()));
	}
	
	public boolean hasCollidedWithBottom(World world)
	{
		if (super.getCenter().getPy() + super.getRadius() >= world.getHeight()) {
			return true;
		}
		return false;
	}
}
