package com.itextpdf.samples;

import com.itextpdf.io.font.FontCache;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.licensing.base.LicenseKey;
import com.itextpdf.test.RunnerSearchConfig;
import com.itextpdf.test.WrappedSamplesRunner;
import com.itextpdf.test.annotations.type.SampleTest;

import ch.qos.logback.classic.Logger;
import java.io.FileInputStream;
import java.util.Collection;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.Parameterized;
import org.slf4j.LoggerFactory;

@Category(SampleTest.class)
public class CreateFromURLSampleTest extends WrappedSamplesRunner {
    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        RunnerSearchConfig searchConfig = new RunnerSearchConfig();
        searchConfig.addClassToRunnerSearchPath("com.itextpdf.samples.htmlsamples.chapter07.C07E04_CreateFromURL");
        searchConfig.addClassToRunnerSearchPath("com.itextpdf.samples.htmlsamples.chapter07.C07E05_CreateFromURL2");
        searchConfig.addClassToRunnerSearchPath("com.itextpdf.samples.htmlsamples.chapter07.C07E06_CreateFromURL3");

        return generateTestsList(searchConfig);
    }

    @Test(timeout = 180000)
    public void test() throws Exception {
        Logger logger = (Logger) LoggerFactory.getLogger("ROOT");
        try (FileInputStream license = new FileInputStream(System.getenv("ITEXT7_LICENSEKEY")
                + "/all-products.json")) {
            logger.info("Load all-products license.");
            LicenseKey.loadLicenseFile(license);
        }
        FontCache.clearSavedFonts();
        FontProgramFactory.clearRegisteredFonts();

        runSamples();
        LicenseKey.unloadLicenses();
    }

    @Override
    protected void comparePdf(String outPath, String dest, String cmp) throws Exception {

    }
}
