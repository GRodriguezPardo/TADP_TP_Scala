package domain2

sealed trait Item extends StatsProvider {
  final def equiparEn(heroe :Heroe2) :Heroe2 = {
    val heroeNuevo = heroe.inventario.filter(equiparFilter).foldLeft(heroe)(
      (heroeResult, item) => item.desequiparDe(heroeResult)
    )
    val nuevoInventario = heroeNuevo.inventario.appended(this)
    equiparHook(heroeNuevo.copy(inventario = nuevoInventario))
  }

  protected def equiparFilter(item :Item) :Boolean

  protected def equiparHook(heroe :Heroe2) :Heroe2 = heroe

  final def desequiparDe(heroe :Heroe2) :Heroe2 = {
    val nuevoInventario = heroe.inventario.filterNot(_.eq(this))
    desequiparHook(heroe.copy(inventario = nuevoInventario))
  }

  protected def desequiparHook(heroe :Heroe2) :Heroe2 = heroe
}

case class Cabeza() extends Item {
  final override protected def equiparFilter(item :Item) :Boolean = {
    item match {
      case Cabeza() => true
      case _ => false
    }
  }
}

case class Torso() extends Item {
  final override protected def equiparFilter(item :Item) :Boolean = {
    item match {
      case Torso() => true
      case _ => false
    }
  }
}

case class UnaMano() extends Item {
  final override protected def equiparFilter(item :Item) :Boolean = item match {
    case DosManos() => true
    case _ => false
  }

  override protected def equiparHook(heroe :Heroe2) :Heroe2 = {
    var heroeNuevo = heroe
    val inventario = heroe.inventario
    val amount = inventario.count {
      case UnaMano() => true
      case _ => false
    }
    if(amount > 2) {
      val nuevoInventario = inventario diff inventario.filter(e => e match {
        case UnaMano() if !e.eq(this) => true
        case _ => false
      }).take(amount - 2)
      heroeNuevo = heroe.copy(inventario = nuevoInventario)
    }
    super.equiparHook(heroeNuevo)
  }
}

case class DosManos() extends Item {
  final override protected def equiparFilter(item :Item) :Boolean = {
    item match {
      case DosManos() | UnaMano() => true
      case _ => false
    }
  }
}

case class Talisman() extends Item {
  final override protected def equiparFilter(item :Item) :Boolean = false
}