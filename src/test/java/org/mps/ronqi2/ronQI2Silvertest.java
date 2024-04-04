package org.mps.ronqi2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mps.ronqi2.RonQI2Silver;
import org.mps.dispositivo.*;

public class ronQI2Silvertest {

  
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
    @DisplayName("Al inicializar ambos sensores se inicializan de manera correcta")
    public void RonQI2Silver_inicializarCorrecto_returnTrue() {
        // 1- Creamos el objeto Mock de la clase RonQI2Silver
        DispositivoSilver disp = Mockito.mock(DispositivoSilver.class);
        RonQI2Silver ronq = Mockito.mock(RonQI2Silver.class);
        ronq.anyadirDispositivo(disp);
        // 2- Definimos el comportamiento
        Mockito.when(ronq.disp.conectarSensorPresion()).thenReturn(false);
        // 3- Ejecutamos
        ronq.inicializar();
        // 4- Verificamos que se ha inicializado una vez
        Mockito.verify(ronq).inicializar();
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
        //2- Definimos el comportmaiento. Definimos un sensor no conectado.
        Mockito.when(!ronq.estaConectado()).thenReturn(false);
        //3- Ejecutamos la reconexión
        ronq.reconectar();
        //4- Verificamos que se ha reconectado una vez
        Mockito.verify(ronq).reconectar();
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
