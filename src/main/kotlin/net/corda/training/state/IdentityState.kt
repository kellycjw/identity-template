package net.corda.training.state

import net.corda.core.crypto.keys
import net.corda.core.contracts.*
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party
import net.corda.training.contract.IdentityContract
import java.security.PublicKey
import java.util.*

data class IdentityState(val something: String) : ContractState {

    override val contract get() = IdentityContract()

    override val participants: List<AbstractParty> get() = listOf()

}