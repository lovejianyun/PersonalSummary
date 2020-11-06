package com.qijy.awts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class LuckyNum {
    public static void main(String[] args) {
        // 定义验证码字符。去除了O和I等容易混淆的字母（也可写成）
//        String[] scode = { "A", "B", "C", "D", "E", "F", "G", "H", "G",
//                "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
//                "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
//                "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x",
//                "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9" };"宋阳君","周昌恩","张琛","黄雄","唐坤","邓高奇","黄志斌","严鑫洋","漆剑云","帅安文","欧阳俊","张三","李四","王五","麻溜","二狗","宾展","展昭","包青天","轮子妈","二哈","二逼","逗比"
        String [] scode = {"zhangsan","lisi","wangwu"};
        java.util.List<String> list1 = Arrays.asList(scode);
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.addAll(list1);
        // 创建Random对象
        final Random rand = new Random();
        // 创建窗体JFrame对象、放置验证码的JLabel对象、控制开始停止的JButton对象
        final JFrame jf = new JFrame("choujiang");
        final JLabel jl = new JLabel("start");
        final JButton jb = new JButton("start");
        // 创建可以间隔定时执行的Timer对象，间隔为200毫秒，初始ActionListener为null
        final Timer times = new Timer(500, null);
        // 获取JFrame内容面板
        Container con = jf.getContentPane();
        // 设置内容面板布局为null
        con.setLayout(null);

        // 把标签对象添加进内容面板
        con.add(jl);
        // 设置标签文字格式
        jl.setFont(new Font("幼圆", Font.PLAIN, 30));
        // 设置标签的摆放位置和大小
        jl.setBounds(70, 30, 126, 36);

        // 把按钮添加进内容面板
        con.add(jb);
        // 设置按钮的摆放位置和大小
        jb.setBounds(90, 90, 90, 30);
        // 为按钮注册ActionListener事件
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 判断验证码是否在滚动，是 则停止，否 则开始滚动
                if (!times.isRunning()) {
                    times.start();
                } else {
                    times.stop();
                    String text = jl.getText();
                    System.out.println("中奖选手:"+text);
                    if(list.size() > 1){
                        list.remove(text);
                    }else{
                        jl.setText("");
                        list.clear();
                        list.add("抽完了!");
                    }
                }
                // 改变按钮的文字，开始滚动后变为停止，停止后变为开始
                if ("开始".equals(jb.getText())) {
                    jb.setText("停止");
                } else {
                    jb.setText("开始");
                }
                // 为Timer对象添加ActionListener事件，在事件中产生随机验证码并实现验证码的滚动操作
                times.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String str = "";
//                        for (int i = 0; i < 4; i++) {
//                            str += scode[rand.nextInt(scode.length)];
//                        }
                        str = list.get(rand.nextInt(list.size()));
                        jl.setText(str);
                    }
                });
            }
        });

        // 设置JFrame大小
        jf.setSize(260, 200);
        // 窗体置顶显示
        jf.setAlwaysOnTop(true);
        // JFrame不能改变大小
        jf.setResizable(false);
        // JFrame居中显示
        jf.setLocationRelativeTo(null);
        // 显示JFrame窗体
        jf.setVisible(true);
        // 设置JFrame关闭动作
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
