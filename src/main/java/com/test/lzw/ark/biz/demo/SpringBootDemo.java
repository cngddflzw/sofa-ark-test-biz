package com.test.lzw.ark.biz.demo;

import com.test.lzw.ark.biz.facade.SampleService;
import com.test.lzw.ark.plugin.UnexportedSample;
import com.test.lzw.ark.plugin.common.ExportedSample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

@RestController
@ImportResource({"classpath*:META-INF/spring/service.xml"})
@SpringBootApplication
public class SpringBootDemo {

    @Resource
    private SampleService sampleService;

    public static void main(String[] args) {
//        SofaArkBootstrap.launch(args);
        SpringApplication.run(SpringBootDemo.class, args);
        ExportedSample.hello();
        UnexportedSample.hello();
        getResources("Sample_Resource_Exported");
        getResources("Sample_Resource_Not_Exported");
    }

    public static void getResources(String resourceName) {
        try {
            Enumeration<URL> resources = SpringBootDemo.class.getClassLoader().getResources(resourceName);

            while (resources.hasMoreElements()) {
                System.out.println(resourceName + " found: " + resources.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sampleService")
    public String sampleService() {
        return "load by " + sampleService.getClass().getClassLoader() + "\n" + sampleService.service();
    }
}
