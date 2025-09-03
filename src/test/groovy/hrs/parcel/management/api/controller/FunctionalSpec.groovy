package hrs.parcel.management.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

/**
 * Function test base.
 */
// TODO: There should be a separate test module and profile for functional tests
// Right now, function tests are in unit test module and using default profile.
@SpringBootTest
@AutoConfigureMockMvc
class FunctionalSpec extends Specification {
  @Autowired
  protected MockMvc mockMvc

  @Autowired
  protected ObjectMapper objectMapper

}
