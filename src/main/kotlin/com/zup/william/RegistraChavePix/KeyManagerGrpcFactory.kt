package com.zup.william.registraChavePix

import com.william.ChavePixServiceConsultaGrpc
import com.william.ChavePixServiceListaChavesClienteGrpc
import com.william.ChavePixServiceRegistraGrpc
import com.william.ChavePixServiceRemoveGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class KeyManagerGrpcFactory(
    @GrpcChannel("keyManager")
    val channel: ManagedChannel
) {

    @Singleton
    fun registraChave() = ChavePixServiceRegistraGrpc.newBlockingStub(channel)

    @Singleton
    fun remove() = ChavePixServiceRemoveGrpc.newBlockingStub(channel)

    @Singleton
    fun detalha() = ChavePixServiceConsultaGrpc.newBlockingStub(channel)

    @Singleton
    fun listagem() = ChavePixServiceListaChavesClienteGrpc.newBlockingStub(channel)

}