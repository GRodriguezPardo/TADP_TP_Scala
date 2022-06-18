package domain2.items

import domain2.{Cabeza, Heroe2}

object VinchaDelBufaloDeAgua extends Cabeza {
  var reentrant = false

  override protected def equiparHook(heroe: Heroe2): Heroe2 = {
    require(heroe.trabajo match {
      case None => true
      case _ => false
    }, "Cannot equip Vincha del Bufalo de Agua.")
    super.equiparHook(heroe)
  }

  private def statsExceptoInteligencia(heroe :Heroe2) :Int = {
    if (reentrant) return 0
    reentrant = true
    val result = if (heroe.fuerza > heroe.inteligencia) 0 else 10
    reentrant = false
    result
  }

  override def hp(heroe :Heroe2) :Int = statsExceptoInteligencia(heroe)

  override def fuerza(heroe :Heroe2) :Int = statsExceptoInteligencia(heroe)

  override def velocidad(heroe :Heroe2) :Int = statsExceptoInteligencia(heroe)

  override def inteligencia(heroe :Heroe2) :Int = {
    if (reentrant) return 0
    reentrant = true
    val result = if (heroe.fuerza > heroe.inteligencia) 30 else 0
    reentrant = false
    result
  }

}
