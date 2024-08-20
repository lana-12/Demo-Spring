package com.lana.springdemo.Components;


import ch.qos.logback.core.CoreConstants;
import org.springframework.stereotype.Component;

/**
 *
 * @author mattb
 */
@Component
public class DigiComponent {
    public DigiComponent(){
        System.out.println("Component");
    }
}