package reflections;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.scanners.TypeElementsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ua.axiom.core.annotations.core.AnnotationProcessor;

import java.util.Set;


public class AnnotatedPackagesLookUpTest {
    private static Reflections reflections;
    private final static String PACKAGE_TO_SCAN = "ua.axiom";

    @BeforeClass
    public static void before() {
        reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(PACKAGE_TO_SCAN))
                .setScanners(new SubTypesScanner(),
                        new TypeAnnotationsScanner(),
                        new ResourcesScanner(),
                        new TypeElementsScanner())
                .filterInputsBy(new FilterBuilder().includePackage("ua.axiom")));

    }

    @Test
    public void AnnotationProcessorFindTest() {
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(AnnotationProcessor.class);

        Assert.assertTrue(annotatedClasses.size() != 0);
    }

}
