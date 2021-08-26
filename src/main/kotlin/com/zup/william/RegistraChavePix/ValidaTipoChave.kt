package com.zup.william.registraChavePix


import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Constraint(validatedBy = [ValidaTipoChaveValidator::class])
annotation class ValidaTipoChave(
    val message: String = "Chave pix invalida (\${validatedValue.tipoDeChave})",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

class ValidaTipoChaveValidator : ConstraintValidator<ValidaTipoChave, CadastraChavePixRequestDTO> {
      override fun isValid(
          value: CadastraChavePixRequestDTO?,
          annotationMetadata: AnnotationValue<ValidaTipoChave>,
          context: ConstraintValidatorContext
    ): Boolean {
        if (value?.tipoDaChave == null) {
            return false
        }

        return value.tipoDaChave.valida(value.valorDaChave)
    }


}