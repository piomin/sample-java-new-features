package pl.piomin.java.samples.classfile;

import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.invoke.MethodHandles;
import java.util.List;

import static java.lang.constant.ConstantDescs.CD_int;

/**
 * Class-File API (JEP 484, Java 24): a standard way to parse and generate class files.
 * Before it you needed a library like ASM or the internal {@code jdk.internal.org.objectweb.asm}.
 */
public class ClassFileExample {

    public List<String> listMethods(Class<?> clazz) throws IOException {
        byte[] bytes;
        try (var in = clazz.getResourceAsStream(clazz.getSimpleName() + ".class")) {
            bytes = in.readAllBytes();
        }
        return ClassFile.of().parse(bytes).methods().stream()
                .map(m -> m.methodName().stringValue())
                .toList();
    }

    public int generateAndCall() throws ReflectiveOperationException {
        ClassDesc desc = ClassDesc.of(getClass().getPackageName(), "Generated");
        byte[] bytes = ClassFile.of().build(desc, cb -> cb
                .withMethodBody("answer", MethodTypeDesc.of(CD_int), ClassFile.ACC_PUBLIC | ClassFile.ACC_STATIC,
                        code -> code.bipush(42).ireturn()));
        Class<?> generated = MethodHandles.lookup().defineClass(bytes);
        return (int) generated.getMethod("answer").invoke(null);
    }
}
