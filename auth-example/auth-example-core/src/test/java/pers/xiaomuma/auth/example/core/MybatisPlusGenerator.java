package pers.xiaomuma.auth.example.core;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class MybatisPlusGenerator {

    private static final String OUTPUT_DIR = "C:\\auth-example"; //项目位置
    private static final String PACKAGE_PARENT = "pers.xiaomuma.auth.example.core";
    private static final String PACKAGE_MODEL = "crud.modules.xxx";

    private static String url = "jdbc:mysql://127.0.0.1:3306/auth-example?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useUnicode=true&useSSL=false";
    private static String username = "root";
    private static String password = "123456";
    private static String driveClassName = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUTPUT_DIR);
        gc.setOpen(true);
        gc.setBaseResultMap(true);
        gc.setFileOverride(true);
        gc.setMapperName("%sDAO");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driveClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(PACKAGE_MODEL);
        pc.setParent(PACKAGE_PARENT);
        pc.setMapper("dao");
        pc.setEntity("domain");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(true);
        // 写于父类中的公共字段
        strategy.setControllerMappingHyphenStyle(true);
        // 需要生成的表
        strategy.setInclude(
                "oauth_client_details"
        );
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
