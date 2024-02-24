package edu.eci.cvds.tdd.registry;


public class AnalisisEdad {
    private int edad;

    public static boolean calcular(int edad) throws Exception {
        if (edad < 0) {
            throw new Exception(String.valueOf(false));
        }

        if (edad < 17 && edad >= 0) {
            return true;
        }
        if (edad >= 18 && edad < 135) {
            return false;
        }
        return true;
    }
}
