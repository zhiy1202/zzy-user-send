package com.wx;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class ServerApplicationTests {

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/wx?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true", "root", "333")
                .globalConfig(builder -> {
                    builder.author("zzy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\31328\\Desktop\\money\\wx\\server\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("wx")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\31328\\Desktop\\money\\wx\\server\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user","goods_imgs","t_bill","t_car","t_common","t_goods","user_address","user_history","user_like") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")
                            .enableCapitalMode()
                            .entityBuilder()
                            .enableLombok()
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
