package com.zup.william.deletaChavePix

import com.william.ChavePixServiceRemoveGrpc
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import org.slf4j.LoggerFactory


@Controller
class DeletaChavePixController(
    private val chavePixRemove: ChavePixServiceRemoveGrpc.ChavePixServiceRemoveBlockingStub
) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Delete("/api/chave/api")
    fun remove(@Body request: RemoveChavePixRequestDto): HttpResponse<Any> {

        LOGGER.info("Chamando chavePixRemove.remove(request.toGrpc())")
        try {
            val emptyReturn = chavePixRemove.remove(request.toGrpc())
            LOGGER.info("Chamada feita: ${emptyReturn}")
            return HttpResponse.ok("Chave removida com sucesso")

        } catch (e: StatusRuntimeException) {
            LOGGER.warn("Status Runtime capturado:  ${e.status.code}")


            when (e.status.code) {
                Status.NOT_FOUND.code -> return HttpResponse.notFound("Chave não encontrada")
            }

            return HttpResponse.badRequest()


        }


    }


}