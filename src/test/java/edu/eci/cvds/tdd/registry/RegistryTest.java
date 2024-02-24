package edu.eci.cvds.tdd.registry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegistryTest {
    private Registry registry = new Registry();

    @Test
    public void testValidPersonRegistration() {
        // Persona viva y edad válida: 17 < edad < 135
        Person person = new Person("Jeitson", 13579, 30, Gender.MALE, true); 
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void testDeadPersonRegistration() {
        // Persona muerta
        Person person = new Person("Sotsa", 12345, 25, Gender.MALE, false); 
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void testUnderagePersonRegistration() {
        // Persona viva y menor de edad
        Person person = new Person("Milton", 54321, 17, Gender.MALE, true); 
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.UNDERAGE, result);
        // Persona viva y menor de edad
        Person person1 = new Person("Baby", 983492, 0, Gender.FEMALE, true);
        RegisterResult result1 = registry.registerVoter(person1);
        assertEquals(RegisterResult.UNDERAGE, result1);
    }

    @Test
    public void testInvalidAgePersonRegistration1() {
        // Edad invalida:  edad < 0
        Person person = new Person("Mutsia", 67890, -5, Gender.MALE, true); 
        RegisterResult result = registry.registerVoter(person);
        assertEquals(RegisterResult.INVALID_AGE, result);
        // Edad invalida: edad >= 135
        person.setAge(135);
        result = registry.registerVoter(person);
        assertEquals(RegisterResult.INVALID_AGE, result);
        // Edad invalida: edad >= 135
        person.setAge(200);
        result = registry.registerVoter(person);
        assertEquals(RegisterResult.INVALID_AGE, result);
    }


    @Test
    public void testDuplicatedPersonRegistration() {
        // Persona con datos validos
        Person person1 = new Person("Carl", 24680, 40, Gender.MALE, true);
        // Registrando a la persona por primera vez
        RegisterResult result = registry.registerVoter(person1);
        assertEquals(RegisterResult.VALID, result);
        // Crear una persona con el mismo ID no debe permitir el voto
        Person person2 = new Person("Robert", 24680, 41, Gender.MALE, true); 
        result = registry.registerVoter(person2);
        assertEquals(RegisterResult.DUPLICATED, result);
        // Intentar registrar un voto de una persona que ya voto debe ser invalido.
        result = registry.registerVoter(person1);
        assertEquals(RegisterResult.DUPLICATED, result);
    }
}
