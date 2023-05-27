package com.maple.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.maple.plugin.ui.PluginMainDialog;

public class PluginAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("创建GetX");
        PluginMainDialog mainDialog = new PluginMainDialog(e);
        mainDialog.show();

    }
}
