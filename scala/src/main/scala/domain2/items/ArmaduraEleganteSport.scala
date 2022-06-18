package domain2.items

import domain2.{Heroe2, Torso}

object ArmaduraEleganteSport extends Torso {
  override def hp(h:Heroe2) :Int = -30

  override def velocidad(h:Heroe2) :Int = 30
}
