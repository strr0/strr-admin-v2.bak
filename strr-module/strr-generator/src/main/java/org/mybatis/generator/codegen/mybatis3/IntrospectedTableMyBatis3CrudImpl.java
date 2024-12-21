/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3;

import java.util.ArrayList;
import java.util.List;

import com.strr.generator.codegen.controller.JavaBaseControllerGenerator;
import com.strr.generator.codegen.mapper.JavaBaseMapperGenerator;
import com.strr.generator.codegen.mapper.XmlBaseMapperGenerator;
import com.strr.generator.codegen.model.JavaBaseRecordGenerator;
import com.strr.generator.codegen.service.JavaBaseServiceGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedKotlinFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.AbstractKotlinGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.AnnotatedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.MixedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.rules.ConditionalModelRules;
import org.mybatis.generator.internal.rules.FlatModelRules;
import org.mybatis.generator.internal.rules.HierarchicalModelRules;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * Introspected table implementation for generating MyBatis3 artifacts.
 *
 * @author Jeff Butler
 */
public class IntrospectedTableMyBatis3CrudImpl extends IntrospectedTable {

    protected List<AbstractJavaGenerator> javaGenerators = new ArrayList<>();

    protected List<AbstractKotlinGenerator> kotlinGenerators = new ArrayList<>();

    protected AbstractXmlGenerator xmlMapperGenerator;

    public IntrospectedTableMyBatis3CrudImpl() {
        super(TargetRuntime.MYBATIS3);
    }

    @Override
    public void initialize() {
        calculateModelAttributes();
        calculateXmlAttributes();
        calculateJavaMapperAttributes();
        calculateServiceAttributes();
        calculateControllerAttributes();

        if (tableConfiguration.getModelType() == ModelType.HIERARCHICAL) {
            rules = new HierarchicalModelRules(this);
        } else if (tableConfiguration.getModelType() == ModelType.FLAT) {
            rules = new FlatModelRules(this);
        } else {
            rules = new ConditionalModelRules(this);
        }

        context.getPlugins().initialized(this);
    }

    @Override
    protected void calculateModelAttributes() {
        String domainObjectName = this.fullyQualifiedTable.getDomainObjectName();
        this.setBasicName(domainObjectName.substring(0, 1).toLowerCase() + domainObjectName.substring(1));
        String pakkage = getCrudPackage();
        this.setBaseRecordType(String.format("%s.model.%s", pakkage, this.fullyQualifiedTable.getDomainObjectName()));
        int idx = pakkage.lastIndexOf(".");
        if (idx > 0) {
            this.setModuleName(pakkage.substring(idx));
        } else {
            this.setModuleName(pakkage);
        }
    }

    @Override
    protected void calculateXmlAttributes() {
        this.setMyBatis3XmlMapperFileName(this.calculateMyBatis3XmlMapperFileName());
        this.setMyBatis3XmlMapperPackage("mapper");
        this.setMyBatis3FallbackSqlMapNamespace(this.calculateMyBatis3FallbackSqlMapNamespace());
        this.setSqlMapFullyQualifiedRuntimeTableName(this.calculateSqlMapFullyQualifiedRuntimeTableName());
        this.setSqlMapAliasedFullyQualifiedRuntimeTableName(this.calculateSqlMapAliasedFullyQualifiedRuntimeTableName());
    }

    // 设置mapper属性
    protected void calculateJavaMapperAttributes() {
        // mapper
        StringBuilder sb = new StringBuilder();
        sb.append(getCrudPackage());
        sb.append(".mapper");
        sb.append(this.fullyQualifiedTable.getSubPackageForClientOrSqlMap(true));
        sb.append('.');
        if (StringUtility.stringHasValue(this.fullyQualifiedTable.getDomainObjectSubPackage())) {
            sb.append(this.fullyQualifiedTable.getDomainObjectSubPackage());
            sb.append('.');
        }
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("Mapper");
        this.setMyBatis3JavaMapperType(sb.toString());
    }

