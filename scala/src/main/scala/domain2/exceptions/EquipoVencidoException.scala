package domain2.exceptions

import domain2.{Equipo, Tarea}

class EquipoVencidoException(val equipo :Equipo, val tarea :Tarea) extends RuntimeException