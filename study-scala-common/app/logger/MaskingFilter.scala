package logger

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.filter.Filter
import ch.qos.logback.core.spi.FilterReply

class MaskingFilter extends Filter[ILoggingEvent] {

    // Local環境ではlogback.xmlで設定してもクラスローダーの関係で動作しないとのこと
    override def decide(event: ILoggingEvent): FilterReply = {
        val message = event.getFormattedMessage
        val maskedMessage = message.replaceAll("([0-9]{3})-([0-9]{4})-([0-9]{4})", "xxx-xxxx-xxxx")
        event.getLoggerContextVO.getPropertyMap.put("message", maskedMessage)
        FilterReply.ACCEPT
    }
}
