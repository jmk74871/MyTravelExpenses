package com.mwss.mytravelexpenses.model

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Entity(name = "TOKEN")
open class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    open var id: Long? = null

    @Column(name = "TIME_OUT", nullable = false)
    open var timeOut: Long? = null

    @OneToOne(cascade = [CascadeType.PERSIST], optional = false, orphanRemoval = true)
    @JoinColumn(name = "USER_ID", nullable = false)
    open var user: User? = null

    @Column(name = "UUID_STRING", nullable = false, unique = true)
    open var uuidString: String? = null
}