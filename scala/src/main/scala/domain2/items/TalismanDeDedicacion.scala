package domain2.items

import domain2.{Fuerza, HP, Heroe2, Inteligencia, Talisman, Velocidad}

object TalismanDeDedicacion extends Talisman {
  var reentrant :Boolean = false

  def aumento(heroe:Heroe2) :Int = {
    if (reentrant) 0 else {
      reentrant = true
      val result = (heroe.trabajo.map(t => t.statPrincipal) match {
        case Some(HP) => heroe.hp
        case Some(Fuerza) => heroe.fuerza
        case Some(Velocidad) => heroe.velocidad
        case Some(Inteligencia) => heroe.inteligencia
        case Some(_) => 0
        case None => 0
      }).toDouble.*(0.1).toInt
      reentrant = false
      result
    }
  }

  override def hp(h:Heroe2) :Int = aumento(h)
  override def fuerza(h:Heroe2) :Int = aumento(h)
  override def inteligencia(h:Heroe2): Int = aumento(h)
  override def velocidad(h:Heroe2): Int = aumento(h)
}
