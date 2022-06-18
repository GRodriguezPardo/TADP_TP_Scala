package domain2

sealed trait Trabajo extends StatsProvider {
  def statPrincipal :Attribute
}

case object Guerrero extends Trabajo {
  override def statPrincipal :Attribute = Fuerza
  override def hp(h: Heroe2) :Int = 10
  override def fuerza(h: Heroe2) :Int = 15
  override def inteligencia(h: Heroe2) :Int = -10
}

case object Mago extends Trabajo {
  override def statPrincipal :Attribute = Inteligencia
  override def inteligencia(h: Heroe2) :Int = 20
  override def fuerza(h: Heroe2) :Int = -20
}

case object Ladron extends Trabajo {
  override def statPrincipal :Attribute = Velocidad
  override def hp(h: Heroe2) :Int = -5
  override def velocidad(h: Heroe2) :Int = 10
}