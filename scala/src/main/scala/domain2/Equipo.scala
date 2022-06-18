package domain2

case class Equipo(nombre : String, pozo : Int = 0, equipo : List[Heroe2]) {
  require(equipo.nonEmpty, "El equipo no puede estar vacio.")

  def mejorHeroeSegun(crtierio: Heroe2 => Int): Option[Heroe2] = equipo.maxByOption(crtierio)

  def obtenerItem(item: Item): Equipo = ???

  def obtenerMiembro(heroe: Heroe2): Equipo =
    this.copy(equipo = equipo.appended(heroe))

  def reemplazarMiembro(heroeViejo: Heroe2, heroeNuevo: Heroe2): Equipo =
    this.copy(equipo = equipo.filterNot(_.eq(heroeViejo)).appended(heroeNuevo))

  def lider: Option[Heroe2] = ???
}
