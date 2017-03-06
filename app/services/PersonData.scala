package services

import models.PersonSignup

class PersonData {
   def getPersonData(email : String) = {
     val personData = AddUser.listOfPerson.filter(_.email == email)
     personData.head
   }
}
