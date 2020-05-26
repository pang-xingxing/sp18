 public class NBody {
    public static double readRadius(String path) {
    	In in = new In(path);
        int number = in.readInt();
		double radius = in.readDouble();
		return radius;
    }

    public static Planet[] readPlanets(String path) {
    	In in = new In(path);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[number];

        int i = 0;
        while(i < number){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i++] = new Planet(xP, yP, xV, yV, m, img);
        }
		return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < planets.length; i++){
        	planets[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        for (double t = 0; t<=T; t += dt){
        	double[] xForces = new double[planets.length];
        	double[] yForces = new double[planets.length];
        	StdDraw.picture(0, 0, "images/starfield.jpg");
        	for (int j = 0; j < planets.length; j++){
        		xForces[j] = planets[j].calcNetForceExertedByX(planets);
        		yForces[j] = planets[j].calcNetForceExertedByY(planets);
        		planets[j].update(dt, xForces[j], yForces[j]);
        		planets[j].draw();
        	}
        	StdDraw.show();
        	StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
           		planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
    }
}