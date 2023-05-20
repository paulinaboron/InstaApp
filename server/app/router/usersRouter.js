const utils = require("../utils")
const usersController = require("../controller/usersController")

const usersRouter = async (req, res) => {

    let output = "not found"

    if (req.url == "/api/users/register" && req.method == "POST") {
        let data = await utils.getRequestData(req);
        console.log(data);
        let user  = await usersController.registerUser(JSON.parse(data))
        output = await utils.createToken(user)
    }
    else if (req.url.match(/\/api\/users\/confirm\/(...)/) && req.method == "GET") {
        let token = utils.getIdFromUrl(req)
        
        verified = await utils.verifyToken(token)
        console.log(verified);
        if(verified.email){
            usersController.verifyUser(verified.email)
            output = "konto potwierdzone"
        }else{
            output = verified
        }
    }
    else if (req.url == "/api/users/login" && req.method == "POST") {
        let data = await utils.getRequestData(req);
        console.log(data);
        let loggedUser = await usersController.loginUser(JSON.parse(data))
        if(loggedUser){
            let token = await utils.createToken(loggedUser)
            output = {"status": "logged in", "token": token}
        }else output = "błędne dane"
    }
    else if (req.url == "/api/users" && req.method == "GET") {
        output = usersController.getAll()
    }

    res.end(JSON.stringify(output, null, 5))
}

module.exports = usersRouter
