package ru.kuznetsoviv.javawork.objectsizeagent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassTransformer implements ClassFileTransformer {

    private static int count = 0;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("load class: " + className.replace("/", "."));
        System.out.println(String.format("loaded %d classes", ++count));
        return classfileBuffer;
    }
}
