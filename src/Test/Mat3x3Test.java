package Test;

import org.junit.Assert;
import org.junit.Test;
import MathLib.Mat3x3;
import MathLib.Vector3;
import MathLib.Point3;


/**
 * Created by roberto on 10/10/15.
 */
public class Mat3x3Test {

    @Test
    public void testMulVector() throws Exception {
        Mat3x3 m = new Mat3x3(1,0,0,0,1,0,0,0,1);
        Vector3 v = new Vector3(3,2,1);
        Vector3 r = m.mul(v);
        Assert.assertEquals(new Vector3(3, 2, 1), r);
    }

    @Test
    public void testMulPoint() throws Exception {
        Mat3x3 m = new Mat3x3(1,0,0,0,1,0,0,0,1);
        Point3 p = new Point3(3,2,1);
        Point3 r = m.mul(p);
        Assert.assertEquals(new Point3(3, 2, 1), r);
    }

    @Test
    public void testMulMatrix() throws Exception {
        Mat3x3 m1 = new Mat3x3(1,2,3,4,5,6,7,8,9);
        Mat3x3 m2 = new Mat3x3(1,0,0,0,1,0,0,0,1);
        Mat3x3 r = m1.mul(m2);
        Assert.assertEquals(new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9), r);
    }

    @Test
    public void testMulMatrix1() throws Exception {
        Mat3x3 m1 = new Mat3x3(1,2,3,4,5,6,7,8,9);
        Mat3x3 m2 = new Mat3x3(0,0,1,0,1,0,1,0,0);
        Mat3x3 r = m1.mul(m2);
        Assert.assertEquals(new Mat3x3(3, 2, 1, 6, 5, 4, 9, 8, 7), r);
    }

    @Test
    public void testChangeCol1() throws Exception {
        Mat3x3 m = new Mat3x3(1,2,3,4,5,6,7,8,9);
        Vector3 v = new Vector3(8,8,8);
        Mat3x3 r = m.changeCol1(v);
        Assert.assertEquals(new Mat3x3(8, 2, 3, 8, 5, 6, 8, 8, 9), r);
    }

    @Test
    public void testChangeCol2() throws Exception {
        Mat3x3 m = new Mat3x3(1,2,3,4,5,6,7,8,9);
        Vector3 v = new Vector3(8,8,8);
        Mat3x3 r = m.changeCol2(v);
        Assert.assertEquals(new Mat3x3(1, 8, 3, 4, 8, 6, 7, 8, 9), r);
    }

    @Test
    public void testChangeCol3() throws Exception {
        Mat3x3 m = new Mat3x3(1,2,3,4,5,6,7,8,9);
        Vector3 v = new Vector3(8,8,8);
        Mat3x3 r = m.changeCol3(v);
        Assert.assertEquals(new Mat3x3(1, 2, 8, 4, 5, 8, 7, 8, 8), r);
    }
}