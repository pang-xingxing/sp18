public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distance = Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)
		+(yyPos-p.yyPos)*(yyPos-p.yyPos));
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		double force = G * mass * p.mass/(r * r);
		return force;
	}

	public double calcForceExertedByX(Planet p){
		double r = calcDistance(p);
		double force = calcForceExertedBy(p);
		double forceX = force * (p.xxPos - xxPos)/r;
		return forceX;
	}

	public double calcForceExertedByY(Planet p){
		double r = calcDistance(p);
		double force = calcForceExertedBy(p);
		double forceY = force * (p.yyPos - yyPos)/r;
		return forceY;
	}

	public double calcNetForceExertedByX(Planet[] allplanets){
		double netforceX = 0;
		for (int i = 0; i < allplanets.length; i++){
			if (this.equals(allplanets[i])) continue;
			netforceX += calcForceExertedByX(allplanets[i]);
		}
		return netforceX;	
	}

	public double calcNetForceExertedByY(Planet[] allplanets){
		double netforceY = 0;
		for (int i = 0; i < allplanets.length; i++){
			if (this.equals(allplanets[i])) continue;
			netforceY += calcForceExertedByY(allplanets[i]);
		}
		return netforceY;	
	}

	public void update(double dt, double fX, double fY){
		double ax = fX/mass;
		double ay = fY/mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}