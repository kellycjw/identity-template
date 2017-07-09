package net.corda.training.api

import net.corda.client.rpc.notUsed
import net.corda.core.contracts.ContractState
import net.corda.core.contracts.StateAndRef
import net.corda.core.crypto.SecureHash
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.rootCause
import net.corda.core.utilities.loggerFor
import net.corda.nodeapi.RPCException
import net.corda.training.flow.*
import net.corda.training.state.Identity
import net.corda.training.state.IdentityState
import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.bouncycastle.asn1.x500.X500Name
import org.slf4j.Logger
import java.io.File
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("identity")
class IdentityApi(val services: CordaRPCOps) {
    val SERVICE_NODE_NAMES = listOf(X500Name("CN=Controller,O=R3,L=London,C=UK"), X500Name("CN=NetworkMapService,O=R3,L=London,C=UK"))
    private val myLegalName = services.nodeIdentity().legalIdentity.name

    companion object {
        private val logger: Logger = loggerFor<IdentityApi>()
    }

    /**
     * Returns the node's name.
     */
    @GET
    @Path("me")
    @Produces(MediaType.APPLICATION_JSON)
    fun whoami() = mapOf("me" to myLegalName)

    /**
     * Returns all parties registered with the [NetworkMapService]. These names can be used to look up identities
     * using the [IdentityService].
     */
    @GET
    @Path("peers")
    @Produces(MediaType.APPLICATION_JSON)
    fun getPeers(): Map<String, List<X500Name>> {
        val (nodeInfo, nodeUpdates) = services.networkMapUpdates()
        nodeUpdates.notUsed()
        return mapOf("peers" to nodeInfo
                .map { it.legalIdentity.name }
                .filter { it != myLegalName && it !in SERVICE_NODE_NAMES })
    }

    @GET
    @Path("identities")
    @Produces(MediaType.APPLICATION_JSON)
    fun getIdentities() : List<StateAndRef<ContractState>> {
        val (vaultInfo, vaultUpdates) = services.vaultAndUpdates()
        vaultUpdates.notUsed()
        // Filter by state type: Identity.
        return vaultInfo.filter { it.state.data is IdentityState }
    }

    //this is used to upload the attachment
    @POST
    @Path("document")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    fun createDocument(@Context req: HttpServletRequest): Response {
        return Response
                .status(Response.Status.OK)
                .entity("")
                .build()
    }

    //create an identity
    @PUT
    @Path("create")
    fun createIdentity(identity: Identity): Response {
        return Response
                .status(Response.Status.OK)
                .entity("")
                .build()
    }

    //retrieves the attachment for downloading
    @GET
    @Path("document/{secureHash}/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    fun getDocument(@PathParam("secureHash") secureHash: String, @PathParam("fileName") fileName: String): Response {
        return Response
                .status(Response.Status.OK)
                .entity("")
                .build()
    }

    //searches for this identity on the ledger
    @GET
    @Path("find/{CPF}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findIdentity(@PathParam("CPF") cpf: String): Response {
        return Response
                .status(Response.Status.OK)
                .entity("")
                .build()
    }

    @POST
    @Path("update")
    fun updateIdentity(identity: Identity): Response {
        return Response
                .status(Response.Status.OK)
                .entity("")
                .build()
    }

    @POST
    @Path("delete")
    fun deleteIdentity(identity: Identity): Response {
        return Response
                .status(Response.Status.OK)
                .entity("")
                .build()
    }
}