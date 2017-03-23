package com.theduke.bronze.business.entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christoph
 */
public class StartTest {
    
    @Test
    public void testAbgeschlossen() {
        Start instance = new Start();
        boolean result = instance.abgeschlossen();
        assertNull(instance.getAngriffszeit());
        assertNull(instance.getStaffelzeit());
        assertFalse(result);
        
        instance.setAngriffszeit(45.55);
        instance.setStaffelzeit(null);
        result = instance.abgeschlossen();
        assertFalse(result);
        
        instance.setAngriffszeit(null);
        instance.setStaffelzeit(50.0);
        result = instance.abgeschlossen();
        assertFalse(result);
        
        instance.setAngriffszeit(45.55);
        instance.setStaffelzeit(60.15);
        result = instance.abgeschlossen();
        assertTrue(result);
    }
    
    @Test
    public void testGutpunkte() {
        Start instance = new Start();
        int result = instance.gutpunkte();
        assertEquals(500, result);
        
        instance.setAlterspunkte(5);
        result = instance.gutpunkte();
        assertEquals(500+5, result);
    }
    
    @Test
    public void testAbzugspunkte() {
        Start instance = new Start();
        double result = instance.abzugspunkte();
        assertEquals(0.0, result, 0);
        
        instance.setAngriffszeit(45.5);
        result = instance.abzugspunkte();
        assertEquals(45.5, result, 0);
        
        instance.setAngriffszeit(45.5);
        instance.setStaffelzeit(50.5);
        instance.setStrafpunkteAngriff(10);
        instance.setStrafpunkteStaffel(5);
        result = instance.abzugspunkte();
        assertEquals(45.5+50.5+10+5, result, 0);
    }
    
    @Test
    public void testCalculateGesamtpunkte() {
        Start instance = new Start();
        instance.setAlterspunkte(11);
        instance.setAngriffszeit(50.55);
        instance.setStaffelzeit(55.4);
        instance.setStrafpunkteAngriff(10);
        instance.setStrafpunkteStaffel(5); 
        double result = instance.calculateGesamtpunkte();
        assertEquals(500+11-50.55-55.4-10-5, result, 0);
    }

}
