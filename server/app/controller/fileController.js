const formidable = require("formidable");
const fs = require("fs")
let { photosArray } = require("../model/photosModel")

module.exports = {

  addFile: async (req) => {
    var form = new formidable.IncomingForm();
    return new Promise(
      (resolve, reject) => {
        form.parse(req, (err, fields, files) => {
          if (err) reject(err);
          const file = files.file;
          const album = fields.album;
          const albumDirPath = __dirname + "/../../uploads/" + album;
          const newPath = albumDirPath + "/" + file.path.split("\\").pop() + "." + file.name.split(".").pop();
          console.log(newPath);

          fs.mkdir(albumDirPath, (err) => {
            fs.rename(file.path, newPath, (err) => {
              if (err) reject(String(err))
              resolve([album, file.name, newPath.split("/../../")[1]])
            })
          })

        })
      })
  },

  deleteFile: (path) => {
    return new Promise((resolve, reject) => {
      fs.unlink(path, (err) => {
        if (err) reject(err)
        resolve("photo deleted")
      })
    })
  },

  getFile: (id) => {
    return new Promise((resolve, reject) => {
      let photo = photosArray.find(photo => photo.id == id)
      console.log(photo);
      fs.readFile(photo.url, (err, data) => {
        if (err) reject(err)
        resolve(data)
      })
    })
  },
  getFileFromURL: (url) => {
    return new Promise((resolve, reject) => {
      fs.readFile(url, (err, data) => {
        if (err) reject(err)
        resolve(data)
      })
    })
  },

}
