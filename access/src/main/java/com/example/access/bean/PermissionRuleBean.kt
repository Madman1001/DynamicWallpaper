package com.example.access.bean

import java.util.ArrayList

/**
 * @author lhr
 * @date 2021/7/9
 * @des 权限规则类
 */
data class PermissionRuleBean(val type: Int) {
    var priority:Int = 0
    var checkable:Boolean = true
    var ruleIntent: PermissionIntentBean? = null
    var actionList: ArrayList<PermissionActionBean> = ArrayList()
    override fun toString(): String {
        return "PermissionRuleBean(type=$type, priority=$priority, checkable=$checkable, ruleIntent=$ruleIntent, actionList=$actionList)"
    }


}