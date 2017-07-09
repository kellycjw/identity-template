package net.corda.training.state

import net.corda.core.serialization.CordaSerializable
import java.time.LocalDate
import java.util.*

@CordaSerializable
data class Document(val something: String)

@CordaSerializable
data class Identity(val something: String)