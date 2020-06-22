import org.junit.Test;
import static org.junit.Assert.*;

public class TestOfByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.


    @Test
    public void testequalChars() {
        CharacterComparator offBy5 = new OffByN(5);
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertFalse(offBy5.equalChars('f', 'h'));
    }



}
