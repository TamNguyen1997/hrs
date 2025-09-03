package hrs.parcel.management.api.service

import hrs.parcel.management.api.command.GuestSearchCommand
import hrs.parcel.management.api.exception.ResourceNotFoundException
import hrs.parcel.management.api.model.Guest
import hrs.parcel.management.api.orm.GuestDb
import hrs.parcel.management.api.repository.GuestRepository
import org.modelmapper.ModelMapper
import org.springframework.data.jpa.domain.Specification
import spock.lang.Specification as SpockSpec

class GuestServiceSpec extends SpockSpec {

  GuestRepository guestRepository = Mock()
  ModelMapper modelMapper = new ModelMapper()
  GuestService guestService = new GuestService(guestRepository, modelMapper)

  def "getGuest should return Guest when found"() {
    given:
    UUID id = UUID.randomUUID()
    def guestDb = new GuestDb(id: id, name: "Alice", email: "alice@test.com", checkedIn: true, checkedOut: false)
    guestRepository.findById(id) >> Optional.of(guestDb)

    when:
    Guest result = guestService.getGuest(id)

    then:
    result.id == id
    result.name == "Alice"
    result.email == "alice@test.com"
  }

  def "getGuest should throw ResourceNotFoundException when not found"() {
    given:
    UUID id = UUID.randomUUID()
    guestRepository.findById(id) >> Optional.empty()

    when:
    guestService.getGuest(id)

    then:
    thrown(ResourceNotFoundException)
  }

  def "createGuest should save and return Guest"() {
    given:
    def guest = new Guest(UUID.randomUUID(), "Bob", "bob@test.com", false, false)
    def guestDb = modelMapper.map(guest, GuestDb)
    guestRepository.save(_ as GuestDb) >> guestDb

    when:
    Guest result = guestService.createGuest(guest)

    then:
    result.name == "Bob"
    result.email == "bob@test.com"
  }

  def "updateGuest should update and return Guest"() {
    given:
    def id = UUID.randomUUID()
    def guest = new Guest(id, "Charlie", "charlie@test.com", true, false)
    def guestDb = new GuestDb(id: id, name: "Old", email: "old@test.com", checkedIn: false, checkedOut: false)

    guestRepository.findById(id) >> Optional.of(guestDb)
    guestRepository.save(_ as GuestDb) >> { GuestDb g -> g }

    when:
    Guest result = guestService.updateGuest(guest)

    then:
    result.name == "Charlie"
    result.email == "charlie@test.com"
    result.checkedIn
    !result.checkedOut
  }

  def "searchGuests should return mapped results"() {
    given:
    def guestDb = new GuestDb(id: UUID.randomUUID(), name: "Dana", email: "dana@test.com", checkedIn: true, checkedOut: false)
    guestRepository.findAll(_ as Specification) >> [guestDb]

    def command = new GuestSearchCommand()

    when:
    List<Guest> results = guestService.searchGuests(command)

    then:
    results.size() == 1
    results[0].name == "Dana"
  }

  def "deleteGuest should delegate to repository"() {
    given:
    def id = UUID.randomUUID()

    when:
    guestService.deleteGuest(id)

    then:
    1 * guestRepository.deleteById(id)
  }
}
