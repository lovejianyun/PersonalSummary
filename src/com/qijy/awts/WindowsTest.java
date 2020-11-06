package com.qijy.awts;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * @ Description   :  窗口按钮测试
 * @ Author        :  qijy
 * @ CreateDate    :  2020/6/28 10:15
 */
public class WindowsTest {
        public static void main(String args[]) {
            JFrame f = new JFrame("Flow Layout");
            f.setLocation(500, 500);
            JButton button1 = new JButton("确定");
            JButton button2 = new JButton("打开");
            JButton button3 = new JButton("关闭");
            JButton button4 = new JButton("取消");
            f.setLayout(new FlowLayout());
            f.add(button1);
            f.add(button2);
            f.add(button3);
            f.add(button4);
            f.setSize(500, 500);
            f.setVisible(true);
            f.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Window window = (Window) e.getComponent();
                    window.dispose();
                }
            });

        }
}
