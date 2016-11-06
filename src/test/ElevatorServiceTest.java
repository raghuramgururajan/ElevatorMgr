import com.elevatormgr.web.controller.ElevatorController;
import com.elevatormgr.web.controller.ElevatorRequest;
import com.elevatormgr.web.controller.ElevatorService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;

/**
 * Created by raghuram gururajan on 11/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElevatorServiceTest {

    @Autowired
    ElevatorService elevatorService;

    @Before
    public void setUp() {
        elevatorService.startElevator(10);
    }

    @Test
    public void testApeekRequestTest() {
        ElevatorRequest elevatorRequest = new ElevatorRequest(6,7);
        elevatorService.addRequestToElevator(elevatorRequest);
        Assert.assertEquals(elevatorService.topRequest(), elevatorRequest);

    }


    @Test
    public void testBgetElevatorRequestTest() {
        elevatorService.addRequestToElevator(new ElevatorRequest(1,5));
        elevatorService.addRequestToElevator(new ElevatorRequest(5,6));
        ElevatorRequest[] requestList = elevatorService.getAllRequests();
        ElevatorRequest[] actualList = { new ElevatorRequest(1,5) , new ElevatorRequest(5,6)};
        Assert.assertArrayEquals(actualList, requestList);
    }





    @After
    public void tearDown() {
        try {
            Thread.sleep(8000);
        }
        catch (InterruptedException ex) {

        }
        elevatorService.shutDownElevator();
    }


}
