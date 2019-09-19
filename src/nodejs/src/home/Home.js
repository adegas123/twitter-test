import React, { Component } from 'react';
import './Home.css';
import PostsByTime from '../postsbytime/PostsByTime';
import TopUsers from '../topusers/TopUsers';

class Home extends Component {
    render() {
        return (
            <div className="home-container">
                <div className="container">
                    <TopUsers />
                    <PostsByTime />
                </div>
            </div>
        )
    }
}

export default Home;