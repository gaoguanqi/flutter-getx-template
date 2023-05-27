package com.maple.plugin.ui;

import cn.hutool.core.io.FileUtil;
import com.google.common.base.CaseFormat;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.ide.fileTemplates.impl.UrlUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.graph.layout.planar.FrameMaker;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtilKt;
import com.intellij.ui.EditorTextField;
import com.maple.plugin.data.InputData;
import com.maple.plugin.utils.CamelCaseUtils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PluginMainDialog extends DialogWrapper {

    private AnActionEvent anActionEvent;

    public PluginMainDialog(AnActionEvent e) {
        super(true);
        this.anActionEvent = e;
        setTitle("创建GetX文件");
        init();
    }

    private EditorTextField tfInput;

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel jPane = new JPanel(new BorderLayout());
        jPane.setPreferredSize(new Dimension(200,100));
        tfInput = new EditorTextField();
        jPane.add(tfInput,BorderLayout.CENTER);
        return jPane;
    }


    @Override
    protected JComponent createSouthPanel() {
//        return super.createSouthPanel();
        JPanel jPane = new JPanel();
        JButton btnConfirm = new JButton("确定");
        btnConfirm.addActionListener(e ->{
            String input = tfInput.getText();
            if(StringUtils.isEmpty(input)) {
                Messages.showWarningDialog("请输入文件名","提示");
                return;
            }
            InputData inputData = new InputData();
            inputData.fileName = input;
            inputData.upName = CamelCaseUtils.convert(input,CamelCaseUtils.CONVERSION_PASCAL_CASE);
            inputData.lowName = CamelCaseUtils.convert(input,CamelCaseUtils.CONVERSION_LOWER_SNAKE_CASE);
//            generatorCode(inputData);
            System.out.println("文件：" + inputData.toString());
            code(inputData);
        });
        jPane.add(btnConfirm);
        return jPane;
    }

    private static final String TEMPLATE_PATH = "/template";



    private void createView(Configuration configuration, String fullPath, String name,String upName) {
        Writer out = null;
        try {
            String templatePath = UrlUtil.loadText(this.getClass().getResource(TEMPLATE_PATH + "/" + "view.ftl"));
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("view",templatePath);
            configuration.setTemplateLoader(stringTemplateLoader);
            Template template = configuration.getTemplate("view");

            File file = FileUtil.touch(fullPath + "view.dart");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("name", name);
            dataMap.put("upName", upName);
            template.process(dataMap,out);
            System.out.println("--- view建立成功 !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- view建立异常 !" + e.getMessage());
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("--- view异常 !" + e2.getMessage());
            }
        }
    }

    private void createBinding(Configuration configuration, String fullPath, String name,String upName) {
        Writer out = null;
        try {
            String templatePath = UrlUtil.loadText(this.getClass().getResource(TEMPLATE_PATH + "/" + "binding.ftl"));
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("binding",templatePath);
            configuration.setTemplateLoader(stringTemplateLoader);
            Template template = configuration.getTemplate("binding");

            File file = FileUtil.touch(fullPath + "binding.dart");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("name", name);
            dataMap.put("upName", upName);
            template.process(dataMap,out);
            System.out.println("--- binding建立成功 !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- binding建立异常 !" + e.getMessage());
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("--- binding异常 !" + e2.getMessage());
            }
        }
    }
    private void createController(Configuration configuration, String fullPath,String upName) {
        Writer out = null;
        try {
            String templatePath = UrlUtil.loadText(this.getClass().getResource(TEMPLATE_PATH + "/" + "controller.ftl"));
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("controller",templatePath);
            configuration.setTemplateLoader(stringTemplateLoader);
            Template template = configuration.getTemplate("controller");

            File file = FileUtil.touch(fullPath + "controller.dart");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("upName", upName);
            template.process(dataMap,out);
            System.out.println("--- controller建立成功 !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--- controller建立异常 !" + e.getMessage());
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println("--- controller异常 !" + e2.getMessage());
            }
        }
    }

    private void createLibrary(Configuration configuration, String fullPath,String name, String lowName) {
        Writer out = null;
        try {
            String templatePath = UrlUtil.loadText(this.getClass().getResource(TEMPLATE_PATH + "/" + "name.ftl"));
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("name",templatePath);
            configuration.setTemplateLoader(stringTemplateLoader);
            Template template = configuration.getTemplate("name");

            File file = FileUtil.touch(fullPath + name +".dart");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("lowName", lowName);
            template.process(dataMap,out);
            System.out.println( name + "建立成功 !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( name + "建立异常 !" + e.getMessage());
        } finally {
            try {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                System.out.println(name + "异常 !" + e2.getMessage());
            }
        }
    }

    private void code(InputData inputData) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDefaultEncoding("utf-8");
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        String filePath = project.getBasePath();
        String fullPath = filePath + "/lib/pages/" + inputData.fileName + "/" ;
        createView(configuration,fullPath,inputData.fileName,inputData.upName);
        createBinding(configuration,fullPath,inputData.fileName,inputData.upName);
        createController(configuration,fullPath,inputData.upName);
        createController(configuration,fullPath,inputData.upName);
        createLibrary(configuration,fullPath,inputData.fileName,inputData.lowName);

        this.close(DialogWrapper.CANCEL_EXIT_CODE);

        Messages.showWarningDialog("正在生成文件...","提示");
    }

}
