let {Photo, photosArray} = require("../model/photosModel")

module.exports = {
    add: (folder, name, path) => {
        let photo = new Photo(folder, name, path)
        photosArray.push(photo)
        return "photo added " + photo.id
    },
    getAll: () => {
        return photosArray
    },
    getFromAlbum: (data)=>{
        console.log(data.album);
        let photos = photosArray.filter(function(e) {
            return e.album = data.album
        })
        return photos
    },
    getOne: (id) =>{
        for(let i=0; i<photosArray.length; i++){
            if(photosArray[i].id == id) return photosArray[i]
        }
        return "not found"
    },
    delete: (id) => {
        photosArray = photosArray.filter(function(e) {
            return e.id !== id
        })
        return photosArray
    },
    update: (data) => {
        let obj = JSON.parse(data)
        for(let i=0; i<photosArray.length; i++){
            if(photosArray[i].id == obj.id){
                photosArray[i].addStatus(obj.status)
                return photosArray[i]
            }
        }
        return "not found"
    },
    updateTags: (data) =>{
        let obj = JSON.parse(data)
        console.log(obj);

        for(let i=0; i<photosArray.length; i++){
            if(photosArray[i].id == obj.id){
                // const tagNames = photosArray[i].tags.map()

                photosArray[i].addTags(obj.tags)
                return photosArray[i]
            }
        }
        return "not found"
    },
    getTags: (id)=>{
        for(let i=0; i<photosArray.length; i++){
            if(photosArray[i].id == id) return {id: id, tags: photosArray[i].tags}
        }
        return "not found"
    }
}

function getPhotoById(id){
    for(let i=0; i<photosArray.length; i++){
        if(photosArray[i].id == id) return photosArray[i]
    }
    return "not found"
}