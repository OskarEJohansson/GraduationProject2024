package dev.oskarjohansson.api.service

import org.slf4j.LoggerFactory
import java.util.logging.Logger




fun tokenPattern(token:String): Boolean {
    val regex = Regex("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")

    return regex.matches(token)
}