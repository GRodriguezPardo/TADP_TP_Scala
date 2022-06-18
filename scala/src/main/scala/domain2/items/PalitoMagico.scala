package domain2.items

import domain2.{Heroe2, Ladron, Mago, UnaMano}

object PalitoMagico extends UnaMano {
  override protected def equiparHook(heroe :Heroe2) :Heroe2 = {
    require(heroe.trabajo match {
      case Some(Mago) | Some(Ladron) if heroe.inteligenciaBase > 30 => true
      case _ => false
    }, "Cannot equip Palito Magico")
    super.equiparHook(heroe)
  }

  override def inteligencia(h:Heroe2) :Int = 20
}
