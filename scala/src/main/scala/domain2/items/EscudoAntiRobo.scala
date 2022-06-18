package domain2.items

import domain2.{Heroe2, Ladron, UnaMano}

object EscudoAntiRobo extends UnaMano {
  override protected def equiparHook(heroe :Heroe2) :Heroe2 = {
    require(heroe.trabajo match {
      case Some(Ladron) | Some(_) if heroe.fuerzaBase < 20 => false
      case _ => true
    }, "Cannot equip Escudo AntiRobo.")
    super.equiparHook(heroe)
  }

  override def hp(h:Heroe2) :Int = 20
}
