package com.lhr.vpn

/**
 * @author lhr
 * @date 2021/11/13
 * @des 配置信息
 */
object LocalVpnConfig {
    //代理服务名称
    const val PROXY_SESSION_NAME = "Local VPN"

    //代理的主机地址，暂时只支持 ipv4
    const val PROXY_ADDRESS = "192.168.2.2"

    //代理的主机端口
    const val PROXY_PORT = 32

    //允许通过的路由地址
    const val PROXY_ROUTE_ADDRESS = "0.0.0.0"

    //允许通过的路由端口
    const val PROXY_ROUTE_PORT = 0
}