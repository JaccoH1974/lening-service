package nl.bank.leningservice;

import org.junit.Assert;
import org.junit.Test;

public class LeningServiceTest {

    LeningService leningService = new LeningService();

    @Test
    public void testCalculateMaxLening() {
        Double inkomen = 20000.0d;
        Double expectedMax = 80000.0d;
        Assert.assertEquals(expectedMax, leningService.calculateMaxLening(inkomen));
    }

    @Test
    public void testCalculateAnnuiteit(){
//        hypotheek = 100.000
//        hypotheekrente = 5% per jaar
//                looptijd = 360 maanden (30 jaar)
//        maandrente = 0,05 / 12 = 0.00416666
//
//        annuïteit = (0,00416666 / (1 - ((1 + 0,00416666) ^ -360))) * 100.000 = 536.82

        Double jaarrente = 5.0d;
        Double geleendbedrag = 100000.0d;
        Integer looptijdInMaanden = 360;
        Double expectedAnnuiteit = 536.8216230121398d;
        Assert.assertEquals(expectedAnnuiteit, leningService.calculateAnnuiteit(geleendbedrag, looptijdInMaanden, jaarrente));
    }
}
