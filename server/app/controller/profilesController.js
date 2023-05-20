let { usersArray, User } = require("../model/userModel")
const utils = require("../utils")
const formidable = require("formidable");
const fs = require("fs")

module.exports = {

    getProfileByEmail: (email) => {
        for (let i = 0; i < usersArray.length; i++) {
            if (usersArray[i].email == email) return usersArray[i]
        }
        return null
    },

    uploadProfilePicture: async (req, email) => {
        var form = new formidable.IncomingForm();
        const uploadFolder = __dirname + "/../../uploads/"
        form.uploadDir = uploadFolder
        return new Promise(
            (resolve, reject) => {
                form.parse(req, (err, fields, files) => {
                    if (err) reject(err);
                    const file = files.file;
                    const album = email
                    const albumDirPath = __dirname + "/../../uploads/" + album;
                    const newPath = albumDirPath + "/profile.jpg"
                    console.log(newPath);
                    console.log(file.name);
                    console.log(file.name.split(".").pop());

                    fs.mkdir(albumDirPath, (err) => {
                        fs.rename(file.path, newPath, (err) => {
                            if (err) reject(String(err))
                            resolve("przesłano zdjęcie profilowe")
                        })
                    })

                })
            })
    }
}