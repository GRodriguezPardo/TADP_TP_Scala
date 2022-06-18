package domain2.items

import domain2.{Heroe2, Talisman}

object TalismanDelMinimalismo extends Talisman {
  override def hp(h :Heroe2): Int = 50 - h.inventario.count(i => !i.eq(this)) * 10
}
