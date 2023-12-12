package io.tolgee.websocket

import io.tolgee.fixtures.RedisRunner
import org.junit.jupiter.api.AfterAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
  properties = [
    "spring.redis.port=56379",
    "tolgee.websocket.use-redis=true",
    "disable-server-app-test-mock-overrides=true"
  ],
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(
  initializers = [WebsocketWithRedisTest.Companion.Initializer::class],
)
class WebsocketWithRedisTest : AbstractWebsocketTest() {
  companion object {
    val redisRunner = RedisRunner()

    @AfterAll
    @JvmStatic
    fun cleanup() {
      redisRunner.stop()
    }

    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
      override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        redisRunner.run()
      }
    }
  }
}
