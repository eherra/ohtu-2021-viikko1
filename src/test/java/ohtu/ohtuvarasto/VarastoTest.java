package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    @Test
    public void konstruktoriKahdellaParametrillaToimiiOikein() {
        Varasto toinenVarasto = new Varasto(10, 2);
        assertEquals(2.0, toinenVarasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10.0, toinenVarasto.getTilavuus(), vertailuTarkkuus);
                
        Varasto varastoMiinusTilavuudella = new Varasto(-2, 2);
        assertEquals(0.0, varastoMiinusTilavuudella.getSaldo(), vertailuTarkkuus);
        assertEquals(0.0, varastoMiinusTilavuudella.getTilavuus(), vertailuTarkkuus);
        
        Varasto varastoMiinusSaldolla = new Varasto(10, -2);
        assertEquals(0.0, varastoMiinusSaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varastoMiinusSaldolla.getTilavuus(), vertailuTarkkuus);
        
        Varasto varastoMaxTilavuudella = new Varasto(5, 7);
        assertEquals(5.0, varastoMaxTilavuudella.getSaldo(), vertailuTarkkuus);
        assertEquals(5.0, varastoMaxTilavuudella.getTilavuus(), vertailuTarkkuus);
        
        Varasto varastoNormiSaldolla = new Varasto(10, 7);
        assertEquals(7.0, varastoNormiSaldolla.getSaldo(), vertailuTarkkuus);
        assertEquals(10.0, varastoNormiSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void yhdenParametrinKonstruktoriMiinusArvolla() {
        Varasto miinusVarasto = new Varasto(-2);
        assertEquals(0.0, miinusVarasto.getTilavuus(), vertailuTarkkuus);        
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void testGetSaldo() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testGetTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void testPaljonkoMahtuu() {
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void testLisaaVarastoon() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
        
        varasto.lisaaVarastoon(6);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        
        varasto.lisaaVarastoon(-6);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void testOtaVarastosta() {
        varasto.lisaaVarastoon(5);
        double otettu = varasto.otaVarastosta(3);
        assertEquals(2.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(3.0, otettu, vertailuTarkkuus);

        otettu = varasto.otaVarastosta(100);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(2.0, otettu, vertailuTarkkuus);

        otettu = varasto.otaVarastosta(-6);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0.0, otettu, vertailuTarkkuus);
    }

    @Test
    public void testToString() {
        String tulostus = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        assertEquals(tulostus, varasto.toString());
    }

}