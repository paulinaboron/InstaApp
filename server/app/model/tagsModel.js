class Tag{
    constructor(name, popularity){
        this.id = tagsObjects.length
        this.name = name
        this.popularity = popularity
    }
}

let tagsArray = [
    "#love",
    "#instagood",
    "#fashion",
    "#photooftheday",
    "#art",
    "#photography",
    "#instagram",
    "#beautiful",
    "#picoftheday",
    "#nature",
    "#happy",
    "#cute",
    "#travel",
    "#style",
    "#followme",
    "#tbt",
    "#instadaily",
    "#repost",
    "#like4like",
    "#summer",
    "#beauty",
    "#fitness",
    "#food",
    "#selfie",
    "#me",
    "#instalike",
    "#girl",
    "#friends",
    "#fun",
    "#photo"
]

let tagsObjects = []

tagsArray.forEach(e=>{
    let tag = new Tag(e, Math.floor(Math.random()*1000))
    tagsObjects.push(tag)
})

module.exports = {tagsArray, tagsObjects, Tag}