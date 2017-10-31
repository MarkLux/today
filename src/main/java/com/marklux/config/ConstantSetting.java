package com.marklux.config;

/**
 * Created by mark on 17/10/31.
 */
public final class ConstantSetting {
    public static final String BASE_PACKAGE = "com.marklux"; //项目基地址
    public static final String BASE_INPUT_PACKAGE = "com.marklux.common"; //配置项输入地址
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".domain"; //Domain所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".mapper"; //Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service"; //Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl"; //ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller"; //Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_INPUT_PACKAGE + ".MyMapper"; //Mapper插件基础接口的完全限定名
}
