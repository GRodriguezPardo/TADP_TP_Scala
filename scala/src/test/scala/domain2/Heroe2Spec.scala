package domain2

import domain2.items.{ArcoViejo, ArmaduraEleganteSport, CascoVikingo, TalismanDeDedicacion}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers._

import scala.util.{Success, Try}

class Heroe2Spec extends AnyFreeSpec {
  "debería generar un heroe con los parametros correctos" in {
    val heroe = Heroe2(10,20,30,40)
    heroe.hpBase shouldBe 10
    heroe.fuerzaBase shouldBe 20
    heroe.velocidadBase shouldBe 30
    heroe.inteligenciaBase shouldBe 40
  }

  "debería calcular correctamente las stats cuando no tiene items" in {
    val heroe = Heroe2(10,20,30,40)
    heroe.hp shouldBe 10
    heroe.fuerza shouldBe 20
    heroe.velocidad shouldBe 30
    heroe.inteligencia shouldBe 40
  }

  "debería calcular correctamente las stats cuando no tiene items, pero stats negativas" in {
    val heroe = Heroe2(-10,-20,-30,-40)
    heroe.hp shouldBe 0
    heroe.fuerza shouldBe 0
    heroe.velocidad shouldBe 0
    heroe.inteligencia shouldBe 0
  }

  "debería calcular correctamente las stats cuando tiene trabajo" in {
    var heroe = Heroe2(10,20,30,40)
    heroe = heroe.copy(trabajo = Some(Guerrero))
    heroe.hp shouldBe 20
    heroe.fuerza shouldBe 35
    heroe.velocidad shouldBe 30
    heroe.inteligencia shouldBe 30
  }

  "debería calcular correctamente las stats cuando tiene un item" in {
    var heroe = Heroe2(10,20,30,40)
    heroe = heroe.equipar(ArmaduraEleganteSport).get
    heroe.hp shouldBe 0
    heroe.fuerza shouldBe 20
    heroe.velocidad shouldBe 60
    heroe.inteligencia shouldBe 40
  }

  "debería calcular correctamente las stats cuando tiene varios items" in {
    val heroe: Heroe2 = (for {
      x <- Try(Heroe2(10, 40, 30, 40))
      y <- x.equipar(CascoVikingo) //+10hp
      z <- y.equipar(ArcoViejo) //+2fuerza
    } yield z).get
    heroe.hp shouldBe 20
    heroe.fuerza shouldBe 42
    heroe.velocidad shouldBe 30
    heroe.inteligencia shouldBe 40
  }

  "debería calcular correctamente las stats cuando tiene varios items y uno tiene modificacion especial" in {
    val heroe: Try[Heroe2] = for {
      x <- Try(Heroe2(10, 40, 30, 40))
      y <- x.equipar(CascoVikingo) //+10hp
      z <- y.equipar(ArcoViejo) //+2fuerza
      a <- Try(z.copy(trabajo = Some(Guerrero))) //+10hp, +15fuerza, -10intel
      b <- a.equipar(TalismanDeDedicacion)
    } yield b
    val resultado :Heroe2 = heroe.get
    resultado.hp shouldBe (10+10+10)+5
    resultado.fuerza shouldBe (40+2+15)+5
    resultado.velocidad shouldBe (30)+5
    resultado.inteligencia shouldBe (40-10)+5
  }
}