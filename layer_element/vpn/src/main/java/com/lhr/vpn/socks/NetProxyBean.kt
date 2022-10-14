package com.lhr.vpn.socks

import java.net.Inet4Address

/**
 * @CreateDate: 2022/10/14
 * @Author: mac
 * @Description:
 * @param sourceAddress 被代理的地址，默认为vpn地址
 * @param sourcePort 被代理的端口
 * @param targetAddress 目标地址
 * @param targetPort 目标端口
 */
data class NetProxyBean(
    val sourceAddress: Inet4Address,
    val sourcePort: Int,
    val targetAddress: Inet4Address,
    val targetPort: Int
)
