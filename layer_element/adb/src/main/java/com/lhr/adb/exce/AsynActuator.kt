package com.lhr.adb.exce

import com.lhr.utils.MachineUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author lhr
 * @date 2021/11/20
 * @des 并行执行器
 */
class AsynActuator : IActuator {
    private val pool: ExecutorService = Executors.newFixedThreadPool(MachineUtils.getCpuNum())
    override fun addCommand(command: String) {
    }

    override fun removeCommand(command: String) {
    }

    override fun exceCommand() {

    }
}