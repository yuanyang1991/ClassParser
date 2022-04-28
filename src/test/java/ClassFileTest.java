import com.yuanyang.ClassFile;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ClassFileTest {

    @Test
    public void test() throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\wps\\Desktop\\test\\TestClass.class");
        ClassFile classFile = ClassFile.from(inputStream);

    }
}
