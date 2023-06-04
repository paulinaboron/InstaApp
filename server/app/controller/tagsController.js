let {tagsArray, tagsObjects, Tag} = require("../model/tagsModel")

module.exports = {
    getRaw: ()=>{
        return tagsArray
    },

    getObjects: ()=>{
        return tagsObjects
    },

    getOne: (id) =>{
        for(let i=0; i<tagsObjects.length; i++){
            if(tagsObjects[i].id == id) return tagsObjects[i]
        }
        return "not found"
    },

    addTag: (data) =>{
        data = JSON.parse(data)
        if(tagsArray.includes(data.name)) return "already exists"

        let newTag = new Tag(data.name)
        tagsArray.push(data.name)
        tagsObjects.push(newTag)
        return "new tag added"
    }
}