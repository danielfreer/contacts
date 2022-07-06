import dev.dfreer.contacts.ContactsService
import dev.dfreer.contacts.database.Database
import dev.dfreer.contacts.database.IdGenerator
import dev.dfreer.contacts.http.Server
import dev.dfreer.contacts.json.Parser
import dev.dfreer.contacts.logging.KotlinLoggingLogger
import dev.dfreer.contacts.logging.named

fun main() {
    val parser = Parser()
    val idGenerator = IdGenerator()
    val database = Database(idGenerator)
    val baseLogger = KotlinLoggingLogger()
    val contactsService = ContactsService(parser, database, baseLogger)
    val server = Server(contactsService)

    val logger = baseLogger.named {}
    val listenAddress = "0.0.0.0"
    val port = 8080

    logger.info { "Starting contacts server at: $listenAddress:$port" }
    server.start(listenAddress, port)
}
