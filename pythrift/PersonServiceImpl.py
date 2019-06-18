__author__ = "作者"

from py.thrift.generated import ttypes

class PersonServiceImpl:


    def getPersonByUserName(self,username):

        print("got from client param:",username)

        person = ttypes.Person()
        person.username = "python实现"
        person.age = 1
        person.married = False

        return person

    def savePerson(self,person):
        print("Got save call ,param: ")

        print(person.username)
        print(person.age)
        print(person.married)