package com.lhr.sys.reflection

import java.lang.reflect.AccessibleObject
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * @author lhr
 * @date 2021/9/17
 * @des
 */
class SysProxyField(val target: Class<*>, val fieldString: String) : IProxyField,
    SysReflectionBase() {
    /**
     * 设置属性
     */
    private val _setFieldMethod: Method by lazy {
        _FindMethod.invoke(
            Field::class.java,
            "set",
            arrayOf<Class<*>>(Any::class.java, Any::class.java)
        ) as Method
    }

    /**
     * 获取属性
     */
    private val _getFieldMethod: Method by lazy {
        _FindMethod.invoke(Field::class.java, "get", arrayOf<Class<*>>(Any::class.java)) as Method
    }


    /**
     * 授予权限
     */
    private val _AccessMethod: Method by lazy {
        _FindMethod.invoke(
            AccessibleObject::class.java,
            "setAccessible",
            arrayOf(Boolean::class.java)
        ) as Method
    }

    private val field: Field = _FindFeild.invoke(target, fieldString) as Field

    override fun set(obj: Any?, value: Any?) {
        _AccessMethod.invoke(field, true)
        _setFieldMethod.invoke(field, obj, value)
    }

    override fun get(obj: Any?): Any? {
        _AccessMethod.invoke(field, true)
        return _getFieldMethod.invoke(field, obj)
    }

}