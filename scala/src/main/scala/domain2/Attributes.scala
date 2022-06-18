package domain2

sealed trait Attribute

case object HP extends Attribute
case object Fuerza extends Attribute
case object Velocidad extends Attribute
case object Inteligencia extends Attribute