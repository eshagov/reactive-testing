package eu.shagov.fluxandmonoplayground;

import org.junit.Test;

public class EventLoopTest {

    @Test
    public void noOfProcessors(){

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
