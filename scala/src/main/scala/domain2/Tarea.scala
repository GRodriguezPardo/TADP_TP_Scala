package domain2

import scala.util.Try

trait Tarea {
  def realizarPor(equipo: Equipo): Equipo
  def facilidad(equipo: Equipo): Try[Int]
}
