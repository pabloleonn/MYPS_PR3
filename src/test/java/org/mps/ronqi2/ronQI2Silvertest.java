package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mps.ronqi2.RonQI2Silver;
import org.mps.dispositivo.*;


public class ronQI2SilverTest {

  
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

    @Test
    @DisplayName("Al inicializar el sensor de presion retorna false")
    public void RonQI2Silver_inicializarSensorPresion_returnFalse() {
        // 1- Creamos el objeto Mock de la clase RonQI2Silver
        DispositivoSilver disp = Mockito.mock(DispositivoSilver.class);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(false);
        // 3- Ejecutamos
        assertFalse(ronq.inicializar());
    }

    @Test
    @DisplayName("Al inicializar el sensor de presion retorna true y sensor de sonido retorna false")
    public void RonQI2Silver_inicializarSensorSonido_returnFalse() {
        // 1- Creamos el objeto Mock de la clase DispositivoSilver
        DispositivoSilver disp = Mockito.mock(DispositivoSilver.class);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(false);
        // 3- Ejecutamos
        assertFalse(ronq.inicializar());
    }

    @Test
    @DisplayName("Al inicializar ambos sensores se conectan pero no se configura el de presion")
    public void RonQI2Silver_inicializarAmbosSensoresPeroNoConfigura_returnFalse() {
        // 1- Creamos el objeto Mock de la clase DispositivoSilver
        DispositivoSilver disp = Mockito.mock(DispositivoSilver.class);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        // 3- Ejecutamos
        assertFalse(ronq.inicializar());
    }

    @Test
    @DisplayName("Al inicializar ambos sensores se conectan y si se configuran ambos")
    public void RonQI2Silver_inicializarAmbosSensoresYConfigura_returnTrue() {
        // 1- Creamos el objeto Mock de la clase DispositivoSilver
        DispositivoSilver disp = Mockito.mock(DispositivoSilver.class);
        RonQI2Silver ronq = new RonQI2Silver();
        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        when(ronq.disp.configurarSensorPresion()).thenReturn(true);
        when(ronq.disp.configurarSensorSonido()).thenReturn(true);
        // 3- Ejecutamos
        assertTrue(ronq.inicializar());
    }

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */

     @Test
     @DisplayName("Si se desconecta un sensor, se reconecta de manera exitosa")
     public void RonQI2Silver_reconectar_returnTrue() {
        //1- Creamos el objeto Mok de la clase RonQI2Silver
        RonQI2Silver ronq = Mockito.mock(RonQI2Silver.class);
        DispositivoSilver disp = Mockito.mock(DispositivoSilver.class);
        ronq.anyadirDispositivo(disp);
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        when(ronq.disp.conectarSensorPresion()).thenReturn(false);
        //4- Verificamos que se ha reconectado una vez
       assertFalse(ronq.reconectar());
     }

    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}