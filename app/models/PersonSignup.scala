package models


case class PersonSignup (
                        firstName : String,
                        lastName : String,
                        email: String,
                        password: String,
                        rePassword:String,
                        company: String,
                        phone: Int
                        )


case class PersonLogin (
                         email: String,
                         password: String

                       )


