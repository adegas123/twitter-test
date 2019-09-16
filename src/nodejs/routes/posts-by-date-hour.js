var express = require('express');
var router = express.Router();
var PostsByDateHour = require('../models/posts-by-date-hour');

/**
 * Get all posts by date hour!
 */
router.get('/', function(req, res, next) {
    PostsByDateHour.find(function(err, postsByDateHour) {
        if(err) return next(err);
        res.json(postsByDateHour);
    });
});

module.exports = router;