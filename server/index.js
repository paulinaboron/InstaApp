const http = require('http');
const imageRouter = require("./app/router/imageRouter")
const tagsRouter = require("./app/router/tagsRouter")
const filtersRouter = require("./app/router/filtersRouter")
const usersRouter = require("./app/router/usersRouter")
const profilesRouter = require("./app/router/profilesRouter")

require('dotenv').config();

http
   .createServer(async (req, res) => {

      //images
      if (req.url.search("/api/photos") != -1) {
         await imageRouter(req, res)
      }

      //tags
      else if (req.url.search("/api/tags") != -1) {
         await tagsRouter(req, res)
      }

      //filters
      else if (req.url.search("/api/filters") != -1) {
         await filtersRouter(req, res)
      }

      //users
      else if (req.url.search("/api/users") != -1) {
         await usersRouter(req, res)
      }

      //profiles
      else if (req.url.search("/api/profile") != -1) {
         await profilesRouter(req, res)
      }
   })
   .listen(process.env.APP_PORT, () => console.log(`listen on ${process.env.APP_PORT}`))
