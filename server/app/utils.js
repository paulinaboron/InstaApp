const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
require('dotenv').config();

module.exports = {
    getRequestData: async (req) => {
        return new Promise((resolve, reject) => {
            try {

                let body

                req.on("data", (part) => {
                    body = part.toString();
                });

                req.on("end", () => {
                    resolve(body);
                });

            } catch (error) {
                reject(error);
            }
        })
    },

    getIdFromUrl: (req) => {
        let arr = req.url.split("/")
        let id = arr[arr.length - 1]
        return id
    },

    getPhotoById: (id) => {
        return photosArray.find(photo => photo.id == id) || "not found"
    },

    encryptPass: async (password) => {
        return new Promise(async (resolve, reject) => {
            try {
                let encryptedPassword = await bcrypt.hash(password, 10);
                resolve(encryptedPassword)
            } catch (err) {
                reject(err.mesage)
            }
        })
    },

    decryptPass: async (userpass, encrypted) => {
        let decrypted = await bcrypt.compare(userpass, encrypted)
        return decrypted
    },

    createToken: async (user) => {

        let token = await jwt.sign(
            {
                email: user.email,
            },
            process.env.YAHOO_PASS,
            {
                expiresIn: "1d" // "1m", "1d", "24h"
            }
        );
        return token
    },

    verifyToken: async (token) => {
        try {
            let decoded = await jwt.verify(token, process.env.YAHOO_PASS)
            return decoded
        }
        catch (ex) {
            return ex.message
        }
    }

}