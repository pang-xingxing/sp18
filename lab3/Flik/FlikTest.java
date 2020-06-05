import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void TestFilk(){
        assertTrue(Flik.isSameNumber(5, 6));
        assertTrue(Flik.isSameNumber(5, 5));
    }
}
