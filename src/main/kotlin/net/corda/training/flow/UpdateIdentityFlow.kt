package net.corda.training.flow

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.contracts.Command
import net.corda.core.contracts.TransactionType
import net.corda.core.crypto.SecureHash
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatedBy
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC
import net.corda.core.identity.Party
import net.corda.core.node.services.linearHeadsOfType
import net.corda.core.seconds
import net.corda.core.transactions.SignedTransaction
import net.corda.core.transactions.WireTransaction
import net.corda.core.utilities.ProgressTracker
import net.corda.flows.CollectSignaturesFlow
import net.corda.flows.FinalityFlow
import net.corda.flows.SignTransactionFlow
import net.corda.training.contract.IdentityContract
import net.corda.training.state.Anexo
import java.util.*
import net.corda.training.state.Identity
import net.corda.training.state.IdentityState

@InitiatingFlow
@StartableByRPC
class UpdateIdentityFlow(val identity: Identity) : FlowLogic<IdentityFlowResult>() {

    @Suspendable
    override fun call(): IdentityFlowResult {
        return IdentityFlowResult.Success("")
    }
}

/**
 * This is the flow which signs the Update Identity Flow.
 * The signing is handled by the [CollectSignaturesFlow].
 */
@InitiatedBy(UpdateIdentityFlow::class)
class UpdateIdentityFlowResponder(val otherParty: Party): FlowLogic<Unit>() {
    @Suspendable
    override fun call() {
        val signTransactionFlow = object : SignTransactionFlow(otherParty) {
            override fun checkTransaction(stx: SignedTransaction) {
                // Define checking logic.
            }
        }

        subFlow(signTransactionFlow)
    }
}
