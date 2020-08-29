package persistance.generalpersisting;

import org.junit.Assert;
import org.junit.Test;
import ua.axiom.persistance.misc.representation.persision.GeneralPersisting;

import java.lang.reflect.Field;

public class GeneralPersistingNumberTest {
    public Integer integer = 11;
    public Long aLong = 22L;
    public Double aDouble = 33.;

    @Test
    public void testIntegerPersistance() throws NoSuchFieldException {
        Field field = this.getClass().getField("integer");

        Object representation = GeneralPersisting.getRepresentation(field, integer);

        Assert.assertEquals("11", representation);
    }

    @Test
    public void testLongPersistance() throws NoSuchFieldException {
        Field field = this.getClass().getField("aLong");

        Object representation = GeneralPersisting.getRepresentation(field, aLong);

        Assert.assertEquals("22", representation);
    }

    @Test
    public void testDoublePersistance() throws NoSuchFieldException {
        Field field = this.getClass().getField("aDouble");

        Object representation = GeneralPersisting.getRepresentation(field, aDouble);

        Assert.assertEquals("33.0", representation);
    }
}
