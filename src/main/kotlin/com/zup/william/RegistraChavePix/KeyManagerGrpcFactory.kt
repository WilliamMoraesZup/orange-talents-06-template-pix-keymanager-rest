package com.zup.william.RegistraChavePix

import com.william.ChavePixServiceRegistraGrpc
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


}