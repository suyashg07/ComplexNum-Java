/**
 * @author Suyash Gupta
 */

public class ComplexNum {

    /**
     * Represents the real part of the complex number.
     */
    final private double real;
    /**
     * Represents the imaginary part of the complex number.
     */
    final private double imag;

    /**
     * Creates a complex number with the given real and imaginary parts.
     * 
     * @param   real    the real part of the complex number.
     * @param   imag    the imaginary part of the complex number.
     */
    public ComplexNum(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    /**
     * Returns the real part of the complex number.
     * @return  the real part of the complex number.
     */
    public double real() {
        return real;
    }
    
    /**
     * Returns the imaginary part of the complex number.
     * @return  the imaginary part of the complex number.
     */
    public double imag() {
        return imag;
    }

    /**
     * Returns the string representation of the complex number.
     * @return  the string representation of the complex number.
     */
    @Override
    public String toString() {
        if (imag < 0) {
            return real + " - " + (-imag) + "i"; 
        }
        return real + " + " + imag + "i";
    }

    /**
     * Returns the conjugate of a complex number.
     * @return  for a complex number of <i>a + bi</i>, it will return a - bi.
     */
    public ComplexNum conjugate() {
        return new ComplexNum(real, -imag);
    }
    /**
     * Returns the magnitude of a complex number.
     * @return  for a complex number of <i>a + bi</i> it will return sqrt(a^2 + b^2)
     */
    public double mag() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
    }

    /**
     * Returns the phase angle of the complex number.
     * @return  for of <i>a + bi = re^iθ</i> it will return the 
     * value of θ in range [-π, π].
     */
    public double phase() {
        double theta = Math.atan(imag/real);
        if (real < 0) {
            if (imag >= 0) 
                theta += Math.PI;
            if (imag < 0) 
                theta -= Math.PI;
        }
        return theta;
    }

    /**
     * Represents a complex number in polar form.
     */
    public static class Polar {
        final private double r;
        private double theta;
        
        /**
         * Creates a complex number in polar form with the given magnitude and phase.
         * 
         * @param   rad     the magnitude of the complex number.
         * @param   angle   the phase of the complex number.
         */
        public Polar(double rad, double angle) {
            r = rad;
            theta = angle;
            if (theta > Math.PI) {
                while (theta > Math.PI) {
                    theta -= 2*Math.PI;
                }
            }
            if (theta < -Math.PI) {
                while (theta < -Math.PI) {
                    theta += 2*Math.PI;
                }
            }
        }

        /**
         * Returns the magnitude of the complex number.
         * @return  the magnitude of the complex number.
         */
        public double mag() {
            return r;
        }

        /**
         * Returns the phase of the complex number.
         * @return  the phase of the complex number.
         */
        public double phase() {
            return theta;
        }

        /**
         * Returns the string representation of the complex number.
         * @return  the string representation of the complex number.
         */
        @Override
        public String toString() {
            return r + "(e^" + theta + "i)";
        }

        /**
         * Returns the conjugate of the complex number.
         * @return  the conjugate of the complex number.
         */
        public Polar conjugate() {
            return new Polar(r, -theta);
        }

        /**
         * Returns the complex number in rectangular form.
         * @return  the complex number in rectangular form.
         */
        public ComplexNum toComplexRect() {
            if (Math.abs(Math.sin(theta)) == 1 || Math.abs(Math.cos(theta)) == 1) {
                if (Math.abs(Math.cos(theta)) == 1) {
                    return new ComplexNum(r*Math.cos(theta), 0);
                }
                else {
                    return new ComplexNum(0, r*Math.sin(theta));
                }
            }
            else {
                return new ComplexNum(r*Math.cos(theta), r*Math.sin(theta));
            }
        }
    }

    /**
     * Returns the complex number in polar form.
     * @return  the complex number in polar form.
     */
    public Polar toComplexPolar() {
        return new Polar(mag(), phase());
    }

