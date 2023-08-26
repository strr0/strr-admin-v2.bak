package com.strr.generator.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomCodeGenerator {
    public static void run(String filename) {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        CustomConfigurationParser cp = new CustomConfigurationParser();
        Configuration config = cp.parseConfiguration(filename);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (SQLException | InvalidConfigurationException | InterruptedException | IOException throwables) {
            throwables.printStackTrace();
        }
        warnings.forEach(System.err::println);
    }
}
