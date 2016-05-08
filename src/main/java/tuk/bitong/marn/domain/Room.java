package tuk.bitong.marn.domain;

import javax.persistence.*;

/**
 * Created by muhai on 15/04/2559.
 */
@Entity
@Table(name = "room")
public class Room {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roomid")
    private Long roomId;

    @Column(name="roomname",nullable = false)
    private String roomName;
}
