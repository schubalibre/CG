package matVecLib;

/**
 * Created by roberto on 08/10/15.
 */
public class Main {

    public static void main(String[] args) {
        testNormal3();
    }

    private static void testNormal3() {

        Normal3 n = new Normal3(1,2,3);

        System.out.println(n.mul(0.5));

    }
}
