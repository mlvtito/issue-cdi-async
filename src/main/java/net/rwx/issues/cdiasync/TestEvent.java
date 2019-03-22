/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rwx.issues.cdiasync;

/**
 *
 * @author mlvtito
 */
public class TestEvent {
    private String data;

    public static TestEvent of(String data) {
        TestEvent event = new TestEvent();
        event.setData(data);
        return event;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
