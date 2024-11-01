package runner;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRunner.class,
        Runner.class
})
public class AllTests {
}
