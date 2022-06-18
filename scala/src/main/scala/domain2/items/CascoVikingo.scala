package domain2.items

import domain2.{Cabeza, Heroe2}

object CascoVikingo extends Cabeza {
  override protected def equiparHook(heroe :Heroe2) :Heroe2 = {
    require(heroe.fuerzaBase >= 30, "Cannot equip Casco Vikingo.")
    super.equiparHook(heroe)
  }
  override def hp(h:Heroe2) :Int = 10
}
