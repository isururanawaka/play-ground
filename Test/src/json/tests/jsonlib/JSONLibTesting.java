package json.tests.jsonlib;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class JSONLibTesting {

    private static String json;
    private static int n = 40;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder().include(JSONLibTesting.class.getSimpleName()).warmupIterations(100)
                .measurementIterations(1000).threads(2).forks(1).build();

        new Runner(opt).run();
    }

    @Benchmark
    public void serialise() throws IOException {
        String settings = "{\"hello\": \"world\"}";

        JSONObject obj = (JSONObject) JSONSerializer.toJSON(settings);
        // System.out.println(obj.toString());
    }

}
