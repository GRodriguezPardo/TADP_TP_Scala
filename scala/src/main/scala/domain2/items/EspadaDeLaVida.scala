package domain2.items

import domain2.{Heroe2, UnaMano}

object EspadaDeLaVida extends UnaMano {
  override protected def equiparHook(heroe: Heroe2): Heroe2 = {
    val function = Some((heroe :Heroe2) => heroe.hp)
    val heroeNuevo = heroe.copy(fuerzaCalculator = function)
    super.equiparHook(heroeNuevo)
  }

  override protected def desequiparHook(heroe: Heroe2): Heroe2 = {
    val heroeNuevo = heroe.copy(fuerzaCalculator = None)
    super.desequiparHook(heroeNuevo)
  }
}
