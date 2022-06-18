package domain2

import scala.util.Try

case class Heroe2 (
                    hpBase :Int,
                    fuerzaBase :Int,
                    velocidadBase :Int,
                    inteligenciaBase :Int,
                    trabajo :Option[Trabajo] = None,
                    inventario :List[Item] = Nil,
                    hpCalculator :Option[Heroe2 => Int] = None,
                    fuerzaCalculator :Option[Heroe2 => Int] = None,
                    velocidadCalculator :Option[Heroe2 => Int] = None,
                    inteligenciaCalculator :Option[Heroe2 => Int] = None
                  ) {
  def hp :Int = getStat(hpCalculator, _.hp(this), _.hp(this), hpBase)
  def fuerza :Int = getStat(fuerzaCalculator, _.fuerza(this), _.fuerza(this), fuerzaBase)
  def velocidad :Int = getStat(velocidadCalculator, _.velocidad(this), _.velocidad(this), velocidadBase)
  def inteligencia :Int = getStat(inteligenciaCalculator, _.inteligencia(this), _.inteligencia(this), inteligenciaBase)

  def getStat(calculator :Option[Heroe2 => Int], f :Item => Int, t :Trabajo => Int, valorBase :Int) :Int = {
    0.max(calculator.map(_(this)).getOrElse(
      inventario.map(f).sum + trabajo.map(t).getOrElse(0) + valorBase
    ))
  }

  def equipar(item :Item) :Try[Heroe2] = Try {
    item.equiparEn(this)
  }

  def desequipar(item :Item) :Try[Heroe2] = Try {
    item.desequiparDe(this)
  }
}

