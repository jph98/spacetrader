package com.oogly.spacetrader;


/**
 * SapceTrader application.
 */
public class Main {
    
    private SolarSystem system;
    
    public Main() {

        system = new SolarSystem();

        Planet sun = system.addPlanetVertex("sun");
        Planet mercury = system.addPlanetVertex("mercury");
        system.connectPlanets(sun, mercury);

        Planet venus = system.addPlanetVertex("venus");
        system.connectPlanets(mercury, venus);

        Planet moon = system.addPlanetVertex("moon");
        system.connectPlanets(venus, moon);

        Planet earth = system.addPlanetVertex("earth");
        Planet mars = system.addPlanetVertex("mars");
        system.connectPlanets(earth, mars);

        Planet jupiter = system.addPlanetVertex("jupiter");
        system.connectPlanets(mars, jupiter);

        system.display();
    }
    
    public static void main( String[] args ) {
        
        Main trader = new Main();
    }
    
}
