package com.qijy.proxys.dynamics;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DynamicsTest {
    public static void main(String[] args) throws Exception{
        CommonInterfaceImpl commonInterface = new CommonInterfaceImpl();

        CommInterfaceProxy commInterfaceProxy = new CommInterfaceProxy(commonInterface);

        CommonInterface o = (CommonInterface) Proxy.newProxyInstance(commonInterface.getClass().getClassLoader(), commonInterface.getClass().getInterfaces(), commInterfaceProxy);
        o.printName("qijy");
//        List<CommonInterface> animals = new ArrayList<>();
//        List<Class> clazzs = getAllInterfaceAchieveClass(CommonInterface.class);
//        for(Class clazz : clazzs){
//            animals.add((CommonInterface)clazz.newInstance());
//        }
//        //打印Class对象
//        for(CommonInterface cla : animals){
//            System.out.println("实现类:"+cla.getClass());
//        }


    }


    private static ArrayList<Class> findClass(File file, String packagename) {
        ArrayList<Class> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        File[] files = file.listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
                assert !file2.getName().contains(".");//添加断言用于判断
                ArrayList<Class> arrayList = findClass(file2, packagename+"."+file2.getName());
                list.addAll(arrayList);
            }else if(file2.getName().endsWith(".class")){
                try {
                    //保存的类文件不需要后缀.class
                    list.add(Class.forName(packagename + '.' + file2.getName().substring(0,
                            file2.getName().length()-6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


    /**
     * 从指定路径下获取所有类
     * @return
     */
    public static ArrayList<Class> getAllClassByPath(String packagename){
        ArrayList<Class> list = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packagename.replace('.', '/');
        try {
            ArrayList<File> fileList = new ArrayList<>();
            Enumeration<URL> enumeration = classLoader.getResources(path);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                fileList.add(new File(url.getFile()));
            }
            for (int i = 0; i < fileList.size(); i++) {
                list.addAll(findClass(fileList.get(i),packagename));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取所有接口的实现类
     * @return
     */
    public static List<Class> getAllInterfaceAchieveClass(Class clazz){
        ArrayList<Class> list = new ArrayList<>();
        //判断是否是接口
        if (clazz.isInterface()) {
            try {
                ArrayList<Class> allClass = getAllClassByPath(clazz.getPackage().getName());
                /**
                 * 循环判断路径下的所有类是否实现了指定的接口
                 * 并且排除接口类自己
                 */
                for (int i = 0; i < allClass.size(); i++) {

                    //排除抽象类
                    if(Modifier.isAbstract(allClass.get(i).getModifiers())){
                        continue;
                    }
                    //判断是不是同一个接口
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        if (!clazz.equals(allClass.get(i))) {
                            list.add(allClass.get(i));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("出现异常");
            }
        }
        return list;
    }

}
