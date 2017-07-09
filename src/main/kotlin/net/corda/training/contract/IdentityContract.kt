package net.corda.training.contract

import net.corda.core.contracts.*
import net.corda.core.crypto.SecureHash
import net.corda.training.state.IdentityState
import java.time.LocalDate

open class IdentityContract : Contract {

    override val legalContractReference: SecureHash = SecureHash.sha256("Some legal prose.")

    interface Commands : CommandData {

    }

    override fun verify(tx: TransactionForContract) {

    }
}
