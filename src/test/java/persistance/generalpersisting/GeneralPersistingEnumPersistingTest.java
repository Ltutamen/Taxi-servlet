package persistance.generalpersisting;

import org.junit.Assert;
import org.junit.Test;
import ua.axiom.persistance.misc.annotations.PersistingStrategy;
import ua.axiom.persistance.misc.representation.persision.GeneralPersisting;
import ua.axiom.persistance.misc.representation.PersistingDepersistingStrategyProvider;

import java.lang.reflect.Field;

public class GeneralPersistingEnumPersistingTest {
    private enum TestEnum {
        FieldA,
        FieldB;
    }

    @PersistingStrategy(strategy = PersistingDepersistingStrategyProvider.ORDINAL)
    public TestEnum ordinalTestEnum = TestEnum.FieldA;

    @PersistingStrategy(strategy = PersistingDepersistingStrategyProvider.STRING)
    public TestEnum stringTestEnum = TestEnum.FieldA;

    @Test
    public void testEnumOrdinalStrategy() throws NoSuchFieldException {
        Field field = GeneralPersistingEnumPersistingTest.class.getField("ordinalTestEnum");
        Enum fieldValue = ordinalTestEnum;

        Object representation = GeneralPersisting.getRepresentation(field, fieldValue);

        Assert.assertEquals("0", representation);
    }

    @Test
    public void testEnumStringStrategy() throws NoSuchFieldException {
        Field field = GeneralPersistingEnumPersistingTest.class.getField("stringTestEnum");
        Enum fieldValue = stringTestEnum;

        Object representation = GeneralPersisting.getRepresentation(field, fieldValue);

        Assert.assertEquals("FieldA", representation);
    }
}