    // 设置service属性
    protected void calculateServiceAttributes() {
        // service
        StringBuilder sb = new StringBuilder();
        sb.append(getCrudPackage());
        sb.append(".service");
        sb.append(this.fullyQualifiedTable.getSubPackageForClientOrSqlMap(true));
        sb.append('.');
        if (StringUtility.stringHasValue(this.fullyQualifiedTable.getDomainObjectSubPackage())) {
            sb.append(this.fullyQualifiedTable.getDomainObjectSubPackage());
            sb.append('.');
        }
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("Service");
        this.setMyBatis3JavaServiceType(sb.toString());
        // serviceImpl
        sb.setLength(0);
        sb.append(getCrudPackage());
        sb.append(".service");
        sb.append(this.fullyQualifiedTable.getSubPackageForClientOrSqlMap(true));
        sb.append('.');
        if (StringUtility.stringHasValue(this.fullyQualifiedTable.getDomainObjectSubPackage())) {
            sb.append(this.fullyQualifiedTable.getDomainObjectSubPackage());
            sb.append('.');
        }
        sb.append("impl");
        sb.append('.');
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("ServiceImpl");
        this.setMyBatis3JavaServiceImplType(sb.toString());
    }

    // 设置controller
    protected void calculateControllerAttributes() {
        // controller
        StringBuilder sb = new StringBuilder();
        sb.append(getCrudPackage());
        sb.append(".controller");
        sb.append(this.fullyQualifiedTable.getSubPackageForClientOrSqlMap(true));
        sb.append('.');
        if (StringUtility.stringHasValue(this.fullyQualifiedTable.getDomainObjectSubPackage())) {
            sb.append(this.fullyQualifiedTable.getDomainObjectSubPackage());
            sb.append('.');
        }
        sb.append(this.fullyQualifiedTable.getDomainObjectName());
        sb.append("Controller");
        this.setMyBatis3JavaControllerType(sb.toString());
    }

