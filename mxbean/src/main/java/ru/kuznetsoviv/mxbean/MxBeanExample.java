package ru.kuznetsoviv.mxbean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * JMX - технология java, предназначенная для контроля и управления приложениями, системными объектами, устрайствами.
 */
public class MxBeanExample {

    public static void main(String[] args) {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        arguments.forEach(System.out::println);
    }

}
