package Hook;

import java.io.IOException;

public class TestContext {
    private Hook hook;
    public TestContext() throws IOException {
        hook=new Hook();
    }
    public Hook getHook() {
        return hook;
    }
}