    @Override
    public void calculateGenerators(List<String> warnings,
                                    ProgressCallback progressCallback) {
        // java
        calculateJavaModelGenerators(warnings, progressCallback);
        calculateJavaMapperGenerators(warnings, progressCallback);
        calculateJavaServiceGenerators(warnings, progressCallback);
        calculateJavaControllerGenerators(warnings, progressCallback);
        // xml
        calculateXmlMapperGenerator(warnings, progressCallback);
    }

    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator,
                                               List<String> warnings,
                                               ProgressCallback progressCallback) {
        if (javaClientGenerator == null) {
            if (context.getSqlMapGeneratorConfiguration() != null) {
                xmlMapperGenerator = new XMLMapperGenerator();
            }
        } else {
            xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
        }

        initializeAbstractGenerator(xmlMapperGenerator, warnings,
                progressCallback);
    }

    protected AbstractJavaClientGenerator calculateClientGenerators(List<String> warnings,
                                                                    ProgressCallback progressCallback) {
        if (!rules.generateJavaClient()) {
            return null;
        }

        AbstractJavaClientGenerator javaGenerator = createJavaClientGenerator();
        if (javaGenerator == null) {
            return null;
        }

        initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        javaGenerators.add(javaGenerator);

        return javaGenerator;
    }

    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }

        String type = context.getJavaClientGeneratorConfiguration()
                .getConfigurationType();

        AbstractJavaClientGenerator javaGenerator;
        if ("XMLMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new JavaMapperGenerator(getClientProject());
        } else if ("MIXEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new MixedClientGenerator(getClientProject());
        } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new AnnotatedClientGenerator(getClientProject());
        } else if ("MAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new JavaMapperGenerator(getClientProject());
        } else {
            javaGenerator = (AbstractJavaClientGenerator) ObjectFactory
                    .createInternalObject(type);
        }

        return javaGenerator;
    }

    // model
    protected void calculateJavaModelGenerators(List<String> warnings,
                                                ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new JavaBaseRecordGenerator(getCrudProject());
        this.initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        this.javaGenerators.add(javaGenerator);
    }

    // mapper
    protected void calculateJavaMapperGenerators(List<String> warnings, ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new JavaBaseMapperGenerator(getCrudProject());
        this.initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        this.javaGenerators.add(javaGenerator);
    }

    // service
    protected void calculateJavaServiceGenerators(List<String> warnings, ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new JavaBaseServiceGenerator(getCrudProject());
        this.initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        this.javaGenerators.add(javaGenerator);
    }

    // controller
    protected void calculateJavaControllerGenerators(List<String> warnings, ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new JavaBaseControllerGenerator(getCrudProject());
        this.initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        this.javaGenerators.add(javaGenerator);
    }

    // xmlmapper
    protected void calculateXmlMapperGenerator(List<String> warnings, ProgressCallback progressCallback) {
        this.xmlMapperGenerator = new XmlBaseMapperGenerator();
        this.initializeAbstractGenerator(this.xmlMapperGenerator, warnings, progressCallback);
    }

    protected void initializeAbstractGenerator(
            AbstractGenerator abstractGenerator, List<String> warnings,
            ProgressCallback progressCallback) {
        if (abstractGenerator == null) {
            return;
        }

        abstractGenerator.setContext(context);
        abstractGenerator.setIntrospectedTable(this);
        abstractGenerator.setProgressCallback(progressCallback);
        abstractGenerator.setWarnings(warnings);
    }

    @Override
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        List<GeneratedJavaFile> answer = new ArrayList<>();

        for (AbstractJavaGenerator javaGenerator : javaGenerators) {
            List<CompilationUnit> compilationUnits = javaGenerator
                    .getCompilationUnits();
            for (CompilationUnit compilationUnit : compilationUnits) {
                GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                        javaGenerator.getProject(),
                        context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                        context.getJavaFormatter());
                answer.add(gjf);
            }
        }

        return answer;
    }

    @Override
    public List<GeneratedKotlinFile> getGeneratedKotlinFiles() {
        List<GeneratedKotlinFile> answer = new ArrayList<>();

        for (AbstractKotlinGenerator kotlinGenerator : kotlinGenerators) {
            List<KotlinFile> kotlinFiles = kotlinGenerator.getKotlinFiles();
            for (KotlinFile kotlinFile : kotlinFiles) {
                GeneratedKotlinFile gjf = new GeneratedKotlinFile(kotlinFile,
                        kotlinGenerator.getProject(),
                        context.getProperty(PropertyRegistry.CONTEXT_KOTLIN_FILE_ENCODING),
                        context.getKotlinFormatter());
                answer.add(gjf);
            }
        }

        return answer;
    }

    protected String getCrudPackage() {
        return context.getJavaCrudGeneratorConfiguration().getTargetPackage();
    }

    protected String getClientProject() {
        return context.getJavaClientGeneratorConfiguration().getTargetProject();
    }

    protected String getModelProject() {
        return context.getJavaModelGeneratorConfiguration().getTargetProject();
    }

    protected String getCrudProject() {
        return context.getJavaCrudGeneratorConfiguration().getTargetProject();
    }

    protected String getExampleProject() {
        String project = context.getJavaModelGeneratorConfiguration().getProperty(
                PropertyRegistry.MODEL_GENERATOR_EXAMPLE_PROJECT);

        if (StringUtility.stringHasValue(project)) {
            return project;
        } else {
            return getModelProject();
        }
    }

    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<>();

        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf = new GeneratedXmlFile(document,
                    getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(),
                    context.getSqlMapGeneratorConfiguration().getTargetProject(),
                    true, context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answer.add(gxf);
            }
        }

        return answer;
    }

    @Override
    public int getGenerationSteps() {
        return javaGenerators.size()
                + (xmlMapperGenerator == null ? 0 : 1);
    }

    @Override
    public boolean requiresXMLGenerator() {
        AbstractJavaClientGenerator javaClientGenerator =
                createJavaClientGenerator();

        if (javaClientGenerator == null) {
            return false;
        } else {
            return javaClientGenerator.requiresXMLGenerator();
        }
    }
}
