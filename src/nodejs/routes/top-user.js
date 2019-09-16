var express = require('express');
var router = express.Router();
var TopUsers = require('../models/top-users');

/**
 * Get all top users!
 */
router.get('/', function(req, res, next) {
    TopUsers.find(function(err, users) {
        if(err) return next(err);
        res.json(users);
    });
});

module.exports = router;