package net.corda.training.flow

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.contracts.Command
import net.corda.core.contracts.TransactionType
import net.corda.core.crypto.SecureHash
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.StartableByRPC
import net.corda.core.node.services.linearHeadsOfType
import net.corda.core.seconds
import net.corda.flows.FinalityFlow
import net.corda.training.contract.IdentityContract
import net.corda.training.state.IdentityState

@InitiatingFlow
@StartableByRPC
class CreateIdentityFlow(val identityState: IdentityState) : FlowLogic<IdentityFlowResult>() {

    @Suspendable
    override fun call(): IdentityFlowResult {
            return IdentityFlowResult.Success("")
    }
}
