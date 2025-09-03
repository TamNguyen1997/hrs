package hrs.parcel.management.api.service

import hrs.parcel.management.api.model.Guest
import hrs.parcel.management.api.model.Parcel
import hrs.parcel.management.api.orm.ParcelDb
import hrs.parcel.management.api.repository.ParcelRepository
import hrs.parcel.management.api.service.validation.parcel.ParcelValidationService
import org.modelmapper.ModelMapper
import spock.lang.Specification

class ParcelServiceSpec extends Specification {

  ParcelRepository parcelRepository = Mock()
  GuestService guestService = Mock()
  ParcelValidationService parcelValidationService = Mock()
  ModelMapper modelMapper = new ModelMapper()

  ParcelService parcelService = new ParcelService(
      parcelRepository,
      guestService,
      parcelValidationService,
      modelMapper
  )

  def "getAll should return mapped list of parcels"() {
    given:
    def db = new ParcelDb(id: UUID.randomUUID(), name: "TN123", guestId: UUID.randomUUID())
    parcelRepository.findAll() >> [db]

    when:
    def result = parcelService.getAll()

    then:
    result.size() == 1
    result[0].name == "TN123"
  }

  def "createParcel should validate guest and save parcel"() {
    given:
    def guestId = UUID.randomUUID()
    def name = "package"
    def guest = new Guest(guestId, "Alice", "alice@test.com", true, false)
    def parcel = new Parcel(id: UUID.randomUUID(), name: name, guestId: guestId, pickedUp: true)

    when:
    def result = parcelService.createParcel(parcel)

    then:
    1 * guestService.getGuest(guestId) >> guest
    1 * parcelValidationService.validate(guest)
    1 * parcelRepository.save(_ as ParcelDb) >> new ParcelDb(name: name, guestId: guestId)
    result.name == name
    result.guestId == guestId
  }

  def "deleteParcel should delegate to repository"() {
    given:
    def id = UUID.randomUUID()

    when:
    parcelService.deleteParcel(id)

    then:
    1 * parcelRepository.deleteById(id)
  }
}
