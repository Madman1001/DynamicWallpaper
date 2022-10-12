package com.lhr.vpn.net.v4

import java.nio.ByteBuffer

/**
 * @CreateDate: 2022/10/12
 * @Author: mac
 * @Description: tcp数据包解析
 */
class NetTcpPacket {
    constructor(){
        tcpHeader = TcpHeader()
    }
    constructor(array: ByteArray): this(ByteBuffer.wrap(array))

    constructor(buffer: ByteBuffer){
        decodePacket(buffer)
    }

    //tcp协议头
    lateinit var tcpHeader: TcpHeader
        private set

    //tcp数据
    var data: ByteArray = ByteArray(0)

    fun decodePacket(buffer: ByteBuffer){
        val tcpHeader = TcpHeader()
        tcpHeader.source_port = buffer.short
        tcpHeader.target_port = buffer.short
        tcpHeader.sequence_number = buffer.int
        tcpHeader.acknowledgment_sequence_number = buffer.int

        val len = buffer.get().toUByte().toInt()
        val headLength = len ushr 4

        tcpHeader.control_sign = buffer.get()

        tcpHeader.window_size = buffer.short

        tcpHeader.check_sum = buffer.short

        tcpHeader.urgent_pointer = buffer.short

        val optionsLength = headLength * 4 - 20
        val optionWords = ByteArray(optionsLength)
        buffer.get(optionWords)
        tcpHeader.optionWords = optionWords

        this.tcpHeader = tcpHeader

        val dataLength = buffer.capacity() - buffer.position()
        val data = ByteArray(dataLength)
        buffer.get(data)
        this.data = data
    }

    fun encodePacket(): ByteBuffer{
        val size = 20 + tcpHeader.optionWords.size + data.size
        val buffer = ByteBuffer.allocate(size)
        buffer.putShort(tcpHeader.source_port)
        buffer.putShort(tcpHeader.target_port)
        buffer.putInt(tcpHeader.sequence_number)
        buffer.putInt(tcpHeader.acknowledgment_sequence_number)

        //todo 需计算长度
        buffer.put(tcpHeader.head_length)

        buffer.put(tcpHeader.control_sign)

        buffer.putShort(tcpHeader.window_size)

        //todo 需计算校验和
        buffer.putShort(tcpHeader.check_sum)

        buffer.putShort(tcpHeader.urgent_pointer)

        buffer.put(tcpHeader.optionWords)

        buffer.put(data)
        return buffer
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("tcp packet:\n")
            .append("Src:").append(tcpHeader.source_port.toUShort()).append("\n")
            .append("Dst:").append(tcpHeader.target_port.toUShort()).append("\n")
            .append("SeqNumber:").append(tcpHeader.sequence_number.toUInt()).append("\n")
            .append("AckNumber:").append(tcpHeader.acknowledgment_sequence_number.toUInt()).append("\n")
            .append("WindowSize:").append(tcpHeader.window_size.toUShort()).append("\n")
            .append("ControlSign").append(tcpHeader.control_sign.toUByte()).append("\n")
            .append("DataLength:").append(data.size).append("\n")
        return sb.toString()
    }

    class TcpHeader{
        //源端口号 16 bit
        var source_port: Short = 0

        //目标端口号 16 bit
        var target_port: Short = 0

        //序号 32 bit
        var sequence_number: Int = 0

        //确认序号 32 bit
        var acknowledgment_sequence_number: Int = 0

        //首部长度 4 bit (以 32 bit为单位)
        var head_length: Byte = 0
        //控制位 6 bit (0 0 URG ACK PSH RST SYN FIN)
        var control_sign: Byte = 0

        //窗口大小 16 bit
        var window_size: Short = 0

        //校验和 16 bit
        var check_sum: Short = 0

        //紧急指针 16 bit
        var urgent_pointer: Short = 0

        //其它选项 (单位 32 bit)
        var optionWords: ByteArray = ByteArray(0)
    }
}