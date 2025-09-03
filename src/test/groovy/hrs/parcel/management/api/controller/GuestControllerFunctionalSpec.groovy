package hrs.parcel.management.api.controller


import hrs.parcel.management.api.command.GuestCreateCommand
import hrs.parcel.management.api.command.GuestUpdateCommand
import hrs.parcel.management.api.model.Guest
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class GuestControllerFunctionalSpec extends FunctionalSpec {
  def "create and get guest"() {
    given: "a new guest create command"
    def command = new GuestCreateCommand(name: "Alice", email: "alice@test.com", checkedIn: false)

    when: "POST /guests is called"
    def postResult = mockMvc.perform(post("/guests")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(command))
    ).andExpect(status().isOk()).andReturn()

    def response = objectMapper.readValue(postResult.response.contentAsString, Guest)
    def guestId = response.id

    then: "guest is created with an id"
    response.name == "Alice"
    response.email == "alice@test.com"
    guestId != null

    when: "GET /guests/{id} is called"
    def getResult = mockMvc.perform(get("/guests/$guestId"))
        .andExpect(status().isOk())
        .andReturn()

    def fetched = objectMapper.readValue(getResult.response.contentAsString, Guest)

    then: "fetched guest matches created one"
    fetched.id == guestId
    fetched.name == "Alice"
  }

  def "update guest"() {
    given:
    def createCommand = new GuestCreateCommand(name: "Bob", email: "bob@test.com", checkedIn: false)
    def createdJson = mockMvc.perform(post("/guests")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createCommand))
    ).andExpect(status().isOk()).andReturn().response.contentAsString

    def created = objectMapper.readValue(createdJson, Guest)

    when: "PUT /guests/{id} is called with updated data"
    def updateCommand = new GuestUpdateCommand(
        id: created.id,
        name: "Bob Marley",
        email: "bob.marley@test.com",
        checkedIn: true,
        checkedOut: true)

    def updateResult = mockMvc.perform(put("/guests/${created.id}")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updateCommand))
    ).andExpect(status().isOk())
        .andReturn()

    def updated = objectMapper.readValue(updateResult.response.contentAsString, Guest)

    then:
    updated.name == "Bob Marley"
    updated.email == "bob.marley@test.com"
    updated.checkedIn == true
    updated.checkedOut == true
  }

  def "delete guest"() {
    given:
    def createCommand = new GuestCreateCommand(name: "Charlie", email: "charlie@test.com", checkedIn: true)
    def createdJson = mockMvc.perform(post("/guests")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createCommand))
    ).andExpect(status().isOk()).andReturn().response.contentAsString

    def created = objectMapper.readValue(createdJson, Guest)

    when: "DELETE /guests/{id} is called"
    mockMvc.perform(delete("/guests/${created.id}"))
        .andExpect(status().isNoContent())

    then: "subsequent GET returns 404"
    mockMvc.perform(get("/guests/${created.id}"))
        .andExpect(status().isNotFound())
  }
}
