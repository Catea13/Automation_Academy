package Hook;


import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHook {
    private Hooks.TestContext testContext;

    public CucumberHook(Hooks.TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        testContext.getHook().setup();
    }

    @After
    public void tearDown() {
        testContext.getHook().close();
    }
}