    /**
     * Returns the sum of two complex numbers, both in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a + b</i> as a complex number in rectangular form.
     */
    public static ComplexNum PlusC(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real+b.real, a.imag+b.imag);
    }

    /**
     * Returns the sum of two complex numbers, the first in rectangular
     * and the other in polar form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a + b</i> as a complex number in rectangular form.
     */
    public static ComplexNum PlusC(ComplexNum a, Polar b) {
        return PlusC(a, b.toComplexRect());
    }

    /**
     * Returns the sum of two complex numbers, the first in polar
     * and the other in rectangular form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a + b</i> as a complex number in rectangular form.
     */
    public static ComplexNum PlusC(Polar a, ComplexNum b) {
        return PlusC(b, a);
    }

    /**
     * Returns the sum of two complex numbers, both in polar form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a + b</i> as a complex number in polar form.
     */
    public static Polar PlusC(Polar a, Polar b) {
        return PlusC(a.toComplexRect(), b.toComplexRect()).toComplexPolar();
    }

    /**
     * Returns the sum of a complex number in rectangular form and a real number,
     * in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a + b</i> as a complex number in rectangular form
     */
    public static ComplexNum PlusC(ComplexNum a, double b) {
        return PlusC(a, new ComplexNum(b, 0));
    }

    /**
     * Returns the sum of a real number and a complex number of rectangular form.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a + b</i> as a complex number in rectangular form.
     */
    public static ComplexNum PlusC(double a, ComplexNum b) {
        return PlusC(b, a);
    }

    /**
     * Returns the sum of a complex number of polar form and a real number.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a + b</i> as a complex number in polar form.
     */
    public static Polar PlusC(Polar a, double b) {
        return PlusC(a.toComplexRect(), new ComplexNum(b,0)).toComplexPolar();
    }

    /**
     * Returns the sum of a real number and a complex number of polar form.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a + b</i> as a complex number in polar form. 
     */
    public static Polar PlusC(double a, Polar b) {
        return PlusC(b, a);
    }

    /**
     * Returns the subtraction of two complex numbers in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a - b</i> as a complex number in rectangular form.
     */
    public static ComplexNum MinusC(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real - b.real, a.imag - b.imag);
    }

    /**
     * Returns the subtraction of a complex number in polar form from
     * a complex number in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a - b</i> as a complex number in rectangular form.
     */
    public static ComplexNum MinusC(ComplexNum a, Polar b) {
        return MinusC(a, b.toComplexRect());
    }

    /**
     * Returns the subtraction of a complex number in rectangular form
     * from a complex number in polar form.
     * 
     * @param   a   a complex number in poar form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a - b</i> as a complex number in rectangular form.
     */
    public static ComplexNum MinusC(Polar a, ComplexNum b) {
        return MinusC(a.toComplexRect(), b);
    }

    /**
     * Returns the subtraction of two complex numbers in polar form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a - b</i> as a complex number in polar form.
     */
    public static Polar MinusC(Polar a, Polar b) {
        return MinusC(a.toComplexRect(), b.toComplexRect()).toComplexPolar();
    }

    /**
     * Return the subtraction of a real number from a complex number 
     * in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a - b</i> as a complex number in rectangular form.
     */
    public static ComplexNum MinusC(ComplexNum a, double b) {
        return new ComplexNum(a.real - b, a.imag);
    }

    /**
     * Returns the subtraction of a complex number in rectangular form from a 
     * real number.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a - b</i> as a complex number in rectangular form.
     */
    public static ComplexNum MinusC(double a, ComplexNum b) {
        return new ComplexNum(a - b.real, -b.imag);
    }

    /**
     * Returns the subtraction of a real number from a complex number
     *  in polar form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a - b</i> as a complex number in polar form.
     */
    public static Polar MinusC(Polar a, double b) {
        return MinusC(a.toComplexRect(), b).toComplexPolar();
    }

    /**
     * Returns the subtraction of a complex number in polar form 
     * from a real number.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a - b</i> as a complex number in polar form.
     */
    public static Polar MinusC(double a, Polar b) {
        return MinusC(a, b.toComplexRect()).toComplexPolar();
    }

    /**
     * Returns the product of two complex numbers in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a × b</i> as a complex number in rectangular form.
     */
    public static ComplexNum TimesC(ComplexNum a, ComplexNum b) {
        return new ComplexNum(a.real*b.real - a.imag*b.imag, a.real*b.imag + a.imag*b.real);
    }

    /**
     * Returns the product of a complex number in rectangular form
     * and a complex number in polar form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a × b</i> as a complex number in rectangular form.
     */
    public static ComplexNum TimesC(ComplexNum a, Polar b) {
        return TimesC(a, b.toComplexRect());
    }

    /**
     * Returns the product of a complex number in polar form and
     * a complex number in rectangular form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a × b</i> as a complex number in polar form.
     */
    public static ComplexNum TimesC(Polar a, ComplexNum b) {
        return TimesC(b, a);
    }

    /**
     * Returns the product of two complex numbers in polar form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a × b</i> as a complex number in polar form.
     */
    public static Polar TimesC(Polar a, Polar b) {
        return new Polar(a.r * b.r, a.theta + b.theta);
    }

    /**
     * Returns the product of a complex number in rectangular form
     * and a real number.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a × b</i> as a complex number in rectangular form.
     */
    public static ComplexNum TimesC(ComplexNum a, double b) {
        return new ComplexNum(a.real * b, a.imag * b);
    }

    /**
     * Returns the product of a real number and a complex number in
     * rectangular form.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a × b</i> as a complex number in rectangular form.
     */
    public static ComplexNum TimesC(double a, ComplexNum b) {
        return TimesC(b, a);
    }

    /**
     * Returns the product of a complex number in polar form and a real number.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a × b</i> as a complex number in polar form.
     */
    public static Polar TimesC(Polar a, double b) {
        return new Polar(a.r * b, a.theta);
    }

    /**
     * Returns the product of a real number and a complex number in polar form.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a × b</i> as a complex number in polar form.
     */
    public static Polar TimesC(double a, Polar b) {
        return TimesC(b, a);
    }

    /**
     * Returns the quotient of two complex numbers in rectangular form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of <i>a / b</i> as a complex number in rectangular form.
     */
    public static ComplexNum DivideC(ComplexNum a, ComplexNum b) {
        return new Polar(a.mag() / b.mag(), a.phase() - b.phase()).toComplexRect();
    }

    /**
     * Returns the quotient of a complex number in rectangular form
     * and a complex number in polar form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a / b</i> as a complex number in rectangular form.
     */
    public static ComplexNum DivideC(ComplexNum a, Polar b) {
        return DivideC(a, b.toComplexRect());
    }

    /**
     * Returns the quotient of a cmplex number in polar form and a
     * complex number in rectangular form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex numer in rectangular form.
     * 
     * @return  the value of <i>a / b</i> as a complex number in recangular form.
     */
    public static ComplexNum DivideC(Polar a, ComplexNum b) {
        return DivideC(a.toComplexRect(), b);
    }

    /**
     * Returns the quotient of two complex numbers in polar form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a / b</i> as a complex number in polar form.
     */
    public static Polar DivideC(Polar a, Polar b) {
        return new Polar(a.r / b.r, a.theta - b.theta);
    }

    /**
     * Returns the quotient of a complex number in rectangular form
     * and a real number.
     * 
     * @param   a   a complex number in recatngular form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a / b</i> as a complex number in rectangular form.
     */
    public static ComplexNum DivideC(ComplexNum a, double b) {
        return DivideC(a, new ComplexNum(b, 0));
    }

    /**
     * Returns the quotient of a real number and a complex number in 
     * rectangular form.
     * 
     * @param   a   a real number
     * @param   b   a complex number in rectagular form.
     * 
     * @return  the value of <i>a / b</i> as a complex number in rectangular form.
     */
    public static ComplexNum DivideC(double a, ComplexNum b) {
        return DivideC(new ComplexNum(a, 0), b);
    }

    /**
     * Returns the quotient of a complex number in polar form and a real number.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a real number.
     * 
     * @return  the value of <i>a / b</i> as a complex number in polar form.
     */
    public static Polar DivideC(Polar a, double b) {
        return new Polar(a.r / b, a.theta);
    }

    /**
     * Returns the quotient of a real number and a complex number in polar form.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of <i>a / b</i> as a complex number in polar form.
     */
    public static Polar DivideC(double a, Polar b) {
        return new Polar(a / b.r, -b.theta);
    }
    
    /**
     * Raises the power of a complex number in rectangular form to another
     * complex number in rectangular form. Special case:
     * <ul>><li>if the base is 0, the result will be NaN + NaNi.</li></ul>
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of a ^ b as a complex number in rectangular form.
     */
    public static ComplexNum power(ComplexNum a, ComplexNum b) {
        //return new Polar(Math.pow(Math.E, b.real * Math.log(a.mag()) - b.imag * a.phase()), b.real * a.phase() + b.imag * Math.log(a.mag())).toComplexRect();
        ComplexNum z = TimesC(new ComplexNum(Math.log(a.mag()), a.phase()), b);
        return new Polar(Math.pow(Math.E, z.real), z.imag).toComplexRect();
    }

    /**
     * Raises the power of a complex number in rectangular form to a 
     * complex number in polar form.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of a ^ b as a complex number in rectangular form.
     */
    public static ComplexNum power(ComplexNum a, Polar b) {
        return power(a, b.toComplexRect());
    }

    /**
     * Raises the power of a complex number in polar form to a 
     * complex number in rectangular form.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of a ^ b as a complex number in rectangular form.
     */
    public static ComplexNum power(Polar a, ComplexNum b) {
        return power(a.toComplexRect(), b);
    }

    /**
     * Raises the power of a complex number in polar form to another
     * complex number in polar form.
     * 
     * @param   a   a complex number in polar form.   
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of a ^ b as a complex number in polar form.
     */
    public static Polar power(Polar a, Polar b) {
        return power(a.toComplexRect(), b.toComplexRect()).toComplexPolar();
    }

    /**
     * Raises the power of a complex number in rectangular form to a 
     * real number.
     * 
     * @param   a   a complex number in rectangular form.
     * @param   b   a real number.
     * 
     * @return  the value of a ^ b as a complex number in rectangular form.
     */
    public static ComplexNum power(ComplexNum a, double b) {
        Polar z = a.toComplexPolar();
        return new Polar(Math.pow(z.r, b), z.theta * b).toComplexRect();
    }

    /**
     * Raises the power of a real number to a complex number in 
     * rectangular form.
     * 
     * @param   a   a real number.
     * @param   b   a complex number in rectangular form.
     * 
     * @return  the value of a ^ b as a complex number in rectangular form.
     */
    public static ComplexNum power(double a, ComplexNum b) {
        return new Polar(Math.pow(a, b.real), b.imag * Math.log(a)).toComplexRect();
    }

    /**
     * Raises the power of a complex number in polar form
     * to a real number.
     * 
     * @param   a   a complex number in polar form.
     * @param   b   a real number.
     * 
     * @return  the value of a ^ b as a complex number in polar form.
     */
    public static Polar power(Polar a, double b) {
        return new Polar(Math.pow(a.r, b), a.theta * b);
    }
    
    /**
     * Raises a real number to the power of a complex number in 
     * polar form. Special cases:
     * 
     * @param   a   a real number
     * @param   b   a complex number in polar form.
     * 
     * @return  the value of a ^ b as a complex number in polar form.
     */
    public static Polar power(double a, Polar b) {
        return power(a, b.toComplexRect()).toComplexPolar();
    }
}