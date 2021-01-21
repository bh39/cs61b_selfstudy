public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xD = xxPos - p.xxPos;
        double yD = yyPos - p.yyPos;
        return Math.sqrt(xD * xD + yD * yD);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
        double force = (6.67e-11 * mass * p.mass) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double xD = p.xxPos - xxPos;
        return this.calcForceExertedBy(p) * xD / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double yD = p.yyPos - yyPos;
        return this.calcForceExertedBy(p) * yD / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0;
        for (Planet planet : planets) {
            if (!(this.equals(planet))) {
                netForceX += this.calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0;
        for (Planet planet : planets) {
            if (!(this.equals(planet))) {
                netForceY += this.calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double accX = fX / mass;
        double accY = fY / mass;
        xxVel += accX * dt;
        yyVel += accY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }


}
