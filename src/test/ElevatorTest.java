import com.elevatormgr.web.controller.Elevator;
import com.elevatormgr.web.controller.ElevatorRequest;
import com.elevatormgr.web.util.ElevatorStates;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by raghuram gururajan on 11/5/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElevatorTest {
    Elevator elevator;
    private ConcurrentLinkedQueue<ElevatorRequest> requests;
    @Before
    public void setUp () {
        requests = new ConcurrentLinkedQueue<ElevatorRequest>();
        elevator = new Elevator(10,requests);
    }

    @Test
    public void testAmoveUp() throws InterruptedException {
        elevator.moveUp();
        Assert.assertEquals(elevator.getLevel() , 2);
    }

    @Test
    public void testBmoveDown() throws InterruptedException {
        elevator.moveUp();
        elevator.moveDown();
        Assert.assertEquals(elevator.getLevel() , 1);
    }

    @Test
    public void testCopenDoor() throws InterruptedException {
        elevator.openDoor();
        Assert.assertTrue(elevator.getCurrentState() == ElevatorStates.OPEN);
    }

    @Test
    public void testDcloseDoor() throws InterruptedException {
        elevator.closeDoor();
        Assert.assertTrue(elevator.getCurrentState() == ElevatorStates.CLOSE);
    }

    @Test
    public void testIdleState() throws InterruptedException {
        Assert.assertTrue(elevator.getCurrentState() == ElevatorStates.IDLE);

    }






}
