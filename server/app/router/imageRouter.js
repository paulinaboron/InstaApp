const utils = require("../utils")
const jsonController = require("../controller/jsonController")
const fileController = require("../controller/fileController")
const fs = require("fs");
const { log } = require("console");

const router = async (req, res) => {

    console.log(req.url, req.method);
    let output = "not found"

    if (req.url == "/api/photos" && req.method == "GET") {

        output = jsonController.getAll()

    }else if(req.url.match(/\/api\/photos\/album\/(...)/) && req.method == "GET"){
        let email = utils.getIdFromUrl(req)
        console.log(email);
        output = jsonController.getFromAlbum(email)

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

    }
    else if (req.url == "/api/photos/address" && req.method == "PATCH") {

        let data = await utils.getRequestData(req);
        output = jsonController.updateAddress(data)

    } else if (req.url.match(/\/api\/photos\/tags\/([0-9]+)/) && req.method == "GET") {

        output = jsonController.getTags(utils.getIdFromUrl(req))

    }

    else if (req.url.match(/\/api\/photos\/getFile\/([0-9]+)/) && req.method == "GET") {

        let id = utils.getIdFromUrl(req)
        var img = await fileController.getFile(id)
        res.writeHead(200, { "Content-Type": "image/jpeg" });

        res.end(img)
        return;
    }
    else if (req.url.match(/\/api\/photos\/getVideo\/([0-9]+)/) && req.method == "GET") {

        let id = utils.getIdFromUrl(req)
        var video = await fileController.getFile(id)
        res.writeHead(200, { "Content-Type": "video/mp4" });

        res.end(video)
        return;
    }
    else if (req.url.match(/\/api\/photos\/getfileURL\/(...)/) && req.method == "GET") {

        let arr = req.url.split("/")
        let url = arr[4] + "/" + arr[5] + "/" + arr[6]
        var img = await fileController.getFileFromURL(url)
        res.writeHead(200, { "Content-Type": "image/jpeg" });

        res.end(img)
        return;
    }

    if (output == "not found") res.statusCode = 404
    res.end(JSON.stringify(output, null, 5))

}


module.exports = router