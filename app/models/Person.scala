package models


case class Person (
                      username: String,
                      password: String

                    )
trait PersonInfo{
  def getPerson(username: String): Person
}
class PersonData extends PersonInfo {
  val listPerson:List[Person] = List(Person("shubhra.sharma@knoldus.in","shubhra"),Person("you.we@gmail.com","youwe"))

  override def getPerson(username: String): Person = {
    def findPerson(list: List[Person]) : Person = {
      list match {
        case head :: tail if(head.username == username) => head
        case head :: tail => findPerson(tail)
        case _ => Person("","")

      }
    }
   findPerson(listPerson)
  }
}