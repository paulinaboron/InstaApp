const utils = require("../utils")
const filtersController = require("../controller/filtersController")

const filtersRouter = async(req, res) =>{
    let output = "not found"
    console.log(req.url);
    if (req.url.match(/\/api\/filters\/metadata\/([0-9]+)/) && req.method == "GET") {

        output = await filtersController.getMetadata(utils.getIdFromUrl(req))

    }
    else if(req.url == "/api/filters" && req.method == "PATCH"){
        let data = await utils.getRequestData(req);
        output = filtersController.filter(JSON.parse(data))
    }

    res.end(JSON.stringify(output, null, 5))

}


module.exports = filtersRouter
