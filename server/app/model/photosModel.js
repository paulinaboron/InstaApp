const {Tag} = require("../model/tagsModel")
const sharp = require("sharp")

class Photo{
    constructor(album, originalName, url = "url"){
        this.id = Date.now()
        this.album = album
        this.originalName = originalName
        this.url = url
        this.lastChange = "original"
        this.history = [new History("original", this.id, this.url)]
        this.tags = []
        this.address = ""
    }

    addStatus(status){
        let hsr = new History(status, Date.now())
        this.history.push(hsr)
    }

    addTags(tags){
        tags.forEach(e => {
            let newTag = new Tag(e.name, e.popularity)
            this.tags.push(newTag)
        });
    }

    addAddress(address){
        this.address = address;
    }

    getNewPath(extra){
        let newName = this.originalName.split(".")[0] + extra + ".jpg"
        let path = __dirname + "/../../uploads/" + this.album + "/" + newName
        return path
    }

    addStatus(status, path){
        this.lastChange = status
        this.url = path
        let h = new History(status, Date.now(), path)
        this.history.push(h)
    }

    async rotate(deg){
        let path = this.getNewPath("_rotated")
        await sharp(this.originalName)
        .rotate(deg)
        .toFile(path);

        this.addStatus("rotated", path)
    }

    async resize(width, height){
        let path = this.getNewPath("_resized")
        await sharp(this.originalName)
        .resize({
            width: width,
            height: height
        })
        .toFile(path);

        this.addStatus("resized", path)
    }

    async reformat(format){
        let newName = this.originalName.split(".")[0] + "_reformatted." + format
        let path = __dirname + "/../../uploads/" + this.album + "/" + newName

        await sharp(this.originalName)
            .toFormat(format)
            .toFile(path);

            this.addStatus("reformatted", path)
    }

    async crop(width, height, left, top){
        let path = this.getNewPath("_cropped")
        await sharp(this.originalName)
        .extract({
            width: width,
            height: height,
            left: left,
            top: top
        })
        .toFile(path);

        this.addStatus("cropped", path)
    }

    async grayscale(){
        let path = this.getNewPath("_grayscale")
        await sharp(this.originalName)
        .grayscale()
        .toFile(path);

        this.addStatus("grayscale", path)
    }

    async flip(){
        let path = this.getNewPath("_flipped")
        await sharp(this.originalName)
        .flip()
        .toFile(path);

        this.addStatus("flipped", path)
    }

    async flop(){
        let path = this.getNewPath("_flopped")
        await sharp(this.originalName)
        .flop()
        .toFile(path);

        this.addStatus("flopped", path)
    }

    async negate(){
        let path = this.getNewPath("_negated")
        await sharp(this.originalName)
        .negate()
        .toFile(path);

        this.addStatus("negated", path)
    }

    async tint(r, g, b){
        let path = this.getNewPath("_tinted")
        await sharp(this.originalName)
        .tint({
            r: r,
            g: g,
            b: b
        })
        .toFile(path);

        this.addStatus("tinted", path)
    }
}

class History{
    constructor(status, timestamp, url){
        this.status = status
        this.timestamp = timestamp
        this.url = url
    }
}



let photosArray = []

module.exports = {Photo, photosArray, History}