/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rwx.issues.cdiasync;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author mlvtito
 */
@Path("test")
public class TestResource {

    private static final Logger logger = Logger.getLogger(TestResource.class.getName());
    
    @Inject
    private Event<TestEvent> events;

    @GET
    public String get() {
        events.fire(TestEvent.of("FIRE SYNC"));
        events.fireAsync(TestEvent.of("FIRE ASYNC")).exceptionally((t) -> {
            logger.log(Level.SEVERE, t, () -> "####################### Error while processing event");
            return null;
        });
        return "TEST";
    }

    public void observeAsyncEvent(@ObservesAsync TestEvent event) {
        logger.log(Level.INFO, () -> "####################### Processing event " + event);
    }
    
    public void observeEvent(@Observes TestEvent event) {
        logger.log(Level.INFO, () -> "####################### Processing event " + event);
    }
}
