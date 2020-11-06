package com.qijy.kafkas.config;


import java.util.Set;

public enum MonConfig {

    INSTANCE;

    /**
     * kafka配置
     */
    @Config(name = "Bootstrap_Servers")
    private String kafkaServer;
    @Config(name = "Zookeeper_Connect")
    private String zookeeper;
    @Config(name = "Request_Timeout_ms")
    private long requestTimeout = 10000;
    @Config(name = "Session_Timeout_ms")
    private long sessionTimeout = 30000;
    @Config(name = "Heartbeat_Interval_ms")
    private long heartbeat = 5000;
    @Config(name = "Group_Event")
    private String eventGroup = "evtGroup";

    @Config(name = "file_bak", reload = true)
    private boolean bak;

    @Config(name = "backupFilePath")
    private String backupFilePath;
    @Config(name = "filePath")
    private String filePath;


    @Config(name = "pwd", type = Config.PASSWORD)
    private String password;
    @Config(name = "user")
    private String user;
    @Config(name = "jdbcUrl")
    private String jdbcUrl;
    @Config(name = "driverClass")
    private String driverClass;
    @Config(name = "evtids", split = "\\|")
    private Set<String> evtids;
    @Config(name = "proceid")
    private int proceid;
    @Config(name = "hostid")
    private int hostid;
    @Config(name = "apptype")
    private int apptype;
    @Config(name = "noFileSleepTime")
    private long noFileSleepTime=100;
    @Config(name = "kafkaAlarmTime")
    private int kafkaAlarmTime=5;
    @Config(name = "send1300Time")
    private int send1300Time=15;

    @Config(name = "writeKafkaNum")
    private int writeKafkaNum=3;

    @Config(name = "eventQueueSize")
    private int eventQueueSize=50000;

    @Config(name = "linger.ms")
    private int linger_ms=1000;

    @Config(name = "batch.size")
    private int batch_size=563840;

    @Config(name = "retries")
    private int retries=5;

    @Config(name = "acks")
    private String acks="all";

    @Config(name = "buffer.memory")
    private int buffer_memory=33554432;

    @Config(name = "maxDirReadCount",reload = true)
    private int maxDirReadCount = 50;
    @Config(name = "maxRequestSize")
    private int maxRequestSize;
    @Config(name="retryBackoff")
    private int retryBackoff;

    public String getKafkaServer() {
        return kafkaServer;
    }

    public void setKafkaServer(String kafkaServer) {
        this.kafkaServer = kafkaServer;
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public long getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public long getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(long heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getEventGroup() {
        return eventGroup;
    }

    public void setEventGroup(String eventGroup) {
        this.eventGroup = eventGroup;
    }

    public boolean isBak() {
        return bak;
    }

    public void setBak(boolean bak) {
        this.bak = bak;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public Set<String> getEvtids() {
        return evtids;
    }

    public void setEvtids(Set<String> evtids) {
        this.evtids = evtids;
    }

    public String getBackupFilePath() {
        return backupFilePath;
    }

    public void setBackupFilePath(String backupFilePath) {
        this.backupFilePath = backupFilePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getProceid() {
        return proceid;
    }

    public void setProceid(int proceid) {
        this.proceid = proceid;
    }

    public int getHostid() {
        return hostid;
    }

    public void setHostid(int hostid) {
        this.hostid = hostid;
    }

    public int getApptype() {
        return apptype;
    }

    public void setApptype(int apptype) {
        this.apptype = apptype;
    }

    public long getNoFileSleepTime() {
        return noFileSleepTime;
    }

    public void setNoFileSleepTime(long noFileSleepTime) {
        this.noFileSleepTime = noFileSleepTime;
    }

    public int getKafkaAlarmTime() {
        return kafkaAlarmTime;
    }

    public void setKafkaAlarmTime(int kafkaAlarmTime) {
        this.kafkaAlarmTime = kafkaAlarmTime;
    }

    public int getSend1300Time() {
        return send1300Time;
    }

    public void setSend1300Time(int send1300Time) {
        this.send1300Time = send1300Time;
    }

    public int getWriteKafkaNum() {
        return writeKafkaNum;
    }

    public void setWriteKafkaNum(int writeKafkaNum) {
        this.writeKafkaNum = writeKafkaNum;
    }

    public int getEventQueueSize() {
        return eventQueueSize;
    }

    public void setEventQueueSize(int eventQueueSize) {
        this.eventQueueSize = eventQueueSize;
    }

    public int getLinger_ms() {
        return linger_ms;
    }

    public void setLinger_ms(int linger_ms) {
        this.linger_ms = linger_ms;
    }

    public int getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(int batch_size) {
        this.batch_size = batch_size;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public int getBuffer_memory() {
        return buffer_memory;
    }

    public int getMaxRequestSize() {
        return maxRequestSize;
    }

    public void setMaxRequestSize(int maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }

    public int getRetryBackoff() {
        return retryBackoff;
    }

    public void setRetryBackoff(int retryBackoff) {
        this.retryBackoff = retryBackoff;
    }

    public void setBuffer_memory(int buffer_memory) {
        this.buffer_memory = buffer_memory;
    }

    public int getMaxDirReadCount() {
        return maxDirReadCount;
    }

    public void setMaxDirReadCount(int maxDirReadCount) {
        this.maxDirReadCount = maxDirReadCount;
    }
}