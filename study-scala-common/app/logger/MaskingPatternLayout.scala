package logger

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.spi.ILoggingEvent

class MaskingPatternLayout extends PatternLayout {
    override def doLayout(event: ILoggingEvent): String = {
        val originalMessage = event.getFormattedMessage
        val maskedMessage = originalMessage.replaceAll("([0-9]{3})-([0-9]{4})-([0-9]{4})", "xxx-xxxx-xxxx")
        super.doLayout(event).replace(originalMessage, maskedMessage)
    }
}
