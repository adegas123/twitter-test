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
            getPostsByTagLang().then((value) => {
                this.setState({posts: value})
            });
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
                           <table>
                                <thead>
                                    <tr>
                                    <th>Tag</th>
                                    <th>Lang</th>
                                    <th>Count</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.state.posts.map((post, i) => (
                                        <tr key={i}>
                                            <td>{post.tag}</td>
                                            <td>{post.lang}</td>
                                            <td>{post.count}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}

export default PostsByTagLang