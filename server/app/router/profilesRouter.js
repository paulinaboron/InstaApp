const utils = require("../utils")
const profilesController = require("../controller/profilesController")

let tokenBlacklist = []

const profilesRouter = async (req, res) => {

    let output = "not found"

    if (req.url == "/api/profile" && req.method == "GET") {
        if (req.headers.authorization && req.headers.authorization.startsWith("Bearer")) {
            let token = req.headers.authorization.split(" ")[1]
            console.log(token)

            let decoded = await utils.verifyToken(token)
            if (decoded.email) {
                output = profilesController.getProfileByEmail(decoded.email)
            } else output = null
        }
    }
    else if (req.url == "/api/profile" && req.method == "PATCH") {
        if (req.headers.authorization && req.headers.authorization.startsWith("Bearer")) {
            let token = req.headers.authorization.split(" ")[1]
            console.log(token)

            let data = await utils.getRequestData(req);
            console.log(data);
            data = JSON.parse(data)

            let decoded = await utils.verifyToken(token)
            if (decoded.email) {
                let user = profilesController.getProfileByEmail(decoded.email)
                output = user.update(data.name, data.lastname)
            }
        }
    }
    else if (req.url == "/api/profile" && req.method == "POST") {
        if (req.headers.authorization && req.headers.authorization.startsWith("Bearer")) {
            let token = req.headers.authorization.split(" ")[1]
            console.log(token)

            let decoded = await utils.verifyToken(token)
            output = await profilesController.uploadProfilePicture(req, decoded.email)
        }
    }
    else if (req.url == "/api/profile/picture" && req.method == "GET") {
        if (req.headers.authorization && req.headers.authorization.startsWith("Bearer")) {
            let token = req.headers.authorization.split(" ")[1]
            console.log(token)

            let decoded = await utils.verifyToken(token)
            var img = await profilesController.getProfilePicture(decoded.email)
            res.writeHead(200, { "Content-Type": "image/jpeg" });
            res.end(img)
            return;
        }
    }
    else if (req.url == "/api/profile/logout" && req.method == "GET") {
        if (req.headers.authorization && req.headers.authorization.startsWith("Bearer")) {
            let token = req.headers.authorization.split(" ")[1]
            tokenBlacklist.push(token)
            output = "wylogowano"
        }
    }

    res.end(JSON.stringify(output, null, 5))
}

module.exports = profilesRouter
