package domain2

import domain2.exceptions.EquipoVencidoException

import scala.util.{Failure, Success, Try}

class Mision(val tareas :List[Tarea]) {
  def realizarPor(equipoInicial: Equipo) :Try[Equipo] = {
    tareas.foldLeft(Try(equipoInicial)) {
      (equipoIntermedio, tarea) =>
        equipoIntermedio.map { equipo =>
          tarea.facilidad(equipo) match {
            case Success(_) => tarea.realizarPor(equipo)
            case Failure(_) => throw new EquipoVencidoException(equipo, tarea)
          }
        }
    }
  }
}
