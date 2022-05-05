package io.matefinderbackend.facade

import io.matefinderbackend.entities.AppUser
import io.matefinderbackend.entities.Event
import io.matefinderbackend.repository.AppUserRepository
import io.matefinderbackend.repository.EventRepository
import spock.lang.Specification

class EventFacadeSpec extends Specification{

    EventRepository eventRepository
    AppUserRepository appUserRepository
    EventFacade eventFacade


    def setup() {
        eventRepository = Mock(EventRepository.class)
        appUserRepository = Mock(AppUserRepository.class)
        eventFacade = new EventFacade(eventRepository, appUserRepository)
    }

    def "shouldFailOnMaxUsers"() {
        given:
        def eventId = 1
        def userId = 3

        eventRepository.findById(eventId) >> Optional.of(createEventWithMaxUsers())
        appUserRepository.findById(userId) >> Optional.of(createAppUser("user3@email.com"))


        when:
        eventFacade.addUser(eventId,userId)

        then:
        thrown IllegalArgumentException
    }

    def "shouldFailOnAlreadyAddedUser"() {
        given:
        def eventId = 1
        def userId = 1

        eventRepository.findById(eventId) >> Optional.of(createEventWithGivenUser('michal@onet.pl'))
        appUserRepository.findById(userId) >> Optional.of(createAppUser('michal@onet.pl'))

        when:
        eventFacade.addUser(eventId,userId)

        then:
        thrown IllegalArgumentException
    }

    Event createEventWithMaxUsers() {
        AppUser user1 = createAppUser('michal@onet.pl')
        AppUser user2 = createAppUser('michal2@onet.pl')

        return new Event.EventBuilder()
                .appUsers([user1, user2] as Set)
                .maxUsers(2)
                .build()
    }

    Event createEventWithGivenUser(user) {
        AppUser user1 = createAppUser(user)

        return new Event.EventBuilder()
                .appUsers([user1] as Set)
                .maxUsers(2)
                .build()
    }

    AppUser createAppUser(String email) {
        return new AppUser.AppUserBuilder()
                .id(1)
                .age(20)
                .email(email)
                .build();
    }
}
