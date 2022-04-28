import com.yuanyang.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class StreamUtilsTest {


    @Test
    public void testRead4U() throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\wps\\Desktop\\test\\Foo.class");
        int magic = StreamUtils.readU4(inputStream);
        assert magic == 0xcafebabe;
        int minorV = StreamUtils.readU2(inputStream);
        int majorV = StreamUtils.readU2(inputStream);
        assert minorV == 0;
    }


}
