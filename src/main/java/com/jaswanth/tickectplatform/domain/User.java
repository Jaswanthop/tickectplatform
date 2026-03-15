package com.jaswanth.tickectplatform.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @Column(updatable = false,nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    //todo:organized events

    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL)
    private List<Event> organizedEvents=new ArrayList<>();



    // todo:attedening events
    @ManyToMany
    @JoinTable(
            name = "user_attending_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="event_id")
    )
    private  List<Event>  attendingEvents=new ArrayList<>();



    //staffing events
    @ManyToMany
    @JoinTable(
            name="user_staffing_events",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns =@JoinColumn(name="event_id")

    )
    private List<Event> staffingEvents=new ArrayList<>();
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    @Column(updatable = true,nullable = false)
    private LocalDateTime updatedAt;
}
