package edu.eci.cvds.tdd.registry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgeTest {
    @Test
    public void ShouldPassUnderagePerson() throws Exception {
        // Menor de edad
        Person person = new Person("Milton", 54321, 17, Gender.MALE, true);
        int age = person.getAge();
        boolean result = AnalisisEdad.calcular(age);
        assertEquals(true, result);
        // Menor de edad
        Person person1 = new Person("Baby", 983492, 0, Gender.FEMALE, true);
        int age2 = person1.getAge();
        boolean result2 = AnalisisEdad.calcular(age2);
        assertEquals(true, result2);
    }

    @Test
    public void ShouldNotPassPassUnderagePerson() throws Exception {
        // Menor de edad
        Person person = new Person("Milton", 54321, 18, Gender.MALE, true);
        int age = person.getAge();
        boolean result = AnalisisEdad.calcular(age);
        assertEquals(false, result);
        // Menor de edad
        Person person1 = new Person("Juan", 983492, 30, Gender.FEMALE, true);
        int age2 = person1.getAge();
        boolean result2 = AnalisisEdad.calcular(age2);
        assertEquals(false, result2);
    }

    @Test
    public void ShouldNotPassValidAgePerson() throws Exception {
        // Edad invalida:  edad < 0
        Person person = new Person("Mutsia", 67890, -5, Gender.MALE, true);
        int age = person.getAge();
        boolean result = AnalisisEdad.calcular(age);
        assertEquals(false, result);
        // Edad invalida: edad >= 135
        person.setAge(135);
        int age3 = person.getAge();
        boolean result3 = AnalisisEdad.calcular(age3);
        assertEquals(false, result3);
        // Edad invalida: edad >= 135
        person.setAge(200);
        int age2 = person.getAge();
        boolean result2 = AnalisisEdad.calcular(age2);
        assertEquals(false, result2);
    }
}
