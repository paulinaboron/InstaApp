const { photosArray } = require("../model/photosModel")
const sharp = require("sharp");
const utils = require("../utils")

module.exports = {
    getMetadata: async (id) => {
        
        let url = utils.getPhotoById(id).url

        console.log("URL", url);

        return new Promise(async (resolve, reject) => {
            try {

                if (url) {
                    let meta = await sharp(url).metadata()
                    resolve(meta)
                }
                else { resolve("url not found") }

            } catch (err) {
                reject(err.mesage)
            }
        })
    },

    filter: (data) => {
        let photo = utils.getPhotoById(data.id)

        switch (data.action) {
            case "rotate":
                photo.rotate(data.filters.rotate)
                break;
            case "resize":
                photo.resize(data.filters.resize.width, data.filters.resize.height)
                break;
            case "reformat":
                photo.reformat(data.filters.toFormat)
                break;
            case "crop":
                let width = data.filters.crop.width
                let height = data.filters.crop.height
                let left = data.filters.crop.left
                let top = data.filters.crop.top
                photo.crop(width, height, left, top)
                break;
            case "grayscale":
                photo.grayscale()
                break;
            case "flip":
                photo.flip()
                break;
            case "flop":
                photo.flop()
                break;
            case "negate":
                photo.negate()
                break;
            case "tint":
                let r = data.filters.tint.r
                let g = data.filters.tint.g
                let b = data.filters.tint.b
                photo.tint(r, g, b)
                break;
            default:
                return "error: filter not found"
        }

        //todo photo updates async
        return photo;
    }
}