package helpers;

/**
 * This is a example of a helper class, 
 * helper classes are static classes which perform quick operations to prevent DRY (don't repeat yourself)
 * Please note the final modifier for the class and the constructor being private.
 * @author Corne Lukken
 */
public final class Math {

    private Math() {
    }
    
    public static double safeDivision(double a, double b) {
        if(a != 0 && b != 0) {
            return a / b;
        }
        else {
            return 0;
        }
    }
}
