package persistance.generalpersisting;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class GeneralPersistingBigNumberPersistingTest {
    public BigDecimal number = new BigDecimal("123.01");

/*    @Test
    public void testBigDecimalPersisting() throws NoSuchFieldException {
        Field field = this.getClass().getField("number");

        Object result = GeneralPersisting.getRepresentation(field, number);

        Assert.assertEquals(result, "123.01");

    }*/
}
