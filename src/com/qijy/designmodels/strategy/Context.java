package com.qijy.designmodels.strategy;

public class Context {
    private String[] keys;

    public Context(String[] keys) {
        this.keys = keys;
        init();
    }
    private void init(){
        for (String key : keys) {
            if("+".equals(key)){
                Cache.INSTANCE.put(key,new OperationAdd());
            }else if("-".equals(key)){
                Cache.INSTANCE.put(key,new OperationSubtract());
            }else if ("*".equals(key)){
                Cache.INSTANCE.put(key,new OperationMultiply());
            }
        }
    }

    public int excute(int a, int b, String key){
        Strategy strategy = Cache.INSTANCE.get(key);
        return strategy.doOperation(a,b);
    }
}
