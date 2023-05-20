const utils = require("../utils")
const jsonController = require("../controller/jsonController")
const fileController = require("../controller/fileController")
const fs = require("fs");

const router = async (req, res) => {

    console.log(req.url, req.method);
    let output = "not found"

    if (req.url == "/api/photos" && req.method == "GET") {

        output = jsonController.getAll()

    } else if (req.url == "/api/photos" && req.method == "POST") {

        let [album, fileName, path] = await fileController.addFile(req);

        output = jsonController.add(album, fileName, path)

    } else if (req.url.match(/\/api\/photos\/([0-9]+)/) && req.method == "GET") {

        output = jsonController.getOne(utils.getIdFromUrl(req))

    } else if (req.url.match(/\/api\/photos\/([0-9]+)/) && req.method == "DELETE") {

        output = jsonController.delete(utils.getIdFromUrl(req))

    } else if (req.url == "/api/photos" && req.method == "PATCH") {
        let data = await utils.getRequestData(req);
        output = jsonController.update(data)

    } else if (req.url == "/api/photos/tags" && req.method == "PATCH") {

        let data = await utils.getRequestData(req);
        output = jsonController.updateTags(data)

    } else if (req.url.match(/\/api\/photos\/tags\/([0-9]+)/) && req.method == "GET") {

        output = jsonController.getTags(utils.getIdFromUrl(req))

    }

    else if (req.url.match(/\/api\/photos\/getfile\/([0-9]+)/) && req.method == "GET") {

        let id = utils.getIdFromUrl(req)
        var img = await fileController.getFile(id)
        res.writeHead(200, { "Content-Type": "image/jpeg" });

        res.end(img)
        return;
    }

    if (output == "not found") res.statusCode = 404
    res.end(JSON.stringify(output, null, 5))

}


module.exports = router