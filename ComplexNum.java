public class ComplexNum {

    private double Real;
    private double Imag;

    public ComplexNum(double real, double imag) {
        Real = real;
        Imag = imag;
    }
    
    public double real() {
        return Real;
    }

    public double imag() {
        return Imag;
    }

    @Override
    public String toString() {
        if (imag() < 0) {
            return "" + Real + " - " + (-Imag) + "i"; 
        }
        return "" + Real + " + " + Imag + "i";
    }

    public ComplexNum conjugate() {
        return new ComplexNum(Real, -Imag);
    }

    public double mag() {
        if (Real == 0) {
            if (Imag < 0) {
                return -Imag;
            }
            return Imag;
        }
        if (Imag == 0) {
            if (Real < 0) {
                return -Real;
            }
            return Real;
        }
        return Math.pow(Math.pow(Real, 2) + Math.pow(Imag, 2), 0.5);
    }

    public double phase() {
        double y = imag();
        double x = real();
        double theta = Math.atan(y/x);
        if (x < 0) {
            if (y > 0) {
                theta += Math.PI;
            }
            if (y < 0) {
                theta -= Math.PI;
            }
        }
        return theta;
    }

    public static class Polar {
        private double r;
        private double theta;
        
        public Polar(double rad, double angle) {
            r = rad;
            theta = angle;
        }

        public double mag() {
            return r;
        }

        public double phase() {
            double angle = theta;
            if (angle > Math.PI) {
                while (angle > Math.PI) {
                    angle -= 2*Math.PI;
                }
            }
            if (angle < -Math.PI) {
                while (angle < -Math.PI) {
                    angle += 2*Math.PI;
                }
            }
            return angle;
        }

        @Override
        public String toString() {
            return mag() + "(e^" + phase() + "i)";
        }

        public Polar conjugate() {
            return new Polar(mag(), -1*phase());
        }
    }

    public static ComplexNum toComplexRect(Polar z) {
        if (Math.sin(z.phase())==1 || Math.sin(z.phase())==-1 || Math.cos(z.phase())==1 || Math.cos(z.phase())==-1) {
            if (Math.cos(z.phase())==1 || Math.cos(z.phase())==-1) {
                return new ComplexNum(z.mag()*Math.cos(z.phase()), 0);
            }
            else {
                return new ComplexNum(0, z.mag()*Math.sin(z.phase()));
            }
        }
        else {
            return new ComplexNum(z.mag()*Math.cos(z.phase()), z.mag()*Math.sin(z.phase()));
        }
    }

    public static ComplexNum toComplexRect(ComplexNum z) {
        return z;
    }

    public static Polar toComplexPolar(ComplexNum z) {
        return new Polar(z.mag(), z.phase());
    }

    public static Polar toComplexPolar(Polar z) {
        return z;
    }

    /*
     * The next 8 methods are all addition methods which can add two 
     * complex numbers of either format or a complex number and a real number
     */

    public static ComplexNum PlusC(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real()+b.real(), a.imag()+b.imag());
    }

    public static ComplexNum PlusC(ComplexNum a, Polar b) {
        return PlusC(a, toComplexRect(b));
    }

    public static ComplexNum PlusC(Polar a, ComplexNum b) {
        return PlusC(toComplexRect(a), b);
    }

    public static Polar PlusC(Polar a, Polar b) {
        return toComplexPolar(PlusC(toComplexRect(a), toComplexRect(b)));
    }

    public static ComplexNum PlusC(ComplexNum a, double b) {
        return PlusC(a, new ComplexNum(b, 0));
    }

    public static ComplexNum PlusC(double a, ComplexNum b) {
        return PlusC(new ComplexNum(a, 0), b);
    }

    public static Polar PlusC(Polar a, double b) {
        return toComplexPolar(PlusC(toComplexRect(a), new ComplexNum(b,0)));
    }

    public static Polar PlusC(double a, Polar b) {
        return toComplexPolar(PlusC(new ComplexNum(a,0), toComplexRect(b)));
    }

    /*
     * The next 8 methods are all subtraction methods which can subtract two 
     * complex numbers of either format or a complex number and a real number
     */

    public static ComplexNum MinusC(ComplexNum x, ComplexNum y) {
        return new ComplexNum(x.real()-y.real(), x.imag()-y.imag());
    }

    public static ComplexNum MinusC(ComplexNum a, Polar b) {
        return MinusC(a, toComplexRect(b));
    }

    public static ComplexNum MinusC(Polar a, ComplexNum b) {
        return MinusC(b, a);
    }

    public static Polar MinusC(Polar a, Polar b) {
        return toComplexPolar(PlusC(toComplexRect(a), toComplexRect(b)));
    }

    public static ComplexNum MinusC(ComplexNum a, double b) {
        return MinusC(a, new ComplexNum(b, 0));
    }

    public static ComplexNum MinusC(double a, ComplexNum b) {
        return MinusC(b, a);
    }

    public static Polar MinusC(Polar a, double b) {
        return toComplexPolar(MinusC(toComplexRect(a), new ComplexNum(b,0)));
    }

    public static Polar MinusC(double a, Polar b) {
        return MinusC(b, a);
    }

    /*
     * The next 8 methods are all multiplication methods which can multiply two 
     * complex numbers of either format or a complex number and a real number
     */

    public static ComplexNum TimesC(ComplexNum x, ComplexNum y) {
        return new ComplexNum(x.real()*y.real() - x.imag()*y.imag(), x.real()*y.imag() + x.imag()*y.real());
    }

    public static ComplexNum TimesC(ComplexNum a, Polar b) {
        return TimesC(a, toComplexRect(b));
    }

    public static ComplexNum TimesC(Polar a, ComplexNum b) {
        return TimesC(b, a);
    }

    public static Polar TimesC(Polar a, Polar b) {
        return toComplexPolar(TimesC(toComplexRect(a), toComplexRect(b)));
    }

    public static ComplexNum TimesC(ComplexNum a, double b) {
        return TimesC(a, new ComplexNum(b, 0));
    }

    public static ComplexNum TimesC(double a, ComplexNum b) {
        return TimesC(b, a);
    }

    public static Polar TimesC(Polar a, double b) {
        return toComplexPolar(TimesC(toComplexRect(a), new ComplexNum(b,0)));
    }

    public static Polar TimesC(double a, Polar b) {
        return TimesC(b, a);
    }

    /*
     * The next 8 methods are all division methods which can divide two 
     * complex numbers of either format or a complex number and a real number
     */

    public static ComplexNum DivideC(ComplexNum a, ComplexNum b) {
        return toComplexRect(new Polar(a.mag()/b.mag(), a.phase()-b.phase()));
    }

    public static ComplexNum DivideC(ComplexNum a, Polar b) {
        return DivideC(a, toComplexRect(b));
    }

    public static ComplexNum DivideC(Polar a, ComplexNum b) {
        return DivideC(b, a);
    }

    public static Polar DivideC(Polar a, Polar b) {
        return toComplexPolar(DivideC(toComplexRect(a), toComplexRect(b)));
    }

    public static ComplexNum DivideC(ComplexNum a, double b) {
        return DivideC(a, new ComplexNum(b, 0));
    }

    public static ComplexNum DivideC(double a, ComplexNum b) {
        return DivideC(b, a);
    }

    public static Polar DivideC(Polar a, double b) {
        return toComplexPolar(TimesC(toComplexRect(a), new ComplexNum(b,0)));
    }

    public static Polar DivideC(double a, Polar b) {
        return TimesC(b, a);
    }
}