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
        const uploadFolder = __dirname + "/../../profilePics/"
        form.uploadDir = uploadFolder
        return new Promise(
            (resolve, reject) => {
                form.parse(req, (err, fields, files) => {
                    if (err) reject(err);
                    const file = files.file;
                    const path = form.uploadDir + "/" + email + ".jpg";
                    console.log(path);
                    console.log(file.name);
                    console.log(file.name.split(".").pop());

                    fs.mkdir(form.uploadDir, (err) => {
                        fs.rename(file.path, path, (err) => {
                            if (err) reject(String(err))
                            resolve("przesłano zdjęcie profilowe")
                        })
                    })

                })
            })
    },

    getProfilePicture: async (email) => {
        const path = __dirname + "/../../profilePics/" + email + ".jpg";
        const defaultPath = __dirname + "/../../profilePics/undefined.jpg";
        console.log(path);
        return new Promise(
            (resolve, reject) => {
                fs.readFile(path, (err, data) => {
                    if (err) {
                        console.log(err);
                        fs.readFile(defaultPath, (err, data) => {
                            resolve(data)
                        })
                    }else{
                        resolve(data)
                    }
                })
            }
        )
    }
} 