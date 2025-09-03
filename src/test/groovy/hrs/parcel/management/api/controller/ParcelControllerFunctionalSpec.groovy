package hrs.parcel.management.api.controller


import hrs.parcel.management.api.command.GuestCreateCommand
import hrs.parcel.management.api.command.ParcelCreateCommand
import hrs.parcel.management.api.model.Parcel
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ParcelControllerFunctionalSpec extends FunctionalSpec {

  def "create parcel for checked-in guest and retrieve it"() {
    given: "a guest is created and checked in"
    def guestCmd = new GuestCreateCommand(name: "Bob", email: "bob@test.com", checkedIn: true)
    def guestResult = mockMvc.perform(post("/guests")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(guestCmd)))
        .andExpect(status().isOk())
        .andReturn()
    def guestId = objectMapper.readTree(guestResult.response.contentAsString).get("id").asText()

    and: "a parcel command for that guest"
    def parcelCmd = new ParcelCreateCommand(name: "Amazon Box", guestId: UUID.fromString(guestId))

    when: "parcel is created"
    def postResult = mockMvc.perform(post("/parcels")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(parcelCmd)))
        .andExpect(status().isOk())
        .andReturn()

    def createdParcel = objectMapper.readValue(postResult.response.contentAsString, Parcel.class)

    then: "parcel is saved correctly"
    createdParcel.name == "Amazon Box"
    createdParcel.guestId.toString() == guestId

    when: "retrieving all parcels"
    def getResult = mockMvc.perform(get("/parcels"))
        .andExpect(status().isOk())
        .andReturn()

    def parcels = objectMapper.readValue(getResult.response.contentAsString,
        objectMapper.typeFactory.constructCollectionType(List, Parcel))

    then: "the created parcel is in the list"
    parcels*.id.contains(createdParcel.id)
  }
}