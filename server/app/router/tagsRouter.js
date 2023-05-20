const utils = require("../utils")
const tagsController = require("../controller/tagsController")

const tagsRouter = async (req, res) => {

    let output = "not found"

    if (req.url == "/api/tags/raw" && req.method == "GET") {

        output = tagsController.getRaw()

    }else if (req.url == "/api/tags" && req.method == "GET") {

        output = tagsController.getObjects()

    }else if(req.url.match(/\/api\/tags\/([0-9]+)/) && req.method == "GET"){

        output = tagsController.getOne(utils.getIdFromUrl(req))

    }else if(req.url = "/api/tags" && req.method =="POST"){
        
        let data = await utils.getRequestData(req)
        output = tagsController.addTag(data)
    }

    res.end(JSON.stringify(output, null, 5))
}

module.exports = tagsRouter