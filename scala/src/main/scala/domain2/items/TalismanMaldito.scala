package domain2.items

import domain2.{Heroe2, Talisman}

object TalismanMaldito extends Talisman {
  override protected def equiparHook(heroe: Heroe2): Heroe2 = {
    val function = Some((heroe :Heroe2) => 1)
    val heroeNuevo = heroe.copy(
      hpCalculator = function,
      fuerzaCalculator = function,
      velocidadCalculator = function,
      inteligenciaCalculator = function
    )
    super.equiparHook(heroeNuevo)
  }

  override protected def desequiparHook(heroe: Heroe2): Heroe2 = {
    val heroeNuevo = heroe.copy(
      hpCalculator = None,
      fuerzaCalculator = None,
      velocidadCalculator = None,
      inteligenciaCalculator = None
    )
    super.desequiparHook(heroeNuevo)
  }
}
