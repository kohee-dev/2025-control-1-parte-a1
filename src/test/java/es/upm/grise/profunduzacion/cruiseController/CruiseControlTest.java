package es.upm.grise.profunduzacion.cruiseController;

import es.upm.grise.profundizacion.cruiseControl.CruiseControl;
import es.upm.grise.profundizacion.cruiseControl.IncorrectSpeedSetException;
import es.upm.grise.profundizacion.cruiseControl.SpeedSetAboveSpeedLimitException;
import es.upm.grise.profundizacion.cruiseControl.Speedometer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CruiseControlTest {

    private Speedometer speedometer = new Speedometer() {
        @Override
        public int getCurrentSpeed() {return 0;}
    };

    @Test
    void setSpeedIncorrectValueTest() throws IncorrectSpeedSetException {
        CruiseControl cruiseControl = new CruiseControl(speedometer);
        assertThrows(IncorrectSpeedSetException.class, () -> {cruiseControl.setSpeedSet(-1);});
    }

    @Test
    void setSpeedAboveSpeedLimitTest() throws SpeedSetAboveSpeedLimitException {
        CruiseControl cruiseControl = new CruiseControl(speedometer);
        cruiseControl.setSpeedLimit(5); //Set speed limit
        assertThrows(SpeedSetAboveSpeedLimitException.class, () -> {cruiseControl.setSpeedSet(6);}); //one above limit
    }

    @Test
    void correctSpeedSetTest() throws SpeedSetAboveSpeedLimitException, IncorrectSpeedSetException {
        CruiseControl cruiseControl = new CruiseControl(speedometer);
        cruiseControl.setSpeedSet(0);
        assertEquals(0, cruiseControl.getSpeedSet());
    }
}
