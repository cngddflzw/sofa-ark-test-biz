package com.test.lzw.ark.biz.demo;

import com.test.lzw.ark.biz.facade.SampleService;
import com.test.lzw.ark.plugin.UnexportedSample;
import com.test.lzw.ark.plugin.exported.ExportedSample;
import com.zim.test.thirdparty.TPService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@ImportResource({"classpath*:META-INF/spring/service.xml"})
@SpringBootApplication
public class SpringBootDemo {

    @Resource
    private SampleService sampleService;

    public static void main(String[] args) {
//        SofaArkBootstrap.launch(args);
        SpringApplication.run(SpringBootDemo.class, args);

        TPService tps = new TPService();
        System.out.println("tps load by " + tps.getClass().getClassLoader());
        System.out.println("ExportedSample " + ExportedSample.class.getClassLoader());
        System.out.println("UnExportedSample " + UnexportedSample.class.getClassLoader());

        // thirdparty 1.1-SNAPSHOT 版本接口
        System.out.println(tps.echo2());

        // thirdparty 1.0-SNAPSHOT 版本接口
        ExportedSample.hello();
    }

    @RequestMapping("/sampleService")
    public String sampleService() {
        return "load by " + sampleService.getClass().getClassLoader() + "\n" + sampleService.service();
    }
}
