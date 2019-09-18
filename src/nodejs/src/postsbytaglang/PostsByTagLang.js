import React, { Component } from 'react';
import './PostsByTagLang.css';
import { getPostsByTagLang } from '../util/APIUtils';

class PostsByTagLang extends Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            posts: []
        }
    }

    componentDidMount() {
        this.getPostsByTagLangList();
    }
    getPostsByTagLangList = async() => {
        try {
            getPostsByTagLang().then((value) => this.setState({posts: value}));
        } catch (err) {
          console.log(err);
        }
    }
    render() {
        return (
            <div className="topuser-container">
                <div className="container">
                    <div className="topuser-info">
                        <div className="topuser-name">
                           <h2>Posts by Tag / Lang</h2>
                           <ul className="list-group">{this.state.posts.map((post, i) => (
                               <li key={i} className="list-group-item">{post.username} - tag: {post.tag} - lang: {post.lang} - qty: {post.count}</li>
                           ))}</ul>
                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}

export default PostsByTagLang