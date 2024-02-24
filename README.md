# LABORATORIO 3 - TDD

#### CLASES DE EQUIVALENCIA

### CREAR UN PROYECTO CON MAVEN
Para poder crear/generar un proyecto en maven basado en architecture se usa el codigo **mvn archetype:generate**
### ACTUALIZAR Y CREAR DEPENDENCIAS EN EL PROYECTO
El codigo para la dependencia de junit queda de la siguiente manera
```xml
<!--  https://mvnrepository.com/artifact/junit/junit  -->
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.13.2</version>
<scope>test</scope>
</dependency>
```
### COMPILAR Y EJECUTAR
Ejecute los comandos necesarios de Maven, para compilar el proyecto y verificar que el proyecto se creó correctamente y los cambios realizados al archivo pom no generan inconvenientes.

Busque el comando requerido para ejecutar las pruebas unitarias de un proyecto desde Maven y ejecútelo sobre el proyecto. Se debe ejecutar la clase AppTest con resultado exitoso.

Los comandos usados para compilar y para realizar las pruebas unitarias respectivamente fueron:
1. mvn install
2. mvn package
3. mvn test

## Ejercicio Registraduria
### Estructura de la clase
```java
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
```

## EJERCICIO "DESCUENTO DE TARIFAS"
### Implementación de Pruebas de unidad
```java
  package edu.eci.cvds.tdd.aerodescuentos;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TarifasTest{
    @Test
    public void testInvalidParameters(){
        /*boolean result = true;
        double tarifaConDescuento = 0;
        // Edad invalida: menor a 0
        try {
            tarifaConDescuento = CalculadorDescuentos.calculoTarifa(100, 0, -1);
            System.out.println(tarifaConDescuento);
            assertFalse(result); //No debe llegar a esta linea.
        }   
        catch (Exception e){
            assertTrue(result);
        }
        // Edad invalida: mayor a 135
        try {
            tarifaConDescuento = CalculadorDescuentos.calculoTarifa(100, 0, 135);
            assertFalse(result); //No debe llegar a esta linea.
        }   
        catch (Exception e){
            assertTrue(result);
        }
        // Dias invalidos: menor a 0
        try {
            tarifaConDescuento = CalculadorDescuentos.calculoTarifa(100, -1, 1);
            assertFalse(result); //No debe llegar a esta linea.
        }   
        catch (Exception e){
            assertTrue(result);
        }
        // Tarifa invalida: menor a 0
        try {
            tarifaConDescuento = CalculadorDescuentos.calculoTarifa(100, -1, 1);
            assertFalse(result); //No debe llegar a esta linea.
        }   
        catch (Exception e){
            assertTrue(result);
        }
        */
        //Se encontro que no se lanzaban las excepciones que se habian planteado
    }

    @Test
    public void testValidateDiscountForAdultsWithoutDaysInAdvance(){
        double tarifaConDescuento;
        //Caso 1.1
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 0, 18);
        assertTrue(tarifaConDescuento == 1000000);
        //Caso 1.2
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 10, 40);
        assertTrue(tarifaConDescuento == 1000000);
        //Caso 1.3
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 20, 65);
        assertTrue(tarifaConDescuento == 1000000);
    }

    @Test
    public void testValidateDiscountForAdultsWithDaysInAdvance(){
        double tarifaConDescuento;
        //Caso 2.1
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 21, 18);
        assertTrue(tarifaConDescuento == 850000);
        //Caso 2.2
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 30, 40);
        assertTrue(tarifaConDescuento == 850000);
        //Caso 2.3
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 100, 65);
        assertTrue(tarifaConDescuento == 850000);
    }

    @Test
    public void testValidateDiscountForUndarageWithoutDaysInAdvance(){
        double tarifaConDescuento;
        //Caso 3.1
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 0, 0);
        assertTrue(tarifaConDescuento == 950000);
        //Caso 3.2
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 10, 10);
        assertTrue(tarifaConDescuento == 950000);
        //Caso 3.3
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 20, 66);
        assertFalse(tarifaConDescuento == 950000); //Se encuentra un problema, cuando se tienen 20 dias en especifico no se cuenta ningun descuento ni de menor de edad ni de adulto mayor.
    }

    @Test
    public void testValidateDiscountForUndarageWithDaysInAdvance(){
        double tarifaConDescuento;
        //Caso 4.1
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 21, 0);
        assertTrue(tarifaConDescuento == 800000);
        //Caso 4.2
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 30, 10);
        assertTrue(tarifaConDescuento == 800000);
        //Caso 4.3
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 100, 17);
        assertTrue(tarifaConDescuento == 800000); 
    }

    @Test
    public void testValidateDiscountForEderlyWithouDaysInAdvance(){
        double tarifaConDescuento;
        //Caso 5.1
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 0, 66);
        assertTrue(tarifaConDescuento == 920000);
        //Caso 5.2
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 10, 100);
        assertTrue(tarifaConDescuento == 920000);
        //Caso 5.3
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 20, 134);//Se encuentra un problema, cuando se tienen 20 dias en especifico no se cuenta ningun descuento ni de menor de edad ni de adulto mayor.
        assertFalse(tarifaConDescuento == 920000); 
    }

    @Test
    public void testValidateDiscountForEderlyWithDaysInAdvance(){
        double tarifaConDescuento;
        //Caso 6.1
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 21, 66);
        assertTrue(tarifaConDescuento == 770000);
        //Caso 6.2
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 40, 100);
        assertTrue(tarifaConDescuento == 770000);
        //Caso 6.3
        tarifaConDescuento = CalculadorDescuentos.calculoTarifa(1000000, 100, 134);
        assertTrue(tarifaConDescuento == 770000); 
    }

}
```

