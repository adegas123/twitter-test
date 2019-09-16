const mongoose = require('mongoose');

const postsByTagLangSchema = new mongoose.Schema({
    date: Date,
    count: Number
}, { collection : 'postsByTagsAndLang'});

module.exports = mongoose.model('postsByTagLang', postsByTagLangSchema);