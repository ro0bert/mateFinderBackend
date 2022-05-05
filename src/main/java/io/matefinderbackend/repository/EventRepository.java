package io.matefinderbackend.repository;

import io.matefinderbackend.entities.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @EntityGraph(attributePaths = {"address"})
    List<Event> getAllBy();

    List<Event> findByName(String name);
}