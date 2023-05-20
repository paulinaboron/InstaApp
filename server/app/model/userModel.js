const utils = require("../utils")
class User{
    constructor(name, lastname, email, pass){
        this.name = name
        this.lastname = lastname
        this.email = email
        this.pass = pass
        this.confirmed = false
    }

    confirm(){
        this.confirm = true
    }

    update(name, lastname){
        this.name = name;
        this.lastname = lastname

        return "zaktualizowano dane"
    }
}

let usersArray = []

module.exports = {User, usersArray}