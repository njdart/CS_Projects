import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures.Queue;

import java.lang.reflect.Field;


public class QueueTest {

    @Test
    public void testPush() throws IllegalAccessException, NoSuchFieldException{
        int qSize = 4;
        Queue<String> q = new Queue<String>(qSize);

        Class c = q.getClass();
        Field headPointer = c.getDeclaredField("headPointer");
        Field tailPointer = c.getDeclaredField("tailPointer");
        Field size = c.getDeclaredField("allocatedSize");
        Field itemCount = c.getDeclaredField("itemCount");

        headPointer.setAccessible(true);
        tailPointer.setAccessible(true);
        size.setAccessible(true);
        itemCount.setAccessible(true);

        // Check the constructor did it's job properly
        assertEquals(0, headPointer.getInt(q));
        assertEquals(0, tailPointer.getInt(q));
        assertEquals(qSize, size.getInt(q));   //default allocatedSize for a queue is 16
        assertEquals(0, itemCount.getInt(q));
        assertTrue(q.isEmpty());

        q.push("Hello");
        q.push("Beautiful");
        q.push("World!");

        assertEquals(3, headPointer.getInt(q));
        assertEquals(0, tailPointer.getInt(q));
        assertEquals(qSize, size.getInt(q));
        assertEquals(3, itemCount.getInt(q));
        assertFalse(q.isEmpty());
    }

    @Test
    public void testSizeIncrease() throws IllegalAccessException, NoSuchFieldException{
        Queue<String> q = new Queue<String>(new String[] {"Hello", "World"});

        Class c = q.getClass();
        Field headPointer = c.getDeclaredField("headPointer");
        Field tailPointer = c.getDeclaredField("tailPointer");
        Field size = c.getDeclaredField("allocatedSize");
        Field itemCount = c.getDeclaredField("itemCount");

        headPointer.setAccessible(true);
        tailPointer.setAccessible(true);
        size.setAccessible(true);
        itemCount.setAccessible(true);

        assertEquals(1, headPointer.getInt(q));
        assertEquals(0, tailPointer.getInt(q));
        assertEquals(2, size.getInt(q));
        assertEquals(2, itemCount.getInt(q));
        assertFalse(q.isEmpty());

        //Double size
        q.push("Foo");
        q.push("Bar");

        //Double it again
        q.push("FooBar");

        assertEquals(5, headPointer.getInt(q));
        assertEquals(0, tailPointer.getInt(q));
        assertEquals(8, size.getInt(q));
        assertEquals(5, itemCount.getInt(q));
        assertFalse(q.isEmpty());

        //Pop the top two, just to make sure
        assertEquals("Hello", q.pop());
        assertEquals("World", q.pop());
    }

    @Test
    public void testPop() throws IllegalAccessException, NoSuchFieldException{
        Queue<String> q = new Queue<String>(new String[] {"Fred", "Bob", "Joe"});

        Class c = q.getClass();
        Field headPointer = c.getDeclaredField("headPointer");
        Field tailPointer = c.getDeclaredField("tailPointer");
        Field size = c.getDeclaredField("allocatedSize");
        Field itemCount = c.getDeclaredField("itemCount");

        headPointer.setAccessible(true);
        tailPointer.setAccessible(true);
        size.setAccessible(true);
        itemCount.setAccessible(true);

        assertEquals(2, headPointer.getInt(q));
        assertEquals(0, tailPointer.getInt(q));
        assertEquals(3, size.getInt(q));
        assertEquals(3, itemCount.getInt(q));
        assertFalse(q.isEmpty());

        assertEquals(q.pop(), "Fred");

        assertEquals(2, headPointer.getInt(q));
        assertEquals(1, tailPointer.getInt(q));
        assertEquals(3, size.getInt(q));
        assertEquals(2, itemCount.getInt(q));
        assertFalse(q.isEmpty());

        assertEquals(q.pop(), "Bob");

        assertEquals(2, headPointer.getInt(q));
        assertEquals(2, tailPointer.getInt(q));
        assertEquals(3, size.getInt(q));
        assertEquals(1, itemCount.getInt(q));
        assertFalse(q.isEmpty());

        assertEquals(q.pop(), "Joe");

        assertEquals(2, headPointer.getInt(q));
        assertEquals(0, tailPointer.getInt(q));
        assertEquals(3, size.getInt(q));
        assertEquals(0, itemCount.getInt(q));
        assertTrue(q.isEmpty());

        assertNull(q.pop());
    }

    @Test
    public void emptyQueue(){
        Queue<String> q = new Queue<String>(new String[] {"Foo", "Bar", "Foobar"});
        assertEquals("Foo", q.pop());
        assertEquals("Bar", q.pop());
        assertEquals("Foobar", q.pop());
        assertNull(q.pop());
        q.push("Jim");
        q.push("Tim");
        q.push("Bob");
        assertEquals("Jim", q.pop());
        assertEquals("Tim", q.pop());
        assertEquals("Bob", q.pop());
        assertNull(q.pop());
    }
}
