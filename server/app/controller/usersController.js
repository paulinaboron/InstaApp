require('dotenv').config();
let { usersArray, User } = require("../model/userModel")
const utils = require("../utils")
const usersRouter = require("../router/usersRouter");

const config = {
    service: 'Yahoo',
    auth: {
        user: process.env.YAHOO_LOGIN,
        pass: process.env.YAHOO_PASS
    }
}

module.exports = {

    registerUser: async (data) => {
        let encrypted = await utils.encryptPass(data.pass)
        let user = new User(data.name, data.lastname, data.email, encrypted)
        usersArray.push(user)
        console.log(user);
        return user
    },

    getAll: () => {
        return usersArray;
    },

    verifyUser: (email) =>{
        usersArray.forEach(user=>{
            if(user.email == email) user.confirmed = true
        })
    },

    loginUser: async (data) => {
        console.log("data login json:", data);
        for(let i=0; i<usersArray.length; i++){
            let decrypted = await utils.decryptPass(data.pass, usersArray[i].pass)
            console.log(decrypted);
            if(decrypted && usersArray[i].confirmed) return usersArray[i]
        }
        return null
    }
}