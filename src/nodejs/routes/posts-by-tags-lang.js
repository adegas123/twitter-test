var express = require('express');
var router = express.Router();
var PostsbyTagsAndLang = require('../models/posts-by-tag-lang');

/**
 * Get all posts by tags and lang!
 */
router.get('/', function(req, res, next) {
    PostsbyTagsAndLang.find(function(err, postsByTagsAndLang) {
        if(err) return next(err);
        res.json(postsByTagsAndLang);
    });
});

module.exports = router;