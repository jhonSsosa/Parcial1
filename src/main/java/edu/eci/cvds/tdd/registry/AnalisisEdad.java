package edu.eci.cvds.tdd.registry;


public class AnalisisEdad {
    private int edad;
    public static boolean calcular(int edad){
        if (edad <= 17 && edad >= 0) {
            return true;
        }
        if (edad > 18 && edad < 135){
            return false;
        }
        return false;
    }
}
