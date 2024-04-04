package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mps.dispositivo.*;

@ExtendWith(MockitoExtension.class)
public class ronQI2SilverTest {

    @Mock(lenient = true)
    public DispositivoSilver disp;
    public RonQI2Silver ronq;

    @BeforeEach
    public void init(){
        disp = Mockito.mock(DispositivoSilver.class);
        ronq = new RonQI2Silver();
    }

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

        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        when(ronq.disp.configurarSensorPresion()).thenReturn(true);
        when(ronq.disp.configurarSensorSonido()).thenReturn(true);
        // 3- Ejecutamos
        assertTrue(ronq.inicializar());
    }


    @Test
    @DisplayName("Al inicializar ambos sensores se conectan y si se configuran ambos")
    public void RonQI2Silver_inicializarAmbosSensoresYConfiguraPresion_returnFalse() {
        // 1- Creamos el objeto Mock de la clase DispositivoSilver

        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        when(ronq.disp.configurarSensorPresion()).thenReturn(true);
        when(ronq.disp.configurarSensorSonido()).thenReturn(false);
        // 3- Ejecutamos
        assertFalse(ronq.inicializar());
    }


    @Test
    @DisplayName("Al inicializar ambos sensores se conectan y si se configuran ambos")
    public void RonQI2Silver_inicializarAmbosSensoresYConfiguraSonido_returnFalse() {
        // 1- Creamos el objeto Mock de la clase DispositivoSilver

        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        when(ronq.disp.configurarSensorPresion()).thenReturn(false);
        when(ronq.disp.configurarSensorSonido()).thenReturn(true);
        // 3- Ejecutamos
        assertFalse(ronq.inicializar());
    }

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */

    @Test
    @DisplayName("Si no se ha desconectado el dispositivo, no se reconecta ningun sensor y devuelve false.")
    public void RonQI2Silver_noEstaDesconectado_returnFalse() {
        //1- Creamos el objeto Mok de la clase RonQI2Silver

        ronq.anyadirDispositivo(disp);
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        when(ronq.disp.estaConectado()).thenReturn(true);
        //4- Verificamos que se ha reconectado una vez
       assertFalse(ronq.reconectar());
       verify(disp,times(0)).conectarSensorPresion();
       verify(disp,times(0)).conectarSensorSonido();
    }

     @Test
     @DisplayName("Si se ha desconectado el dispositivo y solo se reconecta el sensor de presión, devuelve false. ")
     public void RonQI2Silver_reconectarSoloPresion_returnFalse() {
        //1- Creamos el objeto Mok de la clase RonQI2Silver
        ronq.anyadirDispositivo(disp);
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        when(ronq.disp.estaConectado()).thenReturn(false);
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(false);
        //4- Verificamos que se ha reconectado una vez
       assertFalse(ronq.reconectar());
     }


     @Test
     @DisplayName("Si se ha desconectado el dispositivo y solo se reconecta el sensor de sonido, devuelve false. ")
     public void RonQI2Silver_reconectarSoloSonido_returnFalse() {
        //1- Creamos el objeto Mok de la clase RonQI2Silver

        ronq.anyadirDispositivo(disp);
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        when(ronq.disp.estaConectado()).thenReturn(false);
        when(ronq.disp.conectarSensorPresion()).thenReturn(false);
        lenient().when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        //4- Verificamos que se ha reconectado una vez
       assertFalse(ronq.reconectar());
     }


     @Test
     @DisplayName("Si se ha desconectado el dispositivo y se reconectan ambos sensores, devuelve true. ")
     public void RonQI2Silver_reconectarAmbos_returnTrue() {
        //1- Creamos el objeto Mok de la clase RonQI2Silver
        ronq.anyadirDispositivo(disp);
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        when(ronq.disp.estaConectado()).thenReturn(false);
        when(ronq.disp.conectarSensorPresion()).thenReturn(true);
        when(ronq.disp.conectarSensorSonido()).thenReturn(true);
        //4- Verificamos que se ha reconectado una vez
       assertTrue(ronq.reconectar());
     }


     @Test
     @DisplayName("Si no se conecta ningún sensor después de desconectar el dispositivo, devuelve false. ")
     public void RonQI2Silver_reconectarNinguno_returnFalse() {

        ronq.anyadirDispositivo(disp);
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        when(ronq.estaConectado()).thenReturn(false);
        when(ronq.disp.conectarSensorPresion()).thenReturn(false);
        lenient().when(ronq.disp.conectarSensorSonido()).thenReturn(false);
        //4- Verificamos que se ha reconectado una vez
       assertFalse(ronq.reconectar());
     }

    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
    */
    @Test
    @DisplayName("Si no se conecta ningún sensor después de desconectar el dispositivo, devuelve false. ")
    public void RonQI2Silver_evaluarApneaSuenyoEnRango_returnTrue() {
       ronq.anyadirDispositivo(disp);
       //2- Definimos el comportmaiento. Definimos un sensor no conectado.
       when(disp.leerSensorPresion()).thenReturn(5.0f);
       when(disp.leerSensorPresion()).thenReturn(10.0f);
       when(disp.leerSensorPresion()).thenReturn(7.0f);
       when(disp.leerSensorSonido()).thenReturn(20.0f);
       when(disp.leerSensorSonido()).thenReturn(25.0f);
       lenient().when(disp.leerSensorSonido()).thenReturn(15.0f);
        ronq.obtenerNuevaLectura();
       //4- Verificamos que se ha reconectado una vez
      assertTrue(ronq.evaluarApneaSuenyo());
    }

    @Test
    @DisplayName("Si no se conecta ningún sensor después de desconectar el dispositivo, devuelve false. ")
    public void RonQI2Silver_evaluarApneaSuenyoSonidoFueraDeRango_returnTrue() {
       ronq.anyadirDispositivo(disp);
       //2- Definimos el comportmaiento. Definimos un sensor no conectado.
       when(disp.leerSensorPresion()).thenReturn(5.0f);
       when(disp.leerSensorSonido()).thenReturn(50.0f);
       
       when(disp.leerSensorPresion()).thenReturn(10.0f);
       when(disp.leerSensorSonido()).thenReturn(70.0f);
     
       when(disp.leerSensorPresion()).thenReturn(17.0f);
       lenient().when(disp.leerSensorSonido()).thenReturn(65.0f);
       ronq.obtenerNuevaLectura();
       //4- Verificamos que se ha reconectado una vez
      assertTrue(ronq.evaluarApneaSuenyo());
    }


    @Test
    @DisplayName("Si no se conecta ningún sensor después de desconectar el dispositivo, devuelve false. ")
    public void RonQI2Silver_evaluarApneaSuenyoPresionFueraDeRango_returnTrue() {
       ronq.anyadirDispositivo(disp);
       //2- Definimos el comportmaiento. Definimos un sensor no conectado.
       when(disp.leerSensorPresion()).thenReturn(35.0f);
       when(disp.leerSensorSonido()).thenReturn(10.0f);

       when(disp.leerSensorPresion()).thenReturn(40.0f);
       when(disp.leerSensorSonido()).thenReturn(12.0f);

       lenient().when(disp.leerSensorPresion()).thenReturn(27.0f);
       lenient().when(disp.leerSensorSonido()).thenReturn(15.0f);
       ronq.obtenerNuevaLectura();
       //4- Verificamos que se ha reconectado una vez
      assertTrue(ronq.evaluarApneaSuenyo());
    }


    @Test
    @DisplayName("Si no se conecta ningún sensor después de desconectar el dispositivo, devuelve false. ")
    public void RonQI2Silver_evaluarApneaSuenyoSonidoyPresionFueraDeRango_returnFalse() {
       ronq.anyadirDispositivo(disp);
       //2- Definimos el comportmaiento. Definimos un sensor no conectado.
       when(disp.leerSensorPresion()).thenReturn(100.0f);
       when(disp.leerSensorSonido()).thenReturn(150.0f);
  
       when(disp.leerSensorPresion()).thenReturn(1000.0f);
       when(disp.leerSensorSonido()).thenReturn(170.0f);
    
       when(disp.leerSensorPresion()).thenReturn(100.0f);
       lenient().when(disp.leerSensorSonido()).thenReturn(165.0f);
       ronq.obtenerNuevaLectura();
       //4- Verificamos que se ha reconectado una vez
      assertFalse(ronq.evaluarApneaSuenyo());
    }


     
     
    /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
    */
}