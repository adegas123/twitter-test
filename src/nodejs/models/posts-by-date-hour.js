const mongoose = require('mongoose');

const postsByDateHourSchema = new mongoose.Schema({
    date: Date,
    count: Number
}, { collection : 'postsByDateHour'});

module.exports = mongoose.model('postsDateHour', postsByDateHourSchema);