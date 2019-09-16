const mongoose = require('mongoose');

const topUsersSchema = new mongoose.Schema({
    id: Number,
    userName: String,
    followers: String
}, { collection : 'topUsersByFollowers'});

module.exports = mongoose.model('topUsers', topUsersSchema);